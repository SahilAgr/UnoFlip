import java.util.ArrayList;

public class TextView implements View{

    public TextView(){
        UnoDeck deck = new UnoDeck();
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players, deck);
        game.setView(this);
    }

    public void nextPlayer(){

    }

    public void cardPlayed(){

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
