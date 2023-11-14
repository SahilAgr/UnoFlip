import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class responsible for controlling the flow Uno game.
 */
public class UnoController implements ActionListener {

    private Game game;

    /**
     * Constructs a new UnoController with specified game instance.
     * @param game The Game instance to control.
     */
    public UnoController(Game game){
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        //Regex splits the ActionCommand into command and, if there is a number, (i.e. card index), the card index.
        System.out.println(e.getActionCommand());
        String[] command = e.getActionCommand().split("(?<![0-9])(?=[0-9]+)");
        switch (command[0]){
            case "draw":{
                game.attemptDrawCard();
            }
            case "play":{
                game.attemptPlayCard(Integer.parseInt(command[1]));
            }
            case "colour":{

            }
        }
    }

    public void getPlay(Player player){
        //temporarily still here so as not to cause compiler crashes while waiting on new View code.
    }

    /**
     * Allows the user to add players to the game, specifying the number of players and their names.
     */
    public void addPlayers(String name){
        this.game.addPlayer(new Player(name));
    }

}
