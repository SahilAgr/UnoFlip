import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(String inputName) {
        super(inputName);
    }

    public Card.Colour getColour() {
        return Card.Colour.BLACK;
    }

    public ArrayList<Card.Colour> getRandomElement(ArrayList<Card.Colour> list)
    {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        ArrayList<Card.Colour> newList = new ArrayList<>();

        // take a random index between 0 to size
        // of given List
        int randomIndex = rand.nextInt(3);

        // add element in temporary list


        return newList;
    }

    public void legalCards(Game game, Player p){
        ArrayList<Card> playableCards = new ArrayList<>();
        for(Card c: p.getHand()){
            if(game.legalMove(c)){
                playableCards.add(c);
                System.out.println(c);
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

                this.getRandomElement(options);


            }
            else {
                System.out.println("THIS IS WHAT AI PICKS FROM AIPLAYER.JAVA"+playableCards.get(0));
                System.out.println(p.getCardIndex(playableCards.get(0)));
                game.attemptPlayCard(p.getCardIndex(playableCards.get(0)));



            }

        }

    }

}
