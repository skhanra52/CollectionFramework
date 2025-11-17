package com.skhanra52;

import java.util.*;

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
//--------------------------------------------------------------------------------------------------
         /*
        Code will include the use of record, a nested enum on that record, and the use of list
        functions which we already exposed to.
        Also, will use the ASCII code to display the suits of the cards.
        1. We will set up a card class, which will be used to create a deck of playing cards
            (A standard deck consists of 52 unique cards.).
        2. We will be using these cards, deck of cards, to demonstrate many of the methods
            on java.util.collections.
        3. The card will have three fields:
            i. a suit, meaning Club, Diamond, Spade, Heart. This will be an enum.
           ii. a face field, which will be a string, containing either the number of the
                card(2-10) or the face value of the card, Jack(J), Queen(Q), King(K), or Ace(A).
          iii. a rank, an integer.
        The card should override the toString method and print the card with the face value
        (abbreviated, if a face card), the ASCII character of the suit, and the rank in parentheses.
        Below are the ASCII characters that will print out each suit as a printable character.
        CLUB(9872), DIAMOND(9830), HEART(9829), SPADE(9824).
        Output: The output should show all the cards in a standard deck of playing cards,
                stored by suit and rank.

         The card should have the following public static methods to assists anyone using this class:
         -> getNumericCard which should return an instance of a card, based on suit and number passed
            to it.
         -> getFacedCard which should return an instance of card,
            based on suit and abbreviation(J,Q,K,A). passed to it.
         -> getStandardDeck which should return a list of cards you'd find in a standard deck.
         -> printDeck which should take a description, a list of card, and a row count.
            This method will print the Cards out in the number of rows passed.
         -> Finally, the Card should have an overloaded printDeck method,
            that will print "Current deck"
            as description, and use 4, as the number of rows to be printed.
     */

        Card[] cardArray = new Card[13];
        Card aceOfHeart = Card.getFaceCard(Suit.HEART,'A');
        Arrays.fill(cardArray, aceOfHeart); // This is the fill method of Arrays class
        Card.printDeck(Arrays.asList(cardArray), "Aces of Heart", 1);

        // Now the Collections class has a fill method as well and that method takes a list and element

        // empty list of Card type with initial value 52
        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards,aceOfHeart);
        Card.printDeck(cards,"Aces of HEART", 1);
        System.out.println("Card List length "+cards.size()); // length is zero, empty list
        // Above 3 lines gives does not give output as cards is empty, it could not fill with heart
        // directly. When we initialize the arrayList passing 52 it just sets the capacity to 52.
        // It doesn't populate the list with elements, unlike an array list is not populated with 52
        // no references.
        // Using Collections.fill() here from the Collections class would fill the list if the List
        // size is grater than zero, meaning it would replace every element with the element passed
        // to the method.
        // The fill method does not actually add element. So if the cardList is empty it stays empty.
        // How can we fill this with some default values, fortunately, Collections class offers us some
        // alternatives that is nCopies() which creates a new list with number of elements we specified
        // in the first argument, filling in with the element you pass in the 2nd argument.

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHeart);
        Card.printDeck(acesOfHearts, "Aces of Hearts 2nd", 1);

        Card kingsOfClub = Card.getFaceCard(Suit.CLUB,'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingsOfClub);
        Card.printDeck(kingsOfClubs, "Kings of CLUBS", 1);

        // Like List, the Collections class includes the addAll() method. It's first argument is the
        // List we want to add elements to. And the second argument is for the element to be added,
        // but unlike List's addAll() method, this is a variable argument of element to be added.
        // Difference is that List's addAll() method takes the collection of elements to be added,

        // Above we have created an object call "cards" which is still empty with the initial capacity
        // of 52.

//        Collections.addAll(cards,cardArray);
//        Card.printDeck(cards, "Card collection with aces added", 1);
        // if we use List's addAll() method which takes the argument as collection, it will not
        // accept array as argument.
        // cards.addAll(cardArray); this will not compile because cardArray is an array not collection
//---------------------------------------------------------------------------------------------------------
        // Copy method in java.util.Collections
//        Collections.copy(cards, kingsOfClubs);
//        Card.printDeck(cards,"Card collection with kings copied", 1);
        // We get an exception in thread "main" java.lang.IndexOutOfBoundsException:
        // Source does not fit in destination at
        // java.base/java.util.Collections.copy(Collections.java:561)
        // This is similar to the problem like Collections.fill() method where initially cards is empty
        // No elements are present in the cards so the size is zero, and we are trying to add
        // kingsOfClubs whose size is 13. We can't use the Collections.copy() method if the number
        // of element in the current list(which is 13) is less than the number of elements in the
        // source list which is zero.
        // Collections.copy() method does not create a new copy of the list, instead it use the reference
        // of the original list and copy the content from there.
        // If you want to get a true copy then we have to use List.copyOf() method, but this is immutable.
        cards = List.copyOf(kingsOfClubs);
        Card.printDeck(cards, "List copies of kings", 1);
        // Collections.shuffle() method------------------------------------------------------------------
        // Deck contain the standard cards of 52
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
        // We can use random to shuffle cards. However, we can use Collections.shuffle() method as well.
        // This is useful method if you are writing a card game certainly, if you ever want to randomize
        // with the existing elements.
//        Collections.shuffle(deck);
//        Card.printDeck(deck, "Shuffle cards", 4);
        // Collections.reverse() method-------------------------------------------------------------------
        Collections.reverse(deck);
        Card.printDeck(deck,"Reversed Deck", 4);
        // Collections.sort() method---------------------------------------------------------------------
        // There are two flavours to it, much like list interface's sort() method. One requires elements
        // in the list to implement comparable, and one does not.
        // Here, the sort() method which does not need comparable.
        // Collections.sort() method takes the list as first argument. If the element implements comparable
        // that would be it, but if they don't or if we want to force alternative sort, we can pass comparator.
        // We can set up comparator as local variable so that we can reuse it.
        // Collections.sort() used in the legacy code. In the new codes we can use list.sort() method.
       Comparator<Card> sortingAlgorithm  = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "sorted deck of cards", 13);
        //-Collection.subList() method-----------------------------------------------------------------------------
        // Compare sublist to full list, we will carve out few smaller list from the standard list using subList()
        // and passing them to new ArrayList constructor.
        List<Card> threes = new ArrayList<>(deck.subList(4,8));
        Card.printDeck(threes, "Sublist of Threes", 1);
        //Collection.indexOfSubList()-------------------------------------------------------------------------------
        // Collection.indexOfSubList() will take list as first argument in which the sublist should be found, and
        // subList in the 2nd element. This provides the starting index of the sublist in the original list.
        int subListIndex = Collections.indexOfSubList(deck, threes);
        System.out.println("sublist index for threes "+subListIndex);
        //deck.containsAll()----------------------------------------------------------------------------------------
        // deck.containsAll() method will return true if the parent list contains all the element of sublist.
        // This is the legacy method, we can use list.containsAll() method.
        // we can wrap the code in HashSet constructor new HashSet<>(deck).containsAll(threes) as well.
        System.out.println(deck.containsAll(threes));

        // Collections.disjoint() method , it takes two list of argument and compares them, if any one of the element
        // of the second list found in the first list then it will return false, means it has element in common.
        boolean disjoint = Collections.disjoint(deck,threes);
        System.out.println("disjoint "+disjoint);

        // Like the Arrays class that gave us a binarySearch for the arrays, Collections class supports a binary search
        // for the lists.
        // Condition 1: Both method require element to be sorted first and neither guarantees what index is returned
        //              if there is duplicates.
        // Condition 2: The binarySearch() method in Collections is overloaded like sort() method.
        //              We can pass a comparable elements, or we can pass a list of separate comparator, which will
        //              match the way elements are sorted.
        // Condition 3: Both methods takes an element to be searched. The element should be same type as the elements
        //              in the list being searched.

        Card tenOfHearts = Card.getNumericCard(Suit.HEART, 10);
        deck.sort(sortingAlgorithm); // must be sorted, here we are using list.sort() method not collection as line 144.
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("Found index "+foundIndex);
        System.out.println(deck.get(foundIndex));
        // We can use the same using list's indexOf() method
        int findIndexOfList = deck.indexOf(tenOfHearts);
        System.out.println("FindIndexOfList "+findIndexOfList );
        //------------------------------------------------------------------------------------------------
        // Collections.replaceAll(), it takes first argument a list, second argument as which has to be replaced,
        // third argument by which element. Meaning, second will be replaced by third args.
        /*
            java.util.Collections
            @Contract(mutates = "param1")
            public static <T> boolean replaceAll(
                @NotNull   java.util.List<T> list,
                @Nullable   T oldVal,
                @Nullable   T newVal
                )
            Replaces all occurrences of one specified value in a list with another. More formally,
            replaces with newVal each element e in list such that (oldVal==null ? e==null : oldVal.equals(e)).
            (This method has no effect on the size of the list.)
            Params:
            list – the list in which replacement is to occur.
            oldVal – the old value to be replaced.
            newVal – the new value with which oldVal is to be replaced.
            Type parameters:
            <T> – the class of the objects in the list
            Returns:
            true if list contained one or more elements e such that (oldVal==null ? e==null : oldVal.equals(e)).
            Throws:
            UnsupportedOperationException – if the specified list or its list-iterator does not support
                                            the set operation.
            Since: 1.4
         */

        Card tenOfClubs = Card.getNumericCard(Suit.CLUB, 10);
        System.out.println(tenOfClubs +" index "+ deck.indexOf(tenOfClubs) + " and "
                + tenOfHearts + "index "+deck.indexOf(tenOfHearts));
        Card.printDeck(deck, "Display deck", 4);
        boolean replaced =  Collections.replaceAll(deck,tenOfHearts,tenOfClubs);
        Card.printDeck(deck,"Replaced tenOfHearts by tenOfClubs", 4);
