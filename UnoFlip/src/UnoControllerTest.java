import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class UnoControllerTest {

    private UnoController controller;
    private Game game;
    private ArrayList<Player> players;

    private GUIView view;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        game = new Game(players);
        view = new GUIView(game);
        controller = new UnoController(game, view);
        game.setView(view);
    }

    @Test
    void addPlayers() {
        String input = "2\nPlayer1\nPlayer2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.addPlayer("bill");
        assertEquals(2, players.size());
    }

    @Test
    void getPlay() {
        setUp();
        Player player = new Player("Player1");

        player.addCard(new Card(Card.Rank.ONE, Card.Colour.RED, null, Card.Type.LIGHT));
        player.addCard(new Card(Card.Rank.TWO, Card.Colour.BLUE, null, Card.Type.LIGHT));


        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.addPlayer(player);

        game.startGame();

    }


}