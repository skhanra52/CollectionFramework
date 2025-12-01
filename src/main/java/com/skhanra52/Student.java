package com.skhanra52;

import java.time.LocalDate;

record Course(String courseId, String name, String subject){}
record Purchase(String courseId, int studentIs, double price, int yr, int dayOfYear ){
    // Yr is the date of purchase and dayOfYear from 1-365.

    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr,dayOfYear);
    }

}

public class Student {
}
