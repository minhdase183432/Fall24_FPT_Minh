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
public class Main {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 SinglyLinkedList list = new SinglyLinkedList();
        list.addToHead(3);
        list.addToHead(1);
        list.addToTail(5);
        list.addToTail(9);
        list.traverse();
        
        list.sort();
        list.traverse();
        
        list.reverse();
        list.traverse();

        System.out.println("Max: " + list.max());
        System.out.println("Min: " + list.min());
        System.out.println("Sum: " + list.sum());
        System.out.println("Average: " + list.avg());
    }
    
}
