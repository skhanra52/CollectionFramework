package com.skhanra52;

import java.util.*;

public class EnumCollectionMain {

    enum weekDay {SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY}

    public static void main(String[] args) {

        // defining ann's working days for the week.
        List<weekDay> annsWorkDays = new ArrayList<>(List.of(weekDay.MONDAY, weekDay.TUESDAY,
                weekDay.THURSDAY, weekDay.FRIDAY));
        // Use Ann's workdays to figure out what kind of coverage we need for other employees if we would like to hire.
        // Use the factory method of EnumSet to create a set of ann's workDays.
        EnumSet<weekDay> annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        /*
        Output of the above print as "RegularEnumSet".
        => What is "RegularEnumSet"?
           -> EnumSet are represented internally as bit vectors, which are just a series of ones and zeros.
           -> A one indicates the enum constant (with an original value that is equal to index of the bit) is in the set.
           -> A zero indicates the enum constant is not in the set.
           -> Using a bit vector allows all Set operations to use bit math, which makes it very fast.
           -> A RegularEnumSet uses a single long as its bit vector, which means it can contain a maximum of 64 bits,
              representing 64 enum values.
           -> A "JumboEnumSet" gets returned if you have more than 64 enums.
         */

        annsDaysSet.forEach(System.out::println);
        System.out.println("--------allOf()-------------------------------");
        EnumSet<weekDay> allDaysSet = EnumSet.allOf(weekDay.class);
        allDaysSet.forEach(System.out::println);
        System.out.println("---------------------------------------");
        /*
        Full set of possible workDays minus annsDaysSet, it gives the difference of original set and provided set.
        The same can be implemented using removeAll() method.
         */
        Set<weekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        newPersonDays.forEach(System.out::println);
        System.out.println("--------removeAll()-------------------------------");
        Set<weekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsWorkDays);
        anotherWay.forEach(System.out::println);
        System.out.println("-------range()--------------------------------");
        Set<weekDay> businessDays = EnumSet.range(weekDay.MONDAY, weekDay.FRIDAY);
        businessDays.forEach(System.out::println);
        System.out.println("--------------Union-------------------------");
        Set<weekDay> weekend = EnumSet.of(weekDay.SATURDAY, weekDay.SUNDAY);
        EnumSet<weekDay> copyOfBusinessDays = EnumSet.copyOf(businessDays);
        copyOfBusinessDays.addAll(weekend);
        copyOfBusinessDays.forEach(System.out::println);
        System.out.println("--------------Intersection-------------------------");
        weekend.retainAll(EnumSet.of(weekDay.SUNDAY));
        weekend.forEach(System.out::println);
        System.out.println("---------------------------------------");

        /*
        EnumMap is not abstract, so we can create instance of the same. But unlike other Map implementation it does not
        have an argument constructor.
         */
        Map<weekDay, String[]> employeeMap = new EnumMap<>(weekDay.class);
        employeeMap.put(weekDay.FRIDAY, new String[]{"Ann", "Mary", "Bob"});
        employeeMap.put(weekDay.MONDAY, new String[]{"Bob", "Mary"});
        employeeMap.forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));

        /*
        1. What are EnumSet and EnumMap?
            Both are specialized collection classes designed only for enums.
            Feature	                EnumSet(abstract class)                   	     EnumMap(Concrete Class)
            ------------------------------------------------------------------------------------------------------------
            Data Structure          Set optimized for enums	                         Map optimized for enums
            Implementation	        Bit vector (extremely fast)	                     Array indexed by ordinal()
            Keys	                Enum values,	                                 Enum values,
            Values	                None (it's a Set)	                             Any type
            Performance	            Faster than HashSet	                             Faster than HashMap
            Memory	                Extremely low,	                                 Extremely low
         */

        // Calling the methods from the User class
        User suman = new User();
        suman.grant(User.Permission.READ);
        suman.grant(User.Permission.WRITE);
        System.out.println("------Permissions-----------------------------");
        System.out.println("Suman's permission:  "+suman);
        System.out.println("Suman can read? "+suman.can(User.Permission.READ));
        System.out.println("Suman can write? "+suman.can(User.Permission.WRITE));
        System.out.println("Suman can update? "+suman.can(User.Permission.UPDATE));

        suman.grant(User.Permission.UPDATE);
        System.out.println("Suman can update? "+suman.can(User.Permission.UPDATE));
    }
}

/**
 *  Real world example of EnumSet for permission management.
 */
class User {
    enum Permission{READ,WRITE,UPDATE,DELETE};
    // Creating an empty enum called p with Permission type.
    // Each user gets an EnumSet and hold their permission.
    EnumSet<Permission> permissions = EnumSet.noneOf(Permission.class);

    // Add a permission
    void grant(Permission p ){
        permissions.add(p);
    }

    // Remove a permission
    void revoke(Permission p){
        permissions.remove(p);
    }

    // Check if user has the permission
    boolean can(Permission p){
        return permissions.contains(p);
    }

    @Override
    public String toString() {
        return "permissions=" + permissions ;
    }
}