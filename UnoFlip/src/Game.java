import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;


    //Not sure where and how exactly to create the deck
    //ArrayList<Card> deck;

    Card topCard;
    Player currPlayer;

    int currPlayerIndex;




    public Game(ArrayList<Player> players, Card topCard){
        this.players = players;
        this.topCard = topCard;
        this.currPlayerIndex = 0;
        this.currPlayer = players.get(0);
        startGame();

    }
    /**
     * public Game(ArrayList<Player> players, Card topCard){
     *         this.players = players;
     *         this.topCard = topCard;
     *     }
     */

    public void startGame(){
        System.out.println(topCard);
        boolean gameFinished = false;
        while (gameFinished == false){

        }
    }

    public void legalMove(Card card, Card topCard){
        if(card.getCardNum() == topCard.getCardNum()){
            //place()
        }
        else if(card.getCardColour() == topCard.getCardColour() || card.getSpeicalType() == topCard.getSpecialType()) {
            if (!card.getSpecialType()) {
                //do special thing
            } else {
                //place()
            }
        }
        else if (card.getSpecialType && card.getCardNum == null){
            //do other special
            }
        else{
            System.out.println("You cannot place this card");
        }

        }

        public void drawCard(PLayer currPlayer, int amount){
            for(int i = 0; i < amount; i++){
                currPlayer.addCard(deck.removeCard());
            }
        }




    public void print(int currPlayer){
        System.out.println();

    }



    public void iteratePlayers(){
        if(currPlayerIndex < players.size()-1){
            currPlayerIndex++;
        }else{
            currPlayerIndex = 0;
        }
        currPlayer = players.get(currPlayerIndex);
    }


}
