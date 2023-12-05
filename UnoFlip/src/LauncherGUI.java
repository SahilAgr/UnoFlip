import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
                    FileInputStream fileInputStream = new FileInputStream("autosave.ser");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    Game loadedGame = (Game) objectInputStream.readObject();
                    new GUIView(loadedGame);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Either no save file has been found, or the save file has been corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
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
