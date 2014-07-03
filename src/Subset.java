/**
 * 
 * @author Fred Habster
 *
 */
public class Subset {

    /**
     * A dummy constructor (should never be used).
     */
    public Subset() {
        // A dummy constructor.
    }

    /**
     *  Takes a command-line integer k; reads in a sequence of N strings from standard input. 
     *  Prints out exactly k of them, uniformly at random. Each item from the sequence can be 
     *  printed out at most once. We assume that k >= 0 and no greater than the number of 
     *  string N on standard input.
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        String kString = args[0];
        int k = Integer.parseInt(kString);
        assert (k > 0); // Makes no sense to return zero or fewer strings.
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        // Read in the strings from standard input.
        while (!StdIn.isEmpty()) {
            String curString = StdIn.readString();
            rq.enqueue(curString);
        }

        int numberStringsRead = rq.size();
        assert (k <= numberStringsRead);

        // Return k at random
        for (int i = 1; i <= k; ++i) {
            String curString = rq.dequeue();
            System.out.println(String.format("%s", curString));
        }
    }
}
