/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnhMinhSe183432;

/**
 *
 * @author msi2k
 */
public class SinglyLinkedList {
    
    
   Node head, tail;

    public SinglyLinkedList() {
        head = tail = null;
    }

    // 1. Add a node to the head
    public void addToHead(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    // 2. Add a node to the tail
    public void addToTail(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // 3. Add a node after a specific node p
    public void addAfter(Node p, int x) {
        if (p == null) return;
        Node newNode = new Node(x);
        newNode.next = p.next;
        p.next = newNode;
        if (p == tail) {
            tail = newNode;
        }
    }

    // 4. Traverse the list and display all nodes
    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // 5. Count the number of nodes
    public int count() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // 6. Delete the head and return its value
    public int deleteFromHead() {
        if (head == null) return -1;  // list is empty
        int value = head.data;
        head = head.next;
        if (head == null) tail = null;  // list became empty
        return value;
    }

    // 7. Delete the tail and return its value
    public int deleteFromTail() {
        if (head == null) return -1;  // list is empty
        if (head == tail) {  // only one element
            int value = head.data;
            head = tail = null;
            return value;
        }
        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }
        int value = tail.data;
        tail = temp;
        tail.next = null;
        return value;
    }

    // 8. Delete the node after node p
    public int deleteAfter(Node p) {
        if (p == null || p.next == null) return -1;  // no node to delete
        int value = p.next.data;
        if (p.next == tail) {
            tail = p;
        }
        p.next = p.next.next;
        return value;
    }

    // 9. Delete the first node with value x
    public void deleteNode(int x) {
        if (head == null) return;
        if (head.data == x) {
            deleteFromHead();
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.data != x) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            if (temp.next == null) {
                tail = temp;
            }
        }
    }

    // 10. Search for the node with value x
    public Node search(int x) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                return temp;
            }
            temp = temp.next;
        }
        return null;  // not found
    }

    // 11. Delete a specific node p
    public void delete(Node p) {
        if (head == null || p == null) return;
        if (head == p) {
            deleteFromHead();
            return;
        }
        Node temp = head;
        while (temp.next != p && temp != null) {
            temp = temp.next;
        }
        if (temp.next == p) {
            temp.next = p.next;
            if (p == tail) {
                tail = temp;
            }
        }
    }

    // 12. Delete an i-th node from the list
    public void deleteNode2(int i) {
        if (i < 0 || head == null) return;
        if (i == 0) {
            deleteFromHead();
            return;
        }
        Node temp = head;
        for (int index = 0; temp != null && index < i - 1; index++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) return;
        if (temp.next == tail) {
            tail = temp;
        }
        temp.next = temp.next.next;
    }

    // 13. Add a node with value x before node p
    public void addBefore(Node p, int x) {
        if (p == null || head == null) return;
        if (p == head) {
            addToHead(x);
            return;
        }
        Node temp = head;
        while (temp.next != p) {
            temp = temp.next;
        }
        Node newNode = new Node(x);
        newNode.next = p;
        temp.next = newNode;
    }

    // 14. Sort the list by ascending order of info
    public void sort() {
        if (head == null) return;
        for (Node i = head; i.next != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.data > j.data) {
                    int temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
    }

    // 15. Reverse the list using one pass
    public void reverse() {
        Node prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        tail = head;
        head = prev;
    }

    // 16. Convert the list to an array
    public int[] toArray() {
        int[] arr = new int[count()];
        Node temp = head;
        int i = 0;
        while (temp != null) {
            arr[i++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }

    // 17. Merge two ordered lists into one ordered list
    public void merge(SinglyLinkedList other) {
        Node temp1 = this.head, temp2 = other.head;
        SinglyLinkedList result = new SinglyLinkedList();
        while (temp1 != null && temp2 != null) {
            if (temp1.data <= temp2.data) {
                result.addToTail(temp1.data);
                temp1 = temp1.next;
            } else {
                result.addToTail(temp2.data);
                temp2 = temp2.next;
            }
        }
        while (temp1 != null) {
            result.addToTail(temp1.data);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            result.addToTail(temp2.data);
            temp2 = temp2.next;
        }
        this.head = result.head;
        this.tail = result.tail;
    }

    // 18. Attach another singly linked list to the end
    public void attach(SinglyLinkedList other) {
        if (this.head == null) {
            this.head = other.head;
            this.tail = other.tail;
        } else {
            this.tail.next = other.head;
            this.tail = other.tail;
        }
    }

    // 19. Find the maximum value
    public int max() {
        if (head == null) return Integer.MIN_VALUE;
        int maxVal = head.data;
        Node temp = head.next;
        while (temp != null) {
            if (temp.data > maxVal) {
                maxVal = temp.data;
            }
            temp = temp.next;
        }
        return maxVal;
    }

    // 20. Find the minimum value
    public int min() {
        if (head == null) return Integer.MAX_VALUE;
        int minVal = head.data;
        Node temp = head.next;
        while (temp != null) {
            if (temp.data < minVal) {
                minVal = temp.data;
            }
            temp = temp.next;
        }
        return minVal;
    }

    // 21. Sum of all values
    public int sum() {
        int sum = 0;
        Node temp = head;
        while (temp != null) {
            sum += temp.data;
            temp = temp.next;
        }
        return sum;
    }

    // 22. Average of all values
    public int avg() {
        int sum = sum();
        int count = count();
        return (count == 0) ? 0 : sum / count;
    }

    // 23. Check if the list is sorted
    public boolean sorted() {
        Node temp = head;
        while (temp != null && temp.next != null) {
            if (temp.data > temp.next.data) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    // 24. Insert into a sorted list
    public void insert(int x) {
        Node newNode = new Node(x);
        if (head == null || head.data >= x) {
            addToHead(x);
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.data < x) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    // 25. Check if two lists have the same contents
    public boolean isEqual(SinglyLinkedList other) {
        Node temp1 = this.head, temp2 = other.head;
        while (temp1 != null && temp2 != null) {
            if (temp1.data != temp2.data) {
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return (temp1 == null && temp2 == null);
    }
    
}
