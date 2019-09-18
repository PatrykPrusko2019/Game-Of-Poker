package gameOfPoker;

public class StartGame {

    private Player firstPlayer;
    private Player secondPlayer;
    private String[] pokerHand;
    private DeckOfCards deckOfCards;


    public StartGame() {

        System.out.println("GAME OF CLASSIC POKER: ");

        deckOfCards = new DeckOfCards();

        deckOfCards.shuffle(); //shuffles the cards

        this.firstPlayer = new Player("Patrick");
        this.secondPlayer = new Player("Patryshia");

        deckOfCards.dealCards(firstPlayer); // deal
        deckOfCards.dealCards(secondPlayer);

        checksWhoWon();

        deckOfCards.setCurrentCard(0); //sets the current card
    }

    public DeckOfCards getDeckOfCards() {
        return deckOfCards;
    }
    public void setDeckOfCards(DeckOfCards deckOfCards) {
        this.deckOfCards = deckOfCards;
    }
    public Player getFirstPlayer() {
        return firstPlayer;
    }
    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }
    public Player getSecondPlayer() {
        return secondPlayer;
    }
    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }
    //tests
    public StartGame(String test) {

        this.deckOfCards = new DeckOfCards();
        this.firstPlayer = new Player("Patrick");
        this.secondPlayer = new Player("Patryshia");

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

        for(int i = 0; i < pokerHand.length; i++) {
            if(firstPlayer.getCardLayout().equals(pokerHand[i])) {
                firstPlayerCard = i;
            }
            if(secondPlayer.getCardLayout().equals(pokerHand[i])) {
                secondPlayerCard = i;
            }
        }
            int result;
        if(firstPlayerCard > -1 || secondPlayerCard > -1) {

            if(firstPlayerCard == -1) {
                firstPlayerCard = 7 ;
            } else if(secondPlayerCard == -1) {
                secondPlayerCard = 7;
            }
            result = 0;
        } else {
            result = 1;
        }

        System.out.println();
            switch (result) {
                case 0: {
                    if (firstPlayerCard == secondPlayerCard) {
                        HighCard highCardResult = new HighCard();
                        System.out.println("THERE IS DRAW !!!\nPOKER HAND IS: " + firstPlayer.getCardLayout() + " -> first player: " + firstPlayer.getNamePlayer() + " and POKER HAND IS: " + secondPlayer.getCardLayout() + " -> second player: " + secondPlayer.getNamePlayer() );
                        highCardResult.whoWonWithTheSamePokerHand(firstPlayer, secondPlayer);
                        break;
                    } else if (firstPlayerCard < secondPlayerCard) { //for example first= 1 < second= 5 -> winner is first
                        System.out.println("WINNER IS first player -> " + firstPlayer.getNamePlayer() + " have -> " + firstPlayer.getCardLayout());
                    } else {
                        System.out.println("WINNER IS second player -> " + secondPlayer.getNamePlayer() + " have -> " + secondPlayer.getCardLayout());
                    }
                    break;
                }
                case 1: { // IF NO POKER HAND
                    System.out.println("first player: " + firstPlayer.getNamePlayer() + " and second player: " + secondPlayer.getNamePlayer() + " no have hand poker !!!!");
                    HighCard highCardResult = new HighCard();
                    highCardResult.checkWhoWinner(firstPlayer, secondPlayer);
                    break;
                }
            }

    }



    private void checkPlayerCards(Player player) {
                                       //0                 1               2         3         4                   5           6
        pokerHand = new String[]{"Straight Flush", "Four of a Kind", "Full House", "Flush", "Three of a Kind", "Two Pairs", "One Pair"};//poker hand

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
                    player.setCardLayout(""); //reset value
                    break;
                }
            }

    }

}
