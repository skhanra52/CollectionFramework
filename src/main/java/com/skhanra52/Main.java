package com.skhanra52;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Using contains() for simple types
        List<String> list = new ArrayList<>();
        String[] names = {"Suman", "Vihaan","Geetha"};
        list.addAll(Arrays.asList(names));
        System.out.println(list);
        list.add("Surajit");
        list.addAll(List.of("Swagata","Sonam","Radhika"));
        System.out.println("Is Radhika in list -> "+list.contains("radhika")); // true (case-sensitive)
        System.out.println("Is Rashika in list -> " +ignoreCase(list,"radhika")); // true (case-insensitive)

        // Using contains() for custom objects
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Suman", 37));
        persons.add(new Person("Geetha", 37));

        Person findPerson = new Person("Suman", 37);

        System.out.println("Is suman there in the persons list -> "+persons.contains(findPerson)); // true

    }

    // Helper class to compare the string without case-sensitive
    public static boolean ignoreCase(List<String> list, String name){
        if(list == null || name == null) return false;
        return list.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }
}