package com.skhanra52;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

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


    }

    // Helper class to compare the string without case-sensitive
    public static boolean ignoreCase(List<String> list, String name){
        if(list == null || name == null) return false;
        return list.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }
}