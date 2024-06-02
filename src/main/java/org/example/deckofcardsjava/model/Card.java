package org.example.deckofcardsjava.model;

import org.example.deckofcardsjava.config.FaceValue;
import org.example.deckofcardsjava.config.Suit;

import java.util.Objects;

/**
 * Represents a Card.
 *
 * <p>This class provides constructors to initialize an empty Card or a Card with a Suit and CardValue.
 * As well as methods to set/get the Suit and set/get the CardValue.
 */
public class Card {

    private Suit suit;
    private FaceValue faceValue;

    public Card(){}

    public Card(Suit suit, FaceValue faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(FaceValue faceValue) {
        this.faceValue = faceValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && faceValue == card.faceValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, faceValue);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", faceValue=" + faceValue +
                '}';
    }
}
