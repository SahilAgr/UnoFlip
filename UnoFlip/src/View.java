import java.util.ArrayList;

/**
 * Interface defines the new methods that need to be implemented by any class
 * providing a user interface for the game.
 */
public interface View {

    /**
     * Notifies the next player's turn and displays the current top card.
     * @param player Next player to play.
     * @param topCard Current player who is playing.
     */
    public void nextPlayer(Player player, Card topCard);

    public void autosaveToggled(boolean status, String path);

    /**
     * Notifies that a card has been played and whether it's a valid move.
     * @param card Card that was played.
     * @param valid True if playing card is a valid move, otherwise false.
     */
    public void cardPlayed(Card card, Boolean valid);

    /**
     * Notifies the player that their move was illegal and prompts them to try again.
     */
    public void illegalMove();

    /**
     * Notifies when a player has drawn one or more cards and displays the drawn card.
     * @param player Player who drew cards.
     * @param cardsDrawn List of cards drawn.
     */
    public void drawCard(Player player, ArrayList<Card> cardsDrawn);

    /**
     * Notifies the end of a game round and the winner of the round.
     * @param roundWinner Player who won the round.
     */
    public void roundEnd(Player roundWinner);

    /**
     * Notifies the start of a new game round and displays the round count.
     * @param roundCounter Number of the current round.
     */
    public void roundStart(int roundCounter);

    /**
     * Notifies the overall winner of the game and displays their total score.
     * @param winner Player who won the game with their total score.
     */
    public void winner(Player winner);

    /**
     * Allows the user to pick a colour for a Wild Card.
     * @return Colour that was chosen.
     */
    public Card.Colour getColour();

    public void AiPlayerPlayed(Card card);

    void undo();

    void redo(boolean upToDate);
}
