package org.cards.model;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by maverick on 11-Mar-16.
 */
public class Hand {
    private Set<Card> cards;

    private OptionalInt maximumSize;

    public Hand(Comparator<Card> comparator, OptionalInt maximumSize) {
        this.cards = new TreeSet<>(comparator);
        this.maximumSize = maximumSize;
    }

    public Hand addCard(Card card) {
        maximumSize.ifPresent(value -> {
            if (value <= cards.size()) {
                throw new IllegalStateException("The Hand is filled to maximum capacity");
            } else {
                this.cards.add(card);
            }
        });
        return this;
    }

    public Hand dropCard(Card card) {
        cards.remove(card);
        return this;
    }

    public int size() {
        return cards.size();
    }
}
