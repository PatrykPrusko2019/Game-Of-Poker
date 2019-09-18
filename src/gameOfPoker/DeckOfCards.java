package gameOfPoker;

import java.security.SecureRandom;

public class DeckOfCards {
   private int[] tableOfCardsToBeSkipped = new int[5];
   private Card[] arrayOfPlayerCards;
   private Card cardActuall;
   private Card secondCard;

   private String[] cardsStringOfFaces = new String[13];

   private Card[] testDeck = new Card[NUMBER_OF_CARDS];

   private static final SecureRandom randomNumbers = new SecureRandom();
   private static final int NUMBER_OF_CARDS = 52; // final number of Card objects

   private Card[] deck = new Card[NUMBER_OF_CARDS];
   private int currentCard = 0; // Index of next issue cards (0-51).

   // fills the deck of cards
   public DeckOfCards() {
      String[] faces = {"Two", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" , "Ace"};
      String[] suits = {"diamond", "heart", "club", "spade"};

      for (int count = 0; count < deck.length; count++) {
         deck[count] =
            new Card(faces[count % 13], suits[count / 13]); // every 13 count assigns 13 figures of one color
      }

      //for tests
      for(int i = 0; i < faces.length; i++) {
         cardsStringOfFaces[i] = faces[i]; //assigns the order to the figure
      }

      for(int i = 0; i < deck.length; i++ ) {
         testDeck[i] = deck[i];
      }
   }

   public Card[] getTestDeck() {
      return testDeck;
   }
   // Shuffle the deck of cards
   public void shuffle() {

      //For each Card, select a different, random Card (0-51) and replace them.
      for (int first = 0; first < deck.length; first++) {
         //Choose a random number between 0 and 51.
         int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

         //Swap the current Card with a randomly selected Card.
         Card temp = deck[first];
         deck[first] = deck[second];
         deck[second] = temp;
      }
   }

   //Spend one card from the deck
   public void dealCards(Player player) {

      for(int i = 0; i < player.getCardsOfPlayer().length; i++, currentCard++) {
         if(currentCard < deck.length) {
            player.createPlayerCards(deck[currentCard], i);
         }
      }

   }

   public void setCurrentCard (int currentCard) {
      this.currentCard = currentCard;
   }

   //one pair
   public boolean pairOfCards(Player playerCards) {
      arrayOfPlayerCards = playerCards.getCardsOfPlayer();

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         cardActuall = arrayOfPlayerCards[i]; // 1 card
         for(int j = i+1; j < arrayOfPlayerCards.length; j++) {

            //checks by figure
            if( cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace()) ) { //if the card currently being checked is the same as the 5 player's cards then true
               secondCard = arrayOfPlayerCards[j]; //2 card
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

      if (pairOfCards(playerTwoPair)) {   //if there is one pair, check if there is another pair
         completesTheCardsToBeSkipped(playerTwoPair);//assigns 2 cards to create a pair

         arrayOfPlayerCards = playerTwoPair.getCardsOfPlayer();

         for (int i = 0; i < arrayOfPlayerCards.length; i++) {
                  if(checkGoodIndexInCard(i)) {
                     for (int j = i + 1; j < arrayOfPlayerCards.length; j++) {

                        if(checkGoodIndexInCard(j)) { // no such index to omit -> true

                           if (arrayOfPlayerCards[i].getFace().equals(arrayOfPlayerCards[j].getFace())) {
                              tableOfCardsToBeSkipped[i] = i + 1; // 3 card -> second pair
                              tableOfCardsToBeSkipped[j] = j + 1; // 4 card -> second pair
                              playerTwoPair.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);
                              fillInTableOfCardsToBeSkippedWithZeros(); //resets arrays with indexes to skip
                              return true;
                           }
                        }

                     }
                  }
         }

      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }

   //resets arrays with indexes to skip
   private void fillInTableOfCardsToBeSkippedWithZeros() {
      for (int i = 0; i < tableOfCardsToBeSkipped.length; i++) {
         tableOfCardsToBeSkipped[i] = 0;
      }
   }

   private boolean checkGoodIndexInCard(int indexCard) {
      indexCard++;
      for(int i = 0; i < tableOfCardsToBeSkipped.length; i++) {
         if(indexCard == tableOfCardsToBeSkipped[i]) {
            return false;
         }
      }
      return true;
   }

   //completes the cards to be skipped in the table -> tableOfCardsToBeSkipped
   private void completesTheCardsToBeSkipped(Player playerTwoPair) {

      for(int i = 0; i < playerTwoPair.getCardsOfPlayer().length; i++) {
         if(cardActuall.equals(playerTwoPair.getCardsOfPlayer()[i]) ) {
            tableOfCardsToBeSkipped[i] = i + 1; //the first card assigns an index number to skip
         }
      }

      for(int i = 0; i < playerTwoPair.getCardsOfPlayer().length; i++) {
            if (secondCard.equals(playerTwoPair.getCardsOfPlayer()[i]) ) { // by figure
               secondCard = playerTwoPair.getCardsOfPlayer()[i]; // 2 card
               tableOfCardsToBeSkipped[i] = i + 1; //the second card assigns an index number to skip
            }
      }

   }


   public boolean threeCardsOfOneFigure(Player cardsPlayer) {
      arrayOfPlayerCards = cardsPlayer.getCardsOfPlayer();

      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         if(checkGoodIndexInCard(i) ) {
            cardActuall = arrayOfPlayerCards[i];

            for (int j = i + 1; j < arrayOfPlayerCards.length; j++) {

               if (cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace())) {
                  fillInTableOfCardsToBeSkippedWithZeros(); // resets if the case happens, for example, 2 aces, 2 kings, so that he sets cards to be skipped again
                  tableOfCardsToBeSkipped[i] = i + 1; // first card to skip
                  tableOfCardsToBeSkipped[j] = j + 1; // second card to skip
                  if (lookForTheThirdOrFourFigure()) { //third card to skip
                     cardsPlayer.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);
                     fillInTableOfCardsToBeSkippedWithZeros();
                     return true;
                  }
               }

            }
         }
      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }

