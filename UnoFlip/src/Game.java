import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sahil, Nic
 * @version 1.0
 */
public class Game {
    private final ArrayList<Player> players;

    public enum State{GAME_START, IN_ROUND, BETWEEN_ROUND, GAME_END}

    private State gameState;

    private Card topCard;

    private ArrayList<Card> playedCards;

    private UnoDeck deck;

    private Player currPlayer;

    private int playerChoice;

    private int roundCounter;

    private Player roundWinner;

    private Player gameWinner;

    private View view;

    int currPlayerIndex;

    /**
     * Constructor used to create a game.
     * @param players The players playing in the game.
     */
    public Game(ArrayList<Player> players){
        this.players = players;
        this.roundCounter = 0;
        this.gameState = State.GAME_START;
    }

    /**
     * Sets the view of the MVC
     * @param view The view
     */
    public void setView(View view){
        this.view = view;
    }

    public void startGame(){
        updateGame();
    }

    /**
     * Adds a player to the game, so long as a round is not currently in effect.
     * @param player The player to be added to the game.
     */
    public void addPlayer(Player player){
        if (gameState != State.IN_ROUND){
            players.add(player);
            view.addPlayer(player);
        }
    }

    /**
     * Checks if any player has won.
     * If they have, the state changes, and a winner is declared.
     * If not, the roundCounter increases, and the next round begins.
     */
    private void updateGame(){
        this.gameState = State.BETWEEN_ROUND;
        if (checkWinner()){
            view.winner(gameWinner);
            this.gameState = State.GAME_END;
        }
        else {
            roundCounter ++;
            playRound();
        }
    }

    /**
     * Plays a round of Uno with all the current players.
     * It first creates a new deck and empty discard pile, then runs on a loop
     * until one of the players reaches zero cards. It then calculates the scores,
     * and cals updateGame()
     */
    private void playRound(){
        //Initializing round variables and objects
        this.currPlayerIndex = 0;
        this.deck = new UnoDeck();
        this.playedCards = new ArrayList<Card>();
        playCard(deck.getNCards(1).get(0));

        view.roundStart(roundCounter);

        this.gameState = State.IN_ROUND;

        for (Player player : this.players){
            drawCard(player, 7);
        }

        //Runs round
        while (gameState == State.IN_ROUND){
            this.topCard = this.playedCards.get(this.playedCards.size()-1);
            this.currPlayer = players.get(currPlayerIndex);

            view.nextPlayer(currPlayer, topCard);

            if (playerChoice != 0){
                Card card = currPlayer.getHand().get(playerChoice-1);
                view.cardPlayed(card, legalMove(card));
                while (!legalMove(card)){
                    view.illegalMove(currPlayer);
                    card = currPlayer.getHand().get(playerChoice-1);
                    view.cardPlayed(card, legalMove(card));
                }
                if (card.getSpecialType() != null){
                    switch (card.getSpecialType()) {
                        case DRAW_ONE -> {
                            drawOne();
                        }
                        case FLIP -> {
                            flip();
                        }
                        case REVERSE -> {
                            reverse();
                        }
                        case SKIP -> {
                            skip();
                        }
                        case WILD -> {
                            card.setColour(view.getColour());
                        }
                        case WILD_DRAW_TWO_CARDS -> {
                            card.setColour(view.getColour());
                            wildDrawTwo();
                        }
                        default -> {
                        }
                    }
                }
                playCard(currPlayer.playCard(card));

            }
            else {
                drawCard(currPlayer, 1);
            }
            if (currPlayer.getHand().isEmpty()){
                gameState = State.BETWEEN_ROUND;
                roundWinner = currPlayer;
            }
            //Moves on to the next player
            iteratePlayers();
        }
        //Scoring
        int roundScore = 0;
        for (Player player : players){
            if (player != roundWinner) {
                roundScore += player.getHandScore();
            }
        }
        roundWinner.addPoints(roundScore);
        view.roundEnd(roundWinner);
        updateGame();
    }

    /**
     * Makes the player after currPlayer draw one card.
     */
    private void drawOne() {
        if(currPlayerIndex == players.size()-1){
            drawCard(players.get(currPlayerIndex+1),1);
            currPlayerIndex = 0;
        }
        else{
            drawCard(players.get(currPlayerIndex+1),1);
            currPlayerIndex++;
        }
    }

    //Not yet necessary, but place here for posterity's sake
    private void flip(){

    }

    /**
     * Reverses the order of the player list, then updates the index to reflect that change.
     */
    public void reverse() {
        Collections.reverse(players);
        currPlayerIndex = players.indexOf(currPlayer);
    }

    /**
     * Skips the player after currPlayer's turn.
     */
    private void skip() {
        if(currPlayerIndex == players.size()-1){
            currPlayerIndex = 0;
        }
        else{
            currPlayerIndex++;
        }
        /**
        if(currPlayerIndex == players.size() - 1) {
            currPlayer = players.get(0);
        }
        else {
            currPlayer = players.get(currPlayerIndex + 1);
        }
         */
    }


    /**
     * Transforms the most recently player card into a colour of the current player's choice
     * and causes the following player to draw 2.
     */
    private void wildDrawTwo() {
        //Set the top card to the returned colour of the of view.getColour then remove it from the players hand and add 2 cards to the next player
        //topCard = new Card(null,)


        if(currPlayerIndex == players.size()-1){
            drawCard(players.get(currPlayerIndex+1),2);
            currPlayerIndex = 0;
        }
        else{
            drawCard(players.get(currPlayerIndex+1),2);
            currPlayerIndex++;
        }

    }

    /**
     * Checks if a player has exceeded 500 points. If one has, set them to be the winner, adn return true. Otherwise, return false.
     * @return true if someone has exceeded 500 points, false otherwise.
     */
    private boolean checkWinner(){
        for (Player player : this.players){
            if (player.getPoints() >= 500){
                this.gameWinner = player;
                return true;
            }
        }
        return false;
    }

    /**
     * Used by the controller to transmit what option the player chose, which
     * is then used in the main playRound loop.
     * @param choice The input choice of the player, an integer between 0 and the number of cards in their hand.
     */
    public void setPlayerChoice(int choice){
        this.playerChoice = choice;
    }

    /**
     * Verifies whether a given card can be legally played on topCard.
     * @param card A card, normally the one being played.
     * @return whether the provided card is a legal move
     */
    private boolean legalMove(Card card){
        if (card.getCardColour() == Card.Colour.BLACK){
            return true;
        }
        if (card.getSpecialType() != null){
            return (card.getCardColour() == topCard.getCardColour() || card.getSpecialType() == topCard.getSpecialType());
        }
        return (card.getCardNum() == topCard.getCardNum() || card.getCardColour() == topCard.getCardColour());
    }

    /**
     * Plays a given card, adding it to the end of the discard pile.
     * @param card the card being played
     */
    private void playCard(Card card){
        playedCards.add(card);
    }

    /**
     * Draws amount cards from the deck, and gives them to provided player.
     * @param player The player drawing the cards
     * @param amount The amount of cards the player draws
     */
    private void drawCard(Player player, int amount){
        ArrayList<Card> cards = deck.getNCards(amount);
        player.addCard(cards);
        view.drawCard(player, cards);
    }

    /**
     * Either increments or loops the current player index currPlayerIndex
     * back around to 0.
     */
    public void iteratePlayers(){
        if(currPlayerIndex < players.size()-1){
            currPlayerIndex ++;
        } else {
            currPlayerIndex = 0;
        }
        currPlayer = players.get(currPlayerIndex);
    }
}
