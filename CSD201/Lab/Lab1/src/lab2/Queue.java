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
public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(T x) {
        Node<T> node = new Node<>(x);
        if (rear == null) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T value = front.value;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return value;
    }

    public T first() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return front.value;
    }

    public void traverse() {
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static void decimalToBinary(double decimal) throws EmptyQueueException {
        Queue<Integer> queue = new Queue<>();
        while (decimal > 0) {
            double remainder = decimal * 2;
            int integerPart = (int) remainder;
            queue.enqueue(integerPart);
            decimal = remainder - integerPart;
        }
        System.out.print("Binary representation: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue());
        }
        System.out.println();
    }
    
    
}