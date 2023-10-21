import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;

    public enum State{GAME_START, IN_ROUND, BETWEEN_ROUND, GAME_END}

    private State gameState;

    private Card topCard;

    private ArrayList<Card> playedCards;

    private UnoDeck deck;

    private Player currPlayer;

    private int playerChoice;

    private int roundCounter;

    private Player roundWinner;

    private Player gameWinner;

    private View view;

    int currPlayerIndex;

    public Game(ArrayList<Player> players){
        this.players = players;
        this.roundCounter = 0;
        this.gameState = State.GAME_START;
    }

    public Game(){
        this.roundCounter = 0;
    }

    public void setView(View view){
        this.view = view;
    }

    public void startGame(){
        updateGame();
    }

    public void addPlayer(Player player){
        if (gameState != State.IN_ROUND){
            players.add(player);
            view.addPlayer(player);
        }
    }

    private void updateGame(){
        this.gameState = State.BETWEEN_ROUND;
        if (checkWinner()){
            view.winner(gameWinner);
            this.gameState = State.GAME_END;
        }
        else {
            roundCounter ++;
            playRound();
        }
    }

    private void playRound(){
        this.currPlayerIndex = 0;
        this.deck = new UnoDeck();
        this.playedCards = new ArrayList<Card>();
        playCard(deck.getNCards(1).get(0));

        view.roundStart(roundCounter);

        this.gameState = State.IN_ROUND;

        for (Player player : this.players){
            drawCard(player, 7);
        }

        while (gameState == State.IN_ROUND){
            this.topCard = this.playedCards.get(this.playedCards.size()-1);
            this.currPlayer = players.get(currPlayerIndex);

            view.nextPlayer(currPlayer, topCard);

            if (playerChoice != 0){
                Card card = currPlayer.getHand().get(playerChoice-1);
                view.cardPlayed(card, legalMove(card));
                while (!legalMove(card)){
                    view.illegalMove(currPlayer);
                    card = currPlayer.getHand().get(playerChoice-1);
                    view.cardPlayed(card, legalMove(card));
                }
                playCard(currPlayer.playCard(card));
            }
            else {
                drawCard(currPlayer, 1);
            }
            if (currPlayer.getHand().isEmpty()){
                gameState = State.BETWEEN_ROUND;
                roundWinner = currPlayer;
            }
            iteratePlayers();
        }
        //Scoring
        int roundScore = 0;
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

    public void setPlayerChoice(int choice){
        this.playerChoice = choice;
    }

    private boolean legalMove(Card card){
        return (card.getCardNum() == topCard.getCardNum() || card.getCardColour() == topCard.getCardColour() || card.getSpecialType() == topCard.getSpecialType());
    }

    private void playCard(Card card){
        playedCards.add(card);
    }

    private void drawCard(Player player, int amount){
        ArrayList<Card> cards = deck.getNCards(amount);
        player.addCard(cards);
        view.drawCard(player, cards);
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
