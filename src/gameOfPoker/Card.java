package gameOfPoker;// Rysunek 7.9. Card.java
// Klasa Card reprezentuje kartę do gry.

public class Card {
   private final String face; // Figura karty ("As", "Dwójka", ...)
   private final String suit; // Kolor karty ("kier", "trefl", ...)

   // Konstruktor dwuargumentowy inicjalizuje zmienne face i suit.
   public Card(String cardFace, String cardSuit) {
      this.face = cardFace; // Inicjalizacja figury.
      this.suit = cardSuit; // Inicjalizacja koloru.
   }

   public String getFace() {
      return this.face;
   }

   public String getSuit() {
      return this.suit;
   }

   // Zwraca tekstową reprezentację obiektu Card.
   public String toString() {             
      return face + " " + suit;        
   }


} 


