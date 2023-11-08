import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Implements the View interface and provides a text-based interface for the game.
 */
public class TextView implements View{
    UnoController controller;
    private Card topCard;

    /**
     * Constructs an instance to start the game with a text-based interface.
     * Initializes the game, adds players, and starts the game.
     */
    public TextView(){
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        controller = new UnoController(game);
        game.setView(this);

        JFrame jFrame = new JFrame("UNO GAME");
        jFrame.setSize(600,600);
        String numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");
        while(numPlayers == null || numPlayers.length() > 1 || Integer.parseInt(numPlayers) < 2 || Integer.parseInt(numPlayers) > 4){
            JOptionPane.showMessageDialog(null,"Please enter a value that is between 2-4! ");
            numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");
        }

        for (int i = 1; i <= Integer.parseInt(numPlayers); i ++){
            String name = JOptionPane.showInputDialog("Name of player "+i);
            controller.addPlayers(name);
        }


        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();

        Dimension dimension = new Dimension(600,600);
        jButton2.setSize(dimension);

        jPanel1.add(jButton1);
        jPanel2.add(jButton2);
        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL,jPanel1,jPanel2);
        splitPane.setDividerLocation(300);
        jFrame.add(splitPane);


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.validate();
        jFrame.repaint();


        game.startGame();
    }

    /**
     * Displays the top card of the game.
     * @param card The top card to be displayed.
     */
    public void topCard(Card card) {
        System.out.println("Top Card: " + card + "\n");
    }

    /**
     * Prompts the current player to make the decision, play a card, or draw a card.
     * @param player The current player playing.
     */
    public void playerDecision(Player player) {
        System.out.println("Enter card index to play or 0 to draw a card: \n");
    }

    /**
     * Notifies when a new player has been added to the game.
     * @param player The newly added player.
     */
    public void addPlayer(Player player){
        System.out.println("Added player " + player.getName());
    }

    /**
     * Displays information and instructions for the next player's turn.
     * @param player The next player who will play.
     * @param topCard The current top card in the game.
     */
    public void nextPlayer(Player player, Card topCard){
        System.out.println("\n The top card is " +  topCard);
        this.topCard = new Card(topCard.getCardNum(),topCard.getCardColour(),topCard.getSpecialType());
        System.out.println(player.getName() + "'s Turn\n");
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    /**
     * Displays the player's hand of cards.
     * @param playerHand List of cards in the player's hand.
     */
    public void cardHand(ArrayList<Card> playerHand) {
        System.out.println("Your Cards: \n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(String.valueOf(i+1) + " " + playerHand.get(i));
        }
        System.out.println("\nThe top card is " +  this.topCard);
        System.out.println("Enter card index to play or 0 to draw a card: ");
    }

    /**
     * Displays the cards that a player has drawn.
     * @param player Player who drew the cards.
     * @param cardsDrawn List of cards drawn.
     */
    public void drawCard(Player player, ArrayList<Card> cardsDrawn){
        System.out.println("\n"+player.getName()+" has drawn:");
        for (Card card : cardsDrawn){
            System.out.println(card);
        }
    }

    /**
     * Notfies the player that their move is illegal and prompts them to try again.
     * @param player Player who made the illegal move.
     */
    public void illegalMove(Player player){
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    /**
     * Notifies when a card is played and whether it's a valid move.
     * @param card Card that was played.
     * @param validCard True if card is valid, otherwise false.
     */
    public void cardPlayed(Card card, Boolean validCard){
        if (!validCard) {
            System.out.println("Card doesn't match the top card. Try again.\n");
        }
        System.out.println("Played: " + card + ".\n");
    }

    /**
     * Displays game round number at the start of a new round.
     * @param roundCount New game round number.
     */
    public void roundStart(int roundCount){
        System.out.println("Starting round " + roundCount);
    }

    /**
     * Displays the end of a game round and the winner of the round.
     * @param roundWinner Player who won the game round and their total score.
     */
    public void roundEnd(Player roundWinner){
        System.out.println(roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints());
        System.out.println("Starting new round...");
    }

    /**
     * Displays the overall winner of the game.
     * @param winner Player who won the game and their total score.
     */
    public void winner(Player winner){
        System.out.println(winner.getName() + " has won the with a score of " + winner.getPoints());
    }

    /**
     * Allows the user to pick a colour for a Wild Card.
     * @return Chosen colour.
     */
    public Card.Colour getColour(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick a Colour that you would like to change to (RED,BLUE,YELLOW,GREEN): ");

        boolean test = true;

        System.out.println("Pick a Colour that you would like to change to: ");
        System.out.println("1 RED");
        System.out.println("2 BLUE");
        System.out.println("3 GREEN");
        System.out.println("4 YELLOW");
        int input = sc.nextInt();

        while(test) {
            if (input == 1) {
                  test = false;
                return Card.Colour.RED;
            } else if (input == 2) {
              test = false;
              return Card.Colour.BLUE;

          } else if (input == 3) {
              test = false;
              return Card.Colour.GREEN;

          } else if (input == 4) {
              test = false;
              return Card.Colour.YELLOW;

          } else {
              System.out.println("\n" + input + " is not a valid input.");
              input = sc.nextInt();
          }
      }

        return Card.Colour.BLACK;

    }


    public static void main(String[] args){
        new TextView();
    }

}
