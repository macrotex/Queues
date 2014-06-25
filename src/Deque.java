import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	/**
	public class Deque<Item> implements Iterable<Item> {
		   public Deque()                           // construct an empty deque
		   public boolean isEmpty()                 // is the deque empty?
		   public int size()                        // return the number of items on the deque
		   public void addFirst(Item item)          // insert the item at the front
		   public void addLast(Item item)           // insert the item at the end
		   public Item removeFirst()                // delete and return the item at the front
		   public Item removeLast()                 // delete and return the item at the end
		   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
		   public static void main(String[] args)   // unit testing
		}
	*/

     /*
      * Keep track of the first and last nodes in the deque.
      */
     Node first  = null;
     Node last   = null;
     
     /**
      * Let's also keep track of the size.
      */
     int size = 0;
     
    /**
     * Doubly-linked list
     * @author fdslkjds
     *
     */
    private class Node {
        Node next;
        Node previous;
        Item item;
        
        public Node(Item item){
            this.item = item;
            
            this.next     = null;
            this.previous = null;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int size() {
        return this.size;
    }
    
    public void addFirst(Item item){
        Node newNode = new Node(item);
        
        switch(this.size) {
            case 0:
                first = newNode;
                last  = newNode;
            default:
                newNode.previous = first;
                first.next       = newNode;

                first = newNode;
        }

        this.size = this.size + 1;

        return;
    }
    
    public void addLast(Item item) {
        Node newNode = new Node(item);
        
        switch(this.size) {
            case 0:
                first = newNode;
                last  = newNode;
            default:
                newNode.next  = last;
                last.previous = newNode;

                last = newNode;
        }

        this.size = this.size + 1;

        return;
    }
    
    public Item removeFirst() 
            throws NoSuchElementException{

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
            Node B = this.first.previous;
            B.next = null;
            this.first  = B;
            break;
            
        }
        this.size = this.size - 1;
        
        return itemToReturn;
    }

    public Item removeLast() 
            throws NoSuchElementException{

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
            this.last = null;
            break;       
        default:
            /* Two elements:
             *   BEFORE: B (last) -> A -> ...
             *   AFTER:  A (last) -> ...
             */
            Node B = this.last;
            Node A = B.next;

            last = A;

            A.previous = null;
            break;
            
        }
        this.size = this.size - 1;
        
        return itemToReturn;
    }
    
    public Deque() {
                
	}
	
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = Deque.this.first;
        
        public boolean hasNext() { 
            return (current != null);
        }
        public void remove() { /* not implemented */ }
        public Item next() {
            Item itemToReturn = current.item;
            current = current.previous;
            return itemToReturn;
        }
		
	}
	
	    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

