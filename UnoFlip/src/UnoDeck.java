import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UnoDeck {

    private ArrayList<Card> cards;

    /*
    * generate card in deck
    * */

    public UnoDeck(){
        this.cards = new ArrayList<Card>();

        // rank 1-9 with color red,yellow,green,blue. NO BLACK, 36 cards
        for (Card.Colour colour:Card.Colour.values()) {
            if (colour != Card.Colour.BLACK) {
                for (Card.Rank rank : Card.Rank.values()) {
                    Card Number_pair = new Card(rank, colour,null);
                    cards.add(Number_pair); // 36 cards in deck
                    cards.add(Number_pair); // 72 cards in deck
                    //System.out.println("ben:" + Number_pair);
                }
            // NO black,no wild, each special with red,yellow,green,blue,16 cards
                for (Card.Special special: Card.Special.values()){
                    if(special != Card.Special.WILD && special != Card.Special.WILD_DRAW_TWO_CARDS){
                        Card special_pair = new Card(null,colour,special);
                        cards.add(special_pair);//16 cards in deck
                        cards.add(special_pair);//32 cards in deck
                    }
                }


            }
            //add the wild card

            else {
                for (int i=0;i<4;i++) {
                    cards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD));
                    cards.add(new Card(null, Card.Colour.BLACK, Card.Special.WILD_DRAW_TWO_CARDS));
                }
            }
        }
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getDeckCards(){
        return cards;
    }

    public ArrayList<Card> getNCards(int nCards){
        ArrayList<Card> cardArray = new ArrayList<Card>(nCards);
        Random rand = new Random();
        int int_random;

        for(int i = 0; i < nCards; i++){
            int_random = rand.nextInt(cards.size());
            cardArray.add(cards.get(int_random));
            cards.remove(int_random);
        }
        return cardArray;
    }
}