/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesprogrammanagement;
import Manage.CourseManager;
import Manage.LearnerManager;
import Manage.TopicManager;
import Object.Course;
import Object.Learner;
import Object.Topic;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class CoursesProgramManagement {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws ParseException {
        TopicManager topicManager = new TopicManager();
        CourseManager courseManager = new CourseManager();
        LearnerManager learnerManager = new LearnerManager();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
                // Load data on startup
        topicManager.loadData("topics.dat");
        courseManager.loadData("courses.dat");
        learnerManager.loadData("learners.dat");

        boolean running = true;
        while (running) {
            System.out.println("===== Course Management System =====");
            System.out.println("1. Manage Topics");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Learners");
            System.out.println("4. Search Information");
            System.out.println("5. Save Data");
            System.out.println("6. Quit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    manageTopics(topicManager, scanner);
                    break;
                case 2:
                    manageCourses(courseManager, topicManager, scanner, sdf);
                    break;
                case 3:
                    manageLearners(learnerManager, courseManager, scanner, sdf);
                    break;
                case 4:
                    searchInformation(topicManager, courseManager, scanner);
                    break;
                case 5:
                                        // Save all data
                    topicManager.saveData("topics.dat");
                    courseManager.saveData("courses.dat");
                    learnerManager.saveData("learners.dat");
                    System.out.println("Data saved successfully.");
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageTopics(TopicManager topicManager, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Topics ---");
            System.out.println("1. Add Topic");
            System.out.println("2. Update Topic");
            System.out.println("3. Delete Topic");
            System.out.println("4. Display All Topics");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Topic ID: ");
                    String topicID = scanner.nextLine();
                    System.out.print("Enter Topic Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Topic Type (long/short): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Topic Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Topic Duration (days): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    topicManager.addTopic(new Topic(topicID, name, type, title, duration));
                    break;
                case 2:
                    System.out.print("Enter Topic ID to update: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter new name (leave blank to keep the same): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new type (leave blank to keep the same): ");
                    String newType = scanner.nextLine();
                    System.out.print("Enter new title (leave blank to keep the same): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new duration (enter 0 to keep the same): ");
                    int newDuration = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    topicManager.updateTopic(updateID, newName, newType, newTitle, newDuration);
                    break;
                case 3:
                    System.out.print("Enter Topic ID to delete: ");
                    String deleteID = scanner.nextLine();
                    topicManager.deleteTopic(deleteID);
                    break;
                case 4:
                    topicManager.displayAllTopics();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageCourses(CourseManager courseManager, TopicManager topicManager, Scanner scanner, SimpleDateFormat sdf) throws ParseException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Courses ---");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display All Courses");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    String courseID = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Course Type (online/offline): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Course Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Begin Date (dd/MM/yyyy): ");
                    String beginDateStr = scanner.nextLine();
                    System.out.print("Enter End Date (dd/MM/yyyy): ");
                    String endDateStr = scanner.nextLine();
                    System.out.print("Enter Tuition Fee: ");
                    double tuitionFee = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Topic ID for this Course: ");
                    String topicID = scanner.nextLine();
                    
                    Topic topic = topicManager.getTopicByID(topicID);
                    
                    if (topic != null) {
                        courseManager.addCourse(new Course(courseID, name, type, title, sdf.parse(beginDateStr), sdf.parse(endDateStr), tuitionFee, topic));
                    } else {
                        System.out.println("Topic ID not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Course ID to update: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter new name (leave blank to keep the same): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new type (leave blank to keep the same): ");
                    String newType = scanner.nextLine();
                    System.out.print("Enter new title (leave blank to keep the same): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new tuition fee (enter 0 to keep the same): ");
                    double newTuitionFee = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    courseManager.updateCourse(updateID, newName, newType, newTitle, newTuitionFee);
                    break;
                case 3:
                    System.out.print("Enter Course ID to delete: ");
                    String deleteID = scanner.nextLine();
                    courseManager.deleteCourse(deleteID);
                    break;
                case 4:
                    courseManager.displayAllCourses();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageLearners(LearnerManager learnerManager, CourseManager courseManager, Scanner scanner, SimpleDateFormat sdf) throws ParseException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Learners ---");
            System.out.println("1. Add Learner to Course");
            System.out.println("2. Enter Learner Score");
            System.out.println("3. Display All Learners");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Learner ID: ");
                    String learnerID = scanner.nextLine();
                    System.out.print("Enter Learner Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
                    String dobStr = scanner.nextLine();
                    System.out.print("Enter Course ID for this Learner: ");
                    String courseID = scanner.nextLine();
                    
                    Course course = courseManager.getCourseByID(courseID);
                    
                    if (course != null) {
                        learnerManager.addLearner(new Learner(learnerID, name, sdf.parse(dobStr), 0.0, course));
                    } else {
                        System.out.println("Course ID not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Learner ID to update score: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter new score: ");
                    double newScore = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    learnerManager.updateLearnerScore(updateID, newScore);
                    break;
                case 3:
                    learnerManager.displayAllLearners();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchInformation(TopicManager topicManager, CourseManager courseManager, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Search Information ---");
            System.out.println("1. Search Topic by Name");
            System.out.println("2. Search Course by Name");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Topic Name to search: ");
                    String topicName = scanner.nextLine();
                    topicManager.searchTopicByName(topicName);
                    break;
                case 2:
                    System.out.print("Enter Course Name to search: ");
                    String courseName = scanner.nextLine();
                    courseManager.searchCourseByName(courseName);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}