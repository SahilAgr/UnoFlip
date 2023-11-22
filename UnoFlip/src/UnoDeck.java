import java.util.*;

public class UnoDeck implements FlipListener {
    private ArrayList<Card> deck;
    private ArrayList<Card> activeDeck;
    private ArrayList<Card> inactiveDeck;

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

        for (int i = 0; i < lightCards.size(); i++) {
            System.out.println("Setting "+lightCards.get(i)+" to match with "+darkCards.get(i));
            lightCards.get(i).setOtherSide(darkCards.get(i));
            darkCards.get(i).setOtherSide(lightCards.get(i));
        }

        activeDeck = lightCards;
        inactiveDeck = darkCards;
    }

    /**
     * returns all the cards in the deck
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getDeck() {
        return activeDeck;
    }


    /**
     * returns n amount of cards from the deck of cards
     * @param nCards int
     * @return ArrayList<Card>
     */
    public ArrayList<Card> drawNCard(int nCards) {
        ArrayList<Card> drawnCards = new ArrayList<>();

        // Check if there are enough cards in the deck
        if (activeDeck.size() < nCards) {
            throw new IllegalStateException("Not enough cards in the deck to draw " + nCards + " cards.");
        }
        Card card;
        // Draw the specified number of cards
        for (int i = 0; i < nCards; i++) {
            card = activeDeck.remove(0);
            drawnCards.add(card); // Removes and returns the top card from the deck
            inactiveDeck.remove(card.getOtherSide());
        }

        return drawnCards;
    }

    public void handleFlip(){
        ArrayList<Card> temp = activeDeck;
        activeDeck = inactiveDeck;
        inactiveDeck = temp;
    }


}