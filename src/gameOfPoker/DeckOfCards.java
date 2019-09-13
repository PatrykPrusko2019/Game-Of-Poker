package gameOfPoker;// Rysunek 7.10. DeckOfCards.java
// Klasa DeckOfCards reprezentuje talię kart do gry.
/**
 * suit /suːt/ – kolor (w kartach)
 * diamond /ˈdaɪəmənd/ – karo
 * heart /hɑː(r)t/ – kier
 * club /klʌb/ – trefl
 * spade /speɪd/ – pik
 * playing cards /ˈpleɪɪŋ ˌkɑː(r)dz/ – karty do gry
 * joker /ˈdʒəʊkə(r)/, (AmE) /ˈdʒoʊkə(r)/ – dżoker
 * ace /eɪs/ – as
 * king /kɪŋ/ – król
 * queen /kwiːn/ – dama
 * jack /dʒæk/ , knave /neɪv/ – walet
 * card game /kɑː(r)d ɡeɪm/ – gra w karty, gra karciana
 * play cards /pleɪ ˌkɑː(r)dz/ – grać w karty
 * card /ˌkɑː(r)d/ – karta do gry
 * pack of cards /pæk əv ˌkɑː(r)dz/ , deck of cards (AmE) /dek əv ˌkɑː(r)dz/ – talia kart
 */

import java.security.SecureRandom;

//talia kart -> deck of cards
public class DeckOfCards {
   private int[] tableOfCardsToBeSkipped = new int[5];
   private Card[] arrayOfPlayerCards;
   private Card cardActuall;
   private Card secondCard;

   private String[] cardsStringOfFaces = new String[13];

   private Card[] testDeck = new Card[NUMBER_OF_CARDS];

   // private int[] actualResultCardOfPlayer = new int[5];
   // Generator liczb losowych.
   private static final SecureRandom randomNumbers = new SecureRandom();
   private static final int NUMBER_OF_CARDS = 52; // Stała liczba obiektów Card.

   private Card[] deck = new Card[NUMBER_OF_CARDS]; // Referencje do Card.
   private int currentCard = 0; // Indeks następnej karty do wydania (0-51).

   // Konstruktor wypełnia talię kart.
   public DeckOfCards() {  //suits -> kolor, faces -> twarze/figury
      String[] faces = {"Two", "Three", "Four", "Five", "Six", // figury
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" , "Ace"};
      String[] suits = {"diamond", "heart", "club", "spade"};   //kolory

      // Wypełnij talię obiektami Card.
      for (int count = 0; count < deck.length; count++) {
         deck[count] =
            new Card(faces[count % 13], suits[count / 13]); //specjalny algorytm -> co 13 count przypisuje 13 figur po jednym kolorze
      }

      //for tests
      for(int i = 0; i < faces.length; i++) {
         cardsStringOfFaces[i] = faces[i]; //przypisuje figury kolejnosc !!!!!
      }


      for(int i = 0; i < deck.length; i++ ) {
         testDeck[i] = deck[i];
      }
      /////

   }

   // Przetasuj talię kart algorytmem jednoprzebiegowym.
   public void shuffle() {
      // Następne wywołanie metody dealCard powinno zaczynać się ponownie od deck[0].

      // Dla każdej Card wybierz inną, losową Card (0-51) i zamień je.
      for (int first = 0; first < deck.length; first++) {
         // Wybierz liczbę losową między 0 i 51.
         int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

         // Zamień aktualne Card z losowo wybranym Card.
         Card temp = deck[first];
         deck[first] = deck[second];
         deck[second] = temp;
      }
   }

   // Wydaj jedną kartę z talii.
   //deck -> talia kart
   public void dealCards(Player player) {

      for(int i = 0; i < player.getCardsOfPlayer().length; i++, currentCard++) { //karty danego gracza po 5 kart
         if(currentCard < deck.length) {
            player.createPlayerCards(deck[currentCard], i);
         }
      }

   }


   //ustawiam na nowo obecna karte
   public void setCurrentCard (int currentCard) {
      this.currentCard = currentCard;
   }

   //wyswietla dane karty gracza
   public void showPlayerCards(Player playerCards) {

      arrayOfPlayerCards = playerCards.getCardsOfPlayer();

      System.out.println("cards of player : " + playerCards.getNamePlayer());

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         System.out.println( (i + 1) + " -> " + arrayOfPlayerCards[i].getFace() + " " + arrayOfPlayerCards[i].getSuit() );
      }

   }

