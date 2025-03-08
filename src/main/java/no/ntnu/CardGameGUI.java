package no.ntnu;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CardGameGUI extends Application {
  private DeckOfCards deck; // Kortstokk
  private List<PlayingCard> currentHand; // Lagrer trukket hånd
  private TextField sumField; // Felt for sum
  private TextField heartsField; // Felt for hjerterkort
  private TextField queenOfSpadesField; // Felt for spar dame
  private TextField flushField; // Felt for flush

  @Override
  public void start(Stage primaryStage) {
    deck = new DeckOfCards(); // Initialiserer kortstokken én gang

    VBox layout = new VBox(10);
    TextArea carDisplay = new TextArea();
    carDisplay.setEditable(false);
    carDisplay.setPrefSize(300, 150);

    TextArea checkResultDisplay = new TextArea();
    checkResultDisplay.setEditable(false);
    checkResultDisplay.setPrefSize(300, 100);

    sumField = new TextField("Sum: -");
    sumField.setEditable(false);
    sumField.setPrefWidth(100);

    heartsField = new TextField("Hearts: ");
    heartsField.setEditable(false);
    heartsField.setPrefWidth(200);

    queenOfSpadesField = new TextField("Queen of Spades: -");
    queenOfSpadesField.setEditable(false);
    queenOfSpadesField.setPrefWidth(200);

    flushField = new TextField("Flush: -");
    flushField.setEditable(false);
    flushField.setPrefWidth(200);

    Button dealHandButton = new Button("Deal Hand");
    Button checkHandButton = new Button("Check Hand");

    // EventHandler for "Deal Hand"-knappen
    dealHandButton.setOnAction(e -> {
      currentHand = deck.dealHand(5); // Lagrer den trukkede hånden

      // Viser kortene i TextArea
      StringBuilder handText = new StringBuilder("Trukket hånd:\n");
      for (PlayingCard card : currentHand) {
        handText.append(card.toString()).append("\n");
      }
      carDisplay.setText(handText.toString());

      // Resetter sjekkresultatene
      checkResultDisplay.setText("");
      sumField.setText("Sum: -");
      heartsField.setText("Hearts: -");
      queenOfSpadesField.setText("Queen of Spades: -");
      flushField.setText("Flush: -");
    });

    // EventHandler for "Check Hand"-knappen
    checkHandButton.setOnAction(e -> {
      if (currentHand == null || currentHand.isEmpty()) {
        checkResultDisplay.setText("Ingen hånd trukket enda!");
        sumField.setText("Sum: -");
        return;
      }

      // Sjekker om hånden inneholder hjerter
      boolean hasHeart = currentHand.stream()
          .anyMatch(card -> card.getSuit() == 'H');

      // Sjekker om hånden inneholder spar dame
      boolean hasQueenOfSpades = currentHand.stream()
          .anyMatch(card -> card.getSuit() == 'S' && card.getRank() == 12);

      // Beregner summen av kortverdiene
      int sum = currentHand.stream()
          .mapToInt(PlayingCard::getRank)
          .sum();

      // Filtrer ut alle kort som er av typen hjerter
      String hearts = currentHand.stream()
          .filter(card -> card.getSuit() == 'H')
          .map(PlayingCard::toString)
          .reduce((a, b) -> a + " " + b)
          .orElse("No Hearts");

      // Sjekker om hånden er en flush (alle kort har samme farge)
      boolean isFlush = currentHand.stream()
          .map(PlayingCard::getSuit)
          .distinct()
          .count() == 1;

      // Oppdater GUI-feltene
      checkResultDisplay.setText("Har hjerter: " + (hasHeart ? "Ja" : "Nei"));
      sumField.setText("Sum: " + sum);
      heartsField.setText("Hearts: " + hearts);
      queenOfSpadesField.setText("Queen of Spades: " + (hasQueenOfSpades ? "Yes" : "No"));
      flushField.setText("Flush: " + (isFlush ? "Yes" : "No"));
    });

    // Legger til alle elementene i GUI-en
    layout.getChildren().addAll(carDisplay, dealHandButton, checkHandButton, checkResultDisplay, sumField, heartsField, queenOfSpadesField, flushField);

    Scene scene = new Scene(layout, 400, 400);
    primaryStage.setTitle("Kortspill GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

