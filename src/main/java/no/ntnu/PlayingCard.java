package no.ntnu;

public class PlayingCard {     //enkelt kortspill
  private final char suit; //(kortspill) hjerter, spar, ruter, kløver
  private final int rank;  //Tall fra 1 til 13 (konge)

  public PlayingCard(char suit, int rank) {
    this.suit=suit;
    this.rank= rank;
  }

  public char getSuit() {
    return  suit;
  }

  public int getRank() {
    return rank;
  }

  @Override
  public String toString() {
      return suit + String.valueOf(rank);
    }

  }

