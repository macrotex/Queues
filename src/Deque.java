import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A generic implementation of Deque
 * @author Fred Habster
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    /*
     * Keep track of the first and last nodes in the deque.
     */
    private Node first;
    private Node last;

    /**
     * Let's also keep track of the size.
     */
    private int size = 0;

    /**
     * Constructor
     */
    public Deque() {
        this.first = null;
        this.last  = null;
    }

    /**
     * Doubly-linked list
     * @author Fred Habster
     *
     */
    private class Node {
        Node next;
        Node previous;
        Item item;

        /**
         * The constructor for the private Node class
         * @param item the object to put inside the Node.
         */
        public Node(Item item) {
            this.item     = item;
            this.next     = null;
            this.previous = null;
        }
    }

    /**
     * Indicates whether the queue is empty or not.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * The current length of the queue.
     * @return the current length of the queue.
     */
    public int size() {
        return this.size;
    }

    /**
     * Given an item, add it to the front of the queue.
     * @param item item to be prepended
     * @throws NullPointerException if argument being added is null
     */
    public void addFirst(Item item) throws NullPointerException {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        
        Node newNode = new Node(item);

        switch(this.size) {
        case 0:
            this.first = newNode;
            this.last  = newNode;
            
            assert (this.first.next     == null);
            assert (this.first.previous == null);
            break;
        default:
            newNode.previous = this.first;
            this.first.next  = newNode;

            this.first = newNode;
            
            assert (newNode.previous != null);
            assert (newNode.next == null);
            assert (this.first == newNode);
        }

        this.size = this.size + 1;

        return;
    }

    /**
     * Add an item to the END of the queue.
     * @param item The item to add at the end of the queue.
     * @throws NullPointerException if argument being added is null
     */
    public void addLast(Item item) throws NullPointerException {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node newNode = new Node(item);

        switch(this.size) {
        case 0:
            this.first = newNode;
            this.last  = newNode;
            break;
        default:
            newNode.next       = this.last;
            this.last.previous = newNode;

            this.last = newNode;
        }

        this.size = this.size + 1;

        return;
    }

    /**
     * Remove and return the first item on the queue.
     * @return the first item on the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Item removeFirst() 
            throws NoSuchElementException {

        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item itemToReturn = this.first.item;

        switch(this.size) {
        case 1:
            /* One element:
             *   BEFORE: A (first, last) -> null
             *   AFTER:  first = last = null
             */
            this.first = null;
            this.last = null;
            break;       
        default:
            /* Two or more elements:
             *   BEFORE: ... -> B -> A (first) -> null
             *   AFTER:  ... -> B (first) -> null
             */
            Node nB = this.first.previous;
            nB.next = null;
            this.first  = nB;
            break;
        }
        this.size = this.size - 1;

        return itemToReturn;
    }

    /**
     * Remove and return the LAST item on the queue.
     * @return the last item on the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Item removeLast() 
            throws NoSuchElementException {

        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item itemToReturn = this.last.item;

        switch(this.size) {
        case 1:
            /* One element:
             *   BEFORE: A (first, last) -> null
             *   AFTER:  first = last = null
             */
            this.first = null;
            this.last  = null;
            break;
        default:
            /* Two elements:
             *   BEFORE: B (last) -> A -> ...
             *   AFTER:  A (last) -> ...
             */
            Node nB = this.last;
            Node nA = nB.next;

            this.last = nA;

            nA.previous = null;
            break;
        }
        this.size = this.size - 1;

        return itemToReturn;
    }


    /**
     * Return an iterator for the Deque class.
     * @return a Deque iterator
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Construct an appropriate iterator for the Deque class. 
     * The iterator starts at the FRONT and iterates until it gets to the END.
     * @author Fred Habster
     *
     */
    private class DequeIterator implements Iterator<Item> {
        private Node current = Deque.this.first;

        /**
         * Every iterator needs a hasNext() method. For the Deque
         * class hasNext simply returns true unless current is null.
         * @return true if current points at a non-null deque node, false
         * otherwise.
         */
        public boolean hasNext() { 
            return (this.current != null);
        }

        /**
         * We don't use this method.
         * @throws UnsupportedOperationException in all cases
         */
        public void remove() 
            throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        /**
         * Return the next item in the iterator.
         * @return the next item
         * @throws NoSuchElementException if there is no next element.
         */
        public Item next()
                throws NoSuchElementException {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            } else {
                Item itemToReturn = this.current.item;
                this.current = this.current.previous;
                return itemToReturn;
            }
        }
    }


    /**
     * A main (needed by spec but not used).
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        /* Not used. */
    }

}

