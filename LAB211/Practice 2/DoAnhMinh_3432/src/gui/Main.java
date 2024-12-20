/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import java.util.Scanner;
import manager.RamManager;
import object.Ram;

/**
 *
 * @author msi2k
 */
public class Main {

    public static void main(String[] args) {
        RamManager ramManager = new RamManager("ram_data.dat");
        ramManager.loadFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------RAM Management System-------");
            System.out.println("| 1. Thêm RAM mới                  |");
            System.out.println("| 2. Tìm kiếm RAM theo loại        |");
            System.out.println("| 3. Tìm kiếm RAM theo tốc độ bus  |");
            System.out.println("| 4. Tìm kiếm RAM theo thương hiệu |");
            System.out.println("| 5. Cập nhật thông tin RAM        |");
            System.out.println("| 6. Xóa RAM                       |");
            System.out.println("| 7. Hiển thị tất cả RAM           |");
            System.out.println("| 8. Thoát                         |");
            System.out.println("------------------------------------");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRamItem(ramManager, scanner);
                    break;
                case 2:
                    searchByType(ramManager, scanner);
                    break;
                case 3:
                    searchByBus(ramManager, scanner);
                    break;
                case 4:
                    searchByBrand(ramManager, scanner);
                    break;
                case 5:
                    updateRamItem(ramManager, scanner);
                    break;
                case 6:
                    deleteRamItem(ramManager, scanner);
                    break;
                case 7:
                    displayAllRAM(ramManager);
                    break;
                case 8:
                    System.out.println("Tạm biệt!");
                    ramManager.saveToFile();
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void addRamItem(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập mã RAM: ");
        String code = scanner.nextLine();
        System.out.print("Nhập loại RAM: ");
        String type = scanner.nextLine();
        System.out.print("Nhập tốc độ bus: ");
        String bus = scanner.nextLine();
        System.out.print("Nhập thương hiệu: ");
        String brand = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Nhập tháng và năm sản xuất (MM/YYYY): ");
        String productionMonthYear = scanner.nextLine();

        Ram ramItem = new Ram(code, type, bus, brand, quantity, productionMonthYear, true);
        ramManager.addRamItem(ramItem);

    }

    private static void searchByType(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập loại RAM: ");
        String type = scanner.nextLine();

        List<Ram> matchingItems = ramManager.searchByType(type);
        if (matchingItems.isEmpty()) {
            System.out.println("Không tìm thấy RAM phù hợp.");
        } else {
            System.out.println("RAM phù hợp:");
            for (Ram ramItem : matchingItems) {
                System.out.println("Mã RAM: " + ramItem.getCode());
                System.out.println("Loại RAM: " + ramItem.getType());
                System.out.println("Tháng và năm sản xuất: " + ramItem.getProductionMonthYear());
                System.out.println("Số lượng: " + ramItem.getQuantity());
                System.out.println();
            }
        }
    }

    private static void searchByBus(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập tốc độ bus: ");
        String bus = scanner.nextLine();

        List<Ram> matchingItems = ramManager.searchByBus(bus);
        if (matchingItems.isEmpty()) {
            System.out.println("Không tìm thấy RAM phù hợp.");
        } else {
            System.out.println("RAM phù hợp:");
            for (Ram ramItem : matchingItems) {
                System.out.println("Mã RAM: " + ramItem.getCode());
                System.out.println("Tốc độ bus: " + ramItem.getBus());
                System.out.println("Tháng và năm sản xuất: " + ramItem.getProductionMonthYear());
                System.out.println("Số lượng: " + ramItem.getQuantity());
                System.out.println();
            }
        }
    }

    private static void searchByBrand(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập thương hiệu: ");
        String brand = scanner.nextLine();

        List<Ram> matchingItems = ramManager.searchByBrand(brand);
        if (matchingItems.isEmpty()) {
            System.out.println("Không tìm thấy RAM phù hợp.");
        } else {
            System.out.println("RAM phù hợp:");
            for (Ram ramItem : matchingItems) {
                System.out.println("Mã RAM: " + ramItem.getCode());
                System.out.println("Loại RAM: " + ramItem.getType());
                System.out.println("Tốc độ bus: " + ramItem.getBus());
                System.out.println("Thương hiệu: " + ramItem.getBrand());
                System.out.println("Số lượng: " + ramItem.getQuantity());
                System.out.println("Tháng và năm sản xuất: " + ramItem.getProductionMonthYear());
                System.out.println();
            }
        }
    }

    private static void updateRamItem(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập mã RAM: ");
        String code = scanner.nextLine();

        Ram ramItem = ramManager.findRamItem(code);
        if (ramItem != null) {
            System.out.print("Nhập loại RAM mới (hoặc nhấn Enter để giữ nguyên): ");
            String type = scanner.nextLine();
            if (!type.isEmpty()) {
                ramItem.setType(type);
            }

            System.out.print("Nhập tốc độ bus mới (hoặc nhấn Enter để giữ nguyên): ");
            String bus = scanner.nextLine();
            if (!bus.isEmpty()) {
                ramItem.setBus(bus);
            }

            System.out.print("Nhập thương hiệu mới (hoặc nhấn Enter để giữ nguyên): ");
            String brand = scanner.nextLine();
            if (!brand.isEmpty()) {
                ramItem.setBrand(brand);
            }

            System.out.print("Nhập số lượng mới (hoặc nhấn Enter để giữ nguyên): ");
            if (scanner.hasNextInt()) {
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                ramItem.setQuantity(quantity);
            }
           

            System.out.print("Nhập tháng và năm sản xuất mới (MM/YYYY) (hoặc nhấn Enter để giữ nguyên): ");
            String productionMonthYear = scanner.nextLine();
            if (!productionMonthYear.isEmpty()) {
                ramItem.setProductionMonthYear(productionMonthYear);
            }

            ramManager.updateRamItem(ramItem);
            System.out.println("Thông tin RAM đã được cập nhật thành công.");
        } else {
            System.out.println("RAM không tìm thấy.");
        }
    }

    private static void deleteRamItem(RamManager ramManager, Scanner scanner) {
        System.out.print("Nhập mã RAM: ");
        String code = scanner.nextLine();

        Ram ramItem = ramManager.findRamItem(code);
        if (ramItem != null) {
            System.out.print("Bạn có chắc chắn muốn xóa RAM này không? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                ramManager.deleteRamItem(code);
                System.out.println("RAM đã được xóa thành công.");
            }
        } else {
            System.out.println("RAM không tìm thấy.");
        }
    }

    private static void displayAllRAM(RamManager ramManager) {
        List<Ram> allItems = ramManager.getAllActiveItems();
        if (allItems.isEmpty()) {
            System.out.println("Không có RAM nào.");
        } else {
            System.out.println("Tất cả RAM:");
            for (Ram ramItem : allItems) {
                System.out.println(ramItem.getCode()+ "|" +ramItem.getType()+ "|" +ramItem.getBus() +"Mhz"+ "|" +ramItem.getQuantity()+ "cái"+ "|" +ramItem.getBrand()+ "|" +ramItem.getProductionMonthYear());
                System.out.println();
            }
        }
    }
}
