import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedDeque<String> d = new LinkedDeque<String>();
        LinkedStack<String> s = new LinkedStack<String>();
        while (!StdIn.isEmpty()) {
            String w = StdIn.readString();
            if (!d.isEmpty() && less(w, d.peekFirst())) {
                d.addFirst(w);
            } else if (!d.isEmpty() && less(d.peekLast(), w)) {
                d.addLast(w);
            } else {
                while (!d.isEmpty() && less(d.peekFirst(), w)) {
                    String t = d.removeFirst();
                    s.push(t);
                }
                d.addFirst(w);
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
