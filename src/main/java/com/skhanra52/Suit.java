package com.skhanra52;

public enum Suit {
    CLUB, DIAMOND, HEART, SPADE;

    /*
    Returns the ASCII character to print for each suit.
    */
    public char getImage(){
        return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()]; // ordinal() provide position
    }
}


//Or
//public enum Suit {
//    CLUB('♣'),
//    DIAMOND('♦'),
//    HEART('♥'),
//    SPADE('♠');
//
//    private final char symbol;
//
//    Suit(char symbol) {
//        this.symbol = symbol;
//    }
//
//    public char getImage() {
//        return symbol;
//    }
//}

