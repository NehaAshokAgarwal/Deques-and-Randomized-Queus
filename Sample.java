import stdlib.StdOut;

public class Sample {
    // Entry point.
    public static void main(String[] args) {
        // Accept lo, hi , and k as command line arguments.
        // converted from string to int datatype
        int lo = Integer.parseInt(args[0]);
        int hi = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        // Accepts mode of string datatype as command line argument.
        String mode = args[3];

        // Calling the constructor to create a random queue q to store the integers
        // [lo, hi]
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        // for each integer from lo to hi, add/enque the number to the random queue q.
        for (int i = lo; i <= hi; i++) {
            q.enqueue(i);
        }
        // if mode is '+'
        if (mode.equals("+")) {
            // Then sample and write k integers from lo to hi.
            for (int i = 0; i < k; i++) {
                int r = q.sample();
                StdOut.println(r);
            }
            // If mode is '-'
        } else if (mode.equals("-")) {
            // then deque and write k integers from lo to hi.
            for (int i = 0; i < k; i++) {
                int r = q.dequeue();
                StdOut.println(r);
            }
            // otherwise, throw an error saying illegal mode.
        } else {
            throw new IllegalArgumentException("Illegal mode");
        }
    }
}
