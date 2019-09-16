package gameOfPoker;
//wystapila wysoka karta , sprawdzamy kto wygral
public class HighCard {

    private String[] figures;
    private int[] highCardTabFirstPlayer;
    private int[] highCardTabSecondPlayer;

    private int highCardFirstPlayer;
    private int kickerFirstPlayer;

    private int highCardSecondPlayer;
    private int kickerSecondPlayer;

    public HighCard() {
         this.figures = new String[]{"Two", "Three", "Four", "Five", "Six", // figury -> 0 - 12
                 "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
         this.highCardTabFirstPlayer = new int[5];
         this.highCardTabSecondPlayer = new int[5];
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

        System.out.println("****** HIGH CARD ******\n no poker hands "); // wysoka karta decyduje kto wygral

        completesTheCardsOfFirstPlayer(firstPlayer.getCardsOfPlayer()); //przypisujemy wartosc figury 1 playerowi
        completesTheCardsOfFirstPlayer(secondPlayer.getCardsOfPlayer()); //przypisujemy wartosc figury 2 playerowi

        setsHighCardAndKicker(getHighCardTabFirstPlayer() , 1 );
        setsHighCardAndKicker(getHighCardTabSecondPlayer(), 2 );

       int highCard = checksWhoWon();

        String highCardFirst = figures[highCardFirstPlayer];
        String highCardSecond = figures[highCardSecondPlayer];
        String kickerFirst = figures[kickerFirstPlayer];
        String kickerSecond = figures[kickerSecondPlayer];


       switch(highCard) {
           case 0: {
               System.out.println("DRAW: High card is -> " + highCardFirst);
               break;
           }
           case 1: {
               System.out.println("winner is player first: " + firstPlayer.getNamePlayer() + "\nHIGH CARD: " +  highCardFirst + ", KICKER: " + kickerFirst);
               break;
           }
           case 2: {
               System.out.println("winner is player second: " + secondPlayer.getNamePlayer() + "\nHIGH CARD: " +  highCardSecond + ", KICKER: " + kickerSecond);
               break;
           }
       }
    }

    //if 0 -> remis, if 1 - winner player 1, 2 - winner player 2
    private int checksWhoWon() {

        int resultFirst, resultSecond;
        //sprawdzamy czy taka sama wysoka karta
        if(highCardFirstPlayer == highCardSecondPlayer) {
            resultFirst = highCardFirstPlayer - kickerFirstPlayer;
            resultSecond = highCardSecondPlayer - kickerSecondPlayer;
            if(resultFirst == resultSecond) {
                return 0;
            } else if(resultFirst < resultSecond) { // example 1 > 2 -> winner 1 player bo mniejsza roznica wyniku
                return 1;
            } else {
                return 2;
            }
        } else if(highCardFirstPlayer > highCardSecondPlayer) {
            return 1;
        } else {
            return 2;
        }
    }
    //znajduje wysoką karte i kickera -> 2 karty -> exaple as and dupek
    private void setsHighCardAndKicker(int[] highCardTabPlayer, int playerValue) {
        //szuka najwyzszej karty danego playera
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
            setHighCardSecondPlayer(maxValue_highCard); //ustawiamy wysoka karte i kickera playera
            setKickerSecondPlayer(kicker);
        }

    }

    public void whoWonWithTheSamePokerHand(Player firstPlayer, Player secondPlayer) {

        System.out.println("****** HIGH CARD:  ******\nhigh card with the same poker hand\n"); // wysoka karta decyduje kto wygral

        completesTheCardsOfFirstPlayer(firstPlayer.getCardsOfPlayer()); //przypisujemy wartosc figury 1 playerowi
        completesTheCardsOfFirstPlayer(secondPlayer.getCardsOfPlayer()); //przypisujemy wartosc figury 2 playerowi

        if( checksWhatPokerSystem(firstPlayer, secondPlayer) ) { // if true only high card or kicker by used

            setsHighCardAndKicker(getHighCardTabFirstPlayer(), 1);
            setsHighCardAndKicker(getHighCardTabSecondPlayer(), 2);

            showResult(firstPlayer, secondPlayer);

        } else {

            showResult(firstPlayer, secondPlayer);
        }
    }


    private void showResult(Player firstPlayer, Player secondPlayer) {
        int highCard = whoWonWithTheSamePokerHandSecond();

        String highCardFirst = figures[highCardFirstPlayer];
        String highCardSecond = figures[highCardSecondPlayer];

        switch (highCard) {
            case 0: {
                System.out.println("DRAW: High card is -> " + highCardFirst);
                break;
            }
            case 1: {
                System.out.println("winner is player first: " + firstPlayer.getNamePlayer() + "\nHIGH CARD: " + highCardFirst);
                break;
            }
            case 2: {
                System.out.println("winner is player second: " + secondPlayer.getNamePlayer() + "\nHIGH CARD: " + highCardSecond);
                break;
            }
        }
    }


