import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String inputName, ArrayList<Card> inputHand){
        this.name = inputName;
        this.hand = inputHand;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String inputName){
        this.name = inputName;
    }

    public Card playCard(Card card){
        this.hand.remove(card);
        return card;
    }

    public void addCard(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }

    public void removeCard(Card card){
        hand.remove(card);
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public void removeAllCards(){
        hand = new ArrayList<Card>();
    }
}
