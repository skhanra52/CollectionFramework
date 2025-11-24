/**
 * The ContactData class is going to emulate getting data from an external source, but instead of external source
 *         we will set this data up with two different text books, in the format below
 *         Phone Data                          |   Email Data
 *         ---------------------------------------------------------------------------
 *         Suman Khanra, 8553242342            |  Geetha, geethaxyz@gmail.com
 *         Charlie Brown,3334445556            |  Suman Khanra, example1@gmail.com
 *         Robin Hood,   4562341234            |  Geetha, geethaxyz@gmail.com
 *         Charlie Brown,3334445556
 *         -> Create a method name getData(), that takes String type(either "phone" or "email")and return a List of contact
 *         -> We are going to use the scanner to parse the data in this text blocks.
 *         */

package com.skhanra52;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactData {

    private static final String phoneData = """
            Suman Khanra, 8553242342
            Charlie Brown,3334445556
            Robin Hood,   4562341234
            Charlie Brown,3334445557
            """;
    private static final String emailData = """
            Geetha, geethaxyz@gmail.com
            Suman Khanra, example1@gmail.com
            Geetha, geethaxyz@gmail.com
            Suman Khanra, example2@gmail.com
            Robin Hood,   example3@gmail.com
            """;


    public static List<Contact> getData(String type){
        List<Contact> dataList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(type.equals("phone") ? phoneData : emailData ); // mocking data instead of reading from file
        while(scanner.hasNext()) {
            String[] data = scanner.nextLine().split(",");
            Arrays.asList(data).replaceAll(String::trim);
            if(type.equals("phone")){
                dataList.add(new Contact(data[0], Long.parseLong(data[1])));
            }else if(type.equals("email")){
                dataList.add(new Contact(data[0], data[1]));
            }
        }
        return dataList;
    }
}
