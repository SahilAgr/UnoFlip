import java.util.Scanner;

// view folder

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void main(String[] args) {

        boolean playersNotChosen = true;

        ArrayList<Player> playerList = new ArrayList<Player>();

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
                    i++;
                }
                playersNotChosen = false;
            }

        }
    }
}