package com.skhanra52;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        /*
        If we want to demonstrate the additional method of SortedSet or NavigableSet then we need to declare Set of one
        of these two.
        Reference type as NavigableSet, it is the most specific of the interfaces which will cover all the methods unique
        to both SortedSet and NavigableSet.
         */

//        Comparator<Contact> mySort = Comparator.comparing((s) -> s.getName());
//        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);

        NavigableSet<Contact> sorted = new TreeSet<>(Comparator.comparing(Contact::getName));
        sorted.addAll(phones);
//        System.out.print(sorted);
        sorted.forEach(System.out::println);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(item -> justNames.add(item.getName()));
        justNames.forEach(System.out::println);

        // We can also provide a sorted set in the TreeSet constructor. Sorted is already a sorted navigable set.
        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        // There is a method on the SortedSet interface, that returns the comparator used in the set
        // ie(Comparator.comparing(Contact::getName)

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
//        fullList.sort(Comparator.comparing(Contact::getName)); // this line can be replaced by
        fullList.sort(sorted.comparator()); // sorted.comparator() returning the comparator we have created earlier for set.
        System.out.println("---------------------------------------------");
        fullList.forEach(System.out::println);

        // The first, last, pollFirst and pollLast methods on this class.
        // Get the min and max values of the collection.
        /*
        Contact max = Collections.max(fullList); lines fails because max, min usages natural ordering elements
        with signature below:

        public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)

        Here is the explanation of " <T extends Object & Comparable<? super T>> T" : The type T must implement Comparable.
        -> how we read:
            “Give me a collection of items whose type is T or a subtype of T that is "Collection<? extends T> coll".
            T must implement Comparable (directly or through a parent).
            I will return the largest value from the collection.”
         */

        Contact min = Collections.min(fullSet, Comparator.comparing(Contact::getName));
        Contact max = Collections.max(fullSet, sorted.comparator());
        System.out.println("Min value "+min);
        System.out.println("Max value "+max);

        Contact first = fullSet.first();
        Contact last = fullSet.last();
        System.out.println("---------------------------------------------------");
        System.out.println("First Element "+first);
        System.out.println("Last Element "+last);


    }
}
