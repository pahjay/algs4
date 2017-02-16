package week3.notes;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Implemented from memory on 02/16/2017
 * Had one bug, StackOverflowError, fixed it by stopping recursion once high<=low and returning
 */
public class DijkstraQuickSortMemory {
    public static void main(String[] args){
        Integer[] ints = {1,2,3,4,5,6,7,8,9,10};
        sort(ints);
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int low, int high){
        if(high<=low) return;
        int lowerBound = low, upperBound = high;
        Comparable v = a[low];
        int i = low;

        while ( i <= upperBound ){
            int cmp = a[i].compareTo(v);

            if      ( cmp < 0 ) exch(a, i++, lowerBound++);
            else if ( cmp > 0 ) exch(a, i,   upperBound--);
            else                i++;
        }

        sort(a, low, lowerBound-1);
        sort(a, upperBound+1, high);
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
