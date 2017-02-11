package week2.notes;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ryan on 2/8/17.
 */
public class KnuthShuffle {
    public static void main(String[] args){
        Integer[] rand = {1,2,3,4,5};
        shuffle(rand);

        for(int i = 0; i < rand.length; i++){
            System.out.println(rand[i]);
        }

    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void shuffle(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            int random = StdRandom.uniform(i + 1);
            exch(a, i, random);
        }
    }
}
