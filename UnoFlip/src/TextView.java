import java.util.ArrayList;

public class TextView implements View {

    UnoController controller;

    public TextView(){
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        controller = new UnoController(game);
        game.setView(this);

        System.out.println("How many players are there?");
        controller.addPlayers();

        game.startGame();
    }

    public void topCard(Card card) {
        System.out.println("Top Card: " + card + "\n");
    }

    public void playerDecision(Player player) {
        System.out.println("Enter card index to play or 0 to draw a card: \n");
    }

    public void addPlayer(Player player){
        System.out.println("Added player " + player.getName());
    }

    public void nextPlayer(Player player, Card topCard){
        System.out.println("The top card is " +  topCard);
        System.out.println(player.getName() + "'s Turn\n");
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    public void cardHand(ArrayList<Card> playerHand) {
        System.out.println("Your Cards: \n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(String.valueOf(i+1) + " " + playerHand.get(i));
        }
    }

    public void drawCard(Player player, ArrayList<Card> cardsDrawn){
        System.out.println("\n"+player.getName()+" has drawn:");
        for (Card card : cardsDrawn){
            System.out.println(card);
        }
    }

    public void illegalMove(Player player){
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    public void cardPlayed(Card card, Boolean validCard){
        if (!validCard) {
            System.out.println("Card doesn't match the top card. Try again.\n");
        }
        System.out.println("Played: " + card + ".\n");
    }

    public void roundStart(int roundCount){
        System.out.println("Starting round " + roundCount);
    }

    public void roundEnd(Player roundWinner){
        System.out.println(roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints());
        System.out.println("Starting new round...");
    }

    public void winner(Player winner){
        System.out.println(winner.getName() + " has won the with a score of " + winner.getPoints());
    }

    public static void main(String[] args){
        new TextView();
    }

}
