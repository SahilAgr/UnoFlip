import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class CardTest {
    // Create an UnoDeck instance
    UnoDeck unoDeck = new UnoDeck();

    // Get the deck of card pairs
    ArrayList<Card> deck = unoDeck.getDeck();

    @Test
    /**
     * Checks Card class if you are able to get the card number.
     * **/
    public void getCardNum() {
        Card card = new Card(Card.Rank.EIGHT, Card.Colour.RED,null, Card.Type.LIGHT);
        Assert.assertEquals(Card.Rank.EIGHT,card.getCardNum());
    }

    @Test
    /**
     * Checks Card class if you are able to get the card Colour.
     *  **/
    public void getCardColour() {
        Card card = new Card(Card.Rank.EIGHT, Card.Colour.RED,null, Card.Type.LIGHT);
        Assert.assertEquals(Card.Colour.RED,card.getCardColour());

        card = new Card(Card.Rank.EIGHT, null,null, Card.Type.LIGHT);
        Assert.assertEquals(null,card.getCardColour());
    }

    @Test
    /**
     * Checks Card class if you are able to get the card specialType.
     *  **/
    public void getSpecialType() {
        Card card = new Card(Card.Rank.EIGHT, Card.Colour.RED,null, Card.Type.LIGHT);
        Assert.assertEquals(null,card.getSpecialType());

        card = new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS, Card.Type.LIGHT);
        Assert.assertEquals(Card.Special.WILD_DRAW_TWO_CARDS,card.getSpecialType());
    }


    @Test
    /**
     * Checks Card class if you are able to get the card specialType.
     *  **/
    public void setColour() {
        Card card = new Card(Card.Rank.EIGHT, Card.Colour.RED,null, Card.Type.LIGHT);
        card.setColour(Card.Colour.BLUE);
        Assert.assertEquals(Card.Colour.BLUE,card.getCardColour());

        card = new Card(Card.Rank.EIGHT, null,null, Card.Type.LIGHT);
        card.setColour(Card.Colour.GREEN);
        Assert.assertEquals(Card.Colour.GREEN,card.getCardColour());
    }




    @Test
    public void testGetCardNum() {
        Card card = new Card(Card.Rank.EIGHT, Card.Colour.RED,null, Card.Type.LIGHT);
        Assert.assertEquals(Card.Rank.EIGHT,card.getCardNum());
    }




}