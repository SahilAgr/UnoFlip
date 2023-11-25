import java.util.ArrayList;

public class Player implements FlipListener {
    private String name;
    private ArrayList<Card> hand;

    private int points;

    /**
     * Player constructor
     * @param inputName String
     */
    public Player(String inputName){
        this.name = inputName;
        this.hand = new ArrayList<Card>();
        this.points = 0;
    }
    public Player(String inputName, ArrayList<Card> inputHand){
        this.name = inputName;
        this.hand = inputHand;
        this.points = 0;
    }

    /**
     * Returns the name of the player
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the name of the player
     * @param inputName
     */
    public void setName(String inputName){
        this.name = inputName;
    }

    /**
     * Plays a chosen card by the player
     * @param cardIndex int
     * @return card
     */
    public Card playCard(int cardIndex){
        return this.hand.remove(cardIndex);
    }

    /**
     * Adds a card to the players hand
     * @param cardList ArrayList<Card>
     */
    public void addCard(ArrayList<Card> cardList){
        hand.addAll(cardList);
    }

    /**
     * removes a card from the players hand
     * @param card Card
     */
    public void removeCard(Card card){
        hand.remove(card);
    }

    /**
     * Returns all the cards in a players hand
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getHand(){
        return this.hand;
    }

    /**
     * Returns the score of a players hand based on the rules
     * @return int
     */
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

    /**
     * Removes all cards from a players hand
     */
    public void removeAllCards(){
        this.hand = new ArrayList<Card>();
    }

    /**
     * returns how many points a player has
     * @return int
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Adds points to a player
     * @param roundScore int
     */
    public void addPoints(int roundScore){
        this.points += roundScore;
    }

    public void handleFlip() {
        Card otherSide;
        for (int i = 0; i < hand.size(); i ++){
            otherSide = hand.get(i).getOtherSide();
            hand.set(i, otherSide);
        }
    }

    public int getCardIndex(Card c){
        for(int i = 0; i < hand.size(); i++){
            if (hand.get(i) == c){
                return i;
            }
        }

        return -1;
    }
}
