package org.example.deckofcardsjava.model;

import org.example.deckofcardsjava.config.FaceValue;
import org.example.deckofcardsjava.config.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testCard() {
        Card card1 = new Card();
        Assertions.assertNull(card1.getSuit());
        Assertions.assertNull(card1.getFaceValue());

        Card card2 = new Card(Suit.SPADES, FaceValue.ACE);
        Assertions.assertEquals(Suit.SPADES, card2.getSuit());
        Assertions.assertEquals(FaceValue.ACE, card2.getFaceValue());
    }

    @Test
    public void testCardSettersAndGetters() {
        Card card = new Card();
        card.setSuit(Suit.SPADES);
        card.setFaceValue(FaceValue.ACE);
        Assertions.assertEquals(Suit.SPADES, card.getSuit());
        Assertions.assertEquals(FaceValue.ACE, card.getFaceValue());
    }

    @Test
    public void testCardHashCode() {
        Card card = new Card();
        card.setSuit(Suit.SPADES);
        card.setFaceValue(FaceValue.ACE);
        int hashCode1 = card.hashCode();
        int hashCode2 = card.hashCode();
        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testCardEquals() {
        Card card1 = new Card(Suit.SPADES, FaceValue.ACE);
        Assertions.assertEquals(card1, card1);
        Card card2 = new Card(Suit.SPADES, FaceValue.ACE);
        Assertions.assertEquals(card1, card2);
        Card nullCard = null;
        Assertions.assertNotEquals(card1, nullCard);
        Object o = new Object();
        Assertions.assertNotEquals(card1, o);
        Card card3 = new Card(Suit.HEARTS, FaceValue.JACK);
        Assertions.assertNotEquals(card1, card3);
    }

    @Test
    public void testCardToString() {
        Card card = new Card(Suit.SPADES, FaceValue.ACE);
        Assertions.assertEquals(card.toString(), "Card{suit=SPADES, faceValue=ACE}");
    }
}
