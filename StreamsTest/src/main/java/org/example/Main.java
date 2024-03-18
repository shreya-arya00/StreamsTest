package org.example;

import java.util.*;
import java.util.stream.Collectors;
class Student {
    private int id;
    private String name;
    private int grade;
    private int age;
    private String standard;
    private String subject;

    // Constructor
    public Student(int id, String name, int grade, int age, String standard, String subject) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.standard = standard;
        this.subject = subject;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", age=" + age +
                ", standard='" + standard + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(); // Assuming you have a list of Student objects

        // Task 1: List of students with grades between 1 and 35
        List<Student> studentsWithGradesBetween1And35 = students.stream()
                .filter(student -> student.getGrade() >= 1 && student.getGrade() <= 35)
                .collect(Collectors.toList());

        // Task 2: Find the students with grades more than 80 and sort them by their name
        List<Student> studentsWithGradesMoreThan80Ascending = students.stream()
                .filter(student -> student.getGrade() > 80)
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        List<Student> studentsWithGradesMoreThan80Descending = students.stream()
                .filter(student -> student.getGrade() > 80)
                .sorted(Comparator.comparing(Student::getName).reversed())
                .collect(Collectors.toList());

        // Task 3: Find all the distinct standards
        Set<String> distinctStandards = students.stream()
                .map(Student::getStandard)
                .collect(Collectors.toSet());

        // Task 4: Group the students by standard
        Map<String, List<Student>> studentsGroupedByStandard = students.stream()
                .collect(Collectors.groupingBy(Student::getStandard));

        // Task 5: For each standard, find the student with the max grade
        Map<String, Optional<Student>> maxGradeByStandard = students.stream()
                .collect(Collectors.groupingBy(Student::getStandard,
                        Collectors.maxBy(Comparator.comparing(Student::getGrade))));

        // Printing the results
        System.out.println("Students with grades between 1 and 35: " + studentsWithGradesBetween1And35);
        System.out.println("Students with grades more than 80 (ascending): " + studentsWithGradesMoreThan80Ascending);
        System.out.println("Students with grades more than 80 (descending): " + studentsWithGradesMoreThan80Descending);
        System.out.println("Distinct standards: " + distinctStandards);
        System.out.println("Students grouped by standard: " + studentsGroupedByStandard);
        System.out.println("Max grade by standard: " + maxGradeByStandard);
    }
}
