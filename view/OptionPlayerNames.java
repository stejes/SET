package view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OptionPlayerNames {

    JPanel playerPanel = new JPanel();
    List<JTextField> playerNames = new ArrayList<>();
    int number = 0;

    public OptionPlayerNames(int number) {
        this.number = number;
        System.out.println("number: " + number);
        for (int i = 0; i < number; i++) {
            JTextField playerName = new JTextField();
            System.out.println("voeg nu speler toe aan list, met textfield met waarde: " + playerName.getName());
            playerPanel.add(new JLabel("Player " + (i + 1)));
            playerPanel.add(playerName);

            playerNames.add(playerName);
        }
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(Box.createHorizontalStrut(5));

    }

    public JPanel getPanel() {
        return playerPanel;
    }




}
