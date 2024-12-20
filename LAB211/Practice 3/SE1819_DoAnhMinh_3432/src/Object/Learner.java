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

public class Learner implements Serializable{
    private static final long serialVersionUID = 1L;
    private String learnerID;
    private String name;
    private Date dateOfBirth;
    private double score;
    private Course course;

    public Learner(String learnerID, String name, Date dateOfBirth, double score, Course course) {
        this.learnerID = learnerID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.score = score;
        this.course = course;
    }

    // Getters and Setters
    public String getLearnerID() { return learnerID; }
    public void setLearnerID(String learnerID) { this.learnerID = learnerID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    @Override
    public String toString() {
        return "Learner ID: " + learnerID + ", Name: " + name + ", DOB: " + dateOfBirth + ", Score: " + score;
    }
}

