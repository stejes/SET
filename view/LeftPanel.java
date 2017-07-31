package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Left Panel is linkse gedeelte
 */
public class LeftPanel extends JPanel{



    private JButton newGame;
    private JButton quitGame;

    Controller controller;
    OptionNewGame actionListener;


    public LeftPanel(Controller controller) {
        System.out.println("Controller in lefltpanel: " + controller);
        this.controller = controller;
        initComponents();
        layoutComponenets();
        initListeners();
        System.out.println("this.Controller in lefltpanel: " + this.controller);

    }

    private void initComponents() {
        newGame = new JButton("New Game");
        quitGame = new JButton("Quit Game");
    }

    private void layoutComponenets() {
        Box box = Box.createVerticalBox();//als box achter elkaar, maar in dit geval onder elkaar
        box.add(Box.createVerticalStrut(150));//afstand tussen de bovenkant en eerste knop
        box.add(newGame);
        box.add(Box.createVerticalStrut(30));//afstand tussen de twee knoppen
        box.add(quitGame);
        add(box);
    }

    private void initListeners(){

        actionListener = new OptionNewGame(newGame, controller);
        newGame.addActionListener(actionListener);



        quitGame.addActionListener(e -> {
            int quitGame = JOptionPane.showConfirmDialog(null, "Would you like to close the application?",
                    "Exit Game?", JOptionPane.YES_NO_OPTION);
            if ((quitGame == JOptionPane.YES_OPTION)) {
                System.exit(0);
                System.out.println("\"\t\tMYFRAME, menu, quit, ik exit het game en schrijf resultaten weg");
            }
        });
    }

    public int getNumber(){
        return actionListener.getNumber();
    }
    public ArrayList<String> getNames(){
        return actionListener.getNames();
    }

    public String getDeckSize(){
        return actionListener.getDeckSize();
    }


}

