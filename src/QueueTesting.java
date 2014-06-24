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
        
        
    }

}
