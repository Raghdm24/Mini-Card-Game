package no.ntnu;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    DeckOfCards deck = new DeckOfCards();

    System.out.println("Kortstokken inneholder " + deck.getDeck().size() + " kort.");
    System.out.println("Første kort i konstruktøren: " + deck.getDeck().get(0));

    //Trekker et kort og viser det
      PlayingCard drawnCard = deck.drawCard();
    System.out.println(("Trakk kortet: " + drawnCard));
    System.out.println("Kortstokken har nå " + deck.getDeck().size() + " kort igjen.");

    //  TREKKER EN HÅND MED 5 TILFELDIGE KORT
    List<PlayingCard> hand = deck.dealHand(5);
    System.out.println("Trukket hånd: " + hand);

    System.out.println("Kortstokken har nå " + deck.getDeck().size() + " kort igjen.");
  }
}