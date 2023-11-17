/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        validateArgument(item);
        if (N == queue.length) resize(2 * queue.length);
        queue[N++] = item;
    }


    // remove and return a random item
    public Item dequeue() {
        thisBitchEmpty();
        int randomInt = StdRandom.uniformInt(N);
        Item item = queue[randomInt];
        queue[randomInt] = queue[N - 1];
        N--;
        if (N > 0 && N == queue.length / 4) resize(queue.length / 4);
        return item;
    }

    private void resize(int i) {
        assert i >= N;
        Item[] oldQ = queue;
        queue = (Item[]) new Object[i];
        for (int k = 0; k < N; k++) {
            queue[k] = oldQ[k];
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        thisBitchEmpty();
        int randomInt = StdRandom.uniformInt(N);
        return queue[randomInt];
    }

    private void thisBitchEmpty() {
        if (N == 0) throw new NoSuchElementException("this bitch empty");
    }

    private void validateArgument(Item item) {
        if (item == null) throw new IllegalArgumentException("yuck dont want");
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Item[] copy;
        private int current;

        public ListIterator() {
            copy = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                copy[i] = queue[i];
            }
            StdRandom.shuffle(copy);
            current = N;
        }

        public boolean hasNext() {
            return current > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no more");
            return queue[--current];
        }

        public void remove() {
            throw new UnsupportedOperationException("no undo (Rosetti)");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        System.out.println("Queue is empty? " + q.isEmpty());
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
        System.out.println("size: " + q.size());
        for (int i = 0; i < 5; i++) {
            System.out.println("removing: " + q.dequeue());
        }
        System.out.println("size: " + q.size());
        for (int i = 0; i < 5; i++) {
            System.out.println("sampling: " + q.sample());
        }
        System.out.println("size: " + q.size());

        Iterator<Integer> it = q.iterator();
        while (it.hasNext()) {
            int x = it.next();
            System.out.println(x);
        }

    }
}