   //looking for 3 or 4 figures
   private boolean lookForTheThirdOrFourFigure() {
      for(int i = 0; i < arrayOfPlayerCards.length; i++) {
         if(checkGoodIndexInCard(i)) {
                 if(cardActuall.getFace().equals(arrayOfPlayerCards[i].getFace())) {
                    tableOfCardsToBeSkipped[i] = i+1; //3 card or 4 card
                    return true;
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

            if(cardActuall.getFace().equals(arrayOfPlayerCards[j].getFace())) { //if two are the same, look for the next two cards after the figure
               tableOfCardsToBeSkipped[i] = i+1; // first card to skip
               tableOfCardsToBeSkipped[j] = j+1; // second card to skip
               if(lookForTheThirdAndFourFigure()) {
                  cardsPlayer.setCurrentPlayerCardScore(tableOfCardsToBeSkipped);
                  fillInTableOfCardsToBeSkippedWithZeros();
                  return true;
               }
            }

         }
      }
      fillInTableOfCardsToBeSkippedWithZeros();
      return false;
   }



   private boolean lookForTheThirdAndFourFigure() {
      if(lookForTheThirdOrFourFigure()) { // 3 figure
         if(lookForTheThirdOrFourFigure()) { // 4 figure found
            return true;
         }
      }
      return false;
   }



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
         return true;
      } else {
         return false;
      }
   }

   //POKER
   public boolean fiveConsecutiveCardsOfTheSameSuit(Player cardsPlayer) {
      int indexFirstCard;
      int indexSecondCard;
      // first checks for the same color
      if( fiveCardsOfTheSameSuit(cardsPlayer) ) {

         //sorts cards in ascending order
         for(int i = 0; i < arrayOfPlayerCards.length; i++) {
            indexFirstCard = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[i]);

            for(int j = i+1; j < arrayOfPlayerCards.length; j++) {
               indexSecondCard = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[j]);

               if( indexFirstCard > indexSecondCard) {
                  cardActuall = arrayOfPlayerCards[i];
                  arrayOfPlayerCards[i] = arrayOfPlayerCards[j];
                  arrayOfPlayerCards[j] = cardActuall;
               }

            }
         }

         //then check check the order of the cards
         int orderValue, nextCardIndex, counter = 1;
         orderValue = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[0]);

         for(int i = 1; i < arrayOfPlayerCards.length; i++) {
            nextCardIndex = getNumberIndexArrayCardsStringOfFaces(arrayOfPlayerCards[i]);

            if(orderValue + i == nextCardIndex) {
               counter++;
            }
         }

         if(counter == 5) {
            return true;
         }

      }
      return false;
   }

   //gives the index number from the cardsStringOfFaces table -> helpful when checking the order of figures
   private int getNumberIndexArrayCardsStringOfFaces(Card card) {

      for(int i = 0; i < cardsStringOfFaces.length; i++) {
         if(card.getFace().equals(cardsStringOfFaces[i])) {
            return i;
         }
      }
      return 0;
   }

   // a pair of figures and three figures the same -> FULL
   public boolean pairOfFiguresAndThreeFiguresTheSame(Player cardsOfPlayer) {

      //checks if there is one pair of cards
      if( pairOfCards(cardsOfPlayer) ) {
         completesTheCardsToBeSkipped(cardsOfPlayer);
         //checks if there are three cards of the same figure
         if( threeCardsOfOneFigure(cardsOfPlayer)) {
            //is FULL, he segregates 5 player cards
            setTheOrderForFull( cardsOfPlayer.getCardsOfPlayer() );
            return true;
         }
      }
     return false;
   }

   private void setTheOrderForFull(Card[] cardsOfPlayer) {

      //first part
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

}


