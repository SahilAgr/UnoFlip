import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UnoDeck {

    private ArrayList<Card> cards;

    public UnoDeck(){
        this.cards = new ArrayList<Card>();

        for(int i = 0; i <= 9; i++){
            Card card = new Card(i, Card.Colour.RED,null);
            Card card1 = new Card(i, Card.Colour.YELLOW,null);
            Card card2 = new Card(i, Card.Colour.BLUE,null);
            Card card3 = new Card(i, Card.Colour.GREEN,null);

            cards.add(card);
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
        }

        Card card8 = new Card(-1, Card.Colour.RED, Card.Special.SKIP);
        Card card9 = new Card(-1, Card.Colour.RED, Card.Special.REVERSE);
        Card card10 = new Card(-1, Card.Colour.RED, Card.Special.WILD_DRAW_TWO_CARDS);
        Card card11 = new Card(-1, Card.Colour.BLACK, Card.Special.WILD);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);

        Card card12 = new Card(-1, Card.Colour.YELLOW, Card.Special.SKIP);
        Card card13 = new Card(-1, Card.Colour.YELLOW, Card.Special.REVERSE);
        Card card14 = new Card(-1, Card.Colour.YELLOW, Card.Special.WILD_DRAW_TWO_CARDS);
        Card card15 = new Card(-1, Card.Colour.BLACK, Card.Special.WILD);
        cards.add(card12);
        cards.add(card13);
        cards.add(card14);
        cards.add(card15);

        Card card16 = new Card(-1, Card.Colour.BLUE, Card.Special.SKIP);
        Card card17 = new Card(-1, Card.Colour.BLUE, Card.Special.REVERSE);
        Card card18 = new Card(-1, Card.Colour.BLUE, Card.Special.WILD_DRAW_TWO_CARDS);
        Card card19 = new Card(-1, Card.Colour.BLACK, Card.Special.WILD);
        cards.add(card16);
        cards.add(card17);
        cards.add(card18);
        cards.add(card19);

        Card card20 = new Card(-1, Card.Colour.GREEN, Card.Special.SKIP);
        Card card21 = new Card(-1, Card.Colour.GREEN, Card.Special.REVERSE);
        Card card22 = new Card(-1, Card.Colour.GREEN, Card.Special.WILD_DRAW_TWO_CARDS);
        Card card23 = new Card(-1, Card.Colour.BLACK, Card.Special.WILD);
        cards.add(card20);
        cards.add(card21);
        cards.add(card22);
        cards.add(card23);

        Collections.shuffle(cards);
    }

    public ArrayList<Card> getDeckCards(){
        return cards;
    }

    public ArrayList<Card> getSevenCards(){
        ArrayList<Card> sevenCards = new ArrayList<Card>(7);
        Random rand = new Random();
        int upperbound = 52;
        int int_random = rand.nextInt(upperbound);

        for(int i = 0; i < 7; i++){
            sevenCards.add(cards.get(int_random));
            cards.remove(int_random);
        }
        return sevenCards;
    }

    public static void main(String[] args) {
        UnoDeck unoDeck = new UnoDeck();
        //unoDeck.gameCards();
        int a = unoDeck.cards.size();

        System.out.println(unoDeck.getDeckCards());
        System.out.println(unoDeck.getSevenCards());
        System.out.println(a);
        int b = unoDeck.cards.size();
        System.out.println(b);

        System.out.println(a-b);
    }
}
