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
public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super("Queue is empty");
    }
}
