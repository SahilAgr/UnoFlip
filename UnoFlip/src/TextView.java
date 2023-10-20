import java.util.ArrayList;

public class TextView implements View {

    public TextView(){
        UnoDeck deck = new UnoDeck();
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players, deck);
        game.setView(this);
    }

    public void topCard(Card card) {
        System.out.println("Top Card: " + card + "\n");
    }

    public void playerDecision(Player player) {
        System.out.println("Enter card index to play or 0 to draw a card: \n");
    }

    public void cardPlayed(Card card, Boolean validCard){
        if (!validCard) {
            System.out.println("Card doesn't match the top card. Try again.\n");
        }
        System.out.println("Played: " + card + ".\n");
    }

    public void nextPlayer(Player player){
        System.out.println(player + "'s Turn\n");
    }

    public void cardHand(ArrayList<Player> playerHand) {
        System.out.println("Your Cards: \n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(String.valueOf(i) + playerHand.get(i) + "\n");
        }
    }

    public void roundEnd(Player roundWinner){
        System.out.println(roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints());
        System.out.println("Starting new round...");
    }

    public void winner(Player winner){
        System.out.println(winner.getName() + " has won the with a score of " + winner.getPoints());
    }

    public static void main(String[] args){
        TextView t = new TextView();
    }

}
