import java.util.*;

public class UnoDeck {
    private ArrayList<Card> deck;

    public UnoDeck() {
        ArrayList<Card> cards = new ArrayList<Card>();

        // Create Light and Dark cards
        for (Card.Colour colour : Card.Colour.values()) {
            if (colour != Card.Colour.BLACK) { // Assuming BLACK is not used in Light/Dark cards
                for (Card.Rank rank : Card.Rank.values()) {


                    cards.add(new Card(rank, colour, null, Card.Type.LIGHT));
                    cards.add(new Card(rank, colour, null, Card.Type.LIGHT));


                }

                for (Card.Special special : Card.Special.values()) {
                    if (special != Card.Special.WILD && special != Card.Special.WILD_DRAW_TWO_CARDS) {
                        cards.add(new Card(null, colour, special, Card.Type.LIGHT));
                        cards.add(new Card(null, colour, special, Card.Type.LIGHT));
                    }
                }
            }
            /**
             * Adding the wild cards
             */
            else {
                for (int i=0;i<4;i++) {
                    cards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD,Card.Type.LIGHT));
                    cards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS,Card.Type.LIGHT));
                }
            }
        }
        Collections.shuffle(cards);

        this.deck = cards;
    }

    /**
     * returns all the cards in the deck
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }


    /**
     * returns n amount of cards from the deck of cards
     * @param nCards int
     * @return ArrayList<Card>
     */
    public ArrayList<Card> drawNCard(int nCards) {
        ArrayList<Card> drawnCards = new ArrayList<>();

        // Check if there are enough cards in the deck
        if (deck.size() < nCards) {
            throw new IllegalStateException("Not enough cards in the deck to draw " + nCards + " cards.");
        }

        // Draw the specified number of cards
        for (int i = 0; i < nCards; i++) {
            drawnCards.add(deck.remove(0)); // Removes and returns the top card from the deck
        }

        return drawnCards;
    }




}