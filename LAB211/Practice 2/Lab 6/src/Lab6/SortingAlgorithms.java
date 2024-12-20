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
public class SortingAlgorithms {

    // Insertion Sort (Ascending)
    public static void insertionSort(Employee[] employees) {
        for (int i = 1; i < employees.length; i++) {
            Employee key = employees[i];
            int j = i - 1;
            while (j >= 0 && employees[j].compareTo(key) > 0) {
                employees[j + 1] = employees[j];
                j = j - 1;
            }
            employees[j + 1] = key;
        }
    }

    // Selection Sort (Ascending)
    public static void selectionSort(Employee[] employees) {
        for (int i = 0; i < employees.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < employees.length; j++) {
                if (employees[j].compareTo(employees[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Employee temp = employees[minIndex];
            employees[minIndex] = employees[i];
            employees[i] = temp;
        }
    }

    // Bubble Sort (Ascending)
    public static void bubbleSort(Employee[] employees) {
        int n = employees.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (employees[j].compareTo(employees[j + 1]) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // Insertion Sort (Descending)
    public static void insertionSortDescending(Employee[] employees) {
        for (int i = 1; i < employees.length; i++) {
            Employee key = employees[i];
            int j = i - 1;
            while (j >= 0 && employees[j].compareTo(key) < 0) {
                employees[j + 1] = employees[j];
                j = j - 1;
            }
            employees[j + 1] = key;
        }
    }
}
