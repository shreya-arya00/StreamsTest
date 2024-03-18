package org.example;

public class Students {
    private int grade;
    private String standard;
    private String name;

    // Constructor
    public Students(int grade, String standard, String name) {
        this.grade = grade;
        this.standard = standard;
        this.name = name;
    }

    // Getter for grade
    public int getGrade() {
        return this.grade;
    }

    // Getter for standard
    public String getStandard() {
        return this.standard;
    }

    // Getter for name
    public String getName() {
        return this.name;
    }
}
