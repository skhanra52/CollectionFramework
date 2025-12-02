package com.skhanra52;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

 /*
    Ordered and sorted Map implementation:-------------------------------------
    -> The Map interface has the LinkedHashMap and TreeMap classes.
    -> The LinkedHashMap is a key/value entry collection whose keys are ordered by insertion order.
    -> The TreeMap is sorted by its keys, so a key needs to implement comparable,
       or be initialized with a specific comparator.

     Created a class called Student, see further over there.
     */

public class SortedMapMain {


    public static Map<String, Purchase> purchases = new LinkedHashMap<>();
    // NavigableMap will give all the method access of NavigableMap and the SortedMap like Set.
    public static NavigableMap<String, Student> students = new TreeMap<>();


    public static void main(String[] args) {
        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("jmc102", "Python Master Class", "Python");
        addPurchase("Mary Martin",jmc, 129.99);
        addPurchase("Andy Martin", python, 139.99);
        addPurchase("Mary Martin",python, 149.99);
        addPurchase("Joe Jones",jmc, 149.99);
        addPurchase("Bill Brown",python, 119.99);

        purchases.forEach((k,v) -> System.out.println(k+" "+v));
        students.forEach((k,v) -> System.out.println(k +" "+v) );

    }

    private static void addPurchase(String name, Course course, double price){
        Student existingStudent = students.get(name);
        if(existingStudent == null) {
            existingStudent = new Student(name,course);
            students.put(name,existingStudent);
        }else{
            existingStudent.addCourse(course);
        }

        int day = purchases.size() + 1;
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }
}
