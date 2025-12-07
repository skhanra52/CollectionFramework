package com.skhanra52;

import java.util.*;

public class EnumCollectionMain {

    enum weekDay {SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY}

    public static void main(String[] args) {

        // defining ann's working days for the week.
        List<weekDay> annsWorkDays = new ArrayList<>(List.of(weekDay.MONDAY,weekDay.TUESDAY,
                weekDay.THURSDAY,weekDay.FRIDAY));
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
        System.out.println("---------------------------------------");
        EnumSet<weekDay> allDaysSet = EnumSet.allOf(weekDay.class);
        allDaysSet.forEach(System.out::println);
        System.out.println("---------------------------------------");
        /*
        Full set of possible workDays minus annsDaysSet, it gies the difference of original set and provided set.
        Same can be achived using removeAll() method.
         */
        Set<weekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        newPersonDays.forEach(System.out::println);
        System.out.println("---------------------------------------");
        Set<weekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsWorkDays);
        anotherWay.forEach(System.out::println);
        System.out.println("---------------------------------------");
        Set<weekDay> businessDays = EnumSet.range(weekDay.MONDAY,weekDay.FRIDAY);
        businessDays.forEach(System.out::println);
        System.out.println("---------------------------------------");

        /*
        EnumMap is not abstract, so we can create instance of the same. But unlike other Map implementation it does not
        have an argument onstructor.
         */
        Map<weekDay, String[]> employeeMap = new EnumMap<>(weekDay.class);
        employeeMap.put(weekDay.FRIDAY, new String[]{"Ann","Mary","Bob"});
        employeeMap.put(weekDay.MONDAY, new String[]{"Bob","Mary"});
        employeeMap.forEach((k,v) -> System.out.println(k + " "+Arrays.toString(v)));

    }
}
