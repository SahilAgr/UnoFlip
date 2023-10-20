import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    private int points;

    public Player(String inputName, ArrayList<Card> inputHand){
        this.name = inputName;
        this.hand = inputHand;
        this.points = 0;
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

    public int getHandScore(){
        int total = 0;
        for (Card card : this.hand){
            if (card.getCardNum() == null){
                if (card.getSpecialType() == Card.Special.WILD || card.getSpecialType() == Card.Special.WILD_DRAW_TWO_CARDS){
                    total += 50;
                }
                else {
                    total += 20;
                }
            }
            else {
                total += card.getCardNum().ordinal()+1;
            }
        }
        return total;
    }

    public void removeAllCards(){
        this.hand = new ArrayList<Card>();
    }

    public int getPoints(){
        return this.points;
    }
    public void addPoints(int roundScore){
        this.points += roundScore;
    }
}
