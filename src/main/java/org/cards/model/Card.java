package org.cards.model;

import java.util.Comparator;

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

    public final static Comparator<Card> FACEVALUE_COMPARATOR = (o1, o2) -> o1.faceValue.ordinal() - o2.faceValue.ordinal();

    public final static Comparator<Card> SUIT_BASED_LEFT_HIGH_FACEVALUE_COMPARATOR = (o1, o2) -> {
        return o1.suit.equals(o2.suit) ? o2.faceValue.ordinal() - o1.faceValue.ordinal() : o1.suit.ordinal() - o2.suit.ordinal();
    };

    public final static Comparator<Card> ACES_HIGH_LEFT_HIGH_FACEVALUE_COMPARATOR = (o1, o2) -> {
        return o2.faceValue.equals(FaceValue.ACE) ? 1 : o2.faceValue.ordinal() - o1.faceValue.ordinal();
    };

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
        int hash = 31 * faceValue.hashCode();
        hash += (Math.pow(31, 2) * suit.hashCode());
        return hash;
    }
}
