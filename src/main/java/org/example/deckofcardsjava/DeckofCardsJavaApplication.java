package org.example.deckofcardsjava;


import java.util.Scanner;

public class DeckofCardsJavaApplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome! I have a new deck of cards here. Should I shuffle them? Y/N");
		boolean run = true;
		while (run) {
			String response = in.nextLine();
			if (response == null) {
				System.out.println("Did you say something? Please respond with either Y or N");
			} else if (response.equalsIgnoreCase("Y")){
				System.out.println("Welcome to DeckofCardsJavaApplication");
			} else if (response.equalsIgnoreCase("N")) {
				System.out.println("How boring... Good bye!");
				run = false;
			} else {
				System.out.println("What do you mean? Please respond with either Y or N");
			}
		}
	}
}
