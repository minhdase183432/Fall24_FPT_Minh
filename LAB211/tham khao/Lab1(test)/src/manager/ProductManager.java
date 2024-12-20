package manager;

import java.util.ArrayList;
import java.util.Scanner;
import object.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import object.Brand;
import object.Category;

public class ProductManager {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Brand> brands = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();

    // Load brands from Brand.txt
    public void loadBrands() {
        brands.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("Brand.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Brand brand = new Brand(parts[0], parts[1], parts[2]);
                brands.add(brand);
            }
        } catch (IOException e) {
 System.out.println("Error loading brands from file: " + new java.io.File("Brand.txt").getAbsolutePath() + " - " + e.getMessage());        }
    }

    // Load categories from Category.txt
    public void loadCategories() {
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

    // Check if brand ID exists
    public boolean isValidBrandId(String brandId) {
        for (Brand brand : brands) {
            if (brand.getId().equals(brandId)) {
                return true;
            }
        }
        return false;
    }

    // Check if category ID exists
    public boolean isValidCategoryId(String categoryId) {
        for (Category category : categories) {
            if (category.getId().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }

    // Generate unique ID
    private String generateId() {
        return "P" + (products.size() + 1);
    }

    // Add a new product
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        // Load brands and categories
        loadBrands();
        loadCategories();

        // Get product details from user
        String id = generateId();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        String brandId;
        do {
            System.out.print("Enter brand id: ");
            brandId = scanner.nextLine();
            if (!isValidBrandId(brandId)) {
                System.out.println("Invalid brand Id. Please try again.");
            }
        } while (!isValidBrandId(brandId));

        String categoryId;
        do {
            System.out.print("Enter category id: ");
            categoryId = scanner.nextLine();
            if (!isValidCategoryId(categoryId)) {
                System.out.println("Invalid category Id. Please try again.");
            }
        } while (!isValidCategoryId(categoryId));

        int modelYear = 0;
        boolean validYear = false;
        while (!validYear) {
            try {
                System.out.print("Enter model year: ");
                modelYear = Integer.parseInt(scanner.nextLine());
                if (modelYear >= 1900) {
                    validYear = true;
                } else {
                    System.out.println("Model year must be 1900 or later.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid year.");
            }
        }

        double listPrice = 0.0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter list price: ");
                listPrice = Double.parseDouble(scanner.nextLine());
                if (listPrice > 0) {
                    validPrice = true;
                } else {
                    System.out.println("List price must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid price.");
            }
        }

        // Validate inputs
        if (name.isEmpty()) {
            System.out.println("Invalid input! Product name cannot be empty.");
            return;
        }

        // Add new product to the list
        Product product = new Product(id, name, brandId, categoryId, modelYear, listPrice);
        products.add(product);
        System.out.println("Product added successfully.");
    }

    // Display all products
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Search product by name
    public void searchProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchString = scanner.nextLine();

        ArrayList<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchString.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        if (foundProducts.isEmpty()) {
            System.out.println("No products found with the given name.");
        } else {
            System.out.println("Found Products:");
            for (Product product : foundProducts) {
                System.out.println(product);
            }
        }
    }

    // Update a product
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id to update: ");
        String id = scanner.nextLine();

        Product productToUpdate = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productToUpdate = product;
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter new product name (leave blank to keep the same): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            productToUpdate.setName(newName);
        }

        // Repeat for other fields (brandId, categoryId, etc.)

        System.out.println("Product updated successfully.");
    }

    // Delete a product
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id to delete: ");
        String id = scanner.nextLine();

        Product productToDelete = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete == null) {
            System.out.println("Product not found!");
            return;
        }

        products.remove(productToDelete);
        System.out.println("Product deleted successfully.");
    }

    // Save products to file
    public void saveProductsToFile() {
        try (FileWriter writer = new FileWriter("Product.txt")) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getBrandId() + ","
                        + product.getCategoryId() + "," + product.getModelYear() + "," + product.getListPrice() + "\n");
            }
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products to file.");
        }
    }

    // Load products from file
    public void loadProductsFromFile() {
        products.clear();
        loadBrands();
        loadCategories();
        try (BufferedReader reader = new BufferedReader(new FileReader("Product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Product product = new Product(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                        Double.parseDouble(parts[5]));
                products.add(product);
            }
            System.out.println("Products loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading products from file.");
        }
    }

    // Get brand name by ID
    private String getBrandNameById(String brandId) {
        for (Brand brand : brands) {
            if (brand.getId().equals(brandId)) {
                return brand.getName();
            }
        }
        return "Unknown Brand";
    }

    // Get category name by ID
    private String getCategoryNameById(String categoryId) {
        for (Category category : categories) {
            if (category.getId().equals(categoryId)) {
                return category.getName();
            }
        }
        return "Unknown Category";
    }
}
