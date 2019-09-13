package gameOfPoker;

public class StartGame {

    private Player firstPlayer;
    private Player secondPlayer;
    private String[] pokerHand;
    private DeckOfCards deckOfCards;

    public StartGame() {
        deckOfCards = new DeckOfCards();

        deckOfCards.shuffle(); //tasujemy karty

        this.firstPlayer = new Player("Patrick");
        this.secondPlayer = new Player("Patryshia");


        deckOfCards.dealCards(firstPlayer); //rozdanie
        deckOfCards.dealCards(secondPlayer);


        deckOfCards.setCurrentCard(0); //zerujemy aktualna karte
    }



    //testujemy
    public StartGame(String test) {
        deckOfCards = new DeckOfCards();

        this.firstPlayer = new Player("Patrick");
        this.secondPlayer = new Player("Patryshia");

        System.out.println("\n****************test 1 pair:");
        deckOfCards.returnToCardsForOnePairTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForOnePairTests(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 1 pair:");
        deckOfCards.returnToCardsForOnePairTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();


        //////////////////////////////////

        System.out.println("\n****************test 2 pairs:");
        deckOfCards.returnToCardsForTwoPairsTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForTwoPairsTests(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 2 pairs:");
        deckOfCards.returnToCardsForTwoPairsTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        //////////////////////////////////

        System.out.println("\n****************test 3 FIGURE :");
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 3 FIGURE :");
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        /////////////////////////////////////

        System.out.println("\n****************test 5 SUIT :");
        deckOfCards.returnTocardsFiveOfTheSameSuitForTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnTocardsFiveOfTheSameSuitForTests(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 5 SUIT :");
        deckOfCards.returnTocardsFiveOfTheSameSuitForTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        ///////////////////////////////////////


        System.out.println("\n****************test FULL 1 pair and 3 FIGURE :");
        deckOfCards.returnToCardsForFullTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForFullTests(secondPlayer.getCardsOfPlayer());
        checksWhoWon();


        System.out.println("\n****************test FULL 1 pair and 3 FIGURE :");
        deckOfCards.returnToCardsForFullTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        //////////////////////////////////////

        System.out.println("\n****************test 4 FIGURE :");
        deckOfCards.returnToCardsForFourCardsOfOneFigureTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForFourCardsOfOneFigureTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 4 FIGURE :");
        deckOfCards.returnToCardsForFourCardsOfOneFigureTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        /////////////////////////////////


        System.out.println("\n****************test POKER :");
        deckOfCards.returnToCardsFiveConsecutiveCardForTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsFiveConsecutiveCardForTests(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test POKER :");
        deckOfCards.returnToCardsFiveConsecutiveCardForTests(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();


    }



    public void checksWhoWon() {

        firstPlayer.showCardsPlayer(firstPlayer);

        secondPlayer.showCardsPlayer(secondPlayer);

        checkPlayerCards(firstPlayer);
        checkPlayerCards(secondPlayer);

        finishGame();
    }
    private void finishGame() {

        int firstPlayerCard = -1, secondPlayerCard = -1;
        boolean trueOrFalse = false;
        for(int i = 0; i < pokerHand.length; i++) {
            if(firstPlayer.getCardLayout().equals(pokerHand[i])) {
                firstPlayerCard = i;
                trueOrFalse = true;
            }

            if(secondPlayer.getCardLayout().equals(pokerHand[i])) {
                secondPlayerCard = i;
                trueOrFalse = true;
            }
        }

        //sprawdzam czy sa takie same

        if(trueOrFalse) {
            if (firstPlayerCard == secondPlayerCard) {
                System.out.println("draw in the game !!!!" + " the first and second players have -> " + firstPlayer.getCardLayout()); //remis
            } else if (firstPlayerCard < secondPlayerCard) {
                System.out.println("WINNER IS first player -> " + firstPlayer.getNamePlayer() + " have -> " + firstPlayer.getCardLayout());
            } else {
                System.out.println("WINNER IS second player -> " + secondPlayer.getNamePlayer() + " have -> " + secondPlayer.getCardLayout());
            }

        } else {
            System.out.println("first and second player no have hand poker !!!!");
        }



    }



    private void checkPlayerCards(Player player) {
        //straightFlush -> poker
        //fourCardsOfOneFigure -> KARETA -> 4 figury takie same
        //fullHouse -> 1 para i 3 Figury takie same
        //fiveCardsOfTheSameSuit -> FLUSH -> 5 kolorow takich samych
        //threeCardsOfOneFigure -> 3 karty jednej figury
        //twoPairOfCards -> 2 pary po Figurze
        //pairOfCards -> 1 para po figurze
                                                //0                 1               2         3         4                   5           6
        pokerHand = new String[]{"Straight Flush", "Four of a Kind", "Full House", "Flush", "Three of a Kind", "Two Pairs", "One Pair"};//uklad pokerowy

            switch (pokerHand[0]) {
                case "Straight Flush": {
                    if(deckOfCards.fiveConsecutiveCardsOfTheSameSuit(player)) {
                        player.setCardLayout(pokerHand[0]);
                        break;
                    }
                }
                case "Four of a Kind": {
                    if(deckOfCards.fourCardsOfOneFigure(player)) {
                        player.setCardLayout(pokerHand[1]);
                        break;
                    }
                }
                case "Full House": {
                    if(deckOfCards.pairOfFiguresAndThreeFiguresTheSame(player)) {
                        player.setCardLayout(pokerHand[2]);
                        break;
                    }
                }
                case "Flush": {
                    if(deckOfCards.fiveCardsOfTheSameSuit(player)) {
                        player.setCardLayout(pokerHand[3]);
                        break;
                    }
                }
                case "Three of a Kind": {
                    if(deckOfCards.threeCardsOfOneFigure(player)) {
                        player.setCardLayout(pokerHand[4]);
                        break;
                    }
                }
                case "Two Pairs": {
                    if(deckOfCards.twoPairOfCards(player)) {
                        player.setCardLayout(pokerHand[5]);
                        break;
                    }
                }
                case "One Pair": {
                    if(deckOfCards.pairOfCards(player)) {
                        player.setCardLayout(pokerHand[6]);
                        break;
                    }
                }
                default: {
                    System.out.println("PLAYER: " + player.getNamePlayer() + " has no poker hand !!!");
                    break;
                }
            }

    }

}
