/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import object.Ram;

public class RamManager {

    private List<Ram> ramItems;
    private String fileName;

    public RamManager(String fileName) {
        this.ramItems = new ArrayList<>();
        this.fileName = fileName;
        loadFromFile();
    }
public void loadFromFile() {
        ramItems.clear(); // Clear current items before loading from file

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the header line (if present)
            reader.readLine(); // This reads the header and discards it

            while ((line = reader.readLine()) != null) {
                // Use a regex to split by commas with or without spaces
                String[] parts = line.split("\\s*,\\s*");
                if (parts.length == 7) {
                    String code = parts[0];
                    String type = parts[1];
                    String bus = parts[2];
                    String brand = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    String productionMonthYear = parts[5];
                    boolean active = parts[6].equalsIgnoreCase("active");

                    Ram item = new Ram(code, type, bus, brand, quantity, productionMonthYear, active);
                    ramItems.add(item); // Add item to the list
                    System.out.println("Loaded RAM item: " + code + " successfully.");
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.printf("%-8s %-6s %-8s %-12s %-8s %-12s %-8s%n",
                    "Code", "Type", "Bus", "Brand", "Qty", "Prod.Date", "Active");

            for (Ram item : ramItems) {
                String activeStatus = item.isActive() ? "active" : "inactive";
                writer.printf("%-8s %-6s %-8s %-12s %-8d %-12s %-8s%n",
                        item.getCode(), item.getType(), item.getBus(), item.getBrand(),
                        item.getQuantity(), item.getProductionMonthYear(), activeStatus);
            }

            System.out.println("Data saved to " + fileName + " in table format.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    
    public void addRamItem(Ram ramItem) {
        if (isCodeUnique(ramItem.getCode())) {
            ramItems.add(ramItem);
            System.out.println("RAM đã được thêm thành công.");
        } else {
            System.out.println("Mã RAM đã tồn tại. Vui lòng thử lại.");
        }
    }

    private boolean isCodeUnique(String code) {
        for (Ram ramItem : ramItems) {
            if (ramItem.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    public List<Ram> searchByType(String type) {
        List<Ram> matchingItems = new ArrayList<>();
        for (Ram ramItem : ramItems) {
            if (ramItem.getType().equals(type) && ramItem.isActive()) {
                matchingItems.add(ramItem);
            }
        }
        return matchingItems;
    }

    public List<Ram> searchByBus(String bus) {
        List<Ram> matchingItems = new ArrayList<>();
        for (Ram ramItem : ramItems) {
            if (ramItem.getType().equals(bus) && ramItem.isActive()) {
                matchingItems.add(ramItem);
            }
        }
        return matchingItems;
    }

    public List<Ram> searchByBrand(String brand) {
        List<Ram> matchingItems = new ArrayList<>();
        for (Ram ramItem : ramItems) {
            if (ramItem.getType().equals(brand) && ramItem.isActive()) {
                matchingItems.add(ramItem);
            }
        }
        return matchingItems;
    }

    public Ram findRamItem(String code) {
        for (Ram ramItem : ramItems) {
            if (ramItem.getCode().equals(code)) {
                return ramItem;
            }
        }
        return null;
    }

    public void updateRamItem(Ram ramItem) {
        Ram existingItem = findRamItem(ramItem.getCode());
        if (existingItem != null) {
            existingItem.setType(ramItem.getType());
            existingItem.setBus(ramItem.getBus());
            existingItem.setBrand(ramItem.getBrand());
            existingItem.setQuantity(ramItem.getQuantity());
            existingItem.setProductionMonthYear(ramItem.getProductionMonthYear());
            saveToFile();
        }
    }

    public void deleteRamItem(String code) {
        Ram ramItem = findRamItem(code);
        if (ramItem != null) {
            ramItem.setActive(false);
        }
    }

    public List<Ram> getAllActiveItems() {
        List<Ram> activeItems = new ArrayList<>();
        for (Ram ramItem : ramItems) {
            if (ramItem.isActive()) {
                activeItems.add(ramItem);
            }
        }
        return activeItems;
    }

}
