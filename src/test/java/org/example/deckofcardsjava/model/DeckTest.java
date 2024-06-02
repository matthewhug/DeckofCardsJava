package org.example.deckofcardsjava.model;


import org.example.deckofcardsjava.config.FaceValue;
import org.example.deckofcardsjava.config.Suit;
import org.example.deckofcardsjava.exceptions.DeckOutOfCardsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

public class DeckTest {

    private final List<Card> TEST_CARDS = Arrays.asList(new Card(Suit.DIAMONDS, FaceValue.ACE), new Card(Suit.HEARTS, FaceValue.ACE));

    @Test
    public void testDeckAndGetUnsetCards() {
        Deck deck = new Deck();
        Assertions.assertInstanceOf(List.class, deck.getCards());
        Assertions.assertEquals(0, deck.getCards().size());
    }

    @Test
    public void testDeckSetAndGetCards() {
        Deck deck = new Deck();
        deck.setCards(TEST_CARDS);
        Assertions.assertInstanceOf(List.class, deck.getCards());
        Assertions.assertEquals(2, deck.getCards().size());
        Assertions.assertTrue(deck.getCards().containsAll(TEST_CARDS));
    }

    @Test
    public void testDeckHashCode() {
        Deck deck = new Deck();
        deck.setCards(TEST_CARDS);
        int hashCode1 = deck.hashCode();
        int hashCode2 = deck.hashCode();
        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testDeckEquals() {
        Deck deck1 = new Deck();
        deck1.setCards(TEST_CARDS);
        Assertions.assertEquals(deck1, deck1);
        Deck nullDeck = null;
        Assertions.assertNotEquals(deck1, nullDeck);
        Object o = new Object();
        Assertions.assertNotEquals(deck1, o);
        Deck deck2 = new Deck();
        deck2.setCards(TEST_CARDS);
        Assertions.assertEquals(deck1, deck2);
        Deck deck3 = new Deck();
        Assertions.assertNotEquals(deck1, deck3);
    }

    @Test
    public void testDeckToString() {
        Deck deck = new Deck();
        Assertions.assertEquals(deck.toString(), "Deck{cards=null}");
    }

    @Test
    public void testShuffleExpectedSize() {
        Deck deck = new Deck();
        deck.shuffle();
        int expectedSize = Suit.values().length * FaceValue.values().length;
        Assertions.assertEquals(expectedSize, deck.getCards().size());
    }

    @Test
    public void testShuffleExpectedCardsWithExpectedOccurrences() {
        int expectedOccurrences = 1;
        Deck deck = new Deck();
        deck.shuffle();
        Map<Integer, Integer> expectedCards = Stream.of(Suit.values())
                .flatMap(s -> Stream.of(FaceValue.values())
                        .map(cv -> new Card(s, cv)))
                .collect(Collectors.toMap(
                        Card::hashCode,
                        card -> 0
                ));
        for (Card c: deck.getCards()) {
            int actualOccurrences = expectedCards.get(c.hashCode()) + 1;
            if (expectedOccurrences < actualOccurrences) {
                fail(String.format("%s has already occurred %d time(s)", c, actualOccurrences));
            } else {
                expectedCards.put(c.hashCode(), actualOccurrences);
            }
        }
    }

    @Test
    public void testShuffleRandomness() {
        Deck deck1 = new Deck();
        deck1.shuffle();

        Deck deck2 = new Deck();
        deck2.shuffle();
        int numCardsSameIndex = 0;
        for (int i = 0; i < deck1.getCards().size(); i++) {
            if (deck1.getCards().get(i).equals(deck2.getCards().get(i))) {
                numCardsSameIndex++;
            }
        }
        // This could result in a false positive but is extremely unlikely.
        Assertions.assertNotEquals(1, (numCardsSameIndex / deck1.getCards().size()));
    }

    @Nested
    class DealCardTestNest{
        private final Deck shuffledDeck = new Deck();
        private final int expectedDeals = Suit.values().length * FaceValue.values().length;

        @BeforeEach
        public void init() {
            this.shuffledDeck.shuffle();
        }

        @Test
        public void testDeal_card() throws DeckOutOfCardsException {
            for (int i = 0; i < expectedDeals; i++) {
                Card c = shuffledDeck.deal_card();
                Assertions.assertInstanceOf(Card.class, c);
                Assertions.assertInstanceOf(Suit.class, c.getSuit());
                Assertions.assertInstanceOf(FaceValue.class, c.getFaceValue());
            }
        }

        @Test
        public void testDeal_cardThrowsException() throws DeckOutOfCardsException {
            Deck deck = new Deck();
            deck.shuffle();
            for(int i = 0; i < expectedDeals; i++) {
                deck.deal_card();
            }
            Assertions.assertThrows(DeckOutOfCardsException.class, deck::deal_card);
        }
    }
}
