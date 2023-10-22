import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UnoController {

    private Game game;

    public UnoController(Game game){
        this.game = game;
    }

    public void addPlayers(){
        String validPlayerCounts = "234";
        Scanner sc = new Scanner(System.in);
        String amount = sc.nextLine();

        while (!validPlayerCounts.contains(String.valueOf(amount)) || amount.length() > 1){
            System.out.println("Amount must be a number between 2 and 4 players!");
            amount = sc.nextLine();
        }

        String name;
        for (int i = 1; i <= Integer.parseInt(amount); i ++){
            System.out.println("Name of player "+i);
            name = sc.nextLine();
            this.game.addPlayer(new Player(name));
        }
    }

    public void getPlay(Player player){
        ArrayList<Integer> options = new ArrayList<Integer>();
        for (int i = 0; i <= player.getHand().size(); i ++){
            options.add(i);
        }
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        while (!options.contains(input)){
            System.out.println("\n"+input+" is not a valid input.");
            input = sc.nextInt();
        }
        game.setPlayerChoice(input);
    }

}
