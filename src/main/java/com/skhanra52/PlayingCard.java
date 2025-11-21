package com.skhanra52;

public class PlayingCard {
    private final String suit;
    private final String face;
    private final int internalHash;

    public PlayingCard(String suit, String face){
        this.suit = suit;
        this.face = face;
        this.internalHash = 1;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

//    @Override
//    public boolean equals(Object o) {
//        if(this == o) return true;
//        if (!(o instanceof PlayingCard that)) return false; // "that" is nothing but PlayingCard p = (PlayingCard) o;
//        System.out.println("Checking the playing card hashcode ---> "+ Objects.equals(suit, that.suit) +" "+Objects.equals(face, that.face));
//        return Objects.equals(suit, that.suit) && Objects.equals(face, that.face);
//    }

    // passing the internalHash code manually so that all the cards hashcode will be 1,
    // and equals() method will  be called.
//    @Override
//    public int hashCode() {
//        return internalHash;
//    }

    // This is the usual hash code overridden method.
//    @Override
//    public int hashCode() {
//        return Objects.hash(suit, face, internalHash);
//    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof PlayingCard that)) return false;

        return internalHash == that.internalHash && suit.equals(that.suit) && face.equals(that.face);
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + face.hashCode();
        result = 31 * result + internalHash;
        return result;
    }
}
