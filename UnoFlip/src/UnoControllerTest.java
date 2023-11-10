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

    private View view;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        game = new Game(players);
        controller = new UnoController(game);
        view = new TextView();
        game.setView(view);
    }

    @Test
    void addPlayers() {
        String input = "2\nPlayer1\nPlayer2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.addPlayers("bill");
        assertEquals(2, players.size());
    }

    @Test
    void getPlay() {
        setUp();
        Player player = new Player("Player1");

        player.addCard(new Card(Card.Rank.ONE, Card.Colour.RED, null));
        player.addCard(new Card(Card.Rank.TWO, Card.Colour.BLUE, null));


        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.addPlayer(player);

        controller.getPlay(player);
        game.startGame();

    }


}