/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

/**
 *
 * @author msi2k
 */
public class Employee implements Comparable<Employee> {
    private String id;
    private String name;
    private int level;

    public Employee(String id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(Employee other) {
        return this.id.compareTo(other.id);  // Comparison by ID
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}


