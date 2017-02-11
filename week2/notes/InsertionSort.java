package week2.notes;

/**
 * Created by ryan on 2/8/17.
 */
public class InsertionSort {
    private static boolean less(Comparable<Comparable> v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a){

        for(int i = 0; i < a.length-1; i++){
            if(less(a[i+1],a[i])) return false;
        }
        return true;
    }

    public static void sort(Comparable[] a){
        // set pointer at start and move right
        for(int i = 0; i < a.length; i++){
            // set pointer to i, and traverse left, checking to see if a swap is needed
            for(int j = i; j > 0; j--){
                if(less(a[j], a[j-1])) exch(a, j, j-1);
                else break;
            }
        }
    }
}
