
package gui;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import manager.ProductManager;
import object.Product;

public class Main {

    public static void main(String[] args) {
        try {
            // Đường dẫn đầy đủ đến các tệp
            String brandFilePath = "C:\\Users\\msi2k\\Documents\\FPT\\LAB211\\Practice 1\\Brand.txt";
            String categoryFilePath = "C:\\Users\\msi2k\\Documents\\FPT\\LAB211\\Practice 1\\Category.txt";

            ProductManager manager = new ProductManager(brandFilePath, categoryFilePath);
            Scanner scanner = new Scanner(System.in);
            String choice;

            do {
                System.out.println("1. Thêm sản phẩm");
                System.out.println("2. Tìm kiếm sản phẩm theo tên");
                System.out.println("3. Cập nhật sản phẩm");
                System.out.println("4. Xóa sản phẩm");
                System.out.println("5. Lưu sản phẩm vào tệp");
                System.out.println("6. In danh sách sản phẩm từ tệp");
                System.out.println("7. Thoát");
                System.out.print("Chọn chức năng: ");
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Nhập id sản phẩm: ");
                        String id = scanner.nextLine();
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = scanner.nextLine();
                        System.out.print("Nhập id thương hiệu: ");
                        String brandId = scanner.nextLine();
                        System.out.print("Nhập id danh mục: ");
                        String categoryId = scanner.nextLine();
                        System.out.print("Nhập năm sản xuất: ");
                        int modelYear = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nhập giá niêm yết: ");
                        double listPrice = Double.parseDouble(scanner.nextLine());

                        Product product = new Product(id, name, brandId, categoryId, modelYear, listPrice);
                        manager.addProduct(product);
                        System.out.println("Sản phẩm đã được thêm.");
                        break;
                    case "2":
                        System.out.print("Nhập tên sản phẩm cần tìm: ");
                        String searchName = scanner.nextLine();
                        List<Product> foundProducts = manager.searchProductByName(searchName);
                        if (foundProducts.isEmpty()) {
                            System.out.println("Không có sản phẩm nào.");
                        } else {
                            for (Product p : foundProducts) {
                                System.out.println(p.getId() + " " + p.getName() + " " + p.getBrandId() + " " + p.getCategoryId() + " " + p.getModelYear() + " " + p.getListPrice());
                            }
                        }
                        break;
                    case "3":
                        System.out.print("Nhập id sản phẩm cần cập nhật: ");
                        String updateId = scanner.nextLine();
                        System.out.print("Nhập tên mới (để trống nếu không thay đổi): ");
                        String newName = scanner.nextLine();
                        System.out.print("Nhập id thương hiệu mới (để trống nếu không thay đổi): ");
                        String newBrandId = scanner.nextLine();
                        System.out.print("Nhập id danh mục mới (để trống nếu không thay đổi): ");
                        String newCategoryId = scanner.nextLine();
                        System.out.print("Nhập năm sản xuất mới (0 nếu không thay đổi): ");
                        int newModelYear = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nhập giá niêm yết mới (0 nếu không thay đổi): ");
                        double newListPrice = Double.parseDouble(scanner.nextLine());

                        boolean updated = manager.updateProduct(updateId, newName, newBrandId, newCategoryId, newModelYear, newListPrice);
                        if (updated) {
                            System.out.println("Sản phẩm đã được cập nhật.");
                        } else {
                            System.out.println("Sản phẩm không tồn tại.");
                        }
                        break;
                    case "4":
                        System.out.print("Nhập id sản phẩm cần xóa: ");
                        String deleteId = scanner.nextLine();
                        boolean deleted = manager.deleteProduct(deleteId);
                        if (deleted) {
                            System.out.println("Sản phẩm đã được xóa.");
                        } else {
                            System.out.println("Sản phẩm không tồn tại.");
                        }
                        break;
                    case "5":
                        try {
                        manager.saveToFile("Product.txt");
                        System.out.println("Dữ liệu đã được lưu vào tệp.");
                    } catch (IOException e) {
                        System.out.println("Lỗi khi lưu tệp: " + e.getMessage());
                    }
                    break;
                    case "6":
                        try {
                        manager.loadFromFile("Product.txt");
                        manager.printProducts();
                    } catch (IOException e) {
                        System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
                    }
                    break;
                    case "7":
                        System.out.println("Thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } while (!choice.equals("7"));
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
        }
    }
}
