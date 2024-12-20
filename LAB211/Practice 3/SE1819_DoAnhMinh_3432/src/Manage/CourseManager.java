/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;
import Object.Course;
import Object.Topic;
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
public class CourseManager {
    private List<Course> courses = new ArrayList<>();
    TopicManager topicManager = new TopicManager();  // Ensure this is done before calling getTopicByID
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");



    public void addCourse(Course course) {
        // Check for duplicate course ID
        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                System.out.println("Duplicate course ID. Cannot add.");
                return;
            }
        }
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    public void updateCourse(String courseID, String newName, String newType, String newTitle, double newTuitionFee) {
        for (Course c : courses) {
            if (c.getCourseID().equals(courseID)) {
                if (!newName.isEmpty()) c.setName(newName);
                if (!newType.isEmpty()) c.setType(newType);
                if (!newTitle.isEmpty()) c.setTitle(newTitle);
                if (newTuitionFee > 0) c.setTuitionFee(newTuitionFee);
                System.out.println("Course updated successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void deleteCourse(String courseID) {
        courses.removeIf(c -> c.getCourseID().equals(courseID));
        System.out.println("Course deleted successfully.");
    }

    public void displayAllCourses() {
        courses.stream().sorted((c1, c2) -> c1.getBeginDate().compareTo(c2.getBeginDate())).forEach(System.out::println);
    }
    
        public Course getCourseByID(String courseID) {
        for (Course c : courses) {
            if (c.getCourseID().equals(courseID)) {
                return c;
            }
        }
        return null; // Return null if the course is not found
    }
        
        public void searchCourseByName(String name) {
        boolean found = false;
        for (Course c : courses) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses found with the name: " + name);
        }
    }
        
    public void saveData(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("CourseID  |  Name  |  Type  |  Title  |  BeginDate  |  EndDate  |  TuitionFee  |  TopicID");  // Header
            for (Course course : courses) {
                writer.printf("%s  +|+  %s  +|+  %s  +|+  %s  +|+  %s  +|+  %s  +|+  %.2f  +|+  %s%n", course.getCourseID(), course.getName(), course.getType(),
                        course.getTitle(), course.getBeginDate(), course.getEndDate(), course.getTuitionFee(), course.getTopic().getTopicID());
            }
            System.out.println("Courses saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // Load courses from a CSV file
    public void loadData(String fileName) throws ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();  // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String courseID = data[0];
                String name = data[1];
                String type = data[2];
                String title = data[3];
                String beginDate = data[4];
                String endDate = data[5];
                double tuitionFee = Double.parseDouble(data[6]);
                String topicID = data[7];  // You need to link this with the existing topics

                // You will need to retrieve the actual Topic object based on topicID.
                // For now, assume you can get it with a method like getTopicByID.
                // Assume topicManager is initialized earlier and passed into this method
Topic topic = topicManager.getTopicByID(topicID);  // Correct way to call the method on an instance

    courses.add(new Course(courseID, name, type, title, sdf.parse(beginDate), sdf.parse(endDate), tuitionFee, topic));

            }
            System.out.println("Courses loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }
}
