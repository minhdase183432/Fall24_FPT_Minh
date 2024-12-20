import java.util.*;
import java.io.*;

public class MyList {
    Node head, tail;
    int size;

    // Default constructor
    MyList() {
        this.head = null;
        this.tail=null;
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while(p != null) {
            f.writeBytes(p.getInfo() + " "); // write data in the node p to the file f
            p = p.next;
        }
        
        f.writeBytes("\r\n");
    }
    
    /**
     *   Do NOT modify this method
     *   Load 3 lines of data from file: 
     *      line k (for location), 
     *      line k+1 (for price), and
     *      line k+2 (for area)
     *   @param k the k-th line where data is started to be loaded
     */
    void loadData(int k) {
        String [] a = Lib.readLineToStrArray("data.txt", k);
        int [] b = Lib.readLineToIntArray("data.txt", k+1);
        int [] c = Lib.readLineToIntArray("data.txt", k+2);
        int n = a.length;
        // insert the new Node(a[i], b[i], c[i]) into the list
        for(int i = 0; i < n; i++) 
            addLast(a[i],b[i],c[i]);
    }
   
    /**
     *  Luy y: 
     *  1. SV KHONG su dung tieng Viet co dau trong bai lam de tranh error khi 
     *  run chuong trinh.
     *  2. Neu khong tuan thu se nhan diem 0 (khong).
     * 
     *  Question 1.1: implement the 'addLast' method that inserts a new Node 
     *  into the list's tail if the attributes 'price' and 'area' of an 
     *  Office are positive (>0).
     *  The output of this method will be written into the file 'f1.txt'. 
     *  Therefore you should open this file to see/test your code output.
     *  Example: with the content given in the file 'data.txt', the content of 
     *  'f1.txt' after insertion should be:  
     *      (Q1,9,8) (Q2,5,3) (Q4,6,5) (Q9,1,1) (TD,7,9) (TB,4,7) (TD,3,2)   
     *  @param xLocation the location of the input Office
     *  @param xPrice the price (gia tien) of the input Office
     *  @param xArea the area (dien tich) of the input Office
     */
    void addLast(String xLocation, int xPrice, int xArea) {
        //---------------------------------------------------------------------
        //--------------- Start your code here---------------------------------       
        if (xPrice > 0 && xArea > 0) {
            Office newOffice = new Office(xLocation, xPrice, xArea);
            Node newNode = new Node(newOffice);

            // If the list is empty, set both head and tail to new node
            if (head == null) {
                head = tail = newNode;
            } else {
                // Otherwise, add the new node at the end
                tail.next = newNode;
                tail = newNode;
            }
        }
        //---------------- End your code here----------------------------------
        //---------------------------------------------------------------------
    }
    
    /**
     *  Do NOT modify this method
     *  This is a helper method for writing data (node's info) stored in the 
     *  linked list to file @throws Exception 
     */
    
