import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
class UnoDeckTest {

    private UnoDeck deck;

    @org.junit.jupiter.api.Test
    void UnoDeck() {
        deck = new UnoDeck();
        ArrayList<CardPair> cards = deck.getDeck();
        assertEquals(112, cards.size());


        assertEquals(8,countCardsOfColour(cards, Card.Colour.BLACK));
        assertEquals(26,countCardsOfColour(cards, Card.Colour.BLUE));
        assertEquals(26,countCardsOfColour(cards, Card.Colour.RED));
        assertEquals(26,countCardsOfColour(cards, Card.Colour.GREEN));
        assertEquals(26,countCardsOfColour(cards, Card.Colour.YELLOW));
    }

    @org.junit.jupiter.api.Test
    void getNCards() {
        deck = new UnoDeck();
        ArrayList<CardPair> fiveCards = deck.drawNCard(5);
        assertEquals(5,fiveCards.size());
    }

    private int countCardsOfColour(ArrayList<CardPair> cards, Card.Colour colour) {
        int count = 0;
        for (CardPair card : cards) {
            if (card.getCard(Card.Type.LIGHT).getCardColour() == colour) {
                count++;
            }
        }
        return count;
    }
}