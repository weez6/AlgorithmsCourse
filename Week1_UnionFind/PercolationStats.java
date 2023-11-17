/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] probs;
    private int trials;
    private static final double confidence95 = 1.96;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n < 1 or trials < 1");
        }

        this.trials = trials;
        probs = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation exp = new Percolation(n);
            int count = 0;
            while (!exp.percolates()) {
                int randRow = StdRandom.uniformInt(n) + 1;
                int randCol = StdRandom.uniformInt(n) + 1;
                if (!exp.isOpen(randRow, randCol)) {
                    exp.open(randRow, randCol);
                    count++;
                }
            }
            probs[i] = (double) count / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(probs);
    }

    public double stddev() {
        return StdStats.stddev(probs);
    }

    public double confidenceLo() {
        return mean() - (confidence95 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (confidence95 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats myStats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + myStats.mean());
        System.out.println("stddev                  = " + myStats.stddev());
        System.out.println("95% confidence interval = [" + myStats.confidenceLo() + ", "
                                   + myStats.confidenceHi() + "]");
    }
}
