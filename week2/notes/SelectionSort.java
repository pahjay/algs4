package week2.notes;

/**
 * Created by ryan on 2/8/17.
 */

// implementation from memory, not notes
public class SelectionSort {

    public static void main(String[] args){
        Integer[] a = {4,3,1,5,2};
        System.out.println(isSorted(a));
        sort(a);

        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
        System.out.println(isSorted(a));

    }

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
        int min;
        for (int i = 0;i < a.length; i++){
            min = i;
            // second pointer starts at i + 1
            for (int x = i + 1; x < a.length; x++){
                if (less(a[x], a[min])) min = x;
            }
            exch(a, i, min);
        }
    }
}