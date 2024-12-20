// ==========================================================================
// Do NOT modify this file 
// ==========================================================================

class Node{
	
    Office info;
    Node next;
    
    // Default constructor (no parameter)
    Node () {}
    
    // Constructor for a typical node
    Node (Office x, Node p) {
        this.info = x; // data stored inside the node
        this.next = p; // link to the next node
    }
    
    //Copy constructor
    Node (Office x) {
        this(x, null);
    }
    
    public Office getInfo() {
        return this.info;
    }
    
    public void setInfo(Office info) {
        this.info = info;
    }
	
 }

