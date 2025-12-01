package com.skhanra52;

import java.util.*;

public class MapViewMain {
    public static void main(String[] args) {
        Map<String,Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(element -> contacts.put(element.getName(),element));
        ContactData.getData("email").forEach(element -> contacts.put(element.getName(),element));
//        contacts.forEach((k,v)->System.out.println(k +" => "+v));

        Set<String> keysView = contacts.keySet();
        System.out.println("keysView -> "+keysView);

        System.out.println("---------copy of keySet-------------------------------");
        Set<String> copyOfkeys = new TreeSet<>(contacts.keySet());
        System.out.println("CopyOfkeys ->  "+copyOfkeys);
        /*
        Check the availability if the key entry in the map using map.contains(key)
         */
        if(contacts.containsKey("Suman Khanra")){
            System.out.println("The entry is found");
        }else {
            System.out.println("Entry not found");
        }
        /* if you want to remove an entry, you can directly remove the key from the keySet which will removed key/value
         pair from the Map.
         */
        System.out.println("----------------------------------------");
//        keysView.remove("Suman Khanra");
//        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));
        System.out.println("----------------------------------------");
        /* Because it a copy of keySet() it does not modify the actual map. So the key/value for "Geetha" will remain
        as it is in the map.
         */
//        copyOfkeys.remove("Geetha");
//        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));
        System.out.println("----------------------------------------");
        /* removeAll/retainAll methods-------------
        Let's say the next, we would like to purge some old contacts like  "Suman Khanra", "Robin Hood", we can use
        removeAll() method to remove these contact from the Map by applying the method on keySet. Or we can use the
        retainAll() method on keySet and provide the keys we would like to keep in the map.
         */
        contacts.forEach((k,v)-> System.out.println(k +" -> "+v));
        System.out.println("-----retainAll------------------------------------");
        keysView.retainAll(List.of("Suman Khanra", "Robin Hood"));
        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));
        System.out.println("----------------------------------------");
//        keysView.removeAll(List.of("Suman Khanra", "Robin Hood"));
//        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));
        System.out.println("----------------------------------------");
        /*
        Functionality available to set returned from keySet()
        -> The set return from keySet method, is backed by map.
        -> This means change to the map reflected in the set, and vice versa.
        -> The set support element removal, which removes corresponding mapping from the map.
        -> You can use the methods remove()/removeAll()/retailAll()/clear()
        -> It does not support add/addAll() operations.
         */
        keysView.clear();
//        System.out.println(contacts);
        ContactData.getData("phone").forEach(contact -> contacts.put(contact.getName(),contact));
        ContactData.getData("email").forEach(contact -> contacts.put(contact.getName(),contact));
        contacts.forEach((k,v)-> System.out.println(k +" -> "+v));
        System.out.println("keysView -> "+keysView);
        System.out.println("-------------------values()------------------");
        Collection<Contact> values = contacts.values();
        values.forEach(System.out::println);

        System.out.println("--------------------------------------------");
        Set<Map.Entry<String, Contact>> entriesView =  contacts.entrySet();
        entriesView.forEach((k) -> System.out.println("Entry -> "+k));

    }
}
