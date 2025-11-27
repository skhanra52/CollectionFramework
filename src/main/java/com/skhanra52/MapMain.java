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
        // The put() also returns null if the element not found in map or the last value of existing element if it is there.
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


    }
}
