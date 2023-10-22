import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextView implements View {

    UnoController controller;
    private Card topCard;

    public TextView(){
        ArrayList<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        controller = new UnoController(game);
        game.setView(this);

        System.out.println("Enter Number of players (2-4): ");
        controller.addPlayers();

        game.startGame();
    }

    public void topCard(Card card) {
        System.out.println("Top Card: " + card + "\n");
    }

    public void playerDecision(Player player) {
        System.out.println("Enter card index to play or 0 to draw a card: \n");
    }

    public void addPlayer(Player player){
        System.out.println("Added player " + player.getName());
    }

    public void nextPlayer(Player player, Card topCard){
        System.out.println("\n The top card is " +  topCard);
        this.topCard = new Card(topCard.getCardNum(),topCard.getCardColour(),topCard.getSpecialType());
        System.out.println(player.getName() + "'s Turn\n");
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    public void cardHand(ArrayList<Card> playerHand) {
        System.out.println("Your Cards: \n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(String.valueOf(i+1) + " " + playerHand.get(i));
        }
        System.out.println("\nThe top card is " +  this.topCard);
        System.out.println("Enter card index to play or 0 to draw a card: ");
    }

    public void drawCard(Player player, ArrayList<Card> cardsDrawn){
        System.out.println("\n"+player.getName()+" has drawn:");
        for (Card card : cardsDrawn){
            System.out.println(card);
        }
    }

    public void illegalMove(Player player){
        cardHand(player.getHand());
        controller.getPlay(player);
    }

    public void cardPlayed(Card card, Boolean validCard){
        if (!validCard) {
            System.out.println("Card doesn't match the top card. Try again.\n");
        }
        System.out.println("Played: " + card + ".\n");
    }

    public void roundStart(int roundCount){
        System.out.println("Starting round " + roundCount);
    }

    public void roundEnd(Player roundWinner){
        System.out.println(roundWinner.getName() + " has won the round, bringing their score to " + roundWinner.getPoints());
        System.out.println("Starting new round...");
    }

    public void winner(Player winner){
        System.out.println(winner.getName() + " has won the with a score of " + winner.getPoints());
    }
    public Card.Colour getColour(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick a Colour that you would like to change to (RED,BLUE,YELLOW,GREEN): ");
        String input = sc.nextLine();
        input = input.toUpperCase();

        boolean test = true;

        while (test) {
            if ((Card.Colour.valueOf(input) == Card.Colour.valueOf("RED")) || (Card.Colour.valueOf(input) == Card.Colour.valueOf("BLUE")) || (Card.Colour.valueOf(input) == Card.Colour.valueOf("YELLOW")) || (Card.Colour.valueOf(input) == Card.Colour.valueOf("GREEN"))) {
                test = false;
            }
            else {
                System.out.println("\n" + input + " is not a valid input.");
                input = sc.next();
            }
        }
        return Card.Colour.valueOf(input);
        /*while (Card.Colour.valueOf(input) != Card.Colour.RED || (Card.Colour.valueOf(input) != Card.Colour.BLUE) || (Card.Colour.valueOf(input) != Card.Colour.YELLOW) || (Card.Colour.valueOf(input) != Card.Colour.GREEN)){
            System.out.println("\n"+input+" is not a valid input.");
            input = sc.next();
        }
        return Card.Colour.valueOf(input);*/
        /**
         * Scanner sc = new Scanner(System.in);
         *         System.out.println("Pick a Colour that you would like to change to: ");
         *         System.out.println("1 RED");
         *         System.out.println("2 BLUE");
         *         System.out.println("3 GREEN");
         *         System.out.println("4 YELLOW");
         *         int input = sc.nextInt();
         *
         *         if(input == 1){
         *             return Card.Colour.RED;
         *         } else if (input == 2) {
         *             return Card.Colour.BLUE;
         *
         *         }
         *         else if (input == 3) {
         *             return Card.Colour.GREEN;
         *
         *         }else if (input == 4) {
         *             return Card.Colour.YELLOW;
         *
         *         }else {
         *             System.out.println("\n"+input+" is not a valid input.");
         *             input = sc.nextInt();
         *         }
         */

    }


    public static void main(String[] args){
        new TextView();
    }

}
