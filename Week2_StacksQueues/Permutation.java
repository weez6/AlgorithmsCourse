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


        // while (!StdIn.isEmpty()) {
        //     String s = StdIn.readString();
        //     stringQ.enqueue(s);
        // }

        // for (int i = 0; i < k; i++) System.out.println(stringQ.dequeue());

        // using Knuths algorithm to restrict size of queue to k:
        int i = 1;
        while (!StdIn.isEmpty()) {
            if (i <= k) {
                stringQ.enqueue(StdIn.readString());
            }
            else {
                if (StdRandom.bernoulli((double) k / i)) {
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
