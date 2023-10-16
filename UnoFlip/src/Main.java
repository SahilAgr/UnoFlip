import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
   /* public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
        Card card = new Card(0, Card.Colour.BLUE, null);
        System.out.println(card.toString());
    }*/

    public static void main(String[] args) {

        boolean playersNotChosen = true;

        while (playersNotChosen){
            Scanner numPlayers = new Scanner(System.in);
            String playerNumber;

            System.out.println("How many players 2 - 4: ");
            playerNumber = numPlayers.nextLine();
            int num = Integer.valueOf(playerNumber);

            if( num <= 1 || num > 4){
                System.out.println("You cannot have: " + num + " Players, please choose again!");
            }else {
                // Set names of the players
                int i = 1;
                while (i <= num){
                    Scanner playerNames = new Scanner(System.in);
                    String name;

                    System.out.println("What is the name of Player#" + i);
                    name = playerNames.nextLine();
                    // Do Something like player.setName(name); where player is from the Player class once created
                    System.out.println(name); // Instead of this do System.out.println(player.toString()); to list names of all players like Player#1: bill, Player#2: ...
                    i++;
                }
                playersNotChosen = false;
            }

        }
    }
}