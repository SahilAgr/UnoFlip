import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the View interface and provides a text-based interface for the game.
 */
public class GUIView implements View{
    UnoController controller;
    private Card topCard;
    private String numPlayers;
    private ScrollPane scrollPane;
    private JLabel currentPlayer;
    private JLabel topCardLabel;
    private ImageIcon topCardImage;
    private JButton drawCardButton;


    JFrame jFrame;

    /**
     * Constructs an instance to start the game with a text-based interface.
     * Initializes the game, adds players, and starts the game.
     */
    public GUIView(){
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        controller = new UnoController(game);
        game.setView(this);

        jFrame = new JFrame("UNO GAME");
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");

        try {
            while (numPlayers == null || numPlayers.length() > 1 || Integer.parseInt(numPlayers) < 2 || Integer.parseInt(numPlayers) > 4) {
                JOptionPane.showMessageDialog(null, "Please enter a value that is between 2-4! ");
                numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");
            }
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Please enter a value that is between 2-4! ");
            new GUIView();
        }

        for (int i = 1; i <= Integer.parseInt(numPlayers); i ++){
            String name = JOptionPane.showInputDialog("Name of player "+i);
            controller.addPlayers(name);
        }

        // Used to create buttons
        ArrayList<JButton> testButtons = addButtons(100);

        JPanel jPanelRight = new JPanel();
        scrollPane = new ScrollPane();
        jPanelRight.setLayout(new BorderLayout());

        JPanel jPanelLeft = new JPanel();
        scrollPane.add(jPanelLeft);
        jPanelLeft.setLayout(new GridLayout(testButtons.size(),1,10,10));

        for(int i =0; i < testButtons.size(); i++) {
            jPanelLeft.add(testButtons.get(i));
        }



        JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL,jPanelLeft,scrollPane);
        currentPlayer = new JLabel();
        currentPlayer.setHorizontalAlignment(JLabel.CENTER);
        jPanelRight.add(currentPlayer, BorderLayout.PAGE_START);

        topCardLabel = new JLabel("Top Card");
        topCardLabel.setHorizontalAlignment(JLabel.CENTER);
        jPanelRight.add(topCardLabel, BorderLayout.CENTER);
        topCardImage = new ImageIcon();
        ImageIcon topCardImage = new ImageIcon(getClass().getResource("/uno_cards/blue_draw_one.png"));
        topCardImage.setImage(topCardImage.getImage());
        JLabel topCardLabel = new JLabel(topCardImage);
        jPanelRight.add(topCardLabel, BorderLayout.CENTER);

        drawCardButton = new JButton("Draw Card");
        drawCardButton.setActionCommand("draw");
        drawCardButton.addActionListener(controller);
        jPanelRight.add(drawCardButton, BorderLayout.PAGE_END);

        splitPane = new JSplitPane(SwingConstants.VERTICAL,jPanelRight,scrollPane);
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(jPanelRight);
        splitPane.setDividerLocation(jFrame.getWidth()/2);
        jFrame.add(splitPane);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.validate();
        jFrame.repaint();


        game.startGame();
    }

    private ArrayList<JButton> addButtons(int numButton){
        ArrayList<JButton> jButtonArrayList = new ArrayList<JButton>();
        JButton button;
        for(int i = 0; i < numButton; i++){
            button = new JButton();
            jButtonArrayList.add(button);
            jButtonArrayList.get(i).setBackground(Color.white);
            jButtonArrayList.get(i).setBorderPainted(false);
        }
        return jButtonArrayList;
    }

    /**
     * Displays the top card of the game.
     * @param card The top card to be displayed.
     */
    public void topCard(Card card) {
        System.out.println("Top Card: " + card + "\n");
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
        scrollPane.removeAll();
        JPanel jPanel = new JPanel();
        int cardIndex = 0;
        Image img;
        Card card;
        ArrayList<Card> playerHand = player.getHand();
        jPanel.setLayout(new GridLayout(playerHand.size(),1,10,10));
        for (JButton button : addButtons(playerHand.size())){
            card = playerHand.get(cardIndex);
            try {
                img = ImageIO.read(getClass().getResource(card.getImagePath()));
                button.setIcon(new ImageIcon(img));
            }
            catch (Exception e){
                System.out.println(e);
            }
            button.setActionCommand("play"+cardIndex);
            button.addActionListener(controller);
            jPanel.add(button);
            cardIndex ++;
        }
        scrollPane.add(jPanel);
        currentPlayer.setText(player.getName() + "'s Turn");
        /*
        System.out.println("\n The top card is " +  topCard);
        this.topCard = new Card(topCard.getCardNum(),topCard.getCardColour(),topCard.getSpecialType(), Card.Type.LIGHT);
        System.out.println(player.getName() + "'s Turn\n");
        cardHand(player.getHand());
        controller.getPlay(player);

         */
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
     */
    public void illegalMove(){
        JOptionPane.showMessageDialog(jFrame, "That is not a legal play!", "Illegal Move", JOptionPane.ERROR_MESSAGE);
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

        Card.Colour[] options = {Card.Colour.RED,
                Card.Colour.BLUE,
                Card.Colour.GREEN,
                Card.Colour.YELLOW};

        int colourChoice = JOptionPane.showOptionDialog(scrollPane,
                "Choose colour to set card to.",
                "Colour Choice",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[3]);

        return options[colourChoice];
    }


    public static void main(String[] args){
        new GUIView();
    }

}
