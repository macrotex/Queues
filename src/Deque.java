
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
     Node first       = null;
     Node last        = null;
     Node penultimate = null;
     
     /**
      * Let's also keep track of the size.
      */
     int size = 0;
     
    /**
     * Every Node points to its next element. 
     * @author fdslkjds
     *
     */
    private class Node {
        Node next ;
        Item item ;
        
        public Node(Item item){
            this.item = item;
            this.next = null;
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
        
        if (this.isEmpty()) {
            // Case 1: there is no first item.
            first = newNode;
            last  = newNode;
        } else {
            // Case 2: there IS a first item.
            first.next = newNode;
        }

        this.size = this.size + 1;

        return;
    }
    
    public Deque() {
                
	}
	
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        public boolean has_Next() { return true; }
        public void remove() { /* not implemented */ }
        public Item next() {
            
            return first.item;
        }
	    
	    
		
	}
	
	    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

