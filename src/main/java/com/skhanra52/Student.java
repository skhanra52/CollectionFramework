package com.skhanra52;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseId, String name, String subject){}
record Purchase(String courseId, int studentId, double price, int yr, int dayOfYear ){
    // Yr is the date of purchase and dayOfYear from 1-365.

    // will be used as key in the upcoming maps.
    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr,dayOfYear);
    }

}

public class Student {
    public static int lastId = 1;
    private String name;
    private int id;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseName = new String[courseList.size()];
        // definition of Arrays.SetAll(): public static <T> void setAll(T[] array, IntFunction<? extends T> generator)
        // Actually its creating a local array which will contains ony course name, no course id or subject
        Arrays.setAll(courseName, i -> courseList.get(i).name());
        return "[%d] : %s".formatted(id,String.join(", ",courseName));
    }
}
