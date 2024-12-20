/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import java.io.Serializable;


/**
 *
 * @author admin
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    private String topicID;
    private String name;
    private String type;  // long-term or short-term
    private String title;
    private int duration; // duration in days

    public Topic(String topicID, String name, String type, String title, int duration) {
        this.topicID = topicID;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    // Getters and Setters
    public String getTopicID() { return topicID; }
    public void setTopicID(String topicID) { this.topicID = topicID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Topic ID: " + topicID + ", Name: " + name + ", Type: " + type + ", Title: " + title + ", Duration: " + duration + " days";
    }
}

