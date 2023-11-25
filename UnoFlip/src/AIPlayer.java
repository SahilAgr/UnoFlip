import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(String inputName) {
        super(inputName);
    }

    public Card.Colour getColour(){
        ArrayList<Card.Colour> colour = new ArrayList<>();
        colour.add(Card.Colour.RED);
        colour.add(Card.Colour.BLUE);
        colour.add(Card.Colour.GREEN);
        colour.add(Card.Colour.YELLOW);
      
        int index = (int)(Math.random() * colour.size());

        return colour.get(index);

    }

    public void legalCards(Game game, Player p){
        ArrayList<Card> playableCards = new ArrayList<>();
        for(Card c: p.getHand()){
            if(game.legalMove(c)){
                playableCards.add(c);
                System.out.println(c);
            }

        }

        if (playableCards.size() == 0){
            game.attemptDrawCard();
        }else {

            System.out.println("THIS IS WHAT AI PICKS FROM AIPLAYER.JAVA"+playableCards.get(0));
            System.out.println(p.getCardIndex(playableCards.get(0)));
            game.attemptPlayCard(p.getCardIndex(playableCards.get(0)));
        }

    }

}
