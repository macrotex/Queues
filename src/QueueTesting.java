import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTesting {

    @Test
    public void QueueTesting1() {
        // Create a Deque object.
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque instanceof Deque);
        
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        
        deque.addFirst(10);
        assertTrue(deque.size() == 1);
        deque.addFirst(11);
        assertTrue(deque.size() == 2);
        deque.addFirst(12);
        assertTrue(deque.size() == 3);
        
        int item;

        item = deque.removeFirst();
        assertTrue(item == 12);
        assertFalse(item == 13);
        
        item = deque.removeFirst();
        assertTrue(item == 11);

        item = deque.removeFirst();
        assertTrue(item == 10);

        
        
        
    }

    @Test
    public void QueueTesting2() {
        // Create a Deque object.
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque instanceof Deque);

        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        
        deque.addLast(10);
        assertTrue(deque.size() == 1);
        deque.addLast(11);
        assertTrue(deque.size() == 2);
        deque.addLast(12);
        assertTrue(deque.size() == 3);

        int item;

        item = deque.removeLast();
        assertTrue(item == 12);
        assertFalse(item == 13);
        
        item = deque.removeLast();
        assertTrue(item == 11);

        item = deque.removeLast();
        assertTrue(item == 10);

    }

    @Test
    public void QueueTestingMixed() {
        // Create a Deque object.
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque instanceof Deque);

        int item;
        
        /* Test 1: 
         *   add first 0
         *   remove last 
         */
        deque.addFirst(-1);
        item = deque.removeLast();
        assertTrue(item == -1);        
        assertTrue(deque.isEmpty());        

        /* Test 2 
         *   add last 0
         *   remove first 
         */
        deque.addLast(-2);
        item = deque.removeFirst();
        assertTrue(item == -2);        
        assertTrue(deque.isEmpty());        

        /* Test 2 
         *   add last 1
         *   add first 2
         *   add first 3
         *   add last 5
         *   add first 7
         *   remove last 
         *   remove last 
         *   remove last 
         */
        deque.addLast(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(5);
        deque.addFirst(7);

        item = deque.removeLast();
        assertTrue(item == 5);        
        item = deque.removeLast();
        assertTrue(item == 1);        
        item = deque.removeLast();
        assertTrue(item == 2);        
        

    }
    
        
    // Test for exceptions.
    @Test(expected = java.util.NoSuchElementException.class)
    public void QueuesTestsException1() {
        Deque<Integer> deque = new Deque<Integer>();
        int item = deque.removeFirst();
        assertTrue(item == 0);
    }        

    @Test
    public void QueuesTestIteration1() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);

        int counter = 0; 
        for (int item : deque){
            counter = counter + 1;
            assertTrue(item == counter);
        }
        assertTrue(counter == 5);
    }        

    @Test
    public void QueuesTestIteration2() {
        Deque<Integer> deque = new Deque<Integer>();

        int counter = 0; 
        for (int item : deque){
            counter = counter + 1;
            assertTrue(item == counter);
        }
        assertTrue(counter == 0);
    }        

}
