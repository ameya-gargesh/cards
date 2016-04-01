package org.cards.model;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by maverick on 11-Mar-16.
 */
public class Deck {

    private List<Card> cardList;

    public Deck() {
        cardList = new ArrayList<Card>(52);
        for (Suit suit : Suit.values()) {
            for (FaceValue faceValue : FaceValue.values()) {
                cardList.add(new Card(faceValue, suit));
            }
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public Deck cut() {
        Collections.rotate(cardList, new Random().nextInt());
        cardList = new ArrayList<>(new LinkedHashSet<>(cardList));
        return this;
    }

    public Deck shuffle() {
        int numberOfIterations = (int) (System.currentTimeMillis() % 1000);
        for (int i = 0; i < numberOfIterations; i++) {
            Collections.shuffle(cardList);
        }
        cardList = new ArrayList<>(new LinkedHashSet<>(cardList));
        return this;
    }

    public List<Hand> deal(List<Hand> hands, int numberOfCards) {
        int numberOfPeople = hands.size();
        Iterator<Hand> handIterator = Iterables.cycle(hands).iterator();
        for (int i = 0; handIterator.hasNext() && i < (numberOfCards * numberOfPeople); i++) {
            handIterator.next().addCard(cardList.remove(0));
        }
        return hands;
    }
}
