
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.*;

/**
 * Created by ryan on 2/1/17.
 */

public class PermutationTester {

    // to use this program, change the file name and specify number of elements to print
    // to create a randomized queue, use dataType: RQ
    // to create a Deque object, use: DQ

    public static void main(String[] args){
        unitTest("duplicates.txt", 8, "RQ");
    }

    public static void unitTest(String filename, int elements, String dataType) {
        Deque<String> dq = new Deque<>();
        RandomizedQueue<String> rand = new RandomizedQueue<>();
        int counter = 0;

        if(dataType == "RQ") {
            try {
                Scanner input = new Scanner(new File(filename));

                while (input.hasNext()) {
                    rand.enqueue(input.next());
                }
            } catch (FileNotFoundException e) {
            }

            while (counter < elements) {
                System.out.println(rand.dequeue());
                counter++;
            }
        }

        if (dataType == "DQ"){
                try {
                    Scanner input = new Scanner(new File(filename));

                    while (input.hasNext()) {
                        dq.addLast(input.next());
                    }
                } catch (FileNotFoundException e) {
                }

                while (counter < elements) {
                    System.out.println(dq.removeLast());
                    counter++;
                }

        } else {
            System.out.println("invalid Data Type entered.");
        }
    }

}
