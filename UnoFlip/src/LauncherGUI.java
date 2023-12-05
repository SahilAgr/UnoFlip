import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LauncherGUI {

    public LauncherGUI(){
        JFrame frame = new JFrame();
        frame.setSize(400, 100);
        frame.setLayout(new GridLayout(1, 2));
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game newGame = new Game(new ArrayList<Player>());
                new GUIView(newGame);
                frame.setVisible(false);
            }
        });
        JButton importGame = new JButton("Import Save");
        importGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file.getName());
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        Game loadedGame = (Game) objectInputStream.readObject();
                        new GUIView(loadedGame);
                        frame.setVisible(false);
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Either no save file has been found, or the save file has been corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(err.getMessage());
                    }
                }
            }
        });
        frame.add(newGame);
        frame.add(importGame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
    public static void main(String[] args){
        new LauncherGUI();
    }
}
