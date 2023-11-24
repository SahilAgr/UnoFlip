import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(String inputName) {
        super(inputName);
    }

    public ArrayList<Card.Colour> getRandomElement(ArrayList<Card.Colour> list, int totalItems)
    {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        ArrayList<Card.Colour> newList = new ArrayList<>();

        // take a random index between 0 to size
        // of given List
        int randomIndex = rand.nextInt(list.size());

        // add element in temporary list
        newList.add(list.get(randomIndex));

        return newList;
    }

    public void legalCards(Game game, Player p){
        ArrayList<Card> playableCards = new ArrayList<>();
        for(Card c: p.getHand()){
            if(game.legalMove(c)){
                playableCards.add(c);
            }

        }

        System.out.println("//////////////////////////////\n");
        System.out.println(playableCards);
        System.out.println("/////////////////////////////\n");

        if (playableCards.size() == 0){
            game.attemptDrawCard();
        }else {
            if(playableCards.get(0).getCardColour() == Card.Colour.BLACK && playableCards.get(0).getSpecialType() == Card.Special.WILD){
                game.attemptPlayCard(p.getCardIndex(playableCards.get(0)));
                ArrayList<Card.Colour> options = new ArrayList<>();
                options.add(Card.Colour.RED);
                options.add(Card.Colour.BLUE);
                options.add(Card.Colour.GREEN);
                options.add(Card.Colour.YELLOW);

                this.getRandomElement(options,4);
                game.currPlayerIndex++;

            }
            else {
                game.attemptPlayCard(p.getCardIndex(playableCards.get(0)));
                game.currPlayerIndex++;

            }

        }

    }

}
