package gameOfPoker;
/** test class */
public class Test{

    private Player firstPlayer;
    private Player secondPlayer;
    private DeckOfCards deckOfCards;

    public Test() {

    }
    public void tests() {

        System.out.println("tests");
        StartGame startGame = new StartGame("test");
        this.firstPlayer = startGame.getFirstPlayer();
        this.secondPlayer = startGame.getSecondPlayer();
        this.deckOfCards = startGame.getDeckOfCards();



        System.out.println("\n****************test 1 pair:");
        returnToCardsForOnePairTests(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForOnePairTests(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 1 pair:");
        returnToCardsForOnePairTests(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();


        //////////////////////////////////

        System.out.println("\n****************test 2 pairs:");
        returnToCardsForTwoPairsTests(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForTwoPairsTests(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 2 pairs:");
        returnToCardsForTwoPairsTests(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        //////////////////////////////////

        System.out.println("\n****************test 3 FIGURE :");
        returnToCardsForThreeCardsOfOneFigureTest(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForThreeCardsOfOneFigureTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 3 FIGURE :");
        returnToCardsForThreeCardsOfOneFigureTest(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        /////////////////////////////////////

        System.out.println("\n****************test 5 SUIT :");
        returnTocardsFiveOfTheSameSuitForTests(this.firstPlayer.getCardsOfPlayer());
        returnTocardsFiveOfTheSameSuitForTests(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 5 SUIT :");
        returnTocardsFiveOfTheSameSuitForTests(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        ///////////////////////////////////////


        System.out.println("\n****************test FULL 1 pair and 3 FIGURE :");
        returnToCardsForFullTests(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForFullTests(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();


        System.out.println("\n****************test FULL 1 pair and 3 FIGURE :");
        returnToCardsForFullTests(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        //////////////////////////////////////

        System.out.println("\n****************test 4 FIGURE :");
        returnToCardsForFourCardsOfOneFigureTest(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForFourCardsOfOneFigureTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 4 FIGURE :");
        returnToCardsForFourCardsOfOneFigureTest(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        /////////////////////////////////


        System.out.println("\n****************test POKER :");
        returnToCardsFiveConsecutiveCardForTests(this.firstPlayer.getCardsOfPlayer());
        returnToCardsFiveConsecutiveCardForTests(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test POKER :");
        returnToCardsFiveConsecutiveCardForTests(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();


        /////////////////////////////////
        //HIGH CARD

        System.out.println("\n****************test HIGH CARD :");
        returnNoPokerHandInPlayerTESTForTest(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test HIGH CARD :");
        returnNoPokerHandInPlayerForTest(this.firstPlayer.getCardsOfPlayer());
        returnNoPokerHandInPlayerForTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();


        //////////////////////////////////

        System.out.println("\n****************test 3 FIGURE HIGH CARD :  HIGH CARD : king and two ");
        returnToCardsForThreeCardsOfOneFigureTestHighCardFirst(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForThreeCardsOfOneFigureTestHighCardSecond(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();

        System.out.println("\n****************test 3 FIGURE HIGH CARD :  HIGH CARD : two and two ");
        returnToCardsForThreeCardsOfOneFigureTest(this.firstPlayer.getCardsOfPlayer());
        returnToCardsForThreeCardsOfOneFigureTest(this.secondPlayer.getCardsOfPlayer());
        startGame.checksWhoWon();
    }

    ///////////////////////////////////////
    //for tests

    //tests 1 pair
    public void returnToCardsForOnePairTests(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[0];  // two
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[13]; // two
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[14]; //
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[15]; //
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[18]; //
    }

    //tests 2 pairs
    public void returnToCardsForTwoPairsTests(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[0];  // two
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[13]; // two
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[1];  // three
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[14]; // three
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[18]; //
    }


    //tests 3 cards of same FIGURE
    public void returnToCardsForThreeCardsOfOneFigureTest(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[0];  // two
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[13]; // two
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[26];  // two
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[27]; //
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[28]; //
    }




    //tests 3 cards of same FIGURE
    public void returnToCardsForThreeCardsOfOneFigureTestHighCardFirst(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[0];  // two
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[13]; // two
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[26];  // two
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[27]; //
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[28]; //
    }

    // 3 cards of Figure the same -> HIGH CARD
    public void returnToCardsForThreeCardsOfOneFigureTestHighCardSecond(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[1]; // ace
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[11]; // king
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[0]; // ace
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[24]; // king
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[37]; // king
    }


    //tests 4 cards of same FIGURE
    public void returnToCardsForFourCardsOfOneFigureTest(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[0];  // two
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[13]; // two
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[26];  // two
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[39]; // two
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[43]; //
    }

    //tests 5 cards of this same suit
    public Card[] returnTocardsFiveOfTheSameSuitForTests(Card[] cardToTest) {

        cardToTest[0] = this.deckOfCards.getTestDeck()[13]; // two
        cardToTest[1] = this.deckOfCards.getTestDeck()[15]; // three
        cardToTest[2] = this.deckOfCards.getTestDeck()[17]; // four
        cardToTest[3] = this.deckOfCards.getTestDeck()[19]; // five
        cardToTest[4] = this.deckOfCards.getTestDeck()[21]; // six

        return cardToTest;
    }


    //tests 5 cards of this same suit and consecutives -> poker
    public Card[] returnToCardsFiveConsecutiveCardForTests(Card[] cardToTest) {

        cardToTest[0] = this.deckOfCards.getTestDeck()[0]; // two
        cardToTest[1] = this.deckOfCards.getTestDeck()[1]; // three
        cardToTest[2] = this.deckOfCards.getTestDeck()[2]; // four
        cardToTest[3] = this.deckOfCards.getTestDeck()[3]; // five
        cardToTest[4] = this.deckOfCards.getTestDeck()[4]; // six

        return cardToTest;
    }

    //tests 1 pair of figure and 3 cards of Figure the same -> FULL
    public void returnToCardsForFullTests(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[12]; // ace
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[11]; // king
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[25]; // ace
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[24]; // king
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[37]; // king
    }

    //for tests no poker hand !!!!!!!!!!!!!!!!!!!!!
    public void returnNoPokerHandInPlayerForTest(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[17]; // six
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[2]; // four
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[29]; // five
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[39]; // two
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[11]; // king
    }

    //for tests no poker hand !!!!!!!!!!!!!!!!!!!!!
    public void returnNoPokerHandInPlayerTESTForTest(Card[] cardsOfPlayer) {
        cardsOfPlayer[0] = this.deckOfCards.getTestDeck()[12]; // ase
        cardsOfPlayer[1] = this.deckOfCards.getTestDeck()[2]; // four
        cardsOfPlayer[2] = this.deckOfCards.getTestDeck()[5]; // five
        cardsOfPlayer[3] = this.deckOfCards.getTestDeck()[39]; // two
        cardsOfPlayer[4] = this.deckOfCards.getTestDeck()[11]; // king
    }
}
