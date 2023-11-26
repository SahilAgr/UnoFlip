import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardPairTest {
    private Card lightCard;
    private Card darkCard;
    private CardPair cardPair;

    @BeforeEach
    public void setUp() {
        lightCard = new Card(Card.Rank.ONE, Card.Colour.BLUE,null, Card.Type.LIGHT);
        darkCard = new Card(Card.Rank.ONE, Card.Colour.BLUE,null, Card.Type.DARK);
        cardPair = new CardPair(lightCard, darkCard);
    }

    @Test
    void testGetCardReturnsCorrectLightCard() {
        assertEquals(lightCard, cardPair.getCard(Card.Type.LIGHT));
    }

    @Test
    void testGetCardReturnsCorrectDarkCard() {
        assertEquals(darkCard, cardPair.getCard(Card.Type.DARK));
    }

    @Test
    public void testToCardArrayList() {
        ArrayList<CardPair> cardPairs = new ArrayList<>();
        cardPairs.add(new CardPair(lightCard, darkCard));

        ArrayList<Card> cards = CardPair.toCardArrayList(cardPairs, Card.Type.LIGHT);

        assertEquals(cardPairs.size(), cards.size());

        for (int i = 0; i < cards.size(); i++) {
            assertEquals(cardPairs.get(i).getCard(Card.Type.LIGHT), cards.get(i));
        }
    }
}