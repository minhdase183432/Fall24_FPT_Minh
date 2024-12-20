/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author admin
 */
import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable{
    private static final long serialVersionUID = 1L;
    private String courseID;
    private String name;
    private String type;  // online or offline
    private String title;
    private Date beginDate;
    private Date endDate;
    private double tuitionFee;
    private Topic topic;  // Associated topic

    public Course(String courseID, String name, String type, String title, Date beginDate, Date endDate, double tuitionFee, Topic topic) {
        this.courseID = courseID;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
    }

    // Getters and Setters
    public String getCourseID() { return courseID; }
    public void setCourseID(String courseID) { this.courseID = courseID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getBeginDate() { return beginDate; }
    public void setBeginDate(Date beginDate) { this.beginDate = beginDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public double getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(double tuitionFee) { this.tuitionFee = tuitionFee; }
    public Topic getTopic() { return topic; }
    public void setTopic(Topic topic) { this.topic = topic; }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Name: " + name + ", Type: " + type + ", Title: " + title + ", Start: " + beginDate + ", End: " + endDate + ", Tuition Fee: " + tuitionFee;
    }
    
    
}

