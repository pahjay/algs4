package week3.notes;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Implemented from memory on 2/15/17
 */

public class QuickSort {

    public static void main(String[] args){
        Character[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        sort(chars);

        for(char c : chars){
            System.out.println(c);
        }
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        System.out.println("shuffled");

        for(Object c: a){
            System.out.print(c + " ");

        }
        System.out.println();

        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int low, int high){
        if(high <= low) return;
        int j = partition(a, low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    // returns partitioning element
    private static int partition(Comparable[] a, int low, int high){
        // assign pointers for traversing
        int i = low, j = high + 1;

        while(true){
            while(less(a[++i], a[low]))
                if (i == high) break; // move pointer until i is less than previous partition element, break if reach end

            while(less(a[low], a[--j]))
                if (j == low) break; // move pointer until j is more than the previous partition element, break if reach beginning

            if (i >= j) break; // if pointers cross, break
            exch(a, i, j); // exchange i and j to maintain the partition, that is, smaller on left side
        }

        exch(a, low, j);
        return j;

    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
