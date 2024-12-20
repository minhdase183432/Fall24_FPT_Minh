/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import object.Brand;
import object.Category;

/**
 *
 * @author msi2k
 */
public class FileManager {

    public static List<Brand> readBrands(String filename) throws IOException {
        List<Brand> brands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    brands.add(new Brand(parts[0], parts[1], parts[2]));
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp: " + filename);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
        }
        return brands;
    }

    public static List<Category> readCategories(String filename) throws IOException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    categories.add(new Category(parts[0], parts[1]));
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp: " + filename);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
        }
        return categories;
    }
}
