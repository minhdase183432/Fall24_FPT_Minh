// ==========================================================================
// Do NOT modify this file 
// ==========================================================================

class Node{
    
    private Truck info;
    Node left,right;
    //int bal,level;
    
    // Default constructor (no parameter)
    Node () {}
    
    // Constructor for a typical node
    Node (Truck x, Node leftChild, Node rightChild) {
        this.info = x; // data stored inside the node
        this.left = leftChild; // link to the left child node
        this.right = rightChild; // link to the right child node
    }
    
    //Copy constructor
    Node (Truck x) {
        this(x, null, null);
    }
    
    public Truck getInfo() {
        return this.info;
    }
    
    public void setInfo(Truck inTruck) {
        this.info = inTruck;
    }
    
    /*public Node getLeft() {
        return this.left;
    }
    
    public Node getRight() {
        return this.right;
    }
    
    public void setLeft(Node left) {
        this.left = left;
    }
    
    public void setRight(Node right) {
        this.right = right;
    }*/
    
}

