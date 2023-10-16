import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String n){
        name = n;
        hand = new ArrayList<Card>();
    }

    public Player(String n, ArrayList<Card> h){
        name = n;
        hand = h;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public Card playCard(Card.Colour c, Card.Special s, int n){
        for (Card card : hand){
            if (card.getCardColour() == c && card.getSpecialType() == s && card.getCardNum() == n){
                hand.remove(card);
                return card;
            }
        }
        //This doesn't feel right, I'm going to fix it later.
        // I just can't remember how you're supposed to handle it right now.
        return null;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }



}
