import java.util.ArrayList;

public class AIPlayer extends Player{
    public AIPlayer(String inputName) {
        super(inputName);
    }


    public void legalCards(Game game, Player p){
        ArrayList<Card> playableCards = new ArrayList<>();
        for(Card c: p.getHand()){
            if(game.legalMove(c)){
                playableCards.add(c);
            }

        }
        if (playableCards.size() == 0){
            game.attemptDrawCard();
        }else {
            game.attemptPlayCard(p.getCardIndex(playableCards.get(0)));
        }

    }

}
