package week2.assignments;

/**
 * Create a data structure that efficiently supports the stack operations(push and pop) and also a return-the-maximum
 * operation. Assume the elements are real numbers so that you can compare them.
 */

public class week2interview2 {
    private int[] items;
    int N = 0;

    public week2interview2(int size) {
        items = new int[size];
    }

    public void push(int i){
        items[N++] = i;
    }
    public int pop(){
        return items[--N];
    }
    public int returnMax(){
        return items[N];
    }
}
