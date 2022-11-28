import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        // Create a deque d which stores the strings from the standard output.
        LinkedDeque<String> d = new LinkedDeque<String>();
        // Creates a deque s which stores the items of the deque d temporarily.
        LinkedStack<String> s = new LinkedStack<String>();
        // Read the strings from the standard output as long as it is not empty.
        while (!StdIn.isEmpty()) {
            String w = StdIn.readString();
            // If deque d is not empty and w(string just read from the standard
            // input) is less than the first item of the deque; then add the
            // item at the head of the deque d.
            if (!d.isEmpty() && less(w, d.peekFirst())) {
                d.addFirst(w);
                // else if deque d is not empty and the w is greater than the
                // last element of the deque d , then add w at the end of the
                // deque d.
            } else if (!d.isEmpty() && less(d.peekLast(), w)) {
                d.addLast(w);
                // otherwise, as long as deque is not empty and first elements of
                // the deque d is less than w, then store the items from the
                // deque d to a temporary deque s.
            } else {
                while (!d.isEmpty() && less(d.peekFirst(), w)) {
                    String t = d.removeFirst();
                    s.push(t);
                }
                // when, the item is not greater anymore, add w to the deque d
                // at the beginning.
                d.addFirst(w);
                // As long as the deque s in not empty, removes its items one by
                // one and add it to the deque d at its beginning. Thus, we get
                // a sorted strings at the end.
                while (!s.isEmpty()) {
                    for (String t : s) {
                        d.addFirst(s.pop());
                    }
                }
            }

        }
        // Print all wthe items of the deque d.
        for (String t : d) {
            StdOut.println(t);
        }

    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
