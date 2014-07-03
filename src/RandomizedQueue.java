import java.util.NoSuchElementException;
import java.lang.NullPointerException;
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
    
    /**
     * The length of the queue.
     * @return the length of the queue (zero if queue is empty).
     */
    public int size() {
        return this.endOfArrayIndex;
    }

    /**
     * Indicates whether the queue is empty or not.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (this.endOfArrayIndex == 0);
    }


    /**
     * Add an item to the queue. Note that enqueue for a randomized
     * queue works the same as for a non-randomized queue.
     * @param item the item to enqueue.
     * @throws NullPointerException if null is the item to be enqueued.
     */
    public void enqueue(Item item) 
        throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (this.endOfArrayIndex == this.items.length) {
            this.resize(2 * this.items.length);
        }

        this.items[this.endOfArrayIndex] = item;
        this.endOfArrayIndex = this.endOfArrayIndex + 1;
    }

    /**
     * Resize the array.
     * @param capacity resize the array to capacity.
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < this.endOfArrayIndex; i++) {
            copy[i] = this.items[i];
        }

        this.items = copy;
    }


    /**
     * Dequeue a random element.
     * @return the element dequeued.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Item dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
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
        this.items[this.endOfArrayIndex - 1] = null;
        
        // Decrement endOfArrayIndex
        --this.endOfArrayIndex;

        // Resize (shrink) the array if the last elements is less than or equal
        // to 1/4 the array length.
        if ((!this.isEmpty()) && (this.endOfArrayIndex <= this.items.length / 4)) {
            this.resize(this.items.length / 2);
        }
        return itemToReturn;
    }


    /**
     * Find the index of a random element of the array.
     * @return the index of the random array location.
     */
    private int chooseRandomArrayIndex() {
        // Pick a random integer in [0, endOfArrayIndex)
        int randomArrayIndex = StdRandom.uniform(this.endOfArrayIndex);
        return randomArrayIndex;
    }


    /**
     * 
     * Return a random element from the array.
     * @return a random element from the array (but array remains the same)
     * @throws NoSuchElementException if the queue is empty.
     */
    public Item sample() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int randomArrayIndex = this.chooseRandomArrayIndex();
        Item itemToReturn = this.items[randomArrayIndex];
        return itemToReturn;
    }
    
    /**
     * Make a copy of this RandomizedQueue object.
     * @return the copy.
     */
    private RandomizedQueue<Item> copy() {
        RandomizedQueue<Item> rqCopy = new RandomizedQueue<Item>();
        
        for (int i = 0; i < this.endOfArrayIndex; ++i) {
            rqCopy.enqueue(this.items[i]);
        }
        
        return rqCopy;
    }
   
    /**
     * The iterator for RandomizedQueue.
     * @return the iterator object.
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * The implementation of the RandomizedQueue iterator.
     * @author Fred Habster
     *
     */
    private final class RandomizedQueueIterator implements Iterator<Item> {
        
        private RandomizedQueue<Item> rq;
        
        /**
         * Initialized the iterator by creating a copy.
         */
        private RandomizedQueueIterator() {
            // Upon initialization, copy the RandomizedQueue we want to iterate over.
            this.rq = RandomizedQueue.this.copy();
        }
        
        /**
         * Indicates whether or not the iterator has a next item.
         * @return true if the iterator has a next item, false otherwise.
         */
        public boolean hasNext() { 
            return !this.rq.isEmpty();
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
         * Return the next item. All we have to do is dequeue from the copy.
         * @return the next item
         * @throws NoSuchElementException if there are more items to offer 
         */
        public Item next() 
                throws NoSuchElementException {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            } else {
                return this.rq.dequeue();
            }
        }
    }
    
    /**
     * We don't use this method, but put it here as a place-holder.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        /* Later? */
    }
    
}
