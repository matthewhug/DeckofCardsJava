package org.example.deckofcardsjava.model;

import org.example.deckofcardsjava.config.FaceValue;
import org.example.deckofcardsjava.config.Suit;
import org.example.deckofcardsjava.exceptions.DeckOutOfCardsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a deck of cards.
 *
 * <p>This class provides methods to shuffle the deck and deal cards from it.
 * The deck is initially empty and can be populated and shuffled using the
 * shuffle method.
 */
public class Deck {

    private List<Card> cards;

    public Deck() {}

    /**
     * Gets the List of cards from Deck
     *
     * <p>This method returns the list of Cards from Deck.
     * If the cards is null, it will return a new empty ArrayList of Cards.
     *
     * @return List of Cards
     */
    public List<Card> getCards() {
        if(this.cards == null) {
            this.cards = new ArrayList<>();
        }
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    /**
     * Shuffles the deck.
     *
     * <p>This method creates a new deck of cards by generating one card for each
     * combination of Suit and CardValue enums, and then shuffles the
     * deck by randomly removing cards from the unshuffled list and adding them to
     * the shuffled list. After shuffling, the deck is set to the new shuffled list of cards.
     */
    public void shuffle() {
        // For each combination of Suit and CardValue enums create a card object to add to the unshuffled Cards list.
        List<Card> unshuffledCards = Stream.of(Suit.values())
                .flatMap(s -> Stream.of(FaceValue.values())
                        .map(cv -> new Card(s, cv)))
                .collect(Collectors.toList());
        List<Card> shuffledCards = new ArrayList<>();
        // Loop until all the unshuffledCards have been removed at random and added to the shuffledCards list
        while (!unshuffledCards.isEmpty()) {
            int randomCardIndex = (int) (Math.random() * (unshuffledCards.size() - 1));
            shuffledCards.add(unshuffledCards.remove(randomCardIndex));
        }
        // Setting the Deck object's cards to shuffledCards
        this.setCards(shuffledCards);
    }

    /**
     * Deals a card from the deck.
     *
     * <p>This method removes and returns the first card from the deck.
     * If the deck is empty, it throws a DeckOutOfCardsException.
     *
     * @return the first Card object from the deck
     * @throws DeckOutOfCardsException if the deck is out of cards to deal
     */
    public Card deal_card() throws DeckOutOfCardsException {
        if (!this.cards.isEmpty()) {
            return this.getCards().remove(0);
        }
        throw new DeckOutOfCardsException("The deck is out of cards to deal. Call shuffle() to deal more cards.");
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
