// =========================================================
// Do NOT modify this file 
// This queue is used for depth-first traversal on graph
// =========================================================
import java.util.*;

public class Stack {
    LinkedList<Integer> t;
    
    Stack() {
        t = new LinkedList<Integer>();
    }
    
    boolean isEmpty() {
        return t.isEmpty();
    }
    
    void clear() {
        t.clear();
    }
    
    void push(int x) {
        t.addLast(x);
    }
    
    Integer pop() {
        if(isEmpty()) 
            return(null);
        
        return t.removeLast();
    }
    
    Integer top() {
        if(isEmpty()) 
            return(null);
        
        return t.getLast();
    }

    int peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
// =========================================================
// Do NOT modify this file 
// =========================================================
