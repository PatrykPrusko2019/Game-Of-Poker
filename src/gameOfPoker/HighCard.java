package gameOfPoker;

public class HighCard {

    private String[] figures;
    private int[] highCardTabFirstPlayer;
    private int[] highCardTabSecondPlayer;

    private int highCardFirstPlayer;
    private int kickerFirstPlayer;

    private int highCardSecondPlayer;
    private int kickerSecondPlayer;
    private boolean isUsedKicker;
    private boolean showKicker;

    public HighCard() {
         this.figures = new String[]{"Two", "Three", "Four", "Five", "Six", // figury -> 0 - 12
                 "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
         this.highCardTabFirstPlayer = new int[5];
         this.highCardTabSecondPlayer = new int[5];
         this.isUsedKicker = false;
         this.showKicker = false;
    }

    public boolean isShowKicker() {
        return showKicker;
    }
    public void setShowKicker(boolean showKicker) {
        this.showKicker = showKicker;
    }
    public String[] getFigures() {
        return figures;
    }
    public void setFigures(String[] figures) {
        this.figures = figures;
    }
    public boolean isUsedKicker() {
        return isUsedKicker;
    }
    public void setUsedKicker(boolean usedKicker) {
        isUsedKicker = usedKicker;
    }
    public int getHighCardFirstPlayer() {
        return highCardFirstPlayer;
    }
    public void setHighCardFirstPlayer(int highCardFirstPlayer) {
        this.highCardFirstPlayer = highCardFirstPlayer;
    }
    public int getKickerFirstPlayer() {
        return kickerFirstPlayer;
    }
    public void setKickerFirstPlayer(int kickerFirstPlayer) {
        this.kickerFirstPlayer = kickerFirstPlayer;
    }
    public int getHighCardSecondPlayer() {
        return highCardSecondPlayer;
    }
    public void setHighCardSecondPlayer(int highCardSecondPlayer) {
        this.highCardSecondPlayer = highCardSecondPlayer;
    }
    public int getKickerSecondPlayer() {
        return kickerSecondPlayer;
    }
    public void setKickerSecondPlayer(int kickerSecondPlayer) {
        this.kickerSecondPlayer = kickerSecondPlayer;
    }
    public int[] getHighCardTabFirstPlayer() {
        return highCardTabFirstPlayer;
    }
    public void setHighCardTabFirstPlayer(int[] highCardTabFirstPlayer) {
        this.highCardTabFirstPlayer = highCardTabFirstPlayer;
    }
    public int[] getHighCardTabSecondPlayer() {
        return highCardTabSecondPlayer;
    }
    public void setHighCardTabSecondPlayer(int[] highCardTabSecondPlayer) {
        this.highCardTabSecondPlayer = highCardTabSecondPlayer;
    }

    public void checkWhoWinner(Player firstPlayer, Player secondPlayer) {

        System.out.println("****** HIGH CARD ******\n no poker hands "); //the high card decides who won

        completesTheCardsOfPlayer(firstPlayer.getCardsOfPlayer(), 1);
        completesTheCardsOfPlayer(secondPlayer.getCardsOfPlayer(), 2);

        setsHighCardAndKicker(getHighCardTabFirstPlayer() , 1 );
        setsHighCardAndKicker(getHighCardTabSecondPlayer(), 2 );

        showResult(firstPlayer, secondPlayer);
    }

    //if 0 -> draw, if 1 - winner first player, 2 - winner second player
    private int checksWhoWon() {

        int resultFirst, resultSecond;

        if(this.highCardFirstPlayer == this.highCardSecondPlayer) {
            setUsedKicker(true); //uses the kicker next to the result
            resultFirst = this.highCardFirstPlayer - this.kickerFirstPlayer;
            resultSecond = this.highCardSecondPlayer - this.kickerSecondPlayer;
            if(resultFirst == resultSecond) {
                return 0;
            } else if(resultFirst < resultSecond) {
                return 1;
            } else {
                return 2;
            }
        } else if (this.highCardFirstPlayer > this.highCardSecondPlayer) {
            return 1;
        } else {
            return 2;
        }
    }

    //finds a high card and a kicker
    private void setsHighCardAndKicker(int[] highCardTabPlayer, int playerValue) {
        int maxValue_highCard = 0, kicker = 0;

        if( playerValue == 1) { //player 1
            for (int i = 0; i < highCardTabPlayer.length; i++) {
                if(highCardTabPlayer[i] == -1) { continue; }
                if (highCardTabPlayer[i] > maxValue_highCard) {
                    maxValue_highCard = highCardTabPlayer[i]; //max value set
                }
            }
            for (int j = 0; j < highCardTabPlayer.length; j++) {
                if(highCardTabPlayer[j] == -1) { continue; }
                if (highCardTabPlayer[j] < maxValue_highCard) {
                    if (highCardTabPlayer[j] > kicker) {
                        kicker = highCardTabPlayer[j]; //kicker set
                    }
                }
            }
            setHighCardFirstPlayer(maxValue_highCard);
            setKickerFirstPlayer(kicker);

        } else { //player 2
            for (int i = 0; i < highCardTabPlayer.length; i++) {
                if(highCardTabPlayer[i] == -1) { continue; }
                if (highCardTabPlayer[i] > maxValue_highCard) {
                    maxValue_highCard = highCardTabPlayer[i]; //max value set
                }
            }
            for (int j = 0; j < highCardTabPlayer.length; j++) {
                if(highCardTabPlayer[j] == -1) { continue; }
                if (highCardTabPlayer[j] < maxValue_highCard) {
                    if (highCardTabPlayer[j] > kicker) {
                        kicker = highCardTabPlayer[j]; //kicker set
                    }
                }
            }
            setHighCardSecondPlayer(maxValue_highCard);
            setKickerSecondPlayer(kicker);
        }

    }

    public void whoWonWithTheSamePokerHand(Player firstPlayer, Player secondPlayer) {

        System.out.println("****** HIGH CARD:  ******\nhigh card with the same poker hand\n");

        completesTheCardsOfPlayer(firstPlayer.getCardsOfPlayer(), 1);
        completesTheCardsOfPlayer(secondPlayer.getCardsOfPlayer(),2);

        if( checksWhatPokerSystem(firstPlayer, secondPlayer) ) { // if true only high card or kicker by used

            setsHighCardAndKicker(getHighCardTabFirstPlayer(), 1);
            setsHighCardAndKicker(getHighCardTabSecondPlayer(), 2);

            showResult(firstPlayer, secondPlayer);

        } else {

            showResult(firstPlayer, secondPlayer);
        }
    }


    private void showResult(Player firstPlayer, Player secondPlayer) {

        int highCard = checksWhoWon();
        String highCardFirst = this.figures[getHighCardFirstPlayer()];
        String highCardSecond = this.figures[getHighCardSecondPlayer()];
        String kickerFirst;
        String kickerSecond;

        if(isShowKicker() ) {
            kickerFirst = " unnecessary value";
            kickerSecond = " unnecessary value";
        } else {
            kickerFirst = this.figures[getKickerFirstPlayer()];
            kickerSecond = this.figures[getKickerSecondPlayer()];
        }
        System.out.println("\nRESULT : ");

        switch(highCard) {
            case 0: {
                if(isUsedKicker()) {
                    System.out.println("THERE IS DRAW. The high card is the same for both players -> " + highCardFirst +
                            "\n***************************************\n" + "THERE IS DRAW. The kicker card is the same for both players -> " + kickerFirst);
                }
                break;
            }
            case 1: {
                if(isUsedKicker()) {
                    System.out.println("THERE IS DRAW. The high card is the same for both players -> " + highCardFirst +
                            "\n***************************************\n" + "larger kicker card -> " + kickerFirst +
                            "\nWINNER IS PLAYER FIRST: " + firstPlayer.getNamePlayer());

                } else {
                    System.out.println("***************************************\nWINNER IS PLAYER FIRST: " + firstPlayer.getNamePlayer() + "\nHIGH CARD: " + highCardFirst);
                }
                break;
            }
            case 2: {
                if(isUsedKicker()) {
                    System.out.println("THERE IS DRAW. The high card is the same for both players -> " + highCardFirst +
                            "\n***************************************\n" + "larger kicker card -> " + kickerSecond +
                            "\nWINNER IS PLAYER SECOND: " + secondPlayer.getNamePlayer());
                } else {
                    System.out.println("***************************************\nWINNER IS PLAYER SECOND: " + secondPlayer.getNamePlayer() + "\nHIGH CARD: " + highCardSecond);
                }
                break;
            }
        }

        setUsedKicker(false);
        setShowKicker(false);
    }


    private boolean checksWhatPokerSystem(Player firstPlayer, Player secondPlayer) {

        String pokerHand = firstPlayer.getCardLayout();
        boolean exit = false;

        switch (pokerHand) {
            case "Straight Flush": {
                //do nothing only high card
                setShowKicker(true);
                exit = true;
                break;
            }
            case "Four of a Kind": {
                if(whoWonWithFourOfKind() ) {
                    exit = false; //different high card values ​​of both players
                } else {
                    exit = true; //same high cards
                }
                break;
            }
            case "Full House": { //1 pair , 3 Figures
                whoWonWIthFullHouse();
                exit = false;
                break;
            }
            case "Flush": { //five same suits of cards
                //do nothing only high card
                setShowKicker(true);
                exit = true;
                break;
            }
            case "Three of a Kind": {
                if( whoWonThreeOfKind() ) {
                    exit = false;
                } else {
                    exit = true;
                }

                break;
            }
            case "Two Pairs": {
                if( whoWonTwoPairs() ) {
                    exit = false;
                } else {
                    exit = true;
                }
                break;
            }
            case "One Pair": {
                if ( whoWonWithOnePair() ) {
                    exit = false;
                } else {
                    exit = true;
                }
                break;
            }

        }
            return exit;
    }
    private boolean whoWonWithOnePair() {
        //checks 1 pair
        if(complelesOfTheHighCards(2)) {
            return true;
        } else {
            deletesThePokerHand(getHighCardFirstPlayer());
            setHighCardFirstPlayer(-2);
            setHighCardSecondPlayer(-2);
            return false;
        }
    }

    private boolean whoWonTwoPairs() {
        //checks 1 pair
        if(complelesOfTheHighCards(2)) {
            return true;
        } else {

            deletesThePokerHand(getHighCardFirstPlayer());
            //checks 2 pair
            if(complelesOfTheHighCards(2)) {
                return true;
            } else {
                deletesThePokerHand(getHighCardFirstPlayer());
                setHighCardFirstPlayer(-2);
                setHighCardSecondPlayer(-2);
                setShowKicker(true);
                return false;
            }
        }
    }


    private boolean whoWonThreeOfKind() {
        if(complelesOfTheHighCards(3)) {
            return true;
        } else {
            deletesThePokerHand(getHighCardFirstPlayer());
            setHighCardFirstPlayer(-2);
            setHighCardSecondPlayer(-2);
            return false;
        }
    }

    //checks the higher card
    private boolean whoWonWithFourOfKind() {
        if(complelesOfTheHighCards(4)) {
            return true;
        } else {
            deletesThePokerHand(getHighCardFirstPlayer());
            setHighCardFirstPlayer(-2);
            setHighCardSecondPlayer(-2);
            setShowKicker(true);
            return false;
        }
    }

    private void whoWonWIthFullHouse() {
        //checks 3 figures -> high Card
        if( ! complelesOfTheHighCards(3)) {
            deletesThePokerHand(getHighCardFirstPlayer());
            complelesOfTheHighCards(2); // checks 1 pair
            setShowKicker(true);
        }
    }

    private void deletesThePokerHand(int valueOfPokerHand) {
        for(int i = 0; i < highCardTabFirstPlayer.length; i++) {
            if(valueOfPokerHand == highCardTabFirstPlayer[i]) {
                highCardTabFirstPlayer[i] = -1;
            }
        }
        for(int i = 0; i < highCardTabSecondPlayer.length; i++) {
            if(valueOfPokerHand == highCardTabSecondPlayer[i]) {
                highCardTabSecondPlayer[i] = -1;
            }
        }
    }


    private boolean complelesOfTheHighCards(int sameTheCards) {
        sameTheCards--;
        int counter = 0, searchThreeFigures;

            for (int i = 0; i < highCardTabFirstPlayer.length; i++) {
                if(highCardTabFirstPlayer[i] == -1) { continue; }

                searchThreeFigures = highCardTabFirstPlayer[i];
                for (int j = i + 1; j < highCardTabFirstPlayer.length; j++) {

                    if (searchThreeFigures == highCardTabFirstPlayer[j]) {
                        counter++;
                    }
                }
                if (counter == sameTheCards) {
                    setHighCardFirstPlayer(searchThreeFigures);
                    break; //exit to loop
                } else {
                    counter = 0;
                }
            }

            counter = 0;
            for (int i = 0; i < highCardTabSecondPlayer.length; i++) {
                if(highCardTabSecondPlayer[i] == -1) { continue; }

                searchThreeFigures = highCardTabSecondPlayer[i];
                for (int j = i + 1; j < highCardTabSecondPlayer.length; j++) {

                    if (searchThreeFigures == highCardTabSecondPlayer[j]) {
                        counter++;
                    }
                }
                if (counter == sameTheCards) {
                    setHighCardSecondPlayer(searchThreeFigures);
                    break; //exit to loop
                } else {
                    counter = 0;
                }
            }

            if (getHighCardFirstPlayer() == getHighCardSecondPlayer()) {
                return false;
            } else {
                return true;
            }
    }

    private void completesTheCardsOfPlayer(Card[] cardsOfPlayer, int whichPlayer) {
        int[] tempCardHeight = new int[5];
        for(int i = 0; i < cardsOfPlayer.length; i++) {
            String cardOfFigure = cardsOfPlayer[i].getFace();

            for(int j = 0; j < figures.length; j++ ) {
                if(figures[j].equals(cardOfFigure)) {
                    tempCardHeight[i] = j;
                }
            }
        }
        if(whichPlayer == 1) {
            setHighCardTabFirstPlayer(tempCardHeight);
        } else {
            setHighCardTabSecondPlayer(tempCardHeight);
        }
    }


}
