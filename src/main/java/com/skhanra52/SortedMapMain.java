package com.skhanra52;

import java.time.LocalDate;
import java.util.*;

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
        addPurchase("Suman Khanra", python, 139.99);
        addPurchase("Geetha",jmc, 149.99);
        addPurchase("Vihaan",jmc, 149.99);
        addPurchase("Geetha",python, 119.99);

        System.out.println("------------Purchase--------------------------------");
        purchases.forEach((k,v) -> System.out.println(k+" -> "+v));
        System.out.println("------------Student---------------------------------");
        students.forEach((k,v) -> System.out.println(k +" -> "+v) );
        System.out.println("----------------------------------------------------");

        /*
        Suppose we would like to track the sale of each day from January 1 through 15.
         */
        NavigableMap<LocalDate,List<Purchase>> datedPurchases = new TreeMap<>();
        for(Purchase p: purchases.values()){
            // System.out.println("P ->"+p);
            datedPurchases.compute(p.purchaseDate(),
                    (pDate,pList) -> {
                        // System.out.println("p.purchaseDate - "+p.purchaseDate());
                        // System.out.println("pDate - "+pDate+" pList "+pList);
                        List<Purchase> list = pList == null ? new ArrayList<>() : pList;
                        // System.out.println(pDate+ " List before -> "+list);
                        list.add(p);
                        //System.out.println(pDate+ " List after -> "+list);
                        return list;
                    });
        }
        datedPurchases.forEach((k,v) -> System.out.println(k+" --> "+v));
        System.out.println("------------headMap()------------------------------------------------");
        /*
        headMap/tailMap -> Like Set, navigableMap also has headMap/tailMap method.
         */

        int currentYear = LocalDate.now().getYear();
        LocalDate firstDay = LocalDate.ofYearDay(currentYear,1);
        LocalDate week1 = firstDay.plusDays(7);
        LocalDate week2 = week1.plusDays(7);
        Map<LocalDate,List<Purchase>> week1Purchase = datedPurchases.headMap(week1);
        Map<LocalDate,List<Purchase>> week2Purchase = datedPurchases.tailMap(week1);
        week1Purchase.forEach((k,v) -> System.out.println(k+" --> "+v));
        System.out.println("------------tailMap()------------------------------------------------");
        week2Purchase.forEach((k,v) -> System.out.println(k+" --> "+v));
        System.out.println("------------using displayStats()--------------------------------------");
        displayStats(1,week1Purchase);
        displayStats(2,week2Purchase);
        System.out.println("---------------------------------------------------------------------");
        /*
        Like treeSet, last, first, pollLast and pollFirst,
        treeMap has lastKey, firstKey, lastEntry, firstEntry,
        and pollLastKey, pollFirstKey, pollLastEntry, pollFirstEntry

         */


    }

//    /**
//     * This function prints Student which is a TreeSet with natural order and purchases with insertion order as
//     * it is a LinkHashMap.
//     * @param name: name of the student.
//     * @param course: course the student purchased.
//     * @param price: price of the course.
//     */
//    private static void addPurchase(String name, Course course, double price){
//        Student existingStudent = students.get(name);
//        if(existingStudent == null) {
//            existingStudent = new Student(name,course);
//            students.put(name,existingStudent);
//        }else{
//            existingStudent.addCourse(course);
//        }
//
//        int day = purchases.size() + 1;
//        String key = course.courseId() + "_" + existingStudent.getId();
//        int year = LocalDate.now().getYear();
//        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
//        purchases.put(key, purchase);
//    }

    private static void addPurchase(String name, Course course, double price){
        Student existingStudent = students.get(name); // this will return Student object
        System.out.println("exstingStudent -> "+existingStudent);
        if(existingStudent == null) {
            existingStudent = new Student(name,course);
            students.put(name,existingStudent);
        }else{
            existingStudent.addCourse(course);
        }

        int day = new Random().nextInt(1,15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    /*
    This function will return the number of course sold per week.
    If weekly data passed, this will be an int from 1 to 52, but if passed monthly data, it would be 1 through 12,
    and for quarterly data, it could be 1 through 4, and so on. Map for the second parameter, again with LocalDate
    as the key, and a List of Purchase records as the value, which is  periodData.
     */
    private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData){
        System.out.println("---------------------------------------");
        // To track the course id's and number of purchases for each.
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key,value) -> {
            System.out.println(key+" --> "+value);
            for(Purchase p : value){
                weeklyCounts.merge(p.courseId(),
                        1,
                        Integer::sum); //this is nothing but : (prev,current) -> { return prev+current;}
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period,weeklyCounts);
    }
}
