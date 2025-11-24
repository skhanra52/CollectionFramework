/**
 * We have tp create a Contact class, that has the fields, name , a String, email, and a HashSet of String, phones,
 *        another HashSet of String.
 *        Fields:
 *         String name;
 *         long phone;
 *         Set<String> email = new HashSet<>();
 *         Set<String> phones = new HashSet<>();
 *        Constructor:
 *         Contact(String name);
 *         Contact(String name, String email);
 *         Contact(String name, long phone);
 *         Contact(String name, String email, long phone);
 *        Methods:
 *         String getName();
 *         String toString();
 *         Contact mergeContactData(Contact contact)
 *
 *         The last constructor should do the following:
 *         -> Add the email argument to the email set, if email is not null.
 *         -> Transform the phone argument, a long(if it is not zero), to a String in the format (123) 456-7890.
 *         -> Add transformed phone to the phones set.
 *        Finally include the method call mergeContactData(), that takes a contact, and return a new contact instance,
 *        which merges the current instance with the contact passed.
 *        */
package com.skhanra52;

import java.util.*;

public class Contact {
    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name){
        this(name, null);
    }
    public  Contact(String name, String email){
        this(name,email,0);
    }
    public Contact(String name, long phone){
        this(name,null,phone);
    }
    public Contact(String name, String email, long phone){
        this.name = name;
        if (email != null){
            emails.add(email);
        }
        if(phone>0){
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0,3),p.substring(4,6),p.substring(6));
            phones.add(p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails,phones);
    }

    // This data can be read from the database/or some file or some kind of service provider.
    public Contact mergeContactData(Contact contact){

        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails); // cloning the data from the current instance
        newContact.phones = new HashSet<>(this.phones); // cloning the data from the current instance
        newContact.emails.addAll(contact.emails); // adding the emails passed through the method arguments
        newContact.phones.addAll(contact.phones); // adding the emails passed through the method arguments
        return newContact;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getName(), contact.getName()) ;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Contact contact = (Contact) o;
//        return Objects.equals(getName(), contact.getName()) && Objects.equals(emails, contact.emails) && Objects.equals(phones, contact.phones);
//    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(getName());
    }

    /*
     * It will generate the companyEmail with the current instances of email set.
     */
    public void addEmail(String companyName){
        String[] names = name.split(" ");
        System.out.println("names"+ Arrays.toString(names));
        String email = "%c%s@%s.com".formatted(name.charAt(0),names[names.length - 1],
                companyName.replaceAll(" ","").toLowerCase());
        System.out.println("Emails "+email);
        if(!emails.add(email)){
            System.out.println(name+ " Name is already exist..");
        }else{
            System.out.println(email+ " email has been added to the contact");
        }
    }

    public void replaceEmailIfExists(String oldEmail, String newEmail){
        if(emails.contains(oldEmail)){
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }


}
