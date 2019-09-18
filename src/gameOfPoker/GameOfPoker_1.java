package gameOfPoker;

/**
 * PLAYING CLASSIC POKER:
 * rules:
 * - two players,
 * - card decks are shuffled,
 * - dealing five cards to each player,
 *
 * Then decide the winnings after the poker hand:
 * 1. Straight Flush -> five cards of the same suit and the correct order of cards.
 * 2. Four of a Kind -> four cards of the same figure.
 * 3. Full House ->
 * a pair of cards and 3 cards of the same figure.
 * 4. Flush -> five cards of the same suit.
 * 5. Three of a Kind -> three cards of the same figure.
 * 6. Two Pairs ->
 * two pairs of cards.
 * 7. One Pairs ->
 * one pair of cards.
 *
 *
 * If players have the same poker hand 1 - 7, then the high card suits:
 * 1. Straight Flush -> a high card wins.
 * 2. Four of a Kind -> first check the high card in poker hands, then the same after the fifth card.
 * 3. Full House ->
 * first works after three identical figures, then a pair.
 * 4. Flush -> a high card wins.
 * 5. Three of a Kind -> first to check a high card in poker hands, then on other cards, if the same cards are followed by a kicker.
 * 6. Two Pairs ->
 * first hand first, then second, then fifth card.
 * 7. One Pairs ->
 * first a poker hand, then the rest of the cards, if the same high cards are followed by a kicker.
 *
 *
 * If both players don't have a poker hand, the high card or kicker decides.
 */
public class GameOfPoker_1 {

    public static void main(String[] args) {


         StartGame s = new StartGame(); // game of poker

        ////////////////////////////////////

         // Test test = new Test(); // tests
         // test.tests();


    }

}
