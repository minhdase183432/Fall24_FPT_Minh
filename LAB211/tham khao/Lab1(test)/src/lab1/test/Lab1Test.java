/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1.test;
import java.util.Scanner;
import manager.ProductManager;

/**
 *
 * @author admin
 */
public class Lab1Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ProductManager manager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nBike Store Management System:");
            System.out.println("1. Add product");
            System.out.println("2. Search product by name");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Save products to file");
            System.out.println("6. Load products from file");
            System.out.println("7. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manager.addProduct();
                    break;
                case 2:
                    manager.searchProductByName();
                    break;
                case 3:
                    manager.updateProduct();
                    break;
                case 4:
                    manager.deleteProduct();
                    break;
                case 5:
                    manager.saveProductsToFile();
                    break;
                case 6:
                    manager.loadProductsFromFile();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }

        scanner.close();
    }
}

