import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Create an UnoDeck instance
        UnoDeck unoDeck = new UnoDeck();

        // Get the deck of card pairs
        ArrayList<CardPair> deck = unoDeck.getDeck();
        Card card1 = new Card(Card.Rank.ONE, Card.Colour.BLUE, null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.TWO, Card.Colour.RED, Card.Special.DRAW_ONE, Card.Type.DARK);
        Card card3 = new Card(Card.Rank.TWO, Card.Colour.RED, null, Card.Type.LIGHT);


        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        for (Card.Colour colour : Card.Colour.values()) {
            if (colour != Card.Colour.BLACK) { // Assuming BLACK is not a valid color for individual cards
                for (Card.Rank rank : Card.Rank.values()) {
                    checkCardImagePath(new Card(rank, colour, null, Card.Type.LIGHT));
                    checkCardImagePath(new Card(rank, colour, null, Card.Type.DARK));
                }

                for (Card.Special special : Card.Special.values()) {
                    checkCardImagePath(new Card(null, colour, special, Card.Type.LIGHT));
                    checkCardImagePath(new Card(null, colour, special, Card.Type.DARK));
                }
            }
        }

        System.out.println("Deck Contents:");
        for (int i = 0; i < deck.size(); i++) {
            CardPair cardPair = deck.get(i);
            Card lightCard = cardPair.getCard(Card.Type.LIGHT);
            Card darkCard = cardPair.getCard(Card.Type.DARK);

            // Concatenating the details of the Light and Dark card for each pair
            String cardPairDetails = String.format("Pair %d: [Light Card: %s, Dark Card: %s]", i + 1, lightCard, darkCard);
            System.out.println(cardPairDetails);
        }


    }

    private static void checkCardImagePath(Card card) {
        String imagePath = card.getImagePath();
        File file = new File(imagePath);
        if (file.exists()) {
            System.out.println("File exists: " + imagePath);
        } else {
            System.out.println("File does not exist: " + imagePath);
        }
    }
}
