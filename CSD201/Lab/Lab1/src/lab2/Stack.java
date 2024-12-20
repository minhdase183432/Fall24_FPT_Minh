/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.EmptyStackException;

/**
 *
 * @author msi2k
 */
public class Stack<T> {
    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public void push(T x) {
        Node<T> node = new Node<>(x);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    public void traverse() {
        Node<T> current = top;
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

    public static void decimalToBinary(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            int remainder = decimal % 2;
            stack.push(remainder);
            decimal /= 2;
        }
        System.out.print("Binary representation: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }
}