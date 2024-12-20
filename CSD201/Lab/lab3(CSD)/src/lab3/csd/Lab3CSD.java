/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3.csd;

import java.util.Scanner;
import javax.swing.tree.TreeNode;

/**
 *
 * @author admin
 */
public class Lab3CSD {

    /**
     * @param args the command line arguments
     */
    
    // bai 1:
    public static int sum(int n){
        if(n <= 1) return n;
        return n + sum(n-1);
    }
    
    // bai 2
    public static int findMin(int a[], int n){
        if(n == 1) return a[0];
        return Math.min(a[n-1], findMin(a,n-1));
    }
    
    // bai 3
    public static int sumArray(int a[], int n){
        if(n == 1) return a[0];
        return a[n-1]+=sumArray(a, n-1);
    }
    
    // bai 4
    public static int ispalindrome(char[] a, int n){
        return ispalindromeHelper(a, 0, n - 1);
    }
    
    public static int ispalindromeHelper(char []a, int start, int end){
        if(start >= end) return 1;
        if(a[start] != a[end]) return 0;
        return ispalindromeHelper(a, start+1, end-1);
    }
    
    // bai 5
    public static int binarySearch(int[] a, int target, int left, int right){
        if (left > right) return -1;
        int mid = left + (right-left)/2;
        if(a[mid] == target) return mid;
        if(a[mid] > target) return binarySearch(a, target, left, mid - 1);
        return binarySearch(a, target, mid + 1, right);
    }
    
    // bai 6
    public static int GCD(int a, int b){
        if(b == 0) return a;
        return GCD(b, a%b);
    }
    
    // bai 7
    public static int power(int base, int exp){
        if(exp == 0) return 1;
        return base * power(base, exp -1);
    }
    
    // bai 8
    public static int fact(int n){
        if(n == 1) return 1;
        return n * fact(n-1);
    }
    
    // bai 9
    public static int fab(int n){
        if (n <= 1) return n;
        return fab(n-1) + fab(n-2);
    }
    
    // bai 10
    public static double addReciprocals(int n){
        if(n == 1) return 1.0;
        return 1.0/n + addReciprocals(n-1);
    }

    
    // bai 12
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
    // bai 1
        System.out.println("Enter n:");
        int n = sc.nextInt();
        System.out.println(sum(n));
        
    // bai 2
        System.out.println("Enter size2 of Array:");
        int size = sc.nextInt();
        int []a = new int[size];
        
        System.out.println("Enter elements of Array:");
        for(int i =0; i<size ;i++){
            a[i] = sc.nextInt();
    
        }
        System.out.println("result: "+findMin(a, size));
    
    // bai 3
        System.out.println("Enter size3 of Array:");
        int size3 = sc.nextInt();
        int []a3 = new int[size3]; 
        
        System.out.println("Enter elements of Array:");
        for(int i =0; i<size3 ;i++){
        a3[i] = sc.nextInt();
        }
        
        System.out.println("result: "+sumArray(a3, size3));
        
    // bai 4
        System.out.println("Enter a string to check palindrome:");
        String str = sc.next();
        char[] charArray = str.toCharArray();  // Chuyển chuỗi sang mảng ký tự
        int result = ispalindrome(charArray, charArray.length);
        if (result == 1) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
    
    // bai 5
        System.out.println("Enter the size of array: ");
        int size5 = sc.nextInt();
        int b5[] = new int[size5];
        
        System.out.println("Enter the elements of array: ");
        for(int i=0; i < size5; i++){
            b5[i] = sc.nextInt();
        }
        
        System.out.println("Enter the targer to find: ");
        int target = sc.nextInt();
        
        int index = binarySearch(b5, target, 0, size5-1);
        if(index == -1){
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + index);
        }
        
    // bai 6
        System.out.println("Enter number 1: ");
        int b1 = sc.nextInt();
        System.out.println("Enter number 2: ");
        int b2 = sc.nextInt();
        
        System.out.println("result 6: " + GCD(b1,b2));
        
        
    // bai 7
        System.out.println("Enter number of base:");
        int base = sc.nextInt();
        System.out.println("Enter number of exp");
        int exp = sc.nextInt();
        System.out.println("result 7: " + power(base,exp));
        
    // bai 8
        System.out.println("Enter Factorial: ");
        int b8 = sc.nextInt();
        System.out.println("result 8: " + fact(b8));
        
    // bai 9
        System.out.println("Enter fibonacci: ");
        int b9 = sc.nextInt();
        System.out.println("Result: " + fab(b9));
        
    // bai 10
        System.out.println("Enter Reciprocals: ");
        int b10 = sc.nextInt();
        System.out.println("Result b10" + addReciprocals(b10));
        

}}
