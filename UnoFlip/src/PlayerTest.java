import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    /**
     * Checks Player class if you are able to get the player's name.
     * **/
    public void getName() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Player player1 = new Player("Bill");
        Assert.assertEquals("Bill", player1.getName());

        Player player2 = new Player("Tim",cards);
        Assert.assertEquals("Tim", player2.getName());
    }

    @Test
    /**
     * Checks Player class if you are able to set the player's name.
     * **/
    public void setName() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Player player1 = new Player("Bill");
        player1.setName("Frank");
        Assert.assertEquals("Frank", player1.getName());

        Player player2 = new Player("Tim",cards);
        player2.setName("Jimmy");
        Assert.assertEquals("Jimmy", player2.getName());
    }

    @Test
    /**
     * Checks Player class if you are able to play a card.
     * **/
    public void playCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Player player = new Player("Tim",cards);

        player.playCard(card3);
        assertEquals(2,cards.size());
        assertEquals("[BLUE EIGHT, GREEN FIVE]", cards.toString());
    }

    @Test
    /**
     * Checks Player class if you are able to add a Card.
     * **/
    public void addCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);

        ArrayList<Card> card = new ArrayList<Card>();
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        card.add(card3);

        Player player = new Player("Tim",cards);
        player.addCard(card);

        assertEquals(3,cards.size());
        assertEquals("[BLUE EIGHT, GREEN FIVE, BLACK WILD_DRAW_TWO_CARDS]", cards.toString());

    }

    @Test
    /**
     * Checks Player class if you are able to remove a card.
     * **/
    public void removeCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Player player = new Player("Tim",cards);
        player.removeCard(card1);

        assertEquals(2,cards.size());
        assertEquals("[GREEN FIVE, BLACK WILD_DRAW_TWO_CARDS]", cards.toString());

    }

    @Test
    /**
     * Checks Player class if you are able to get the player's hand.
     * **/
    public void getHand() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Player player = new Player("Tim",cards);
        player.getHand();

        assertEquals(3,cards.size());
        assertEquals("[BLUE EIGHT, GREEN FIVE, BLACK WILD_DRAW_TWO_CARDS]", cards.toString());

    }

    @Test
    /**
     * Checks Player class if you are able to get the player's hand score.
     * **/
    public void getHandScore() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Player player = new Player("Tim",cards);
        assertEquals(63, player.getHandScore());
    }

    @Test
    /**
     * Checks Player class if you are able to remove all the cards.
     * **/
    public void removeAllCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = new Card(Card.Rank.EIGHT, Card.Colour.BLUE,null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.FIVE, Card.Colour.GREEN,null, Card.Type.LIGHT);
        Card card3 = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Player player = new Player("Tim",cards);
        player.removeAllCards();

        assertEquals(0, player.getHand().size());
        assertEquals("[]", player.getHand().toString());
    }

    @Test
    /**
     * Checks Player class if you are able to get the points.
     * **/
    public void getPoints() {
        Player player = new Player("Tim");
        assertEquals(0, player.getPoints());

        player.addPoints(34);
        assertEquals(34, player.getPoints());
    }

    @Test
    /**
     * Checks Player class if you are able to add the points.
     * **/
    public void addPoints() {
        Player player = new Player("Tim");
        player.addPoints(100);
        assertEquals(100, player.getPoints());
    }
}