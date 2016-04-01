package org.cards.model;

import java.util.*;
import java.util.stream.Collectors;

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
        if (maximumSize.isPresent()) {
            if (maximumSize.getAsInt() <= cards.size()) {
                throw new IllegalStateException("The Hand is filled to maximum capacity");
            } else {
                this.cards.add(card);
            }
        } else {
            this.cards.add(card);
        }
        return this;
    }

    public Hand dropCard(Card card) {
        cards.remove(card);
        return this;
    }

    public static List<Hand> initializeHands(int numberOfPeople, Comparator<Card> comparator, OptionalInt maximumSize) {
        List<Hand> hands = new ArrayList<>(numberOfPeople);
        for (int i = 0; i < numberOfPeople; i++) {
            hands.add(new Hand(comparator, maximumSize));
        }
        return hands;
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.stream().map(Card::toString).collect(Collectors.joining(", ", "{", "}"));
    }
}
