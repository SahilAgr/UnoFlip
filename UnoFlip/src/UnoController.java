import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Class responsible for controlling the flow Uno game.
 */
public class UnoController implements ActionListener, Serializable {

    private Game game;
    private GUIView view;

    /**
     * Constructs a new UnoController with specified game instance.
     * @param game The Game instance to control.
     */
    public UnoController(Game game, GUIView view){
        this.game = game;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        //Regex splits the ActionCommand into command and, if there is a number, (i.e. card index), the card index.
        String[] command = e.getActionCommand().split("(?<![0-9])(?=[0-9]+)");
        switch (command[0]){
            case "draw":{
                game.attemptDrawCard();
                break;
            }
            case "play":{
                game.attemptPlayCard(Integer.parseInt(command[1]));
                break;
            }
            case "export":{
                String path = view.getPath();
                if (!path.isEmpty()) {
                    game.exportGame(path);
                }
                break;
            }
            case "autosave":{
                if (!game.autosaveOn()){
                    String path = view.getPath();
                    if (!path.equals("")) {
                        game.setAutosave(path);
                    }
                }
                game.toggleAutosave();
                break;
            }
        }
    }



    /**
     * Allows the user to add players to the game, specifying the number of players and their names.
     */
    public void addPlayer(String name){
        this.game.addPlayer(new Player(name));
    }

    public void addBot(String name){
        this.game.addPlayer(new AIPlayer(name));
    }

    public void setGame(Game game){
        this.game = game;
    }

}
