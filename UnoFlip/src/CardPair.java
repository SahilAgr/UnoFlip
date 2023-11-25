import java.util.ArrayList;

public class CardPair {
    private final Card lightCard;
    private final Card darkCard;
    private Card activeCard;

    public CardPair(Card lightCard, Card darkCard) {
        this.lightCard = lightCard;
        this.darkCard = darkCard;
        this.activeCard = this.lightCard;
    }

    public Card getCard(Card.Type type){
        if (type == Card.Type.LIGHT){
             return this.lightCard;
        }
        else {
            return this.darkCard;
        }
    }

    public static ArrayList<Card> toCardArrayList(ArrayList<CardPair> list, Card.Type type){
        ArrayList<Card> cardList = new ArrayList();
        for (CardPair card : list){
            cardList.add(card.getCard(type));
        }
        return cardList;
    }

}