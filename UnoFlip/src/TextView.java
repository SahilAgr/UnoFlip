import java.util.ArrayList;

public class TextView implements View {

    public TextView(){
        UnoDeck deck = new UnoDeck();
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players, deck);
        game.setView(this);
    }

    public void topCard(Card card) {
        System.out.println("Top Card: " + card);
    }

    public void playerDecision(Player player) {
        System.out.println("Enter card index to play or 0 to draw a card: \n");
    }

    public void cardPlayed(Card card){
        System.out.println("Played: " + card);
    }

    public void nextPlayer(Player player){
        System.out.println(player + "'s Turn");
    }

    public void cardHand(Player playerHand) {
        System.out.println("Your Cards: \n");

    }

    public void roundEnd(Player roundWinner){
        System.out.println(roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints());
        System.out.println("Starting new round...");
    }

    public void winner(Player winner){

    }

    public static void main(String[] args){
        TextView t = new TextView();
    }

}
