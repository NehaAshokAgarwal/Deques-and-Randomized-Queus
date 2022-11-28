import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node first; // Reference to front of the deque
    private Node last; // Reference to the last of the deque
    private int n; // size of the deque

    // Constructs an empty deque.
    public LinkedDeque() {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this deque.
    public int size() {
        return n;
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {
        // If item is null, then throw a NullPointerException saying that item is null.
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // If item is not null, then add the item at the beginning of the deque.
        // The value of the Node first is assigned to the Node oldfirst, that is
        // both the oldfirst and first Nodes points to the same item in the deque.
        Node oldfirst = first;
        // Creating a completely new Node first.
        first = new Node();
        // Node first points to the item in the deque.
        first.item = item;
        // Node first points to the next node oldfirst in the deque, thus the
        // Node first has become a part of the deque.
        first.next = oldfirst;
        // However, if the item that is added is the very first element in the deque, then both the
        // first and last Node should point to the item.
        if (isEmpty()) {
            last = first;
            // otherwise, the Node oldfirst should points to the Node first as well, because it is a
            // doubly linked list/deque.
        } else {
            oldfirst.prev = first;
        }
        // As the item is added in the deque, increment the count of n.
        n++;
    }

    // Adds item to the back of this deque.
    public void addLast(Item item) {
        // if the item is null then throw a NullPointerException saying that item
        // is null.
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // If the item is not null, then add the item at the end of the deque.
        // Node oldfirst and Node last points to the same item  in the deque.
        Node oldlast = last;
        // Creating a new Node last.
        last = new Node();
        // Node last now points to them 'item' in the deque.
        last.item = item;
        // Node last points to the previous node oldfirst in the deque,the
        // Node last has become a part of the deque.
        last.prev = oldlast;
        // However, if the item that is added is the very first item in the deque, then both the
        // Nodes last and first should point to the same item.
        if (isEmpty()) {
            first = last;
            // otherwise, Node oldlast points to the next Node first.
        } else {
            oldlast.next = last;
        }
        // As the item is added, increment n by 1.
        n++;
    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        // If the deque is empty then throw a NoSuchElementException saying that deque is empty.
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        // otherwise, return the first item in the deque.
        return first.item;
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
        if (isEmpty()) {
            // If the deque is empty then throw a NoSuchElementException saying that deque is empty.
            throw new NoSuchElementException("Deque is empty");
        }
        // Otherwise, delete the first item from the deque.
        // Grab the first item from the deque.
        Item item = first.item;
        // Node first points to the next item in the deque.
        first = first.next;
        // decrement the count by 1, as now node first points to the next item
        // that is the first item from the deque is removed.
        n--;
        // If after removing the item, deque gets empty then set node last to null.
        if (isEmpty()) {
            last = null;
            // Otherwise, node first points to null in the backward direction.That is the Node first
            // is the very first Node.
        } else {
            first.prev = null;
        }
        // Return the grabbed item from the deque that is the item which is
        // deleted from its starting.
        return item;
    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        // If the deque os empty then throw a NoSuchElementException saying that deque is empty.
        if (n == 0) {
            throw new NoSuchElementException("Deque is empty");
        }
        // otherwise, return the last item from the deque.
        return last.item;
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
        // If the deque is empty then throw a NoSuchElementException saying that deque is empty.
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        // grab the last item from the end of the deque.
        Item item = last.item;
        // Node last points to the previous element.
        last = last.prev;
        // As node last now points to the previous element, the item is deleted from the deque.
        // hence, decrement the count of n by 1.
        n--;
        if (isEmpty()) {
            // if after removing the item from the deque, deque gets empty then the node first
            // should point to null.
            first = null;
            // otherwise, node last should point to null in the forward direction.
        } else {
            last.next = null;
        }
        // return the deleted item from the deque.
        return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {
        private Node current; // Refers to the current item.

        // Constructs an iterator.
        public DequeIterator() {
            current = first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current != null;
        }

        // Returns the next item.
        public Item next() {
            // If there is no more item to iterate, throw a NoSuchElementException
            // saying that Iterator is empty.
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            }
            // set item to the current item pointed out by the node current.
            Item item = current.item;
            // Node current points to the next item in the deque(step statement).
            current = current.next;
            // return the current item.
            return item;
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
