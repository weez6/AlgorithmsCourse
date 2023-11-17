/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head, tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;

        private Node() {
            previous = null;
            next = null;
            item = null;
        }
    }

    public Deque() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void validateInput(Item item) {
        if (item == null) throw new IllegalArgumentException("nah broski");
    }

    private void thisBitchEmpty() {
        if (size == 0) throw new NoSuchElementException("this bitch empty");
    }

    public void addFirst(Item item) {
        validateInput(item);
        size++;
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        if (size == 1) tail = head;
        else oldHead.previous = head;
    }

    public void addLast(Item item) {
        validateInput(item);
        size++;
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.previous = oldTail;
        if (size == 1) head = tail;
        else oldTail.next = tail;

    }

    public Item removeFirst() {
        thisBitchEmpty();
        size--;
        Item pop = head.item;
        head = head.next;
        if (size == 0) tail = null;
        return pop;
    }

    public Item removeLast() {
        thisBitchEmpty();
        size--;
        Item pop = tail.item;
        tail = tail.previous;
        if (size == 0) head = null;
        return pop;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("nope");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "unsupportedMethod is not supported by ExampleClass.");
        }


    }


    public static void main(String[] args) {
        Deque<Integer> myDeque = new Deque<>();
        for (int i = 0; i < 10; i++) {
            myDeque.addFirst(i);
            myDeque.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(myDeque.removeFirst());
            System.out.println(myDeque.removeLast());
        }
        System.out.println(myDeque.size);
        System.out.println("............................");

        myDeque = new Deque<>();
        for (int i = 0; i < 10; i++) {
            myDeque.addFirst(i);
        }

        Iterator<Integer> it = myDeque.iterator();
        while (it.hasNext()) {
            int x = it.next();
            System.out.println(x);
        }

        for (int num : myDeque) {
            System.out.println(num);
        }
    }


}
