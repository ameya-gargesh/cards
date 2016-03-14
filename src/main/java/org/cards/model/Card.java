package org.cards.model;

/**
 * Created by maverick on 11-Mar-16.
 */
public class Card {
    private FaceValue faceValue;
    private Suit suit;

    public Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return faceValue.name() + " of " + suit.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card otherCard = (Card) obj;
            return this.faceValue.equals(otherCard.faceValue) &&
                    this.suit.equals(otherCard.suit);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + faceValue.hashCode();
        hash = hash * 31 + suit.hashCode();
        return hash;
    }
}
