import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ryan on 1/31/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    // constructer
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // resizes array
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for(int i = 0; i < n; i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    // returns true if array is empty
    public boolean isEmpty() { return n == 0; }

    // returns size of array
    public int size() { return n; }

    // adds new item to the array, if array is full it shuffles and then doubles the size
    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();

        a[n++] = item;
        if (n == a.length) {
            StdRandom.shuffle(a);
            resize(n * 2);
        }
    }

    // removes item from array uniformly at random
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        } else {
            // resize array to eliminate trailing null values
            resize(n);
            int index = StdRandom.uniform(n);
            // grab item to be returned and removed
            Item item = a[index];
            a[index] = null;

            // move over values if necessary
            while(index + 1 < n){
                a[index] = a[index+1];
                index++;
            }

            // resize if necessary
            if (n == (a.length / 2)) {
                resize(n);
            } else {
                n--;
            }
            return item;
        }
    }

    // returns random array element at random
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        } else {
            return a[StdRandom.uniform(n)];
        }
    }

    public Iterator<Item> iterator() {
        resize(n);
        StdRandom.shuffle(a);
        return new ListIterator();
    }

    // creates new randomized array to iterate through
    private class ListIterator implements Iterator<Item> {
        private int i;
        Item[] randomized = a;

        public ListIterator() {
            i = 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return randomized[i++];
            }
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
