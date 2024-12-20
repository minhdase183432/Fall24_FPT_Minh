package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import object.Brand;
import object.Category;
import object.Product;

public class ProductManager {

    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Brand> brands = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();

    public void loadBrand() {
        brands.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("Brand.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Brand brand = new Brand(parts[0], parts[1], parts[2]);
                brands.add(brand);
            }
        } catch (IOException e) {
            System.out.println("Error loading brands from file: " + new File("Brand.txt").getAbsolutePath() + " - " + e.getMessage());
        }
    }

    public void loadCategory() {
        categories.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("Category.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Category category = new Category(parts[0], parts[1]);
                categories.add(category);
            }
        } catch (IOException e) {
            System.out.println("Error loading categories from file.");
        }
    }
    
    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private static boolean isValidId(String id, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length > 0 && parts[0].trim().equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return false;
    }

    private static void printFileData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath + " - " + e.getMessage());
        }
    }

    public static Product addProduct(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (!isValidId(brandId, "Brand.txt")) {
            throw new IllegalArgumentException("Invalid brand ID.");
        }

        if (!isValidId(categoryId, "Category.txt")) {
            throw new IllegalArgumentException("Invalid category ID.");
        }

        if (modelYear < 0) {
            throw new IllegalArgumentException("Invalid model year.");
        }

        if (listPrice < 0) {
            throw new IllegalArgumentException("Invalid list price.");
        }

        Product product = new Product(id, name, brandId, categoryId, modelYear, listPrice);
        products.add(product);
        return product;
    }

    public static List<Product> searchProductsByName(List<Product> products, String searchString) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchString.toLowerCase())) {
                result.add(product);
            }
        }
        
        return result;
    }

    public static void sortProductsByModelYear(List<Product> products) {
        products.sort(Comparator.comparingInt(Product::getModelYear));
    }

    public static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            sortProductsByModelYear(products);
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public static boolean updateProduct(List<Product> products, String id, String name, String brandId, String categoryId, Integer modelYear, Double listPrice) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                if (!name.isEmpty()) {
                    product.setName(name);
                }
                if (!brandId.isEmpty() && isValidId(brandId, "Brand.txt")) {
                    product.setBrandId(brandId);
                }
                if (!categoryId.isEmpty() && isValidId(categoryId, "Category.txt")) {
                    product.setCategoryId(categoryId);
                }
                if (modelYear != null && modelYear >= 1900 && modelYear <= 2100) {
                    product.setModelYear(modelYear);
                }
                if (listPrice != null && listPrice > 0) {
                    product.setListPrice(listPrice);
                }
                return true;
            }
        }
        return false;
    }

    public static void displayUpdateResult(boolean success) {
        if (success) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product does not exist.");
        }
    }

    public static boolean deleteProduct(List<Product> products, String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                System.out.println("Are you sure you want to delete this product? (y/n)");
                Scanner scanner = new Scanner(System.in);
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    products.remove(product);
                    System.out.println("Product deleted successfully!");
                    return true;
                } else {
                    System.out.println("Delete operation canceled.");
                    return false;
                }
            }
        }
        System.out.println("Product does not exist.");
        return false;
    }

    public static void saveProductsToFile(List<Product> products, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getBrandId() + ","
                        + product.getCategoryId() + "," + product.getModelYear() + "," + product.getListPrice());
                writer.newLine();
            }
            System.out.println("Products saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Product product = new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Double.parseDouble(data[5]));
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
        return products;
    }

    public static void sortProducts(List<Product> products) {
        products.sort(Comparator.comparingDouble(Product::getListPrice).reversed()
                .thenComparing(Product::getName));
    }
}
