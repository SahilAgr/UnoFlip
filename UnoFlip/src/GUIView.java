import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JLabel score;


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
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");

        try {
            while (numPlayers == null || numPlayers.length() > 1 || Integer.parseInt(numPlayers) < 2 || Integer.parseInt(numPlayers) > 4) {
                if (numPlayers == null) System.exit(0);
                JOptionPane.showMessageDialog(null, "Please enter a value that is between 2-4! ");
                numPlayers = JOptionPane.showInputDialog("Enter Number of players (2-4): ");
                System.out.println(numPlayers);
                if (numPlayers == null){
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            if (numPlayers == null){
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Please enter a value that is between 2-4! ");
            new GUIView();
        }

        for (int i = 1; i <= Integer.parseInt(numPlayers); i ++) {
            String name = JOptionPane.showInputDialog("Name of player " + i);
            if (name == null) System.exit(0);
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
        JPanel pageTitle = new JPanel();
        currentPlayer = new JLabel();
        currentPlayer.setHorizontalAlignment(JLabel.CENTER);
        pageTitle.add(currentPlayer);
        //jPanelRight.add(currentPlayer, BorderLayout.PAGE_START);

        score = new JLabel();
        score.setHorizontalAlignment(JLabel.CENTER);
        pageTitle.add(score);
        jPanelRight.add(pageTitle,BorderLayout.PAGE_START);

        topCardLabel = new JLabel("Top Card");
        topCardLabel.setHorizontalAlignment(JLabel.CENTER);
        jPanelRight.add(topCardLabel, BorderLayout.CENTER);
        jPanelRight.add(topCardLabel, BorderLayout.CENTER);

        drawCardButton = new JButton("Draw Card");
        drawCardButton.setActionCommand("draw");
        drawCardButton.addActionListener(controller);
        jPanelRight.add(drawCardButton, BorderLayout.SOUTH);

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
        try {
            topCardLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(topCard.getImagePath()))));
            topCardLabel.setText("Top Card is: " + topCard.toString());
        }catch (Exception e){
            System.out.println(e);
        }
        scrollPane.add(jPanel);
        currentPlayer.setText("Player: "+ player.getName() + "'s Turn");
        score.setText(", Score: " + player.getPoints() + "");
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
        if (cardsDrawn.size() != 7) {
            JDialog dialog;
            JLabel label;
            for (Card card : cardsDrawn) {
                dialog = new JDialog();
                dialog.setLocationRelativeTo(jFrame);
                label = new JLabel(new ImageIcon());
                try {
                    label.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(card.getImagePath()))));
                    label.setText(player.getName() + " has drawn " + card);
                } catch (Exception e) {
                    System.out.println(e);
                }
                dialog.add(label);
                dialog.pack();
                dialog.setVisible(true);
            }
        }
    }

    /**
     * Notfies the player that their move is illegal and prompts them to try again.
     */
    public void illegalMove(){
        JOptionPane.showMessageDialog(jFrame, "That is not a legal play!", "Illegal Move", JOptionPane.ERROR_MESSAGE);
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
        String message = roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints();
        JOptionPane.showMessageDialog(null, message, "Starting new round...", JOptionPane.INFORMATION_MESSAGE);
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

        if (colourChoice == -1){
            return getColour();
        }

        return options[colourChoice];
    }


    public static void main(String[] args){
        new GUIView();
    }

}
