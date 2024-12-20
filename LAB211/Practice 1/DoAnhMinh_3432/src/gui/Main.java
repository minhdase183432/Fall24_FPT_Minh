import gui.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import manager.ProductManager;
import object.Product;

public class Main {

    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            Menu.displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    searchProductByName(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    saveProductsToFile();
                    break;
                case 6:
                    printAllProducts();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
        scanner.close();
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter brand ID: ");
        String brandId = scanner.nextLine();
        System.out.print("Enter category ID: ");
        String categoryId = scanner.nextLine();
        System.out.print("Enter model year: ");
        int modelYear = getIntegerInput(scanner, "Invalid model year. Please enter a valid year.");
        System.out.print("Enter list price: ");
        double listPrice = getDoubleInput(scanner, "Invalid list price. Please enter a valid price.");

        try {
            Product product = ProductManager.addProduct(id, name, brandId, categoryId, modelYear, listPrice);
            products.add(product);
            System.out.println("Product added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchProductByName(Scanner scanner) {
        System.out.print("Enter search string: ");
        String searchString = scanner.nextLine();
        List<Product> result = ProductManager.searchProductsByName(products, searchString);
        ProductManager.displayProducts(result);
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new name (leave blank if no change): ");
        String name = scanner.nextLine();
        System.out.print("Enter new brand ID (leave blank if no change): ");
        String brandId = scanner.nextLine();
        System.out.print("Enter new category ID (leave blank if no change): ");
        String categoryId = scanner.nextLine();
        System.out.print("Enter new model year (leave blank if no change): ");
        String modelYearStr = scanner.nextLine();
        System.out.print("Enter new list price (leave blank if no change): ");
        String listPriceStr = scanner.nextLine();

        Integer modelYear = modelYearStr.isEmpty() ? null : Integer.parseInt(modelYearStr);
        Double listPrice = listPriceStr.isEmpty() ? null : Double.parseDouble(listPriceStr);

        boolean success = ProductManager.updateProduct(products, id, name, brandId, categoryId, modelYear, listPrice);
        ProductManager.displayUpdateResult(success);
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        String id = scanner.nextLine();
        ProductManager.deleteProduct(products, id);
    }

    private static void saveProductsToFile() {
        ProductManager.saveProductsToFile(products, "Product.txt");
    }

    private static void printAllProducts() {
        List<Product> loadedProducts = ProductManager.loadProductsFromFile("Product.txt");
        ProductManager.sortProducts(loadedProducts);
        ProductManager.displayProducts(loadedProducts);
    }

    private static int getIntegerInput(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}