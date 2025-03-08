package no.ntnu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
  private final List<PlayingCard> deck;
  private final char[] suits = {'S', 'H', 'D', 'C'};
  private final Random random = new Random();   //Legger til en random generator

  public DeckOfCards() {
    deck = new ArrayList<PlayingCard>(); // Riktig ArrayList<>-bruk
    generateDeck();
  }

  private void generateDeck() {
    for (char suit : suits) {
      for (int rank = 1; rank <= 13; rank++) {
        deck.add(new PlayingCard(suit, rank));
      }
    }
  }

  public List<PlayingCard> getDeck() {
    return deck;
  }

  public PlayingCard drawCard() {
    if (!deck.isEmpty()) {
      return deck.remove(0); // Fjernet feil "index: 0"
    }
    return null;
  }

  //  NY METODE: Trekker n tilfeldige kort fra kortstokken
  public List<PlayingCard> dealHand(int n) {
    if (n < 1 || n > deck.size()) {
      throw new IllegalArgumentException("Antall kort må være mellom 1 og " + deck.size());
    }

    List<PlayingCard> hand = new ArrayList<>();
    System.out.println(" Trekker " + n + " tilfeldige kort...");

    for (int i = 0; i < n; i++) {
      int randomIndex = random.nextInt(deck.size());      // Tilfeldig indeks
      PlayingCard drawnCard = deck.remove(randomIndex);   // Fjerner kortet fra kortstokken
      hand.add(drawnCard);                                // Legger til i hånden
      System.out.println(" Trukket kort: " + drawnCard);  // Debug
    }

    System.out.println(" Ny kortstokk-størrelse: " + deck.size());
    return hand;
  }
}
