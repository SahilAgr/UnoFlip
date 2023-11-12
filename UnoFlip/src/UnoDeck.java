import java.util.*;

public class UnoDeck {

    private ArrayList<CardPair> deck;

    public UnoDeck() {
        ArrayList<Card> lightCards = new ArrayList<Card>();
        ArrayList<Card> darkCards = new ArrayList<Card>();

        // Create Light and Dark cards
        for (Card.Colour colour : Card.Colour.values()) {
            if (colour != Card.Colour.BLACK) { // Assuming BLACK is not used in Light/Dark cards
                for (Card.Rank rank : Card.Rank.values()) {


                    lightCards.add(new Card(rank, colour, null, Card.Type.LIGHT));
                    darkCards.add(new Card(rank, colour, null, Card.Type.DARK));
                    lightCards.add(new Card(rank, colour, null, Card.Type.LIGHT));
                    darkCards.add(new Card(rank, colour, null, Card.Type.DARK));


                }

                for (Card.Special special : Card.Special.values()) {
                    if (special != Card.Special.WILD && special != Card.Special.WILD_DRAW_TWO_CARDS) {

                        lightCards.add(new Card(null, colour, special, Card.Type.LIGHT));
                        darkCards.add(new Card(null, colour, special, Card.Type.DARK));
                        lightCards.add(new Card(null, colour, special, Card.Type.LIGHT));
                        darkCards.add(new Card(null, colour, special, Card.Type.DARK));
                    }
                }


            }
            /**
             * Adding the wild cards
             */
            else {
                for (int i=0;i<4;i++) {
                    lightCards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD,Card.Type.LIGHT));
                    lightCards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS,Card.Type.LIGHT));
                }
                for(int i=0;i<8;i++){
                    darkCards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD,Card.Type.DARK));
                }
            }
        }
        Collections.shuffle(lightCards);

        Collections.shuffle(darkCards);

        this.deck = new ArrayList<CardPair>();

        for (int i = 0; i < lightCards.size(); i++) {
            this.deck.add(new CardPair(lightCards.get(i), darkCards.get(i)));
        }
    }

    /**
     * returns all the cards in the deck
     * @return ArrayList<Card>
     */
    public ArrayList<CardPair> getDeck() {
        return deck;
    }


    /**
     * returns n amount of cards from the deck of cards
     * @param nCards int
     * @return ArrayList<Card>
     */
    public ArrayList<CardPair> drawNCard(int nCards) {
        ArrayList<CardPair> drawnCards = new ArrayList<>();

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