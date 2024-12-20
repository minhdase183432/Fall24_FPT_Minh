/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Object.RAMItem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class RAMManagementSystem {
    private List<RAMItem> ramItems;
    
    public RAMManagementSystem(){
        this.ramItems = new ArrayList<>();
    }
    
    //Add an Item (70 LOC)
    public void addItem(String code, String type, String bus, String brand,
    int quantity, String productionMonthYear){
        for(RAMItem item : ramItems){
            if(item.getCode().equals(code)){
                System.out.println("Error: RAM code must be unique.");
                return;
            }
        }
        RAMItem newItem = new RAMItem(code, type, bus, brand, quantity, productionMonthYear);
        ramItems.add(newItem);
        System.out.println("RAM item add successfully.");
    }
    
    //Search Submenu (60 LOC)
public void searchItems() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Choose search criteria:");
    System.out.println("1. Type");
    System.out.println("2. Bus");
    System.out.println("3. Brand");
    System.out.println("Enter your choice:");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    String searchTerm = "";
    switch (choice) {
        case 1:
            System.out.print("Enter RAM type to search: ");
            searchTerm = scanner.nextLine();
            for (RAMItem item : ramItems) {
                if (item.getType().equalsIgnoreCase(searchTerm) && item.isActive()) {
                    System.out.println(item.getCode()+", "+item.getType()+", "+item.getProductionMonthYear()+", "+item.getQuantity());
                }
            }
            break;
        case 2:
            System.out.print("Enter RAM bus speed to search: ");
            searchTerm = scanner.nextLine();
            for (RAMItem item : ramItems) {
                if (item.getBus().equalsIgnoreCase(searchTerm) && item.isActive()) {
                    System.out.println(item.getCode()+", "+item.getBus()+", "+item.getProductionMonthYear()+", "+item.getQuantity());
                }
            }
            break;
        case 3:
            System.out.print("Enter RAM brand to search: ");
            searchTerm = scanner.nextLine();
            for (RAMItem item : ramItems) {
                if (item.getBrand().equalsIgnoreCase(searchTerm) && item.isActive()) {
                    System.out.println(item.getCode()+", "+item.getBrand()+", "+item.getProductionMonthYear()+", "+item.getQuantity());
                }
            }
            break;
        default:
            System.out.println("Invalid choice. Please select again.");
    }
}

    
    //Update Item Information (50 LOC):
    public void updateItem(String code, String newType, String newBus, String newBrand, int newQuantity){
        for(RAMItem item : ramItems){
            if(item.getCode().equals(code)){
                if(!newType.isEmpty()) item.setType(newType);
                if(!newBus.isEmpty()) item.setBus(newBus);
                if(!newBrand.isEmpty()) item.setBrand(newBrand);
                if(newQuantity > 0) item.setQuantity(newQuantity);
                System.out.println("RAM item updated successfully.");
                return;
            }
        }
        System.out.println("Error: RAM item not found.");
    }
    
    //Delete Item (50 LOC)
public void deleteItem(String code) {
    Scanner scanner = new Scanner(System.in);
    
    // Find the item to delete
    RAMItem itemToDelete = null;
    for (RAMItem item : ramItems) {
        if (item.getCode().equals(code) && item.isActive()) {
            itemToDelete = item;
            break;
        }
    }

    // If item not found, display an error
    if (itemToDelete == null) {
        System.out.println("Error: RAM item not found or already inactive.");
        return;
    }

    // Display confirmation message
    System.out.println("Are you sure you want to delete the following item?");
    System.out.println(itemToDelete);
    System.out.print("Enter 'yes' to confirm, or 'no' to cancel: ");
    
    String confirmation = scanner.nextLine();
    
    if (confirmation.equalsIgnoreCase("yes")) {
        // Mark item as inactive
        itemToDelete.setActive(false);
        System.out.println("RAM item marked as inactive.");
    } else {
        System.out.println("Deletion canceled.");
    }
}

    //Show All Items (30 LOC)
    public void showAllItems(){
        ramItems.stream()
                .filter(RAMItem :: isActive)
                .sorted(Comparator.comparing(RAMItem :: getType)
                                  .thenComparing(RAMItem :: getBus)
                                  .thenComparing(RAMItem :: getBrand))
                .forEach(System.out::println);
    }
    
    //Store Data to Files (100 LOC)
public void saveToFile() throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("RAMModules.dat"))) {
        oos.writeObject(ramItems); // Writing the list of RAM items to the file
        System.out.println("Data saved to RAMModules.dat file.");
    } catch (IOException e) {
        System.out.println("Error saving data to file: " + e.getMessage());
        throw e; // Rethrow the exception so it can be handled in the calling method
    }
}

        
@SuppressWarnings("unchecked")
public void loadFromFile() throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("RAMModules.dat"))) {
        ramItems = (List<RAMItem>) ois.readObject(); // Reading the list of RAM items from the file
        System.out.println("Data loaded from RAMModules.dat file.");
    } catch (FileNotFoundException e) {
        System.out.println("No previous data found. Starting fresh.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading data from file: " + e.getMessage());
        throw e;
    }
}

    
    //8. Quit Menu (10 LOC)
    public void quite(){
        System.out.println("Exiting the program ...");
    }
}