import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class GameTest {
    private Game game;
    private View mockView;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    @BeforeEach
    void setUp() {

        player1 = new Player("Player1");
        player2 = new Player("Player2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        mockView = new GUIView();
        game = new Game(players);
        game.setView(mockView);
    }

    @Test
    void setView() {
    }

    @Test
    void startGame() {

    }

    @Test
    void addPlayer() {
        Player newPlayer = new Player("Player3");
        game.addPlayer(newPlayer);
    }

    @Test
    void setPlayerChoice() {
    }

    @Test
    public void WinnerGame() {

        for (Player player : players) {
            if ("Player1".equals(player1.getName())) {
                player.addPoints(51);  // Now Bob has 501 points
                break;
            }
        }
    }

}