import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ryan on 1/31/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private int n;

    // Linked List object
    private class Node {
        private Item item;
        private Node next;
    }

    // constructor
    public Deque() {
        head = null;
        n = 0;
    }

    // returns true if the Deque is empty
    public boolean isEmpty() { return head == null; }

    // returns the size of the Deque
    public int size() { return n; }

    // creates a new Node object and adds it in the beginning
    public void addFirst (Item item) {
        if (item == null) throw new NullPointerException();

        Node node = head;
        head = new Node();
        head.item = item;
        head.next = node;
        n++;
    }

    // creates a new node object and adds it at the end
    public void addLast (Item item) {
        if (item == null) throw new NullPointerException();

        if (head == null) {
            head = new Node();
            head.item = item;
            n++;
        } else {
            Node node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node();
            node.next.item = item;
            n++;
        }
    }

    // removes first Node object
    public Item removeFirst(){
        if(head == null) {
             throw new NoSuchElementException();
        } else {
            Item item = head.item;
            head = head.next;
            n--;
            return item;
        }
    }

    // removes last Node object
    public Item removeLast() {
        if(head == null){
            throw new NoSuchElementException();
        } else {
            Node node = head;
            Node prev = head;

            if(head.next == null) {
                head = null;
            }

            while (node.next != null) {
                prev = node;
                node = node.next;
            }

            Item item = node.item;
            prev.next = null;
            n--;

            return item;
        }
    }

    // overloads Iterator class
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    // method overloads of Iterator
    private class ListIterator implements Iterator<Item> {
        private Node current = head;
        public boolean hasNext(){ return current != null; }
        public void remove(){ throw new UnsupportedOperationException(); }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

}
