/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

/**
 *
 * @author msi2k
 */
public class SortingDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("A05", "Tran Quang", 7),
            new Employee("A03", "Nguyen An", 7),
            new Employee("A01", "Truong Phung", 5),
            new Employee("A04", "Pham Thi Lam", 2),
            new Employee("A02", "Do Trung Ton", 5)
        };

        System.out.println("Before Sorting:");
        printArray(employees);

        // Test Insertion Sort Ascending
        SortingAlgorithms.insertionSort(employees);
        System.out.println("\nAfter Insertion Sort (Ascending):");
        printArray(employees);

        // Test Selection Sort Ascending
        SortingAlgorithms.selectionSort(employees);
        System.out.println("\nAfter Selection Sort (Ascending):");
        printArray(employees);

        // Test Bubble Sort Ascending
        SortingAlgorithms.bubbleSort(employees);
        System.out.println("\nAfter Bubble Sort (Ascending):");
        printArray(employees);

        // Test Insertion Sort Descending
        SortingAlgorithms.insertionSortDescending(employees);
        System.out.println("\nAfter Insertion Sort (Descending):");
        printArray(employees);
    }

    public static void printArray(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
