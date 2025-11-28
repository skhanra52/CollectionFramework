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
    }
}
