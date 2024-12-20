/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;
import Object.Course;
import Object.Learner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LearnerManager {
    private List<Learner> learners = new ArrayList<>();
    CourseManager courseManager = new CourseManager();  // Ensure this is done before calling getTopicByID
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");




    public void addLearner(Learner learner) {
        learners.add(learner);
        System.out.println("Learner added successfully.");
    }

    public void updateLearnerScore(String learnerID, double newScore) {
        for (Learner l : learners) {
            if (l.getLearnerID().equals(learnerID)) {
                l.setScore(newScore);
                System.out.println("Learner score updated successfully.");
                return;
            }
        }
        System.out.println("Learner not found.");
    }

    public void displayAllLearners() {
        learners.forEach(System.out::println);
    }
    
    public void saveData(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("LearnerID,Name,DateOfBirth,Score,CourseID");  // Header
            for (Learner learner : learners) {
                writer.printf("%s,%s,%s,%.2f,%s%n", learner.getLearnerID(), learner.getName(), learner.getDateOfBirth(),
                        learner.getScore(), learner.getCourse().getCourseID());
            }
            System.out.println("Learners saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving learners: " + e.getMessage());
        }
    }

    // Load learners from a CSV file
    public void loadData(String fileName) throws ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();  // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String learnerID = data[0];
                String name = data[1];
                String dateOfBirth = data[2];
                double score = Double.parseDouble(data[3]);
                String courseID = data[4];  // Link with actual course

                // Retrieve Course object from courseManager using courseID
                Course course = courseManager.getCourseByID(courseID);

                learners.add(new Learner(learnerID, name, sdf.parse(dateOfBirth), score, course));
            }
            System.out.println("Learners loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading learners: " + e.getMessage());
        }
    }
}