    // This method is used for Question 1.1
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 
        ftraverse(f);
        f.close();
    }
    
    // This method is used for Question 1.2
    void f2() throws Exception {
        clear();
        loadData(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 
        ftraverse(f);
    /**
    * Question 1.2: Find the first node in the linked list where Office's 
    * location is 'TD', if such a node is found then set the price of Office 
    * in this node to 5. 
    * The output of this method will be written into the file 'f2.txt'. 
    * Therefore you should open this file to see/test your code output.
    * Example: if the linked list before change is 
    *       (Q1,9,8) (Q2,5,3) (Q4,6,5) (Q9,1,1) (TD,7,9) (TB,4,7) (TD,3,2)   
    * then the content of 'f3.txt' after change is  
    *       (Q1,9,8) (Q2,5,3) (Q4,6,5) (Q9,1,1) (TD,5,9) (TB,4,7) (TD,3,2) 
    */
        //-------------------------------------------------------------------
        //------ Start your code here----------------------------------------
                Node current = head;
        // Traverse the list to find the first node where location is 'TD'
        while (current != null) {
            if (current.info.location.equals("TD")) {
                // Set the price to 5 if such a node is found
                current.info.price = 5;
                break; // Stop after finding the first 'TD' node
            }
            current = current.next;
        }
        //------ End your code here------------------------------------------
        //-------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    // This method is used for Question 1.3
    void f3() throws Exception {
        clear();
        loadData(5);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     
    /**
      * Question 1.3: With all Offices in location "Q2", "Q9", or "TD", 
      * increase the price by 3.
      * The output of this method will be written into the file 'f3.txt'. 
      * Therefore you should open this file to see/test your code output.
      * Example: if the linked list before change is:           
      *     (Q1,9,8) (Q2,6,3) (Q4,8,5) (Q9,5,4) (TD,4,9)
      * then the content of 'f3.txt' after change is:  
      *     (Q1,9,8) (Q2,9,3) (Q4,8,5) (Q9,8,4) (TD,7,9)   
      * the changed nodes are: (Q2,6,3), (Q9,5,4), and (TD,4,9)
      */
        //---------------------------------------------------------------------
        //--------------- Start your code here---------------------------------
              Node current = head;
        // Traverse the list to find nodes with location "Q2", "Q9", or "TD"
             while (current != null) {
            if (current.info.location.equals("Q2") || 
                current.info.location.equals("Q9") || 
                current.info.location.equals("TD")) {
                // Increase the price by 3 for matching nodes
                current.info.price += 3;
            }
            current = current.next;
             }
        //---------------- End your code here----------------------------------
        //---------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
     
    // This method is used for Question 1.4
    void f4() throws Exception {
        clear();
        loadData(9);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     
    /**
      * Question 1.4: Remove all Offices, except the largest Office? If 
      * there are more than one largest Office, keep the first of them.
      * The output of this method will be written into the file 'f4.txt'. 
      * Therefore you should open this file to see/test your code output.
      * Example: if the linked list is:            
      * (Q1,9,8) (Q2,5,3) (Q4,6,5) (Q9,1,1) (TD,7,9) (TB,4,7) (TD,3,2) (Q3,3,9)
      * then the content of 'f3.txt' is  (TD,7,9)  
      * The largest area is 9. There are two objects with this value: (TD,7,9) 
      * and (Q3,3,9); but we only find the first of them.
      */
        //---------------------------------------------------------------------
        //--------------- Start your code here---------------------------------
             if (head == null) return; // Empty list, nothing to do

        // Step 1: Find the maximum area
        Node current = head;
        int maxArea = head.info.area;
        Node largestNode = head;  // Track the first largest node
        while (current != null) {
            if (current.info.area > maxArea) {
                maxArea = current.info.area;
                largestNode = current;  // Keep track of the node with the largest area
            }
            current = current.next;
        }

        // Step 2: Remove all nodes except the first largest
        current = head;
        Node prev = null;
        while (current != null) {
            // If the current node is not the largest one, remove it
            if (current != largestNode) {
                if (prev == null) { // Removing head
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                // If we are removing the tail, update the tail reference
                if (current == tail) {
                    tail = prev;
                }
            } else {
                prev = current;  // Update prev only if we keep this node
            }
            current = current.next;
        }
        //---------------- End your code here----------------------------------
        //---------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    // This method is used for Question 1.5
    void f5() throws Exception {
        clear();
        loadData(13);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 
    /**
      * Question 1.5: Sort the linked list in an descending order according to 
      * Office's price.
      * The output of this method will be written into the file 'f5.txt'. 
      * Therefore you should open this file to see/test your code output.
      * Example: if the linked list before sorting is:           
      * (Q1,9,8) (Q2,5,3) (Q4,6,5) (Q9,1,1) (TD,7,9) (TB,4,7) (TD,2,2) (Q3,3,9)
      * then the content of 'f4.txt' after sorting is:  
      * (Q1,9,8) (TD,7,9) (Q4,6,5) (Q2,5,3) (TB,4,7) (Q3,3,9) (TD,2,2) (Q9,1,1)
      */
        //---------------------------------------------------------------------
        //--------------- Start your code here---------------------------------
//        sortList();
             if (head == null) return;  // If the list is empty, no need to sort

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            Node prev = null;
            Node next = head.next;

            while (next != null) {
                // Compare the current node's price with the next node's price
                if (current.info.price < next.info.price) {
                    // Swap the nodes
                    swapped = true;
                    if (prev == null) { // We are at the head
                        head = next;
                    } else {
                        prev.next = next;
                    }
                    current.next = next.next;
                    next.next = current;

                    // After swapping, update the prev and next nodes
                    prev = next;
                    next = current.next;
                } else {
                    // Move to the next pair
                    prev = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (swapped);  // Continue until no more swaps are made
        //---------------- End your code here----------------------------------
        //---------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
//    public void sortList() {
//
//    }
    
}

