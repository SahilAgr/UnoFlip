import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class LauncherGUI {

    public LauncherGUI(){
        JFrame frame = new JFrame();
        frame.setSize(400, 100);
        frame.setLayout(new GridLayout(1, 2));
        JButton newGame = new JButton("New Game");
        Game game = new Game(new ArrayList<Player>());
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIView(game);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        JButton importGame = new JButton("Import Save");
        importGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Importer i = new Importer();
                    i.readXMLGameFile("game.xml", game);
                    System.out.println(game.hasPlayers());
                    new GUIView(game);
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }
        });
        frame.add(newGame);
        frame.add(importGame);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.show();
    }
    public static void main(String[] args){
        new LauncherGUI();
    }
}
