/**
 * Created by ryan on 2/9/17.
 */
// only implemented isSorted and less methods from memory
// need to readdress merge and sort methods

public class MergeSort {

    public static void main(String[] args){
        Integer[] nums = {1,4,5,2,3};
        Integer[] aux = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++){
            aux[i] = nums[i];
        }

        sort(nums, aux, 0, nums.length - 1);

        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }

    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;

        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
        assert isSorted(a, low, mid);
        assert isSorted(a, mid+1, high);

        for (int k = low; k <= high; k++){
            aux[k] = a[k];
        }

        int i = low, j = mid+1;
        for (int k = low; k <= high; k++){
            if      (i > mid)              a[k] = aux[j++];
            else if (j > high)             a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];

        }
    }

    private static boolean isSorted(Comparable[] a, int lower, int upper){

        for(int i = lower; i < upper; i++){
            if(less(a[i+1], a[i])) return false;
        }
        return true;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
}
