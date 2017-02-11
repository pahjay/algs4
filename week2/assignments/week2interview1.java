package week2.assignments;

import java.util.Stack;

/**
 * Created by ryan on 1/30/17.
 */
/*
    Implement a queue with two stacks so that each queue operation takes a constant amortized number of stack operations.
 */
public class week2interview1 {
    Stack<Integer> inbound = new Stack<>();
    Stack<Integer> outbound = new Stack<>();

    public void queue(Integer i){
        inbound.push(i);
    }

    public Integer dequeue(){
        if(outbound.isEmpty()){
            while(!inbound.isEmpty()){
                outbound.push(inbound.pop());
            }
        }
        return outbound.pop();
    }
}
