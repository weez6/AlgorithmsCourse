/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> stringQ = new RandomizedQueue<>();

        /////// If size of Queue unrestricted can use this://///////////////////

        // while (!StdIn.isEmpty()) {
        //     String s = StdIn.readString();
        //     stringQ.enqueue(s);
        // }

        // for (int i = 0; i < k; i++) System.out.println(stringQ.dequeue());

        ////////////////////////////////////////////////////////////////////////

        // using Knuth's algorithm to restrict size of queue to k:
        // Add first k elements from stream to queue. For all subsequent
        // candidates (i > k), add to queue with probability k/i - randomly
        // kick out existing member to accommodate.

        int i = 1;
        while (!StdIn.isEmpty()) {
            if (i <= k) {
                stringQ.enqueue(StdIn.readString());
            }
            else {
                // add new element with probability k/i.
                if (StdRandom.bernoulli((double) k / i)) {
                    // Kick one out. dequeue is random (hopefully).
                    stringQ.dequeue();
                    stringQ.enqueue(StdIn.readString());
                }
                else StdIn.readString();
            }
            i++;
        }

        for (String s : stringQ) System.out.println(s);

    }
}
