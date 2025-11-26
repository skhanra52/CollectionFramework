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
        System.out.println("------Full Set starts------------------");
        fullSet.forEach(System.out::println);
        System.out.println("------Full Set ends------------------");

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
        /*
        Remove the first and last element from the set using pollFirst() and pollLast() method.
         */
        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("-----PollFirst and pollLast Example--------------");
        System.out.println("polled first "+copiedSet.pollFirst());
        System.out.println("polled last "+copiedSet.pollLast());
        System.out.println("------------------");
        copiedSet.forEach(System.out::println);
        System.out.println("------------------");
        /*
        Identify the closest match in a set to value passed to the method.
        ceiling(), higher(), lower(), and floor()
        ceiling() -> returns the element that is either grater than or equal to the element passed.
        Higher() -> Returns the element which is only higher than the element passed.
         if the element not preset in the set then higher and ceiling returns the same.
         if it is a last element of the set then ceiling will return the last element. However, higher will return null.
         if it is a last element of the set "snoopy" and does not exist then both will return null.
        floor() -> returns the element that is either less than or equal to passed element if in set.
                   If not found return next element.
        lower() -> returns the element which is less than passed element if in set, if not return null.

         */
        Contact Charlie = new Contact("Charlie Brown"); // preset in the set
        Contact daisy = new Contact("Daisy Duck"); // not in the Set
        Contact snoopy = new Contact("Snoopy"); // who would be last if inserted in the treeSet
        Contact archie = new Contact("Archie");
        System.out.println("------------------");
        for(Contact c : List.of(Charlie,daisy, last, snoopy)){
            System.out.printf("ceiling (%s)=%s %n", c.getName(),fullSet.ceiling(c));
            System.out.printf("Higher (%s)=%s %n", c.getName(),fullSet.higher(c));
        }
        System.out.println("------------------");
        for(Contact c : List.of(Charlie,daisy, first, archie)){
            System.out.printf("Floor (%s)=%s %n", c.getName(),fullSet.floor(c));
            System.out.printf("Lower (%s)=%s %n", c.getName(),fullSet.lower(c));
        }

        /*
        -> descendingSet() -> it gives the reverse set
           if you add or delete any element from the descending set it will modify the original set.
         */
        NavigableSet<Contact> descendingSet = fullSet.descendingSet();
        System.out.println("--------Descending Set------------");
        descendingSet.forEach(System.out::println);

        /*
         -> We can also get the sub sets from the head, or beginning of the SortedSet, or the tail, or end of the
            SortedSet.
         */

        Contact marion = new Contact("Marion");
        System.out.println("--------head Set------------");
        // Returns all the element before marion alphabetically, it will exclude the element passed and return the result.
        // var headSet = fullSet.headSet(marion);
         SortedSet<Contact> headSet = fullSet.headSet(marion);
        // If you want to include the passed element then you have to pass the second argument like below
        // SortedSet<Contact> headSet = fullSet.headSet(marion, true);
        headSet.forEach(System.out::println);
        System.out.println("--------tail Set------------");
        // Returns all the element after marion alphabetically. If the element passed found in the set, it will include
        // the element itself and followed by elements after that.
        // var tailSet = fullSet.tailSet(marion);
         SortedSet<Contact> tailSet = fullSet.tailSet(marion);
        // If you want to exclude the passed element then you have to pass the second argument like below
        // SortedSet<Contact> tailSet = fullSet.tailSet(marion, false);
        tailSet.forEach(System.out::println);

        // We can also use subset to get the subset --------------------
        Contact linus = new Contact("Linus");
        SortedSet<Contact> subSet = fullSet.subSet(Charlie, linus);
        System.out.println("--------Sub Set ------------");
        // includes first element and exclude last element
        subSet.forEach(System.out::println);
        // To exclude the first element and include the last element we need to provide the flags.
        SortedSet<Contact> subSetFirstExclude = fullSet.subSet(Charlie,false, linus,true);
        subSetFirstExclude.forEach(System.out::println);
        /*
        When to use TreeSet?------------------------------------------------
        The TreeSet provides several advantages due to the built-in methods, compared to other set implementations.
        However, it comes with the higher performance cost.
        Consider using TreeSet when:
        -> We have moderate number of elements.
        -> We need collections that is automatically sorted and remain sorted when elements are added or removed.
        -> Ensure that no duplicate elements are stored.
        In such cases TreeSet is strong alternative to ArraySet, specially when you need both sorting and uniqueness.

         */
    }
}
