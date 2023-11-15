import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
class UnoDeckTest {

    private UnoDeck deck;


    @BeforeEach
    public void setUp() {
        deck = new UnoDeck();
    }


    @org.junit.jupiter.api.Test
    void UnoDeck() {

        ArrayList<Card> cards = deck.getDeck();
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
        ArrayList<Card> fiveCards = deck.drawNCard(5);
        assertEquals(5,fiveCards.size());
    }

    private int countCardsOfColour(ArrayList<Card> cards, Card.Colour colour) {
        int count = 0;
        for (Card card : cards) {
            if (card.getCardColour() == colour) {
                count++;
            }
        }
        return count;
    }
}