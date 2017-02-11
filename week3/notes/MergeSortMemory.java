package week3.notes;

/**
 * Created by ryan on 2/10/17. implemented from memory
 */
public class MergeSortMemory {

    /**
     * Recursive Algorithm
     * 1. copy array elements to an auxiliary array
     * 2. sort each half, recursively until you sort to the smallest pair
     * 3. merge the two halves from the aux array back to the original
     */
    public static void main(String[] args){
        Integer[] a = {1,5,4,2,3,6};
        Integer[] aux = new Integer[a.length];

        sort(a, aux, 0, a.length - 1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

    }

    /**
     * Sort recursively calls itself until it reaches a point where it is merging two arrays of size one.
     */
    private static void sort(Comparable[] a, Comparable[] aux, int low, int high){
        if(high <= low) return;
        int mid = low + (high - low) / 2;

        sort(a, aux, low, mid);
        sort(a, aux,mid+1, high);
        merge(a, aux, low, mid, high);
    }

    /**
     * Copies over a to aux to allow a to be rewritten as the merged array.
     * Merge assigns i and j pointers to low and mid+1 positions, respectively, moves them based on four conditions
     * 1. if i is greater than mid
     * 2. if j is greater than high
     * 3. if a[i] is less than a[j]
     * 4. if a[j] is less than a[i]
     */
    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        int i = low, j = mid + 1;

        for(int l = low; l <= high; l++){
            aux[l] = a[l];
        }

        for (int k = low; k <= high; k++) {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > high)              a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length -  1; i++){
            if(less(a[i+1], a[i])) return false;
        }
        return true;
    }
}
