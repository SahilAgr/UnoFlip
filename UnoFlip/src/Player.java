import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<CardPair> hand;

    private int points;

    /**
     * Player constructor
     * @param inputName String
     */
    public Player(String inputName){
        this.name = inputName;
        this.hand = new ArrayList<CardPair>();
        this.points = 0;
    }
    public Player(String inputName, ArrayList<CardPair> inputHand){
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
     * @param card Card
     * @return card
     */
    public CardPair playCard(CardPair card){
        this.hand.remove(card);
        return card;
    }

    /**
     * Adds a card to the players hand
     * @param cardList ArrayList<CardPair>
     */
    public void addCard(ArrayList<CardPair> cardList){
        hand.addAll(cardList);
    }

    /**
     * removes a card from the players hand
     * @param card CardPair
     */
    public void removeCard(CardPair card){
        hand.remove(card);
    }

    /**
     * Returns all the cards in a players hand
     * @return ArrayList<CardPair>
     */
    public ArrayList<CardPair> getHand(){
        return this.hand;
    }

    /**
     * Returns the score of a players hand based on the rules
     * @return int
     */
    public int getHandScore(Card.Type cardType){
        int total = 0;
        for (CardPair card : this.hand){
            if (card.getCard(cardType).getCardNum() == null){
                if (card.getCard(cardType).getSpecialType() == Card.Special.WILD || card.getCard(cardType).getSpecialType() == Card.Special.WILD_DRAW_TWO_CARDS){
                    total += 50;
                }
                else {
                    total += 20;
                }
            }
            else {
                total += card.getCard(cardType).getCardNum().ordinal()+1;
            }
        }
        return total;
    }

    /**
     * Removes all cards from a players hand
     */
    public void removeAllCards(){
        this.hand = new ArrayList<CardPair>();
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
}
