/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;
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
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author admin
 */
public class TopicManager {
    private List<Topic> topics = new ArrayList<>();


    public void addTopic(Topic topic) {
        // Check for duplicate topic ID
        for (Topic t : topics) {
            if (t.getTopicID().equals(topic.getTopicID())) {
                System.out.println("Duplicate topic ID. Cannot add.");
                return;
            }
        }
        topics.add(topic);
        System.out.println("Topic added successfully.");
    }

    public void updateTopic(String topicID, String newName, String newType, String newTitle, int newDuration) {
        for (Topic t : topics) {
            if (t.getTopicID().equals(topicID)) {
                if (!newName.isEmpty()) t.setName(newName);
                if (!newType.isEmpty()) t.setType(newType);
                if (!newTitle.isEmpty()) t.setTitle(newTitle);
                if (newDuration > 0) t.setDuration(newDuration);
                System.out.println("Topic updated successfully.");
                return;
            }
        }
        System.out.println("Topic not found.");
    }

    public void deleteTopic(String topicID) {
        topics.removeIf(t -> t.getTopicID().equals(topicID));
        System.out.println("Topic deleted successfully.");
    }

    public void displayAllTopics() {
        topics.stream().sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).forEach(System.out::println);
    }
    
        public Topic getTopicByID(String topicID) {
        for (Topic t : topics) {
            if (t.getTopicID().equals(topicID)) {
                return t;
            }
        }
        return null; // return null if the topic is not found
    }
        
        public void searchTopicByName(String name) {
        boolean found = false;
        for (Topic t : topics) {
            if (t.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No topics found with the name: " + name);
        }
    }
        
    public void saveData(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("TopicID,Name,Type,Title,Duration");  // Header
            for (Topic topic : topics) {
                writer.printf("%s,%s,%s,%s,%d%n", topic.getTopicID(), topic.getName(), topic.getType(), topic.getTitle(), topic.getDuration());
            }
            System.out.println("Topics saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving topics: " + e.getMessage());
        }
    }

    // Load topics from a CSV file
    public void loadData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();  // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String topicID = data[0];
                String name = data[1];
                String type = data[2];
                String title = data[3];
                int duration = Integer.parseInt(data[4]);

                topics.add(new Topic(topicID, name, type, title, duration));
            }
            System.out.println("Topics loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading topics: " + e.getMessage());
        }
    }
}
