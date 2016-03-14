package org.cards.model;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by maverick on 11-Mar-16.
 */
public class DeckTest {

    private Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @Test
    public void testGetCardList() throws Exception {
        assertThat(deck.getCardList().size()).isEqualTo(52);
        assertThat(deck.getCardList().get(0)).isEqualTo(new Card(FaceValue.ACE, Suit.SPADES));
        assertThat(deck.getCardList().get(51)).isEqualTo(new Card(FaceValue.KING, Suit.CLUBS));
    }

    @Test
    public void testCut() throws Exception {
        deck.cut();
        assertThat(deck.getCardList().size()).isEqualTo(52);
        int index = deck.getCardList().indexOf(new Card(FaceValue.ACE, Suit.SPADES));
        List<Card> temporaryList = (List<Card>)((ArrayList<Card>) deck.getCardList()).clone();
        Collections.rotate(temporaryList, 52 - index);
        assertThat(temporaryList.get(0)).isEqualTo(new Card(FaceValue.ACE, Suit.SPADES));
    }

    @Test
    public void testCutIsRandom() {
        deck.cut();
        int index = deck.getCardList().indexOf(new Card(FaceValue.ACE, Suit.SPADES));
        Deck deck2 = new Deck().cut();
        int index2 = deck2.getCardList().indexOf(new Card(FaceValue.ACE, Suit.SPADES));
        assertThat(index).isNotEqualTo(index2);
    }

    @Test
    public void testShuffleIsRandom() {
        deck.shuffle();
        Card card = deck.getCardList().get(0);
        deck.shuffle();
        assertThat(deck.getCardList().get(0)).isNotEqualTo(card);
        assertThat(deck.getCardList().size()).isEqualTo(52);
    }

    @Test
    public void testShuffleRetainsDistinctCards() {
        List<Card> cardList = (List<Card>)((ArrayList<Card>)deck.getCardList()).clone();
        deck.shuffle();
        Set<Card> cardSet = new LinkedHashSet<>(deck.getCardList());
        assertThat(cardSet.size()).isEqualTo(52);
    }

    @Test
    public void testDeal() {
        List<Hand> hands = new ArrayList<Hand>(4);
        for (int i = 0; i < 4; i++) {
            hands.add(new Hand(cardComparator, OptionalInt.of(3)));
        }
        hands = deck.shuffle().deal(hands, 3);
        assertThat(hands.size()).isEqualTo(4);
        assertThat(hands.get(0).size()).isEqualTo(3);
        assertThat(hands.get(1).size()).isEqualTo(3);
        assertThat(hands.get(2).size()).isEqualTo(3);
        assertThat(hands.get(3).size()).isEqualTo(3);

    }

    private Comparator<Card> cardComparator = (o1, o2) -> {
        if (o1.getFaceValue().equals(o2.getFaceValue())) {
            return o1.getSuit().compareTo(o2.getSuit());
        } else {
            return -o1.getFaceValue().compareTo(o2.getFaceValue());
        }
    };
}