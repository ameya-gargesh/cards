package org.cards.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by maverick on 11-Mar-16.
 */
public class CardTest {

    private Card card;

    @Before
    public void setUp() throws Exception {
        card = new Card(FaceValue.ACE, Suit.SPADES);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(card.toString()).isEqualTo("ACE of SPADES");
    }

    @Test
    public void testGetSuit() {
        assertThat(card.getSuit()).isEqualTo(Suit.SPADES);
    }

    @Test
    public void testGetFaceValue() {
        assertThat(card.getFaceValue()).isEqualTo(FaceValue.ACE);
    }
}