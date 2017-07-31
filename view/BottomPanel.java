package view;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* BottomPanel klasse is waar de score en tekst komt /*

 */
public class BottomPanel extends JPanel {

    JTextField textField = new JTextField ("In single player hint adds a penalty to your time!");

    public BottomPanel(){
        initComponents();
        layoutComponents();
    }

    private void initComponents(){
        add(textField);
    }

    private void layoutComponents(){
        setLayout(new FlowLayout());//op een lijn
        add(textField);

    }

    public void showScore(ArrayList<Player> players){
        removeAll();
        String result = "";
        for(Player p : players){
            result += String.format("%s: %d in %.2f s - ", p.getName(), p.getScore(), p.getTime());
        }
        textField = new JTextField(result);
        add(textField);
        revalidate();
        repaint();
    }

    public void resetScore(){
        removeAll();
        textField = new JTextField ("In single player hint adds a penalty to your time!");
        add(textField);
        revalidate();
        repaint();

    }


}
