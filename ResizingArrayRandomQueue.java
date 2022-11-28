import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q; // Array to store the items of the queue.
    private int n; // size of the queue.

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
        // Constructing array of capacity 2.
        this.q = (Item[]) new Object[2];
        // size of te array is set to 0.
        this.n = 0;
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
        // if item is null, then throw an exception NullPointerException saying
        // item is null.
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // if size of the queue is equal to its length,
        // then resize it to double its length.
        if (n == q.length) {
            resize(2 * q.length);
        }
        // add the item at the current index in the array.
        q[n] = item;
        // increment n by 1.
        n += 1;
    }

    // Returns a random item from this queue.
    public Item sample() {
        // if the queue is empty then throw an exception NoSuchElementException
        // saying Random queue is empty.
        if (n == 0) {
            throw new NoSuchElementException("Random queue is empty");
        }
        // Otherwise, get any random value by calling a random function
        // UniformInt, within the allowed range.
        int r = StdRandom.uniform(0, n);
        // Return the item at the index.
        return q[r];
    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
        // if the random queue is empty, then throw an exception NoSuchElementException
        // saying Random queue is empty.
        if (n == 0) {
            throw new NoSuchElementException("Random queue is empty");
        }
        // otherwise, get a random number within the allowed range,
        int r = StdRandom.uniform(0, n);
        // grab the item at that index
        Item item = q[r];
        // place the item at the last index to the index at r(ranndom number taken above)
        q[r] = q[n - 1];
        // set the last element to null, hence last item is  randomly.
        q[n - 1] = null;
        // If after removing the item, the size of the array is 1/4th of its length
        // then resize it to its half-length.
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        // decrement n by 1, as the item is removed.
        n--;
        // return the deleted item.
        return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        Item[] items; // Array to store the items of queue.
        int current; // Index of the current item in the items.

        // Constructs an iterator.
        public RandomQueueIterator() {
            // Initialise array items of capacity 2.
            items = (Item[]) new Object[n];
            // for each integer from o, to n, store the integers in the items.
            for (int i = 0; i < n; i++) {
                items[i] = q[i];
            }
            // shuffle the items in the array items.
            StdRandom.shuffle(items);
            current = 0;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current < items.length;
        }

        // Returns the next item.
        public Item next() {
            // if there is no item in the array to iterate then throe an exception.
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            }
            // else grab the item at the current index and store its value and return at the end.
            Item item = items[current];
            // Increment count by 1
            current++;
            return item;
        }
    }

    // Resizes the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
