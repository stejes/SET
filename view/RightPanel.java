package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

/**
* De RightPanel is waar het rechter gedeelte komt
*/
public class RightPanel extends JPanel {

    private Controller controller;
    private JButton noSet;
    private JButton hint;
    private JLabel deckLabel; //maakt een label van de deck
    ImageIcon deckImage = new ImageIcon(this.getClass().getResource("/images/backSet.png")); //haalt de back van de kaarten
    boolean enabled = false;



    public RightPanel(Controller controller) {
        initComponents();
        layoutComponents();
        initListeners();
        this.controller = controller;

    }

    private void initComponents() {
            deckLabel = new JLabel(deckImage); //voegt de image aan de label toe
            noSet = new JButton("No Set");
            hint = new JButton("Hint");
    }


    public void layoutComponents() {
        removeAll();
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(150));
        box.add(deckLabel);
        deckLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(10));
        box.add(noSet);
        noSet.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(20));
        box.add(hint);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(box);

        if(enabled == true){
            noSet.setEnabled(true);
            hint.setEnabled(true);
        }
        else{
            noSet.setEnabled(false);
            hint.setEnabled(false);
        }
        revalidate();
        repaint();
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    private void initListeners() {
         noSet.addActionListener                                            // Add an action listener
                 (                                                               // Parameter of method is
                         e -> {
                             // Execute when button is pressed
                             System.out.println("\t\tRIGHT PANEL : You clicked the NO SET button, asking controller to check");
                             try {
                                 if(controller.noSet()){

                                              //game wordt gestart met al bestaande code, enkel deze listener hier toegevoegd
                                 }
                                 else{
                                     JOptionPane.showMessageDialog(null, "There's a set on the table!");
                                 }
                             } catch (URISyntaxException e1) {
                                 e1.printStackTrace();
                             }


                         }                                                           // End of class definition
                 );

        hint.addActionListener                                            // Add an action listener
                (                                                               // Parameter of method is
                        e -> {
                            // Execute when button is pressed
                            System.out.println("\t\tLEFT PANEL : You clicked the HINT button, asking controller to give hint");

                            controller.getHint(); //game wordt gestart met al bestaande code, enkel deze listener hier toegevoegd



                        }
                );
    }

}
