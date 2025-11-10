package com.skhanra52;

import java.util.Objects;

public class Person {

    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age =age;
    }

    // Important: override the Objects.equals() and hashCode()
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        // before type casting in the next line it is checking if the type of object is matches
        if(!(o instanceof Person)) return false;
        Person p = (Person) o;
        return (age == p.age && Objects.equals(name,p.name));
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, age);
    }
}
