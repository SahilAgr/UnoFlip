import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;


    //Not sure where and how exactly to create the deck
    //ArrayList<Card> deck;

    private Card topCard;

    private UnoDeck deck;
    private Player currPlayer;

    private int roundCounter;

    private Player roundWinner;

    private Player gameWinner;

    private View view;

    int currPlayerIndex;

    public Game(ArrayList<Player> players, UnoDeck deck){
        this.players = players;
        this.topCard = deck.getNCards(1).get(0);
        this.deck = deck;
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
    }

    public void updateGame(){
        if (checkWinner()){
            view.winner(gameWinner);
        }
        else {
            roundCounter ++;
            startRound();
        }
    }

    public void startRound(){
        System.out.println(topCard);
        boolean roundFinished = false;
        int roundScore = 0;
        ArrayList<Card> legalMoves;
        while (roundFinished == false){
            legalMoves = new ArrayList<Card>();
            currPlayer = players.get(currPlayerIndex);
            for (Card c: currPlayer.getHand()){
                if (legalMove(c)){
                    legalMoves.add(c);
                }
            }

            if (currPlayer.getHand().isEmpty()){
                roundFinished = true;
                roundWinner = currPlayer;
            }
            iteratePlayers();
        }
        //Scoring
        for (Player player : players){
            if (player != roundWinner) {
                roundScore += player.getHandScore();
            }
        }
        roundWinner.addPoints(roundScore);
        view.roundEnd(roundWinner);
        updateGame();
    }

    private boolean checkWinner(){
        for (Player player : this.players){
            if (player.getPoints() >= 500){
                this.gameWinner = player;
                return true;
            }
        }
        return false;
    }

    public void setView(View view){
        this.view = view;
    }

    public boolean legalMove(Card card){
        return (card.getCardNum() == topCard.getCardNum() || card.getCardColour() == topCard.getCardColour() || card.getSpecialType() == topCard.getSpecialType());
    }

    public void drawCard(Player currPlayer, int amount){
        currPlayer.addCard(deck.getNCards(amount));
    }

    public Player getGameWinner(){
        return gameWinner;
    }

    public Player getRoundWinner(){
        return roundWinner;
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
