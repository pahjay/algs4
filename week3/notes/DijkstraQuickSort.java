package week3.notes;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ryan on 2/15/17.
 */
public class DijkstraQuickSort {

    public static void main(String[] args){
        Integer[] ints = {1,2,3,4,5,4,4,4,9,10};
        sort(ints);

        for(int i : ints){
            System.out.print(i + " ");
        }

    }

    private static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        for(Object i : a){
            System.out.println(i);
        }
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high){
        if(high <= low) return;
        int lb = low, ub = high;
        Comparable v = a[low];
        int i = low;

        while(i <= ub){
            int comp = a[i].compareTo(v);

            if (comp < 0) exch(a, lb++, i++); // a[i] is lower than v, exchange and increase pointer
            else if (comp > 0) exch(a, i, ub--); // a[i] is larger than v, exchange
            else i++;
        }
        sort(a, low, lb - 1);
        sort(a, ub + 1, high);
    }

    private static void exch(Comparable[] a, int i, int j) {
            Comparable temp = a[j];
            a[j] = a[i];
            a[i] = temp;
    }
}
