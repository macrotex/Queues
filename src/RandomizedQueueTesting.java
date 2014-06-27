import static org.junit.Assert.*;

import org.junit.Test;


public class RandomizedQueueTesting {

    @Test
    public void RQTesting1() {
        // Create a Deque object.
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        assertTrue(rq instanceof RandomizedQueue);
        
        assertTrue(rq.size() == 0);
        assertTrue(rq.isEmpty());
    }

    @Test
    public void RQTestingRandomElement() {
        // Create a Deque object.
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        assertTrue(rq instanceof RandomizedQueue);
        
        int item = 0 ;
        
        rq.enqueue(1);
        assertTrue(rq.size() == 1);
        rq.enqueue(2);
        assertTrue(rq.size() == 2);
        rq.enqueue(3);
        assertTrue(rq.size() == 3);
        
        item = rq.dequeue();
        assertTrue(rq.size() == 2);
        System.out.println(String.format("item is %d", item));

        item = rq.dequeue();
        assertTrue(rq.size() == 1);
        System.out.println(String.format("item is %d", item));

        item = rq.dequeue();
        assertTrue(rq.size() == 0);
        System.out.println(String.format("item is %d", item));

        // Do more
        System.out.println("Starting enqueue");

        int numIterations = 10000;
        for (int i=1; i <= numIterations; ++i) {
            rq.enqueue(2*i);
            assertTrue(rq.size() == i);
        }

        System.out.println("Starting dequeue");
        for (int i=1; i <= numIterations; ++i) {
            //System.out.println(String.format("size is %d", rq.size()));
            item = rq.dequeue();
            assertTrue(rq.size() == (numIterations - i));
        }


    }
    
    @Test
    public void RQTestingIterator() {
        // Create a Deque object.
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        assertTrue(rq instanceof RandomizedQueue);

        // Iterate over an empty randomized queue
        int counter = 0;

        for (@SuppressWarnings("unused") int curitem : rq){
            counter = counter + 1;
        }
        assertTrue(counter == 0);

        // Iterate over a single element RQ
        rq.enqueue(13);
        for (int curItem : rq) {
            counter = counter + 1;
            assertTrue(curItem == 13);
        }
        assertTrue(counter == 1);
        assertTrue(rq.dequeue() == 13);
        assertTrue(rq.isEmpty());

        // Iterate over MANY elements (twice!)
        int numIterations = 100;
        for (int i=1; i <= numIterations; ++i) {
            rq.enqueue(3 * i);
        }

        counter = 0;
        for (@SuppressWarnings("unused") int curItem1 : rq){
            for (@SuppressWarnings("unused") int curItem2 : rq){
                counter = counter + 1;
            }
        }
        assertTrue(counter == numIterations * numIterations);


    }

    @Test
    public void RQTestingIterator2() {
        // Create a Deque object.
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        assertTrue(rq instanceof RandomizedQueue);

        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(5);
        rq.enqueue(7);

        int result = rq.dequeue() * rq.dequeue() * rq.dequeue() * rq.dequeue() ;
        assertTrue(result == (2 * 3 * 5 * 7));
    }

}
