package org.example.deckofcardsjava;


import org.example.deckofcardsjava.exceptions.DeckOutOfCardsException;
import org.example.deckofcardsjava.model.Card;
import org.example.deckofcardsjava.model.Deck;

import java.util.Scanner;

public class DeckofCardsJavaApplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome! I have a new deck of cards here. Should I shuffle them? 'Y' or 'N'");
		Deck deck = new Deck();
		boolean run = true;
		while (run) {
			String response = in.nextLine();
			if (response == null) {
				System.out.println("Did you say something? Please respond with either 'Y' or 'N'");
			} else if (response.equalsIgnoreCase("Y")){
				deck.shuffle();
				System.out.println("The deck is shuffled. To get cards please type 'deal'. You can restart with a shuffled deck by typing 'shuffle'. To end type anything else.");
				boolean playing = true;
				while (playing) {
					String response2 = in.nextLine();
					if(response2.equalsIgnoreCase("shuffle")) {
						deck.shuffle();
						System.out.println("The deck is shuffled. To get cards please type 'deal'. You can restart with a shuffled deck by typing 'shuffle'. To end type anything else.");
					} else if(response2.equalsIgnoreCase("deal")) {
						try {
							Card c = deck.deal_card();
							System.out.println(String.format("You were dealt the %s of %s. Should I 'deal', 'shuffle', or type anything else to end.", c.getFaceValue(), c.getSuit()));
						} catch(DeckOutOfCardsException e) {
							System.out.println("The deck is out of cards. You will need to shuffle to deal more cards by typing 'shuffle'.");
						}
					} else {
						System.out.println("Thanks for playing. Good bye!");
						playing = false;
						run = false;
					}
				}
			} else if (response.equalsIgnoreCase("N")) {
				System.out.println("How boring... Good bye!");
				run = false;
			} else {
				System.out.println("What do you mean? Please respond with either Y or N");
			}
		}
	}
}
