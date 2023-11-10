import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Create an UnoDeck instance
        UnoDeck unoDeck = new UnoDeck();

        // Get the deck of card pairs
        ArrayList<UnoDeck.CardPair> deck = unoDeck.getDeck();
        Card card1 = new Card(Card.Rank.ONE, Card.Colour.BLUE, null, Card.Type.LIGHT);
        Card card2 = new Card(Card.Rank.TWO, Card.Colour.RED, Card.Special.DRAW_ONE, Card.Type.DARK);

        System.out.println("Image Path for card1: " + card1.getImagePath());
        System.out.println("Image Path for card2: " + card2.getImagePath());

        checkFileExists(card1.getImagePath());
        checkFileExists(card2.getImagePath());

        System.out.println("Deck Contents:");
        for (int i = 0; i < deck.size(); i++) {
            UnoDeck.CardPair cardPair = deck.get(i);
            Card lightCard = cardPair.getLightCard();
            Card darkCard = cardPair.getDarkCard();

            // Concatenating the details of the Light and Dark card for each pair
            String cardPairDetails = String.format("Pair %d: [Light Card: %s, Dark Card: %s]", i + 1, lightCard, darkCard);
            System.out.println(cardPairDetails);
        }


    }

    private static void checkFileExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("File exists: " + path);
        } else {
            System.out.println("File does not exist: " + path);
        }
    }
}
