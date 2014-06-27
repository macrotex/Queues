import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * The RandomizedQueue structure will consist of a resizable array as discussed in class.
 * Each time we need to dequeue an element, we pick a random element from the array and return 
 * that element. We then take the LAST element of the array and put it where the 
 * just-returned element was stored and then decrement the size of the array, then resize
 * (if necessary). 
 * @author Fred Habster
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * This is the (resizable) array where we store the items.
     */
    private Item[] items;
    
    /**
     *  endOfArray is the location in the array closest to the
     *  start that is NOT being used. So, if the array has no
     *  usable elements, endOfArray is 0. 
     */
    private int endOfArrayIndex;
    
    /**
     * The constructor. Initializes our resizable array to a single 
     * element.
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        // Initialize the array to a single element.
        this.items = (Item[]) new Object[1];
        this.items[0] = null;
        this.endOfArrayIndex = 0;
        
    }
    
    public int size() {
        return this.endOfArrayIndex;
    }

    public boolean isEmpty() {
        return (this.endOfArrayIndex == 0);
    }
    
    /*
     * Enqueing is the same as a non-randomized enqueue.
     */
    public void enqueue(Item item)  {
        //System.out.println(String.format("endOfArrayIndex is %d", this.endOfArrayIndex));
        //System.out.println(String.format("length of items is %d", this.items.length));
        if (this.endOfArrayIndex == this.items.length) this.resize(2 * this.items.length);
        //System.out.println(String.format("endOfArrayIndex is %d", this.endOfArrayIndex));
        this.items[this.endOfArrayIndex] = item;
        this.endOfArrayIndex = this.endOfArrayIndex + 1;
    }
    
    @SuppressWarnings("unchecked")
    /*
     * Resize the array.
     */
    private void resize(int capacity) {
        //System.out.println(String.format("Resizing to capacity %d", capacity));
        Item[] copy = (Item[]) new Object[capacity];
        
        for (int i = 0; i < this.endOfArrayIndex; i++) {
            copy[i] = this.items[i];
        }
        
        this.items = copy;
    }

    /*
     * Return a random item.
     */
    public Item dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new  java.util.NoSuchElementException();
        }
        // Find a random integer in [0, this.endOfArrayIndex)
        int randomArrayIndex = this.chooseRandomArrayIndex();

        // Get that element.
        Item itemToReturn = this.items[randomArrayIndex];

        // We can now "zero-out" that array entry
        this.items[randomArrayIndex] = null;

        // Swap the LAST array element with this one.
        Item lastItem = this.items[this.endOfArrayIndex - 1];
        this.items[randomArrayIndex] = lastItem;

        // Decrement endOfArrayIndex
        --this.endOfArrayIndex;

        // Resize (shrink) the array if the last elements is less than or equal
        // to 1/4 the array length.
        if ((!this.isEmpty()) && (this.endOfArrayIndex <= this.items.length / 4)) {
            this.resize(this.items.length / 2);
        }
        return itemToReturn;
    }
    
    // Return a random array element
    public int chooseRandomArrayIndex() {
        // Pick a random integer in [0, endOfArrayIndex)
        int randomArrayIndex = StdRandom.uniform(this.endOfArrayIndex);
        return randomArrayIndex;
    }

    /*
     * Return a random element from the array.
     */
    public Item sample() {
        int randomArrayIndex = this.chooseRandomArrayIndex();
        Item itemToReturn = this.items[randomArrayIndex];
        return itemToReturn;
    }
    
    private RandomizedQueue<Item> copy(){
        RandomizedQueue<Item> rqCopy = new RandomizedQueue<Item>();
        
        for (int i=0; i < this.endOfArrayIndex; ++i) {
            rqCopy.enqueue(this.items[i]);
        }
        
        return rqCopy;
    }
   
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private RandomizedQueue<Item> rq;
        
        private RandomizedQueueIterator(){
            // Upon initialization, copy the RandomizedQueue we want to iterate over.
            rq = RandomizedQueue.this.copy();
        }
        
        public boolean hasNext() { 
            return !rq.isEmpty();
        }
        public void remove() { /* not implemented */ }
        public Item next() {
            return rq.dequeue();
        }
    }
    
    
    public static void main(String[] args) {
        /* Later? */
    }
    
}
