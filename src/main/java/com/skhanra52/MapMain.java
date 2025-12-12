package com.skhanra52;

import java.util.*;

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
        System.out.println("----------------remove()---------------------------------------------------------------");
        /*
        HashMap's Implementation:-------------------------------
            <<Interface>>/methods available       implementation        <<Interface>>           Implementation
            ----------------------------------------------------        ------------------------------------------------
            Map<K,V>            -------->         new HashMap<>();      Map.Entry<K,V>  ---->   HashMap.Node(K,V)
                                                  table:Node[]          <------------------     key: K; value: V
            keySet(): Set<K>
            entrySet<>: Set<Map.Entry<K,V>>
            values(): Collection<V>

            -> There is a static nested interface on the Map interface, and that its name is Entry.
            -> Concrete classes implements both Map interface and Map.Entry interface.
            -> The HashMap implement Map, and has a static nested class, Node, that implements the Map.Entry interface.
            -> The HashMap maintains an array of these Nodes, in a field called table, whose size is managed by Java.
               and the whole indices are determined by Hashing function. For this reason HashMap is not ordered.

            The Map view Collection:-------------------------------------------------
            -> There are three view collection we get from the Map, these are keySet(), entrySet() and values().
            -> Map keys can't be duplicate, these keys are retrieved as a set view, by calling the keySet() method
               on any map object. keySet(): Set<K>
            -> Each key value pair is stored as an instance of an Entry, and the combination of key and values will
               be unique, because the key is unique.
            -> A Set view of these entries, or nodes, in case of the HashMap, can be retrieved from the method
               entrySet().
            -> Finally, values are stored and referred by the key, and value can be duplicates, meaning, multiple keys
               could refer a single value.
            -> We can get a collection view of these from the values() method, on a Map instance.


            Note: note to see the implementation of keySet()/entrySet()/values() refer MapViewMain class.

         */
        /*
        Practice 09-dec-25
        --------------------
        Map creation:-----------
        Map<String,String> sampleMap = new HashMap<>();
        Map methods:------------
        sampleMap.put("Suman","BE");




         */
        // Step 1 : Create
        Map<String,String> sampleMap = new HashMap<>();
        sampleMap.put("Suman","BE");
        sampleMap.put("Geetha","BE");
        sampleMap.put("Vihu", "LKG");
        System.out.println("sample map: "+sampleMap);

        // Step 2: Read
        System.out.println("Read operation of Suman : "+sampleMap.get("Suman"));
        System.out.println("Read operation of Suman : "+sampleMap.getOrDefault("missingKey","Graduate"));
        if(sampleMap.containsKey("Suman")){
            System.out.println("The key is available");
        }else{
            System.out.println("The key is not available");
        }
        Set<String> keys = sampleMap.keySet();
        keys.forEach(System.out::println);
        Set<Map.Entry<String,String>> entries = sampleMap.entrySet();
//        entries.forEach(System.out::println);
        for(Map.Entry<String,String> entry : entries){
            System.out.println("key: "+entry.getKey()+ " and value: "+entry.getValue());
        }

        // Step 3: Update/Write/Insert
//        Map<String, String> copySample = new HashMap<>(); // same can be achieved by below line in one go
//        copySample.putAll(Map.copyOf(sampleMap));
        Map<String, String> copySample = new HashMap<>(Map.copyOf(sampleMap));
        copySample.putAll(new HashMap<>(Map.ofEntries(
                Map.entry("Surajit","Graduate"),
                Map.entry("Swagata", "Graduate"),
                Map.entry("Shijo", "BE")
            ))
        );
        System.out.println("copy of sample: "+copySample);

        List<String> srManager = new ArrayList<>(List.of("Sr manager"));
        List<String> consultant = new ArrayList<>(List.of("Consultant", "Doctor", "Psychologist"));
        Map<String, List<String>> mergeCopy = new HashMap<>(Map.of(
                "Surajit", srManager,
                "Swagata", consultant
        ));

        for(Map.Entry<String, String> entry : copySample.entrySet()){
            String key = entry.getKey();
            List<String> value = new ArrayList<>(List.of(entry.getValue()));
            System.out.println(key);
            System.out.println(entry.getValue());
            mergeCopy.merge(key, value, (prevValue,currValue) -> {
              prevValue.addAll(currValue);
              return prevValue;
            });
        }
        mergeCopy.forEach((k,v) -> System.out.println(k +" : " + v));

        // Converting list to String[]
        List<String> list = List.of("A", "B", "C");
//        String[] arr = list.toArray(new String[0]);
        String[] arr = list.toArray(String[]::new);
        System.out.println(Arrays.toString(arr));

        // Example of compute method.

        List<PersonLog> logs = new ArrayList<>(List.of(
                new PersonLog("U101", "login"),
                new PersonLog("U102","logout"),
                new PersonLog("U101", "login"),
                new PersonLog("U103","login"),
                new PersonLog("U101","login" ),
                new PersonLog("U102","login" )
        ));

        Map<String, Integer> loginInfo = new HashMap<>();
        Map<String,Integer> logoutInfo = new HashMap<>();
        for(PersonLog person : logs){
            if(person.getAction().equals("login")){
                loginInfo.compute(person.getUserId(), (userId, action) -> {
                    System.out.println(action);
                    if(action == null){
                        action = 1;
                    }else{
                        action+=1;
                    }
                    return action;
                });
            }else{
                logoutInfo.compute(person.getUserId(), (userId, actionCount) ->
                        actionCount == null ? 1 : actionCount+1);
            }
        }

        loginInfo.forEach((k,v) -> System.out.println(k+" -> "+v));
        System.out.println("--------logout-----------------");
        logoutInfo.forEach((k,v) -> System.out.println(k+" -> "+v));




    }
}
