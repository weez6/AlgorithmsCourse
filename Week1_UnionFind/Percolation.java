/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int n;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wqu2;
    private boolean[][] grid;
    private int vb;
    private int vt;
    private int numOpenSites;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n < 1");
        }

        grid = new boolean[n][n];
        this.n = n;
        vt = 0;
        vb = n * n + 1;
        numOpenSites = 0;

        // create grid, set all nodes to blocked
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        // create union object
        wqu = new WeightedQuickUnionUF(n * n + 2);
        wqu2 = new WeightedQuickUnionUF(n * n + 1); // to solve backwash

        // connecting virtual top and bottom nodes:
        for (int i = 1; i <= n; i++) {
            wqu.union(i, vt);
            wqu2.union(i, vt);
        }
        for (int i = n * (n - 1); i <= n * n; i++) {
            wqu.union(i, vb);
        }

    }

    public void open(int row, int col) {
        validateIndex(row, col);
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            numOpenSites++;
        }

        // connecting with  open neighbours
        int flattened = flatten(row, col);
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            wqu.union(flattened, flatten(row - 1, col));
            wqu2.union(flattened, flatten(row - 1, col));
        }
        // down
        if (row < n && isOpen(row + 1, col)) {
            wqu.union(flattened, flatten(row + 1, col));
            wqu2.union(flattened, flatten(row + 1, col));
        }
        // right
        if (col < n && isOpen(row, col + 1)) {
            wqu.union(flattened, flatten(row, col + 1));
            wqu2.union(flattened, flatten(row, col + 1));
        }
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            wqu.union(flattened, flatten(row, col - 1));
            wqu2.union(flattened, flatten(row, col - 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        return isOpen(row, col) && wqu2.find(flatten(row, col)) == wqu2.find(vt);
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        if (n == 1) return isOpen(1, 1) && wqu.find(vt) == wqu.find(vb);
        return wqu.find(vt) == wqu.find(vb);
    }

    private int flatten(int row, int col) {
        //(1:n, 1:n) --> 1:n*n
        return (row - 1) * n + col;
    }

    private void validateIndex(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("index out of bounds :(");
        }
    }

    public static void main(String[] args) {

    }

}