    //straightFlush -> poker
    //fourCardsOfOneFigure -> KARETA -> 4 figury takie same
    //fullHouse -> 1 para i 3 Figury takie same
    //fiveCardsOfTheSameSuit -> FLUSH -> 5 kolorow takich samych
    //threeCardsOfOneFigure -> 3 karty jednej figury
    //twoPairOfCards -> 2 pary po Figurze
    //pairOfCards -> 1 para po figurze
    //sprawdza jaki uklad pokerowy -> czy takie same karty i itp
    private boolean checksWhatPokerSystem(Player firstPlayer, Player secondPlayer) {

        String pokerHand = firstPlayer.getCardLayout();
        boolean exit = false;

        switch (pokerHand) {
            case "Straight Flush": {
                //do nothing only high card
                exit = true;
                break;
            }
            case "Four of a Kind": {
                if(whoWonWithFourOfKind() ) {  //sets 2 high cards in 4 cards
                    exit = false; //rozne wartosci high cards obu zawodnikow
                } else {
                    exit = true; //takie same high cards
                }
                break;
            }
            case "Full House": { //1 pair , 3 Figures
                whoWonWIthFullHouse(); //sets 2 high cards -> first checks -> 3 figures
                exit = false;
                break;
            }
            case "Flush": { //five same suits of cards
                //do nothing only high card
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
        //sprawdzamy po 1 pair
        if(complelesOfTheHighCards(2, false)) {
            return true;
        } else {
            //ustawiamy pair na -1
            complelesOfTheHighCards(2, true);
            return false;
        }
    }

    private boolean whoWonTwoPairs() {
        //sprawdzamy po 1 pair
        if(complelesOfTheHighCards(2, false)) {
            return true;
        } else {

            complelesOfTheHighCards(2, true); //zmiana wartosci na -1
            //sprawdzamy po 2 pair
            if(complelesOfTheHighCards(2, false)) {
                return true;
            } else {
                return false;
            }
        }
    }
    private boolean whoWonThreeOfKind() {
        //sprawdzamy najpierw 3 Figury -> high Card
        if(complelesOfTheHighCards(3, false)) {
            return true;
        } else { // takie same high cards
            complelesOfTheHighCards(3, true); //zmiana wartosci -1
            return false;
        }
    }
    private void whoWonWIthFullHouse() {
        //sprawdzamy najpierw 3 Figury -> high Card
        if( ! complelesOfTheHighCards(3, false)) { // 3 figures -> if ! false == true szukaj po parze
            complelesOfTheHighCards(2, false); // pair
        }
    }

    //zwraca true jesli sa rozne wartosc high cards obu zawodnikow
    //zwraca false jesli sa takie same wartosci high cards obu zawodnikow
    private boolean complelesOfTheHighCards(int sameTheCards, boolean valueToSkip) {
        int counter = 0, searchThreeFigures;

            for (int i = 0; i < highCardTabFirstPlayer.length; i++) {
                if(highCardTabFirstPlayer[i] == -1) { continue; } //if -1 to pomin

                searchThreeFigures = highCardTabFirstPlayer[i];
                for (int j = i + 1; j < highCardTabFirstPlayer.length; j++) {

                    if (searchThreeFigures == highCardTabFirstPlayer[j]) {
                        if(valueToSkip) { //if true zmien wartosc na -1
                            highCardTabFirstPlayer[j] = -1;
                        }
                        counter++;
                    }
                }
                if (counter == sameTheCards) {
                    setHighCardFirstPlayer(searchThreeFigures);
                    break; //exit to loop
                }
            }

            counter = 0;
            for (int i = 0; i < highCardTabSecondPlayer.length; i++) {
                if(highCardTabSecondPlayer[i] == -1) { continue; } //if -1 to pomin

                searchThreeFigures = highCardTabSecondPlayer[i];
                for (int j = i + 1; j < highCardTabSecondPlayer.length; j++) {

                    if (searchThreeFigures == highCardTabSecondPlayer[j]) {
                        if(valueToSkip) { //if true zmien wartosc na -1
                            highCardTabSecondPlayer[j] = -1;
                        }
                        counter++;
                    }
                }
                if (counter == sameTheCards) {
                    setHighCardSecondPlayer(searchThreeFigures);
                    break; //exit to loop
                }
            }

            if(valueToSkip) { return false; }

            if (getHighCardFirstPlayer() == getHighCardSecondPlayer()) {
                return false;
            } else {
                return true; // jesli sa rozne high card obu zawodnikow
            }
    }



    //checks the higher card
    private boolean whoWonWithFourOfKind() {
        return complelesOfTheHighCards(4, false);
        //ustawienie 2 high cards -> and result who won !!!
    }


    private void completesTheCardsOfFirstPlayer(Card[] cardsOfPlayer) {
        for(int i = 0; i < cardsOfPlayer.length; i++) {
            String cardOfFigure = cardsOfPlayer[i].getFace();

            for(int j = 0; j < figures.length; j++ ) {
                if(figures[j].equals(cardOfFigure)) {
                    highCardTabFirstPlayer[i] = j;
                }
            }
        }
    }


    //if 0 -> remis, if 1 - winner player 1, 2 - winner player 2
    private int whoWonWithTheSamePokerHandSecond() {

        //sprawdzamy czy taka sama wysoka karta
        if(this.highCardFirstPlayer == this.highCardSecondPlayer) {
            return 0;
        } else if(this.highCardFirstPlayer > this.highCardSecondPlayer) {
            return 1;
        } else {
            return 2;
        }
    }



}
