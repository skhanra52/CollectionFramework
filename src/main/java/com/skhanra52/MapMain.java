package com.skhanra52;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        System.out.println("Example of Map operations--------------------");
        fullList.forEach(System.out::println);
        System.out.println("--------------------");

        Map<String, Contact> contacts = new HashMap<>();
        fullList.forEach(item -> {
            contacts.put(item.getName(), item); // put method will replace the previous k,v with latest if duplicate k.
        });
        contacts.forEach((k,v) -> System.out.println(k+" : "+v));
        System.out.println("-----------------------");
        System.out.println(contacts.get("Geetha")); // if key not found it will return null, or default value in JDK 8.
        // example of default contact
        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Vihaan", defaultContact));
        System.out.println("--------------------------");
        //------------------------------------------------
        /*
        -> The put() also returns null if the element not found in map or the last value of existing element
           if it is there.
        > put() will add the elements in the map if there is no duplicate key. If it found the duplicate
          key then it will replace the existing [key,value] with latest [key,value].
         */
        contacts.clear();
//        System.out.println("reading initial contacts: "+contacts);
        for (Contact contact : fullList){
            Contact duplicate = contacts.put(contact.getName(), contact);
            if(duplicate != null){
//                System.out.println("Duplicate - "+duplicate);
//                System.out.println("Current "+contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate)); // merging contacts and phone to name
            }
        }
        System.out.println("--------------------------");
        System.out.println(contacts);

        /*
        putIfAbsent():
        -> If we don't want to merge the records and in addition we don't want to replace the initial entry of map
           by latest value. Then we can use putIfAbsent() method.
        -> putIfAbsent() won't put and update the element in the map, it will simply ignore the latest element if it is
           already found in the map.
        -> It returns null when the element is added for the first time. If the element found in the map, it will return
           the element from the map.
        -> The putIfAbsent() also take the 3rd params which is a lambda expression that takes two params and return result.


         */
        contacts.clear();
        for(Contact contact : fullList){
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            System.out.println("Contact -> "+contact);
            System.out.println("duplicate -> "+duplicate);
            if(duplicate != null){
                System.out.println("Inside if -> ");
                contacts.put(contact.getName(),contact.mergeContactData(duplicate));
            }
        }
        System.out.println(contacts);
        System.out.println("-------------------------------------------");
        contacts.clear();
        /*
        Map method : merge() added in JDK 8, [key,value, function]
         */
        fullList.forEach(contact ->
                contacts.merge(
                        contact.getName(),
                        contact,
//                        (previous,current) -> {
//                            System.out.println("prev -> "+previous + "current -> "+current);
//                            Contact merged = previous.mergeContactData(current);
//                            System.out.println("merged -> "+merged);
//                            return merged;
//                        }
                        // or
                        // (prev,curr) -> prev.mergeContactData(curr)
                        // or
                        Contact ::mergeContactData
                        )
        );
        System.out.println(contacts);
        /*
        Map methods: compute, computerIfPresent, computeIfAbsent added in JDK 8
        -> The compute and computeIfAbsent also take two values for their biFunction, but these represent key and
           current values associated with that, key(or null if the key is absent in case of computerIfAbsent),
           rather than two contact object.
        -> The compute methods give you a lot of functionality, for adding elements that aren't in the map,
           replacing values already keyed, resetting all elements in the map to some default value, or executing
           some code on the map elements that do exist.
         */
        System.out.println("------------computer()-------------------------------------");
        for(String contactName : new String[]{"Daisy Duck", "Daffy Duck"}){
            contacts.compute(contactName, (k,v) -> new Contact(k));
        }
        // it replaces the values with new contact, does not matter whether the key is present or not. values will be
        // replaced from the computed biFunction of compute method. Even though Robin Hood is existing contact but the
        // values is empty.
        contacts.forEach((k,v) -> System.out.println("k -> "+k+ ", v -> "+v));
        // computerIfAbsent()-----------
        System.out.println("----------computerIfAbsent()----------------------------------------");
        for(String contactName : new String[]{"Robin Hood", "Suman Khanra", "Geetha", "Daisy Duck", "Daffy Duck"}){
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k,v) -> System.out.println("k -> "+k+ ", v -> "+v));


        System.out.println("----------computeIfPreset()-----opposite of computerIfAbsent-------------------------");
        for(String contactName : new String[]{"Robin Hood", "Suman Khanra", "Geetha", "Daisy Duck", "Daffy Duck"}){
            contacts.computeIfPresent(contactName, (k,v) -> {
                v.addEmail("gmail");
                return v;
            });
        }
        contacts.forEach((k,v) -> System.out.println("k -> "+k+ ", v -> "+v));
        System.out.println("----------replaceAll()-------------------------------------------------------------");
        /*
        Map replace all take [k,v] and a function which should return an object of same type as the value.
        In this below example we are computing replaceAll() method on all the contacts and replacing values of all the
        contacts and replacing the particular email at the end.
         */

        contacts.replaceAll((k,v)->{
            System.out.println("k -> "+k+ " v -> "+v);
            String newEmail = k.replaceAll(" ", " ") +"@funplace.com";
            System.out.println("newEmail -> "+newEmail);
            v.replaceEmailIfExists("example1@gmail.com",newEmail);
            return v;
        });
        contacts.forEach((k,v) -> System.out.println("k -> "+k+ ", v -> "+v));
        System.out.println("----------replace()-------------------------------------------------------------");
        /*
        In the replace() we can only replace the particular contact based on key match of (key and value) match.
        There are two overloaded version of remove methods.
         */
        Contact suman = new Contact("Suman Khanra", "skhanra100@yahoo.com");
        Contact replacedContact = contacts.replace("Suman Khanra", suman); // returns the previous existing value
        System.out.println("Suman "+suman);
        System.out.println("replacedContact "+replacedContact);
        System.out.println(contacts);

        System.out.println("---------replace() overloaded boolean return------------------------------------------");

        assert replacedContact != null;
        Contact updatedSumanContact = replacedContact.mergeContactData(suman);
        System.out.println("Updated suman's contact "+updatedSumanContact);
        boolean success = contacts.replace("Suman Khanra", replacedContact, updatedSumanContact);
        if(success){
            System.out.println("Successfully replaced element.");
        }else{
            System.out.printf("Did not match on both key: %s and value: %s  %n ".formatted("Suman Khanra", replacedContact));
        }
        System.out.println(contacts);
        System.out.println("----------------remove()---------------------------------------------------------------");
        /*
        There are two overloaded remove() methods.
        -> The first remove() takes a key and return the value that was removed.
        -> The second remove() takes both key and value, it only removes the element from the map, if the key is in map,
           and the element to be removed equals the value passed. This method returns boolean.
         */
    }
}
