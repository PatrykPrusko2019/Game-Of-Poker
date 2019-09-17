package gameOfPoker;

public class Test extends StartGame {

    public void tests(Player firstPlayer, Player secondPlayer, DeckOfCards deckOfCards) {


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


        /////////////////////////////////
        //HIGH CARD

        System.out.println("\n****************test HIGH CARD :");
        deckOfCards.returnNoPokerHandInPlayerTESTForTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test HIGH CARD :");
        deckOfCards.returnNoPokerHandInPlayerForTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnNoPokerHandInPlayerForTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();


        //////////////////////////////////

        System.out.println("\n****************test 3 FIGURE HIGH CARD :  HIGH CARD : king and two ");
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTestHighCardFirst(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTestHighCardSecond(secondPlayer.getCardsOfPlayer());
        checksWhoWon();

        System.out.println("\n****************test 3 FIGURE HIGH CARD :  HIGH CARD : two and two ");
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTest(firstPlayer.getCardsOfPlayer());
        deckOfCards.returnToCardsForThreeCardsOfOneFigureTest(secondPlayer.getCardsOfPlayer());
        checksWhoWon();
    }
}
