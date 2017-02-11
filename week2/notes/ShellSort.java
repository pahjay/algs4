package week2.notes;

/**
 * Created by ryan on 2/8/17.
 */
public class ShellSort {

    public static void main(String[] args){
        Integer[] a = {7,2,3,4,5,2,6,8};
        System.out.println(isSorted(a));
        sort(a);
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

        int N = a.length;

        int h = 1;

        while (h < N/3){
            h = 3*h + 1;
            System.out.println(h);
        } // increments h

        while (h >= 1){
            for (int i = h; i < N; i++){
                for(int j = i; j >= h && less(a[j], a[j-h]); j-= h){
                    exch(a, j, j - h);
                }
            }
            System.out.println(h);
            h = h / 3;
        }

    }
}
