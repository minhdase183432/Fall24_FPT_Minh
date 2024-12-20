/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 *
 * @author msi2k
 */
public class Main {

    public static void main(String[] args) {
        // Test Integer Stack
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        System.out.println("IntegerStack:");
        System.out.println("Top: " + integerStack.top());
        System.out.println("Pop: " + integerStack.pop());
        System.out.println("Top: " + integerStack.top());
        integerStack.traverse();

        // Test IntegerQueue
        Queue<Integer> integerQueue = new Queue<>();
        integerQueue.enqueue(1);
        integerQueue.enqueue(2);
        integerQueue.enqueue(3);
        System.out.println("IntegerQueue:");
        System.out.println("First: " + integerQueue.first());
        System.out.println("Dequeue: " + integerQueue.dequeue());
        System.out.println("First: " + integerQueue.first());
        integerQueue.traverse();

        // Test StringStack
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        System.out.println("StringStack:");
        System.out.println("Top: " + stringStack.top());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Top: " + stringStack.top());
        stringStack.traverse();

        // Test StringQueue
        Queue<String> stringQueue = new Queue<>();
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");
        System.out.println("StringQueue:");
        System.out.println("First: " + stringQueue.first());
        System.out.println("Dequeue: " + stringQueue.dequeue());
        System.out.println("First: " + stringQueue.first());
        stringQueue.traverse();

        // Test CharacterStack
        Stack<Character> characterStack = new Stack<>();
        characterStack.push('H');
        characterStack.push('e');
        System.out.println("CharacterStack:");
        System.out.println("Top: " + characterStack.top());
        System.out.println("Pop: " + characterStack.pop());
        System.out.println("Top: " + characterStack.top());
        characterStack.traverse();

        // Test CharacterQueue
        Queue<Character> characterQueue = new Queue<>();
        characterQueue.enqueue('H');
        characterQueue.enqueue('e');
        System.out.println("CharacterQueue:");
        System.out.println("First: " + characterQueue.first());
        System.out.println("Dequeue: " + characterQueue.dequeue());
        System.out.println("First: " + characterQueue.first());
        characterQueue.traverse();

        // Test ObjectStack
        Stack<Object> objectStack = new Stack<>();
        objectStack.push("Computer1");
        objectStack.push("Computer2");
        System.out.println("ObjectStack:");
        System.out.println("Top: " + objectStack.top());
        System.out.println("Pop: " + objectStack.pop());
        System.out.println("Top: " + objectStack.top());
        objectStack.traverse();

        // Test ObjectQueue
        Queue<Object> objectQueue = new Queue<>();
        objectQueue.enqueue("Computer1");
        objectQueue.enqueue("Computer2");
        System.out.println("ObjectQueue:");
        System.out.println("First: " + objectQueue.first());
        System.out.println("Dequeue: " + objectQueue.dequeue());
        System.out.println("First: " + objectQueue.first());
        objectQueue.traverse();
    }
}
