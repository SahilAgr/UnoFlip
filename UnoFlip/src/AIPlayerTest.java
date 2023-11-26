import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {
    private AIPlayer aiPlayer;
    private Game testGame;
    private ArrayList<Player> players;

    @BeforeEach
    public void setUp() {
        aiPlayer = new AIPlayer("AI");
        players = new ArrayList<>();
        players.add(aiPlayer);
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        testGame = new Game(players);

    }

    @Test
    public void testGetColour() {
        Card.Colour colour = aiPlayer.getColour();
        List<Card.Colour> expectedColours = Arrays.asList(Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW);
        assertTrue(expectedColours.contains(colour));
    }





}