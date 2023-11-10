import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class responsible for controlling the flow Uno game.
 */
public class UnoController {

    private Game game;

    /**
     * Constructs a new Uncontroller with specified game instance.
     * @param game The Game instance to control.
     */
    public UnoController(Game game){
        this.game = game;
    }

    /**
     * Allows the user to add players to the game, specifying the number of players and their names.
     */
    public void addPlayers(String name){
        this.game.addPlayer(new Player(name));
    }

    /**
     * Prompts the current player to make a move and get the play input.
     * @param player The current player.
     */
    public void getPlay(Player player){
        ArrayList<String> options = new ArrayList<String>();
        for (int i = 0; i <= player.getHand().size(); i ++){
            options.add(String.valueOf(i));
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!options.contains(input)){
            System.out.println("\n"+input+" is not a valid input.");
            input = sc.nextLine();
        }
        game.setPlayerChoice(Integer.parseInt(input));
    }

}
