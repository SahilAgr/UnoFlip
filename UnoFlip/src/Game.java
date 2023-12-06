import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sahil, Nic
 * @version 1.0
 */
public class Game implements Serializable {
    
    GameStorage storage;

    private View view;
    
    public class GameStorage implements Serializable {

        private final ArrayList<Player> players;

        private boolean autosave;

        private String autosavePath;

        public enum State{GAME_START, IN_ROUND, BETWEEN_ROUND, GAME_END}

        public Card.Type cardType;

        private State gameState;

        private Card topCard;

        private ArrayList<Card> playedCards;

        private UnoDeck deck;

        private Player currPlayer;

        private int roundCounter;

        private Player roundWinner;

        private Player gameWinner;

        private ArrayList<FlipListener> flipListeners;

        int currPlayerIndex;
        public GameStorage(ArrayList<Player> players){
            this.players = players;
            this.roundCounter = 0;
            this.gameState = State.GAME_START;
            this.cardType = Card.Type.LIGHT;
            this.flipListeners = new ArrayList<>();
            this.autosave = false;
        }
    }

    /**
     * Constructor used to create a game.
     * @param players The players playing in the game.
     */
    public Game(ArrayList<Player> players){
        storage = new GameStorage(players);
    }

    /**
     * Sets the view of the MVC
     * @param view The view
     */
    public void setView(View view){
        this.view = view;
        //storage.flipListeners.add((FlipListener) this.view);
    }

    /**
     * Starts the game and calls update game that will send information to the controller
     */
    public void startGame(){
        updateGame();
    }

    /**
     * Adds a player to the game, so long as a round is not currently in effect.
     * @param player The player to be added to the game.
     */
    public void addPlayer(Player player){
        if (storage.gameState != Game.GameStorage.State.IN_ROUND){
            storage.flipListeners.add(player);
            storage.players.add(player);
        }
    }

    /**
     * Checks if any player has won.
     * If they have, the state changes, and a winner is declared.
     * If not, the roundCounter increases, and the next round begins.
     */
    private void updateGame(){
        if (storage.gameState == GameStorage.State.IN_ROUND){
            storage.currPlayerIndex --;
            nextTurn();
        }
        else {
            if (checkWinner()) {
                view.winner(storage.gameWinner);
                storage.gameState = GameStorage.State.GAME_END;
            } else {
                storage.roundCounter++;
                startRound();
            }
        }
    }

    /**
     * Plays a round of Uno with all the current players.
     * It first creates a new deck and empty discard pile, then runs on a loop
     * until one of the players reaches zero cards. It then calculates the scores,
     * and cals updateGame()
     */
    private void startRound(){
        //Initializing round variables and objects
        storage.currPlayerIndex = storage.players.size(); //Guarantees that when nextTurn is first called, it will jump to the first player
        storage.deck = new UnoDeck();
        storage.flipListeners.add(storage.deck);
        storage.playedCards = new ArrayList<Card>();

        Card topTemp = storage.deck.drawNCard(1).get(0);
        while (topTemp.getCardColour() == Card.Colour.BLACK){
            topTemp = storage.deck.drawNCard(1).get(0);
        }
        addToPlayedCards(topTemp);


        view.roundStart(storage.roundCounter);

        storage.gameState = GameStorage.State.IN_ROUND;

        for (Player player : storage.players){
            player.removeAllCards();
            drawCard(player, 7);
        }

        nextTurn();
    }

    /**
     * Either moves on to the next turn in the round, or effects the end of the round.
     */
    public void nextTurn(){
        if (storage.autosave){
            exportGame(storage.autosavePath);
        }
        if (storage.gameState == GameStorage.State.IN_ROUND){
            iteratePlayers();
            storage.topCard = storage.playedCards.get(storage.playedCards.size()-1);
            storage.currPlayer = storage.players.get(storage.currPlayerIndex);
            view.nextPlayer(storage.currPlayer, storage.topCard);
            if (storage.currPlayer instanceof AIPlayer){
                ((AIPlayer) storage.currPlayer).legalCards(this,storage.currPlayer);
            }
        }
        else {
            int roundScore = 0;
            for (Player player : storage.players){
                if (player != storage.roundWinner) {
                    roundScore += player.getHandScore();
                }
            }
            storage.roundWinner.addPoints(roundScore);
            view.roundEnd(storage.roundWinner);
            storage.gameState = Game.GameStorage.State.BETWEEN_ROUND;
            updateGame();
        }
    }

    /**
     * Called by the controller when a card is clicked to be played.
     * @param cardIndex The index of the card in the player's hand which has been clicked on.
     */
    public void attemptPlayCard(int cardIndex){
        Card card = this.storage.currPlayer.getHand().get(cardIndex);
        System.out.println("THIS IS WHAT I AM PLAYING: " + card);
        view.cardPlayed(card, legalMove(card));
        if (storage.currPlayer instanceof AIPlayer){
            System.out.println("THE AI PLAYER TRIED TO PLAY " + card.getCardColour() + card.getCardNum());
        }
        if (legalMove(card)){
            if (card.getSpecialType() != null){
                switch (card.getSpecialType()) {
                    case DRAW_ONE -> {
                        drawOne();
                    }
                    case FLIP -> {
                        flip(card);
                    }
                    case REVERSE -> {
                        reverse();
                    }
                    case SKIP -> {
                        skip();
                    }
                    case WILD -> {
                        if(storage.currPlayer instanceof AIPlayer){
                            card.setColour(((AIPlayer) storage.currPlayer).getColour());
                        } else {
                            card.setColour(view.getColour());
                        }
                    }
                    case WILD_DRAW_TWO_CARDS -> {
                        if(storage.currPlayer instanceof AIPlayer){
                            card.setColour(((AIPlayer) storage.currPlayer).getColour());
                        }else {
                            card.setColour(view.getColour());
                        }
                        wildDrawTwo();
                    }
                    default -> {
                    }
                }
            }
            addToPlayedCards(storage.currPlayer.playCard(cardIndex));
            if (storage.currPlayer.getHand().isEmpty()){
                storage.gameState = Game.GameStorage.State.BETWEEN_ROUND;
                storage.roundWinner = storage.currPlayer;
            }
            if (storage.currPlayer instanceof AIPlayer){
                view.AiPlayerPlayed(card);
            }
            nextTurn();
        }
        else {
            view.illegalMove();
        }
    }

