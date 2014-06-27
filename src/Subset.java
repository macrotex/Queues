
public class Subset {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String kString = args[0];
        int k = Integer.parseInt(kString);
        assert (k > 0); // Makes no sense to return zero or fewer strings.
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        // Read in the strings from standard input.
        while (!StdIn.isEmpty()) {
            String curString = StdIn.readString();
            rq.enqueue(curString);
        }

        // Return k at random
        for (int i = 1; i <= k; ++i) {
            String curString = rq.dequeue();
            System.out.println(String.format("%s", curString));
        }
    }

}