   //one pair
   public boolean pairOfCards(Player playerCards) {
      arrayOfPlayerCards = playerCards.getCardsOfPlayer();

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         cardActuall = arrayOfPlayerCards[i]; // 1 karta
         for(int j = i+1; j < arrayOfPlayerCards.length; j++) {

            //sprawdza po figurze
            if( cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace()) ) { //jesli karta aktualnie sprawdzana jest taka sama co w 5 kartach gracza to true
               secondCard = arrayOfPlayerCards[j]; //2 karta
               tableOfCardsToBeSkipped[i] = i+1;
               tableOfCardsToBeSkipped[j] = j+1;
               playerCards.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);
               fillInTableOfCardsToBeSkippedWithZeros();
               return true;
            }
         }
      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }


   //two pairs
   public boolean twoPairOfCards(Player playerTwoPair) {

      if (pairOfCards(playerTwoPair)) {   //jesli jest jedna para to spawdz czy jest druga para
         completesTheCardsToBeSkipped(playerTwoPair);// przypisuje 2 karte ktora tworzy pare

         arrayOfPlayerCards = playerTwoPair.getCardsOfPlayer();


         for (int i = 0; i < arrayOfPlayerCards.length; i++) {
                  if(checkGoodIndexInCard(i)) { // brak takiego indeksu do pominiecia
                     for (int j = i + 1; j < arrayOfPlayerCards.length; j++) {

                        if(checkGoodIndexInCard(j)) { // brak takiego indeksu do pominiecia

                           if (arrayOfPlayerCards[i].getFace().equals(arrayOfPlayerCards[j].getFace())) {
                              tableOfCardsToBeSkipped[i] = i + 1; // 3 karta -> drugiej pary
                              tableOfCardsToBeSkipped[j] = j + 1; // 4 karta -> drugiej pary
                              playerTwoPair.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);//przypisujemy tablice int z danymi kartami
                              fillInTableOfCardsToBeSkippedWithZeros(); //zerujemy tablice z indeksami do pominiecia
                              return true;
                           }
                        }

                     }
                  }
         }

      }
      fillInTableOfCardsToBeSkippedWithZeros(); //zerujemy tablice z indeksami do pominiecia
      return false;
   }

   //zerujemy tablice z indeksami do pominiecia
   private void fillInTableOfCardsToBeSkippedWithZeros() {
      for (int i = 0; i < tableOfCardsToBeSkipped.length; i++) {
         tableOfCardsToBeSkipped[i] = 0;
      }
   }

   private boolean checkGoodIndexInCard(int indexCard) {
      indexCard++; //zwiekszamy o 1
      for(int i = 0; i < tableOfCardsToBeSkipped.length; i++) {
         if(indexCard == tableOfCardsToBeSkipped[i]) { //jesli np 2 indexCard = 1 a w tableOfCardsToBeSkipped[0] = 1 czyli 1 == [0]=1 -> pomin karte
            return false; // pomin
         }
      }
      return true; // idz dalej
   }

   //uzupelnia karty do pominiecia w tablicy -> tableOfCardsToBeSkipped
   private void completesTheCardsToBeSkipped(Player playerTwoPair) {

      for(int i = 0; i < playerTwoPair.getCardsOfPlayer().length; i++) {
         if(cardActuall.equals(playerTwoPair.getCardsOfPlayer()[i]) ) {
            tableOfCardsToBeSkipped[i] = i + 1; // pierwsza karta przypisujemy nr indeksu do pominiecia
         }
      }

      for(int i = 0; i < playerTwoPair.getCardsOfPlayer().length; i++) {
            if (secondCard.equals(playerTwoPair.getCardsOfPlayer()[i]) ) { // po figurze
               secondCard = playerTwoPair.getCardsOfPlayer()[i]; // 2 karta
               tableOfCardsToBeSkipped[i] = i + 1; // druga karta przypisujemy nr indeksu do pominiecia
            }
      }

   }

   // trzy karty jednej figury
   public boolean threeCardsOfOneFigure(Player cardsPlayer) {
      arrayOfPlayerCards = cardsPlayer.getCardsOfPlayer();

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         cardActuall = arrayOfPlayerCards[i];
         for(int j = i+1; j < arrayOfPlayerCards.length; j++) {

            if(cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace())) {
               fillInTableOfCardsToBeSkippedWithZeros(); //zerujemy jesli sie przypadek zdarzy np 2 asy, 2 krolow , to aby od nowa to ustawial karty do pominiecia
               tableOfCardsToBeSkipped[i] = i+1; // 1 karta do pominiecia
               tableOfCardsToBeSkipped[j] = j+1; // 2 karta do pominiecia
               if(lookForTheThirdOrFourFigure()) { //3 karta do pominiecia
                  cardsPlayer.setCurrentPlayerCardScore(tableOfCardsToBeSkipped); // przypisuje dane karty 3 takie same ASY np
                  fillInTableOfCardsToBeSkippedWithZeros();
                  return true;
               }
            }

         }
      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }

   //szukamy 3 lub 4 figur
   private boolean lookForTheThirdOrFourFigure() {
      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         if(checkGoodIndexInCard(i)) {
                 if(cardActuall.getFace().equals(arrayOfPlayerCards[i].getFace())) {
                    tableOfCardsToBeSkipped[i] = i+1; //3 karta i 4 karta
                    return true; // sa 3 karty o tym samym figurze np 3 damy lub 4 damy  !!!!
                 }
         }
      }
      return false;
   }

   public boolean fourCardsOfOneFigure(Player cardsPlayer) {
      arrayOfPlayerCards = cardsPlayer.getCardsOfPlayer();

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         cardActuall = arrayOfPlayerCards[i];
         for(int j = i+1; j < arrayOfPlayerCards.length; j++) {

            if(cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace())) { //jesli sa 2 takie same to szukaj nastepnych 2 kart po figurze
               tableOfCardsToBeSkipped[i] = i+1; // 1 karta do pominiecia
               tableOfCardsToBeSkipped[j] = j+1; // 2 karta do pominiecia
               if(lookForTheThirdAndFourFigure()) { // 3 i 4 karta do pominiecia po figurze
                  cardsPlayer.setCurrentPlayerCardScore(tableOfCardsToBeSkipped); // 4 karty takie same np. 4 asy
                  fillInTableOfCardsToBeSkippedWithZeros();
                  return true;
               }
            }

         }
      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }


   //znajdz 3 i 4  taka sama karta po figurze
   private boolean lookForTheThirdAndFourFigure() {
      if(lookForTheThirdOrFourFigure()) { // 3 figure
         if(lookForTheThirdOrFourFigure()) { // 4 figure found
            return true;
         }
      }
      return false;
   }


   //5 kart o tym samym kolorze
   public boolean fiveCardsOfTheSameSuit(Player cardsPlayer) {
      arrayOfPlayerCards = cardsPlayer.getCardsOfPlayer();
      int counter = 0;
      cardActuall = arrayOfPlayerCards[0];
      for(int i = 0; i < arrayOfPlayerCards.length; i++) {

            if( cardActuall.getSuit().equals(arrayOfPlayerCards[i].getSuit()) ){
               tableOfCardsToBeSkipped[i] = i+1;
               counter++;
            }
      }

      if(counter == 5) {
         cardsPlayer.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);
         fillInTableOfCardsToBeSkippedWithZeros();
         return true; // jesli 5 takich samych kolorow u playera
      } else {
         return false;
      }
   }

   //POKER -> pięć kolejnych figur w takim samym kolorze
   public boolean fiveConsecutiveCardsOfTheSameSuit(Player cardsPlayer) {
      int indexFirstCard;
      int indexSecondCard;
      //najpierw sprawdzam czy w takim samym kolorze
      if( fiveCardsOfTheSameSuit(cardsPlayer) ) { //true

         //segregujemy rosnąco karty
         for(int i = 0; i < arrayOfPlayerCards.length; i++) {
            indexFirstCard = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[i]);

            for(int j = i+1; j < arrayOfPlayerCards.length; j++) {
               indexSecondCard = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[j]);

               if( indexFirstCard > indexSecondCard) {
                  cardActuall = arrayOfPlayerCards[i]; //tymczasowa karta
                  arrayOfPlayerCards[i] = arrayOfPlayerCards[j];
                  arrayOfPlayerCards[j] = cardActuall;
               }

            }
         }

         //nastepnie sprawdzamy czy piec kolejnych figur
         int orderValue, nextCardIndex, counter = 1;
         orderValue = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[0]);

         for(int i = 1; i < arrayOfPlayerCards.length; i++) {
            nextCardIndex = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[i]);

            if(orderValue + i == nextCardIndex) { // np orderValue = 2 potem dodajemy 1 = 3 czyli 3 == nextCardIndex = 3 jesli true to jest kolejnosc
               counter++;
            }
         }

         if(counter == 5) {
            return true;
         }

      }
      return false;
   }

   //podaje nr indeksu z tablicy cardsStringOfFaces -> pomocna przy sprawdzaniu kolejnosci figur
   private int getNumberIndexArrayCardsStringOfFaces(Card card) {

      for(int i = 0; i < cardsStringOfFaces.length; i++) {
         if(card.getFace().equals(cardsStringOfFaces[i])) {
            return i;
         }
      }
      return 0;
   }


   ///////////////////////////////////////
   //for tests
   public Card[] returnToArrayCardToTests(Card[] cardsTest) {
      int counter = 0;
      for(int i = 0; i < deck.length; i++) {
         for(int j = 0; j < deck.length; j++) {
            if(deck[i].getSuit().equals(deck[j].getSuit())) {

               if(counter < cardsTest.length) {
                  cardsTest[counter] = deck[j];
                  counter++;
               } else {
                  return cardsTest;
               }
            }
         }
      }

      return cardsTest;
   }

   //tests 1 pair
   public void returnToCardsForOnePairTests(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[0];  // two
      cardsOfPlayer[1] = testDeck[13]; // two
      cardsOfPlayer[2] = testDeck[14]; //
      cardsOfPlayer[3] = testDeck[15]; //
      cardsOfPlayer[4] = testDeck[18]; //
   }

   //tests 2 pairs
   public void returnToCardsForTwoPairsTests(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[0];  // two
      cardsOfPlayer[1] = testDeck[13]; // two
      cardsOfPlayer[2] = testDeck[1];  // three
      cardsOfPlayer[3] = testDeck[14]; // three
      cardsOfPlayer[4] = testDeck[18]; //
   }

   //tests 3 cards of same FIGURE
   public void returnToCardsForThreeCardsOfOneFigureTest(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[0];  // two
      cardsOfPlayer[1] = testDeck[13]; // two
      cardsOfPlayer[2] = testDeck[26];  // two
      cardsOfPlayer[3] = testDeck[27]; //
      cardsOfPlayer[4] = testDeck[28]; //
   }


   //tests 4 cards of same FIGURE
   public void returnToCardsForFourCardsOfOneFigureTest(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[0];  // two
      cardsOfPlayer[1] = testDeck[13]; // two
      cardsOfPlayer[2] = testDeck[26];  // two
      cardsOfPlayer[3] = testDeck[39]; // two
      cardsOfPlayer[4] = testDeck[43]; //
   }

   //tests 5 cards of this same suit
   public Card[] returnTocardsFiveOfTheSameSuitForTests(Card[] cardToTest) {

      cardToTest[0] = testDeck[13]; // two
      cardToTest[1] = testDeck[15]; // three
      cardToTest[2] = testDeck[17]; // four
      cardToTest[3] = testDeck[19]; // five
      cardToTest[4] = testDeck[21]; // six

      return cardToTest;
   }


   //tests 5 cards of this same suit and consecutives -> poker
   public Card[] returnToCardsFiveConsecutiveCardForTests(Card[] cardToTest) {

      cardToTest[0] = testDeck[0]; // two
      cardToTest[1] = testDeck[1]; // three
      cardToTest[2] = testDeck[2]; // four
      cardToTest[3] = testDeck[3]; // five
      cardToTest[4] = testDeck[4]; // six

      return cardToTest;
   }

   //tests 1 pair of figure and 3 cards of Figure the same -> FULL
   public void returnToCardsForFullTests(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[12]; // ace
      cardsOfPlayer[1] = testDeck[11]; // king
      cardsOfPlayer[2] = testDeck[25]; // ace
      cardsOfPlayer[3] = testDeck[24]; // king
      cardsOfPlayer[4] = testDeck[37]; // king
   }

   public void returnNoPokerHandInPlayerForTest(Card[] cardsOfPlayer) {
      cardsOfPlayer[0] = testDeck[17]; // ace
      cardsOfPlayer[1] = testDeck[2]; // king
      cardsOfPlayer[2] = testDeck[29]; // ace
      cardsOfPlayer[3] = testDeck[39]; // king
      cardsOfPlayer[4] = testDeck[11]; // king
   }

   // a pair of figures and three figures the same -> FULL
   public boolean pairOfFiguresAndThreeFiguresTheSame(Player cardsOfPlayer) {

      //sprawdzamy czy jest jedna para kart
      if( pairOfCards(cardsOfPlayer) ) {
         //sprawdzamy czy sa 3 karty tej samej figury
         if( threeCardsOfOneFigure(cardsOfPlayer)) {
            //jest FULL to segregujemy tablice 5 cards zawodnika
            setTheOrderForFull( cardsOfPlayer.getCardsOfPlayer() );
            return true;
         }
      }
     return false;
   }


   //ustawia w kolejnosci dla FULLA
   private void setTheOrderForFull(Card[] cardsOfPlayer) {

      //first czesc
      int counter = 0;
      for(int i = 0; i < cardsOfPlayer.length; i++) {

            if(cardsOfPlayer[0].getFace().equals (cardsOfPlayer[i].getFace())) {
               arrayOfPlayerCards[counter] = cardsOfPlayer[i];
                       counter++;
            }
      }

      //second part
      for(int i = 0; i < cardsOfPlayer.length; i++) {

         if(cardsOfPlayer[0].getFace() != cardsOfPlayer[i].getFace()) {
            arrayOfPlayerCards[counter] = cardsOfPlayer[i];
            counter++;
         }
      }
   }

   ///////////////////////////////////////
}