//        if(replaced){
//            System.out.println("The card is replaced");
//        }else {
//            System.out.println("The card is not replaced");
//        }
        // or
        System.out.println(replaced? "Card is replaced": "Card is not replaced");
        //Collection.frequency() method----------------------------------------------------------------------------
        System.out.println("Ten of hearts cards = " +Collections.frequency(deck,tenOfHearts)); // 0 because replaced
        System.out.println("Ten of club cards = "+Collections.frequency(deck,tenOfClubs)); // output 2
        // Collections.min()/Collections.max() method -------------------------------------------------------------
        /*
        Collections class provides us min() and max() method returns the minimum and maximum element in the collection
        respectively based on the natural ordering of the elements or a provided comparator.
        Like sort() and binarySearch() method we can use one of the overloaded versions,
        -> The first if the class implements comparable, which we are not doing in this example.
        -> Second, takes the comparator. We will use the same here for the Card example by passing the sortingAlgorithm
        variable.
         */
        System.out.println("Best card "+Collections.max(deck, sortingAlgorithm));
        System.out.println("Min card "+Collections.min(deck,sortingAlgorithm));

        //Collections.rotate()-----------------------------------------------------------------------------------
         Comparator<Card> sortBySuit = Comparator.comparing(Card::suit)
                 .thenComparing(Card::rank);
         List<Card> standardDeck = Card.getStandardDeck();
         Card.printDeck(deck,"normal deck", 4);
         Collections.sort(standardDeck,sortBySuit); // Legacy method, now we can use standardDeck.sort(sortBySuit)
         Card.printDeck(standardDeck,"Sort by Suit", 4);
         List<Card> copied = new ArrayList<>(standardDeck.subList(0,13));
         Card.printDeck(copied, "Copied card", 1);
         Collections.rotate(copied, 5);
         Card.printDeck(copied,"Rotate standardDeck", 1);
         // Collections.swap()-----------------------------------------------------------------------------------
        copied = new ArrayList<>(deck.subList(0,13));
        for (int i=0;i<copied.size();i++){
            Collections.swap(copied,i, copied.size() - 1 - i);
        }
        Card.printDeck(deck,"swap implementation", 4);





    }

    // Helper class to compare the string without case-sensitive
    public static boolean ignoreCase(List<String> list, String name){
        if(list == null || name == null) return false;
        return list.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }
}