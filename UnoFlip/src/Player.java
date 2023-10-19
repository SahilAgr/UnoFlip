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

    public Card playCard(Card card){
        hand.remove(card);
        return card;
    }

    public void addCard(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }

    public void removeCard(Card card){
        hand.remove(card);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void removeAllCards(){
        hand = new ArrayList<Card>();
    }
}
