import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

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

    private Card findCard(Card.Colour c, Card.Special s, int n){
        for (Card card : hand){
            if (card.getCardColour() == c && card.getSpecialType() == s && card.getCardNum() == n){
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public Card playCard(Card.Colour c, Card.Special s, int n){
        Card card = findCard(c, s, n);
        hand.remove(card);
        return card;
    }

    public void addCard(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }

    public void removeCard(Card.Colour c, Card.Special s, int n){
        Card card = findCard(c, s, n);
        hand.remove(card);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void removeAllCards(){
        hand = new ArrayList<Card>();
    }

}
