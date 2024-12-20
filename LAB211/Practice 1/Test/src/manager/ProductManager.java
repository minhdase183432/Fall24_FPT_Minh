/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import object.Brand;
import object.Category;
import object.Product;

/**
 *
 * @author msi2k
 */
public class ProductManager {

    private List<Product> products = new ArrayList<>();
    private List<Brand> brands;
    private List<Category> categories;

    public ProductManager(String brandFile, String categoryFile) throws IOException {
        this.brands = FileManager.readBrands(brandFile);
        this.categories = FileManager.readCategories(categoryFile);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> searchProductByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                result.add(product);
            }
        }
        return result;
    }

    public boolean updateProduct(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                if (!name.isEmpty()) {
                    product.setName(name);
                }
                if (!brandId.isEmpty()) {
                    product.setBrandId(brandId);
                }
                if (!categoryId.isEmpty()) {
                    product.setCategoryId(categoryId);
                }
                if (modelYear > 0) {
                    product.setModelYear(modelYear);
                }
                if (listPrice > 0) {
                    product.setListPrice(listPrice);
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        return products.removeIf(product -> product.getId().equals(id));
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getBrandId() + "," + product.getCategoryId() + "," + product.getModelYear() + "," + product.getListPrice());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        products.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Product product = new Product(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
                    products.add(product);
                }
            }
        }
    }

    public void printProducts() {
        products.sort(Comparator.comparingDouble(Product::getListPrice).reversed().thenComparing(Product::getName));
        for (Product product : products) {
            System.out.println(product.getId() + " " + product.getName() + " " + product.getBrandId() + " " + product.getCategoryId() + " " + product.getModelYear() + " " + product.getListPrice());
        }
    }
}
