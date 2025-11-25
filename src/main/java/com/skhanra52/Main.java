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
        //-------------------------------------------------------------------------------------------------------
        /*
        * --------Understanding of importance of hash code-----------------------------------------------------
        *
        * HashSet and HashMap are based on the hash codes of object.
        * Since, sets are designed to ensure uniqueness by not allowing duplicates, adding an element required first
        * check if it is already exist in the set.
        * For large sets, this checks can become costly, potentially, taking O(n) time, which is leaner in relation to
        * the size of the set.
        *
        * One way to reduce the cast is through a technique called hashing.
        * By using hashing, we can distribute elements into different buckets,
        * If we divide the elements into two bucket, and each element can reliably determine its bucket, the lookup
        * time can  be roughly halved.
        *
        * Similarly, if we use four buckets, the elements are distributed even further, which could reduce the number
        * of comparisons needed in each bucket, improving lookup efficiency.
        *
        * A hashed collection will optimally create a limited set of buckets, to provide an even distribution of the
        * object across the bucket in a full set.
        * A hash code can be any valid integer, so it could be one of 4.2 billion valid number.
        * If your collection only contain 100,000 elements, you don't want to back it with a storage mechanism of
        * 4 billion possible placeholder.
        * Additionally, iterating through 100,000 elements sequentially to find a match or duplicate would be
        * inefficient.
        *
        * Instead, a hashing mechanism takes an integer hash code and a capacity declaration that specifies the number
        * of buckets over which to distribute the objects.
        * It then maps the wide range of possible hash codes into a more manageable range of bucket identifiers.
        * Hash code implementation often combine the hash code with other techniques to create an efficient bucket
        * system aims to evenly distribute objects, there by optimizing performance.
        *
        * To understand hashing in java, it helps to understand the equality of objects.
        * There are two methods in java.util.Object, that all object inherit. These are equals() and hashCode() and the
        * method signature from the object here.
        *
        * 1. Testing for equality:  public boolean equals(Object o)
        * 2. The hashcode method: public int hashCode()
        *
        * what == means for object?
        * It means two variable have the same reference to a single object in the memory.
        * Because both reference are pointing to a same object, then this is obviously a good equality test.
        * Equality of Object Object.equals():
        * Object can be considered in other instances as well, if their attributes values are same.
        * The String class override the equals() method, so that it can compare all the characters in the string, to
        * confirm that the two strings are equal.
        * Here is the example to understand the hashcode.
        *
        * */

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l", "He", "lo");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = Arrays.asList(aText,bText,cText,dText,eText);
        hellos.forEach(item -> System.out.println(item+" -> "+item.hashCode()));
        /*
        Output:
        Hello -> 69609650
        Hello -> 69609650
        Hello -> 69609650
        Hello -> 69609650
        hello -> 99162322
        If we look at the output , all four output with capital H has the same hashcode and hello with lowercase h
        has a different hashcode. Java does not care if there is a different object in the memory when it tests the
        equality of Strings using the equals() method. It just care the character match with once instance compare to
        another instance.
         */
        /*
        HashSet is the class that implements Set interface, and tracks duplicate by their hashcode.
        The HashSet will only add new references to its collection if it does not find a match in its collection
        --> VVI :   First using the hashcode and then the Object.equals() method.
        --> VVI :   It uses the hashcode to create bucket identifier to store the new reference.
         */
        Set<String> mySet = new HashSet<>(hellos);
        System.out.println(mySet); // output: [Hello, hello]

        mySet.forEach((item) ->  System.out.println(item +" -> "+item.hashCode()));
        /* Output:
        Hello -> 69609650
        hello -> 99162322
         */
        for(String setValue : mySet){
            System.out.print(setValue + " : ");
            for (int i=0;i<hellos.size();i++){
                if(setValue == hellos.get(i)){
                    System.out.print(i +" , ");
                }
            }
            System.out.println(" ");
        }
        /*
        Output:
        Hello : 0 , 1 ,
        hello : 4 ,

        What happening here,
        we set up five String reference variables(aText, bText, cText, dText, eText) in our program,
        but two of them refer the same string object in the memory(aText, bText because the value of them are identical
        "Hello" without any concat or String.join() method), for rest of the variable different memory has been assigned.
        Reference Variables             Objects in Memory                           Hash Collection
        aText   --------------->
                        -------------> "Hello", hashcode: 69609650                  For all four one bucket has
        bText   --------------->                                                    been created as hashcode are same
                                                                                    Bucket 1: Element
        cText   --------------------->  "Hello", hashcode: 69609650
        dText   --------------------->  "Hello", hashcode: 69609650

        eText   --------------------->  "hello", hashcode: 99162322                 Bucket 2: Element

        An indexed bucket is created as an algorithm dependent on the hashcode.
        If the hash codes are different, then unique elements are distributed across algorithmically identified buckets.
        If the hash codes are same, the equals() method is used to determine if the elements in the bucket are equal or
        duplicates.
        When we passed the list of five strings to the HashSet , it added unique instance of its collection. It locates
        elements to match, by first deriving its bucket to look through, based on hashcode.
        It then compares those elements to the next element to be added, with other elements in that bucket using
        equals() method.
        Note : Check the Person class to see how to override the hashcode() and equals() method.
        For detail: Video 205
         */

        PlayingCard aceOfHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingOfClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenOfSpades = new PlayingCard("Spades", "Queen");

        List<PlayingCard> playingCards = Arrays.asList(aceOfHearts,kingOfClubs,queenOfSpades);
        playingCards.forEach(s -> System.out.println(s+" -> "+s.hashCode()));

        /*
        To add in set by coping the List we can use the parameterized constructor.
        Set<PlayingCard> playingCardSet = new HashSet<>(playingCards);
        */

        //Here we are trying to display the hashcode of each playing card using for loop
        Set<PlayingCard> playingDeck = new HashSet<>();
        for(PlayingCard c : playingCards){
            if(!(playingDeck.add(c))){// add() method in any collection returns true if any collection successfully added
                System.out.println("Found a duplicate for "+c);
            }
        }
        System.out.println("Deck "+playingDeck);

       //----Setup for Set and HashSet----------------------------------------------------------------------------
       /*
       In this example, HashSet will be used as fields, and we will use Scanner class.
       We will be using Scanner with just a String passed to the constructor.

       We have tp create a Contact class, that has the fields, name , a String, email, and a HashSet of String, phones,
       another HashSet of String.
       Fields:
        String name;
        long phone;
        Set<String> email = new HashSet<>();
        Set<String> phones = new HashSet<>();
       Constructor:
        Contact(String name);
        Contact(String name, String email);
        Contact(String name, long phone);
        Contact(String name, String email, long phone);
       Methods:
        String getName();
        String toString();
        Contact mergeContactData(Contact contact)

        The last constructor should do the following:
        -> Add the email argument to the email set, if email is not null.
        -> Transform the phone argument, a long(if it is not zero), to a String in the format (123) 456-7890.
        -> Add transformed phone to the phones set.
       Finally include the method call mergeContactData(), that takes a contact, and return a new contact instance,
       which merges the current instance with the contact passed.

       Second Class: ContactData class
        The ContactData class is going to emulate getting data from an external source, but instead of external source
        we will set this data up with two different text books, in the format below
        Phone Data                          |   Email Data
        ---------------------------------------------------------------------------
        Suman Khanra, 8553242342            |  Geetha, geethaxyz@gmail.com
        Charlie Brown,3334445556            |  suman Khanra, example1@gmail.com
        Robin Hood,   4562341234            |  Geetha, geethaxyz@gmail.com
        Charlie Brown,3334445556

        -> Create a method name getData(), that takes String type(either "phone" or "email")and return a List of contact
        -> We are going to use the scanner to parse the data in this text blocks.

        In this main method we have to see public static void printData(String header, Collection<Contact> contacts){}

        */
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);
        /* Introduction to Java Set and HashSet--------------------------------------------------------------
        A Set is not implicitly ordered.
        A set contain no duplicate.
        A Set may contain single null element.
        Sets can be useful because operations on them is are very fast.
        -> Set methods:
        -> The Set interface defines the basic methods, add, remove and clear, to maintain the items in the set.
        -> You can also check if the specific element is their in the set using the contains() method.
        -> There is no way to retrieve an element from the set. You can check if something exist using contains(), and
           you can iterate over all the elements in the set, but attempting to get the 10th element, for example, from a
           Set isn't possible with a single method.

        The HashSet----------------
        -> The best-performing implementation of the Set interface is the HashSet class. This class uses hashing mechanisms
           to store items. This means the hashcode method is used to support even distributions of objects in the set.
        -> Oracle describes this class as offering constant time performance for the basic operations
           (add,remove,contains,size).
        -> This assumes the hash function disperses the elements properly among the buckets.
        -> Constant time has the Big O notation O(1).
        -> The HashSet actually uses a HashMap in its own implementation as of JDK 8.
        -> Continued the code setup of Set and HashSet where we have two classes Contact class and ContactData Class....
        * */
        // Combine these contacts, merging any duplicates into a single contact, with multiple emails and phone numbers,
        // on a single record.
        Set<Contact> emailContacts = new HashSet<>(emails); // emails is a List(which is a collection)
        Set<Contact> phoneContacts = new HashSet<>(phones); // phones is a List(which is a collections)
        printData("Email set", emailContacts);
        printData("Phone set", phoneContacts);
        /*
        In the output we still see the duplicates in the hashed collections however not in the same order as earlier.
        For hashed collections, first by the hash code method, and the equals() method, are using Objects implementation.
        This means each of the instances of contacts are considered unique.
        But since these are personal contacts, we have to make a rule, that contacts that have the same name,
        are really the same person, but with different data. For that we need to override equals() and hashcode()
        in the Contact class.
         */

        int index = emails.indexOf(new Contact("Robin Hood")); // this is working as we have overridden equals() by name.
        System.out.println(emails);
        System.out.println("index "+index);
        Contact robinHood = emails.get(index);
        System.out.println(robinHood);
        robinHood.addEmail("Sherwood Forest");
        System.out.println(robinHood);

        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com","RHood@sherwoodforest.org");
        System.out.println(robinHood);
        /*
        Set Math or Set Operation:----------------------------------------
        When we are trying to understand the data in multiple sets, we might want to get the data that's in all the sets,
        that's in every Set, or data where there's no overlap.
        -> The Collection interface's bulk operations(addAll, retainAll, removeAll, and containAll) can be used to
            perform these set operations.
        Representing Sets in Venn Diagram---------------------------------
        Sets are often represented as circles or ovals, with elements inside, on what is called Venn diagram.

                             Set A                                               Set B
                        Linus Van Pelt                                          Daffy Duck
                        Lucy Van pelt                                           Mickey Mouse
                        Charlie Brown                                           Minnie Mouse

        Here, the Sets contains no elements are in common. Because the items are distinct so there is no overlap.
        If there is few items shared in both the sets then the circle will be overlapped and inside the overlapped
        area shared items will be represented.
                              Set A               Shared elements                  Set B
                        Linus Van Pelt              Goofy                       Daffy Duck
                        Lucy Van pelt               Snoopy                      Mickey Mouse
                        Charlie Brown                                           Minnie Mouse

        -> Intersection of Set - Intersect - A intersect B:
        The intersection of these Sets is represented by the area where the two Sets overlapped and the area contains
        Goofy and Snoopy. Goofy and Snoopy are in both Sets, "Set A" and "Set B". So if we want to get the intersection
        of two Sets SetA and SetB then it will return Goofy and Snoopy. If the intersection has duplicate elements then
        it will only add the first element to the Collection.
            All elements IN COMMON, so any elements that are part of both sets.
            C.addAll(A);
            C.retainAll(B)


        -> Set Operation Union A U B:
        The union of two or more sets will return elements that are in any or all of the sets, removing any duplicates.
        A U B = All the items in Set A and Set B without duplicate, meaning if there are intersection then it will
        consider those items once from the first set.
        -> Java does not have union method on the Collection, but the addAll() method when used on Set, can be used
           to create a union of multiple Sets.
        We will use the two sets which we have created earlier called "emailContacts" and "phoneContacts" to demonstrate
        union of contact by name.
        -> Both union and intersection are symmetric operation, meaning A U B or B U A,  A ∩ B or B ∩ A gives same result.
         */
        // Union of A and B
        Set<Contact> unionByName = new HashSet<>(emailContacts);
        unionByName.addAll(phoneContacts);
        System.out.println("----------------------------------------------");
        System.out.println("Union of Contacts \n"+unionByName);

        // Intersection of Set A ∩ B:
        Set<Contact> interSectAB = new HashSet<>(emailContacts);
        interSectAB.retainAll(phoneContacts);
        System.out.println("-------Contacts with email----------------------------------------");
        // ∩ ASCII character \n2229
        System.out.println("(A ∩ B) Intersect emails A and phones B: "+interSectAB);

        // Intersection of Set A ∩ B:
        Set<Contact> interSectBA = new HashSet<>(phoneContacts);
        interSectBA.retainAll(emailContacts);
        System.out.println("-------Contacts with phone numbers----------------------------------------");
        // ∩ ASCII character \n2229
        System.out.println("(B ∩ A) Intersect phones B and emails A : "+interSectBA);
        /*-------Set Operations - Asymmetric Difference----------------------------------------------
        Another useful evaluation might be to identify which elements are in one set, but not the other. This is
        called a set difference. A difference subtracts elements in common from one Set and another, leaving only
        the distinct elements from the first Set as the result.

                    Set A               (Shared elements)                  Set B
                 Linus Van Pelt          Mickey Mouse                  Lucy Van pelt
                 Daffy Duck              Minnie Mouse                  Charlie Brown
                 Mickey Mouse -common    Robin Hood                    Maid Marion
                 Minnie Mouse -common                                  Mickey Mouse - common
                 Robin Hood   -common                                  Minnie Mouse - common
                                                                       Robin Hood   - common
         -> Set differences(Asymmetric)
         C = A-B
         D = B-A
         -> Set difference A - B
         Linus Van Pelt
         Daffy Duck
          -> Set difference B - A
         Lucy Van pelt
         Charlie Brown
         Maid Marion
         */

        // Difference Asymmetric of Set A - B:
        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        System.out.println("-------Contacts difference by email between set A - Set B ----------------------------------------");
        // ∩ ASCII character \n2229
        System.out.println("(A - B) emails A - phones B: "+AMinusB);

         /*-------Set Operations - Symmetric Difference----------------------------------------------
         The set symmetric difference, as the elements from all sets that don't intersect.
         -> The Symmetric difference
         (union of SetA and SetB) - (intersection of SetA and SetB)
         -> union of SetA and SetB : will give us all the elements of SetA and SetB and common elements without duplicate.
         -> intersection of SetA and SetB: will give us only common elements.
         -> (union of SetA and SetB) - (intersection of SetA and SetB): Will give us all the elements of SetA and SetB
            without common elements.
          */

        /* Order Sets--LinkedHashSet/TreeSet------------------------------------------------------------
        If we want to consider order set then we have to use either LinkedHashSet or TreeSet.
                     LinkedHashSet                                   TreeSet
                   ------------------                           ---------------------
       -> It maintains the insertion order.                     -> It is sorted Collection. Sorted naturally. Or Specific
                                                                   required sort during creating of the set.

       -> LinkedHashSet:---------------------
            -> The LinkHashSet extends the HashSet class. It maintains the relationship between elements using the doubly
            link list between entries.
            -> The "iteration order" is therefore the same as the "insertion order" of the elements, meaning  the order is
            predictable.
            -> All the methods for the LinkedHashSet are the same as those for the HashSet.
            -> Link HashSet, it provides constant-time performance, O(1) for add, contains, and remove operations.
       -> TreeSet: ---------------------------
            -> A TreeSet's class uses a data structure that's a derivative of what's called a binary search tree, or a
               Btree for short,which is based on the concept and efficiencies of binary search.
            -> As elements are added to a TreeSet, they are organized in the form of a tree, where the top of the tree
               represents the mid-point of the elements.
            -> The left node and its children are elements that are less than the parent node.
            -> The right node and its children are elements that are grater than the parent node.
            -> Instead of looking through all the elements in the Collection to locate a match, this allows the tree to
               be quickly traversed, each not being a simple decision point.
            -> Java's internal implementation uses a balanced tree data structure called the red black tree. The main
               point is the tree remains balanced as the elements are added.
          -> TreeSet O Notation----------------------
              Remember that O(1) is constant time, meaning the time or cost of the operation doesn't change regardless of
              how many elements are processed.
              O(n) is the linear time, meaning it grows in line with the way the collection grows.
              Another notation is Big O(log(n)), which means cost falls somewhere in between constant and linear time.
              The TreeSet promises O(log(n)) for the add, remove, and contains operations, compare to the HashSet which has
              constant time O(1) for those same operation.
          -> The TreeSet(Class) interface hierarchy:----------------------------
                1. The TreeSet can be declared and passed to arguments typed with any of the interfaces given below:

                <<interface>> Collection
                Collection<String> strCollection = new TreeSet<>();

                <<interface>> Set
                Set<String> set = new TreeSet<>();

                <<Interface>> SortedSet
                SortedSet<String> sortedSet = new TreeSet<>();

                <<interface>> NavigableSet
                NavigableSet<String> treeSet = new TreeSet<>();

                2. The TreeSet class is sorted and implements the "SortedSet" interface, which has methods such as
                   first(), last(), headSet() and tailSet(), as well as comparator.

                3. This set also maintains the navigableSet interface. As such, it has methods such as ceiling(),

           -> The TreeSet relies on comparable or comparator methods:--------------------------------
                1. Elements which implements comparable(said to have natural order sort, like strings and numbers)
                   can be elements of a treeSet.
                2. If your elements don't implement comparable, you must pass a comparator to the constructor.




         */

    }

    // Helper class to compare the string without case-sensitive
    public static boolean ignoreCase(List<String> list, String name){
        if(list == null || name == null) return false;
        return list.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }

    public static void printData(String header, Collection<Contact> contacts){
        System.out.println("-------------------------------------------");
        System.out.println(header);
        System.out.println("-------------------------------------------");
        contacts.forEach(System.out::println);
    }

}