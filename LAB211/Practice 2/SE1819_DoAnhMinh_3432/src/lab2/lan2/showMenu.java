/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.lan2;

import Management.RAMManagementSystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author admin
 */
class Menu {
    private RAMManagementSystem managementSystem;
    private Scanner scanner;

    public Menu(RAMManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
        this.scanner = new Scanner(System.in);
    }
    
    
public void showMenu() throws IOException, ClassNotFoundException {
    while (true) {
        System.out.println("----- Laptop RAM Management System -----");
        System.out.println("1. Add Item");
        System.out.println("2. Search Items");  // Combined search option
        System.out.println("3. Update Item");
        System.out.println("4. Delete Item");
        System.out.println("5. Show All Items");
        System.out.println("6. Save Data");
        System.out.println("7. Load Data");
        System.out.println("8. Quit");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addItem();
                break;
            case 2:
                searchItems(); // Call combined search method
                break;
            case 3:
                updateItem();
                break;
            case 4:
                deleteItem();
                break;
            case 5:
                managementSystem.showAllItems();
                break;
            case 6:
                saveData();
                break;
            case 7:
                loadData();
                break;
            case 8:
                System.out.println("Exiting the program...");
                return;
            default:
                System.out.println("Invalid option. Please select again.");
        }
    }
}


    private void addItem() {
        System.out.print("Enter code: ");
        String code = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter bus: ");
        String bus = scanner.nextLine();
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter production month and year (MM/YYYY): ");
        String productionMonthYear = scanner.nextLine();
        managementSystem.addItem(code, type, bus, brand, quantity, productionMonthYear);
    }

private void searchItems() {
    managementSystem.searchItems();
}


    private void updateItem() {
        System.out.print("Enter code to update: ");
        String code = scanner.nextLine();
        System.out.print("Enter new type (leave blank to keep current): ");
        String type = scanner.nextLine();
        System.out.print("Enter new bus (leave blank to keep current): ");
        String bus = scanner.nextLine();
        System.out.print("Enter new brand (leave blank to keep current): ");
        String brand = scanner.nextLine();
        System.out.print("Enter new quantity (or 0 to keep current): ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        managementSystem.updateItem(code, type, bus, brand, quantity);
    }

    private void deleteItem() {
        System.out.print("Enter code to delete: ");
        String code = scanner.nextLine();
        managementSystem.deleteItem(code);
    }

private void saveData() {
    try {
        managementSystem.saveToFile();
    } catch (IOException e) {
        System.out.println("Error saving data.");
    }
}

private void loadData() {
    try {
        managementSystem.loadFromFile();
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading data.");
    }
}

}

           