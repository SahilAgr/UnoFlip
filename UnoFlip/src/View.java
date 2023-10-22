import java.util.ArrayList;

public interface View {
    public void nextPlayer(Player player, Card topCard);

    public void cardPlayed(Card card, Boolean valid);

    public void illegalMove(Player player);

    public void drawCard(Player player, ArrayList<Card> cardsDrawn);

    public void roundEnd(Player roundWinner);

    public void roundStart(int roundCounter);

    public void winner(Player winner);

    public void addPlayer(Player player);

    public Card.Colour getColour();

}
