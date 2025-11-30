package com.skhanra52;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapViewMain {
    public static void main(String[] args) {
        Map<String,Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(element -> contacts.put(element.getName(),element));
        ContactData.getData("email").forEach(element -> contacts.put(element.getName(),element));

        Set<String> keysView = contacts.keySet();
        System.out.println("keysView -> "+keysView);

        Set<String> copyOfkeys = new TreeSet<>(contacts.keySet());
        System.out.println("CopyOfkeys ->  "+copyOfkeys);

        if(contacts.containsKey("Suman Khanra")){
            System.out.println("The entry is found");
        }else {
            System.out.println("Entry not found");
        }
        // if you want to remove an entry, you can directly remove the key from the keySet which will removed key/value
        // pair from the Map.
        System.out.println("----------------------------------------");
        keysView.remove("Suman Khanra");
        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));
        System.out.println("----------------------------------------");
        // Because it a copy of keySet() it does not modify the actual map. So the key/value for "Geetha" will remain
        // as it is in the map.
        copyOfkeys.remove("Geetha");
        contacts.forEach((k,v) -> System.out.println(k +"-> "+v));

        System.out.println("--------------------------------------------");
        Set<Map.Entry<String, Contact>> entriesView =  contacts.entrySet();
        entriesView.forEach((k) -> System.out.println("Entry -> "+k));


    }
}
