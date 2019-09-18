package gameOfPoker;

public class Player {

    private Card[] cardsOfPlayer;
    private String namePlayer;
    private int[] currentPlayerCardScore;
    private String cardLayout; // poker hand

    public Player(String namePlayer) {
        this.cardsOfPlayer = new Card[5];
        this.namePlayer = namePlayer;
        this.currentPlayerCardScore = new int[5];
        this.cardLayout = "";
    }

    public String getCardLayout() {
        return cardLayout;
    }
    public void setCardLayout(String cardLayout) {
        this.cardLayout = cardLayout;
    }
    public int[] getCurrentPlayerCardScore() {
        return currentPlayerCardScore;
    }

    public void setCurrentPlayerCardScore(int[] newResult) {

        for(int i = 0; i < newResult.length; i++) {
            if(newResult[i] != 0) {
                currentPlayerCardScore[i] = newResult[i];
            }
        }
    }

    public Card[] getCardsOfPlayer() {
        return this.cardsOfPlayer;
    }

    public void setCardsOfPlayer(Card[] cardsOfPlayer) {
        this.cardsOfPlayer = cardsOfPlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    //creates player cards
    public void createPlayerCards(Card newCard, int index) {
        cardsOfPlayer[index] = newCard;

    }



    public void showCardsPlayerAfterShuffling(Player player) {
        for(int i = 0; i < player.currentPlayerCardScore.length; i++) {
            if(player.currentPlayerCardScore[i] == i+1) {
                System.out.println("-" + player.cardsOfPlayer[i].getFace() + " " + player.cardsOfPlayer[i].getSuit());
            }
        }
        fillZerosInArray();
    }


    public void showCardsPlayer(Player player) {
        System.out.println("\nPLAYER: " + player.getNamePlayer() + "\nCARDS:");
        for(int i = 0; i < player.getCardsOfPlayer().length; i++) {

                System.out.println("-" + player.cardsOfPlayer[i].getFace() + " " + player.cardsOfPlayer[i].getSuit());

        }

    }


    private void fillZerosInArray() {
        for(int i = 0; i < currentPlayerCardScore.length; i++) {
            currentPlayerCardScore[i] = 0;
        }
    }

}
