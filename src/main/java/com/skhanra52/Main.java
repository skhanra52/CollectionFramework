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
//-----------------------------------------------------------------------------------------------------
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
           ii. a face field, which will be a string, containing either the number of the card(2-10) or the
               face value of the card, Jack(J), Queen(Q), King(K), or Ace(A).
          iii. a rank, an integer.
        The card should override the toString method and print the card with the face value
        (abbreviated, if a face card), the ASCII character of the suit, and the rank in parentheses.
        Below are the ASCII characters that will print out each suit as a printable character.
        CLUB(9872), DIAMOND(9830), HEART(9829), SPADE(9824).
        Output: The output should show all the cards in a standard deck of playing cards, stored by suit
                and rank.

         The card should have the following public static methods to assists anyone using this class:
         -> getNumericCard which should return an instance of a card, based on suit and number passed
            to it.
         -> getFacedCard which should return an instance of card, based on suit and abbreviation(J,Q,K,A).
            passed to it.
         -> getStandardDeck which should return a list of cards you'd find in a standard deck.
         -> printDeck which should take a description, a list of card, and a row count. This method will
            print the Cards out in the number of rows passed.
         -> Finally, the Card should have an overloaded printDeck method, that will print "Current deck"
            as description, and use 4, as the number of rows to be printed.
     */

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

    }

    // Helper class to compare the string without case-sensitive
    public static boolean ignoreCase(List<String> list, String name){
        if(list == null || name == null) return false;
        return list.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }
}