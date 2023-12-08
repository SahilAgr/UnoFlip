import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Implements the View interface and provides a text-based interface for the game.
 */
public class GUIView implements View, FlipListener {
    UnoController controller;
    private ScrollPane scrollPane;
    private JLabel currentPlayer;
    private JLabel topCardLabel;
    private JButton drawCardButton;
    private JLabel score;

    private JMenuItem autosave;


    JFrame jFrame;

    /**
     * Constructs an instance to start the game with a visual interface.
     * Initializes the game, adds players, and starts the game.
     */
    public GUIView(Game game){
        ArrayList<Player> players = new ArrayList<Player>();

        controller = new UnoController(game, this);

        game.setView(this);

        if (!game.hasPlayers()){
            game = new Game(players);
            controller.setGame(game);
            game.setView(this);
            getPlayers(game);
        }

        jFrame = new JFrame("UNO GAME");
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Undo, Redo, Replay
        JMenu menu1 = new JMenu("Menu");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        JMenuItem replay = new JMenuItem("Replay");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Save");

        JMenuItem export = new JMenuItem("Export game");
        export.setActionCommand("export");
        export.addActionListener(controller);

        autosave = new JMenuItem("Enable Autosave");
        autosave.setActionCommand("autosave");
        autosave.addActionListener(controller);

        menu1.add(undo);
        menu1.add(redo);
        menu1.add(replay);
        menu.add(export);
        menu.add(autosave);
        menuBar.add(menu1);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);


        // Used to create buttons
        ArrayList<JButton> testButtons = addButtons(100);

        JPanel jPanelRight = new JPanel();
        scrollPane = new ScrollPane();
        jPanelRight.setLayout(new BorderLayout());

        JPanel jPanelLeft = new JPanel();
        scrollPane.add(jPanelLeft);
        jPanelLeft.setLayout(new GridLayout(testButtons.size(),1,10,10));

        for (int i = 0; i < testButtons.size(); i++) {
            jPanelLeft.add(testButtons.get(i));
        }

        jFrame.setLayout(new GridLayout(1, 2));

        JPanel pageTitle = new JPanel();
        currentPlayer = new JLabel();
        currentPlayer.setHorizontalAlignment(JLabel.CENTER);
        pageTitle.add(currentPlayer);

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

        jFrame.add(scrollPane);
        jFrame.add(jPanelRight);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.validate();
        jFrame.repaint();


        game.startGame();
    }

    private void getPlayers(Game game){
        String numHumans = JOptionPane.showInputDialog("Enter Number of Human players (1-4): ");
        try {
            while (numHumans == null || numHumans.length() > 1 || Integer.parseInt(numHumans) < 1 || Integer.parseInt(numHumans) > 4) {
                if (numHumans == null) System.exit(0);
                JOptionPane.showMessageDialog(null, "Please enter a numeric value between 1-4! ");
                numHumans = JOptionPane.showInputDialog("Enter Number of Human players (1-4): ");
                System.out.println(numHumans);
                if (numHumans == null){
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            if (numHumans == null){
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Please enter a numeric value between 1-4! ");
            new GUIView(game);
        }

        for (int i = 1; i <= Integer.parseInt(numHumans); i ++) {
            String name = JOptionPane.showInputDialog("Name of player " + i);
            if (name == null) System.exit(0);
            controller.addPlayer(name);
        }
        String botLimits;
        String numBots = null;

        if (Integer.parseInt(numHumans) == 3){
            int option;
            while (numBots == null) {
                try {
                    option = JOptionPane.showConfirmDialog(jFrame, "Would you like to add a Bot?");
                    if (option == JOptionPane.YES_OPTION){
                        numBots = "1";
                    }
                    else if (option == JOptionPane.NO_OPTION){
                        numBots = "0";
                    }
                }
                catch (Exception e){
                    System.exit(0);
                }
            }
        }
        else if (Integer.parseInt(numHumans) < 3) {

            int minimum;

            if (Integer.parseInt(numHumans) == 1){
                botLimits = "1-"+(4-Integer.parseInt(numHumans));
                minimum = 1;
            }
            else {
                botLimits = "0-"+(4-Integer.parseInt(numHumans));
                minimum = 0;
            }

            numBots = JOptionPane.showInputDialog("Enter Number of Bot players "+botLimits+": ");

            try {
                while (numBots == null || numBots.length() > 1 || Integer.parseInt(numBots) < minimum || Integer.parseInt(numBots) > 4) {
                    if (numBots == null) System.exit(0);
                    JOptionPane.showMessageDialog(null, "Please enter a value that is between "+botLimits+"! ");
                    numBots = JOptionPane.showInputDialog("Enter Number of Human players ("+botLimits+"): ");
                    System.out.println(numBots);
                    if (numBots == null) {
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException e) {
                if (numBots == null) {
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(null, "Please enter a value that is between "+botLimits+"! ");
                new GUIView(game);
            }
        }

        if (numBots != null) {
            for (int i = 1; i <= Integer.parseInt(numBots); i++) {
                String name = JOptionPane.showInputDialog("Name of bot " + i);
                if (name == null) System.exit(0);
                controller.addBot(name);
            }
        }
    }

    public String getPath(){
        System.out.println("Step 1");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(jFrame);
        System.out.println("Step 2");
        if (option == JFileChooser.APPROVE_OPTION){
            System.out.println("Step 3");
            File autosaveFile = fileChooser.getSelectedFile();
            return autosaveFile.getAbsolutePath();
        }
        else {
            return "";
        }
    }

    public void autosaveToggled(boolean status, String path){
        String menuText;
        String popupText;
        if (status) {
            menuText = "Disable Autosave";
            popupText = "Autosave has been enabled, and will save the game to " + path + " at the end of each player turn.";
        }
        else {
            menuText = "Enable Autosave";
            popupText = "Autosave has been disabled. The autosave file can still be found at " + path;
        }
        autosave.setText(menuText);
        JOptionPane.showMessageDialog(jFrame, popupText, "Flip", JOptionPane.INFORMATION_MESSAGE);
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
     * Displays information and instructions for the next player's turn.
     * @param player The next player who will play.
     * @param topCard The current top card in the game.
     */
    public void nextPlayer(Player player, Card topCard){
        if (player instanceof AIPlayer){
            //disable the frame
        }
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

    public void handleFlip(){
        JOptionPane.showMessageDialog(jFrame, "Worlds are shifting... \n Cards are flipping...", "Flip", JOptionPane.INFORMATION_MESSAGE);
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
    public void AiPlayerPlayed(Card card){
        try {
            ImageIcon pic = new ImageIcon(ImageIO.read(getClass().getResource(card.getImagePath())));
            JOptionPane.showMessageDialog(jFrame, "AI has played: " + card, "AI Play", JOptionPane.PLAIN_MESSAGE, pic);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