    /**
     * Called by the controller when the player draws a card.
     */
    public void attemptDrawCard(){
        drawCard(storage.currPlayer, 1);
        nextTurn();
    }

    /**
     * Makes the player after currPlayer draw one card.
     */
    private void drawOne() {
        if(storage.currPlayerIndex == storage.players.size()-1){
            drawCard(storage.players.get(0),1);
            storage.currPlayerIndex = 0;
        }
        else{
            drawCard(storage.players.get(storage.currPlayerIndex+1),1);
            storage.currPlayerIndex++;
        }
    }

    /**
     * Notifies the flip handlers to flip, as well as making a check to see whether the reverse of the card being played needs to have a colour assigned.
     * @param card The flip card that has just been played.
     */
    private void flip(Card card){
        if (card.getOtherSide().getCardColour() == Card.Colour.BLACK){
            if (storage.currPlayer instanceof AIPlayer player) {
                card.getOtherSide().setColour(player.getColour());
            }
            else {
                card.getOtherSide().setColour(view.getColour());
            }
        }
        for (FlipListener listener : storage.flipListeners){
            listener.handleFlip();
        }
    }

    /**
     * Reverses the order of the player list, then updates the index to reflect that change.
     */
    public void reverse() {
        Collections.reverse(storage.players);
        storage.currPlayerIndex = storage.players.indexOf(storage.currPlayer);
    }

    /**
     * Skips the player after currPlayer's turn.
     */
    private void skip() {
        if(storage.currPlayerIndex == storage.players.size()-1){
            storage.currPlayerIndex = 0;
        }
        else{
            storage.currPlayerIndex++;
        }

    }


    /**
     * Transforms the most recently player card into a colour of the current player's choice
     * and causes the following player to draw 2.
     */
    private void wildDrawTwo() {
        if(storage.currPlayerIndex == storage.players.size()-1){
            drawCard(storage.players.get(0),2);
            storage.currPlayerIndex = 0;
        }
        else{
            drawCard(storage.players.get(storage.currPlayerIndex+1),2);
            storage.currPlayerIndex++;
        }
    }

    /**
     * Checks if a player has exceeded 500 points. If one has, set them to be the winner, adn return true. Otherwise, return false.
     * @return true if someone has exceeded 500 points, false otherwise.
     */
    private boolean checkWinner(){
        for (Player player : storage.players){
            if (player.getPoints() >= 500){
                storage.gameWinner = player;
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies whether a given card can be legally played on topCard.
     * @param card A card, normally the one being played.
     * @return whether the provided card is a legal move
     */
    public boolean legalMove(Card card){
        if (card.getCardColour() == Card.Colour.BLACK){
            return true;
        }
        if (card.getSpecialType() != null){
            return (card.getCardColour() == storage.topCard.getCardColour() || card.getSpecialType() == storage.topCard.getSpecialType());
        }
        return (card.getCardNum() == storage.topCard.getCardNum() || card.getCardColour() == storage.topCard.getCardColour());
    }

    /**
     * Plays a given card, adding it to the end of the discard pile.
     * @param card the card being played
     */
    private void addToPlayedCards(Card card){
        storage.playedCards.add(card);
    }

    /**
     * Draws amount cards from the deck, and gives them to provided player.
     * @param player The player drawing the cards
     * @param amount The amount of cards the player draws
     */
    private void drawCard(Player player, int amount){
        ArrayList<Card> cards = storage.deck.drawNCard(amount);
        for (Card card : cards){
            System.out.println(card + " has been drawn, with reverse side " + card.getOtherSide());
        }
        player.addCard(cards);
        view.drawCard(player, cards);
    }

    /**
     * Either increments or loops the current player index currPlayerIndex
     * back around to 0.
     */
    public void iteratePlayers(){
        if(storage.currPlayerIndex < storage.players.size()-1){
            storage.currPlayerIndex ++;
        } else {
            storage.currPlayerIndex = 0;
        }
        storage.currPlayer = storage.players.get(storage.currPlayerIndex);

    }

    public boolean hasPlayers(){
        System.out.println(storage.players.size());
        return !storage.players.isEmpty();
    }

    public boolean autosaveOn(){
        return storage.autosave;
    }

    public void toggleAutosave(){
        storage.autosave = !storage.autosave;
    }

    public void setAutosave(String path){
        storage.autosavePath = path;
    }

    public void importGame(GameStorage s){
        storage = s;
    }

    public void exportGame(String path){
        View savedView = view;
        File saveFile = new File(path);
        view = null;
        try {
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(storage);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+path);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            view = savedView;
        }
    }
}
