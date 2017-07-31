package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class OptionNewGame implements ActionListener{

    Controller controller;
    Menu menu;
    private JMenuItem gameSettings;
    private JButton newGame;
    int number;
    ArrayList<String> playerNames = new ArrayList<>();
    String deckSize = "big";


    public OptionNewGame(JMenuItem gameSettings, Controller controller){
        //System.out.println("In constructor 1: " );
        this.controller = controller;
        this.gameSettings = gameSettings;

    }

    public OptionNewGame(JButton newGame, Controller controller){
        //System.out.println("In constructor 2: " + controller);
        this.controller = controller;
        this.newGame = newGame;
    }



    public void actionPerformed(ActionEvent e) {
        String errorMessage = "";
        do {
            // geeft een input scherm met errors indien er errors zijn
            String playersInput = JOptionPane.showInputDialog(errorMessage + "Enter number player(s). Number between 1 and 8.");

            try {
                number = Integer.parseInt(playersInput);
                if (number > 8 || number < 1) {
                    errorMessage = "That number is not within the \n" + "allowed range!\n";
                } else {
                    JOptionPane
                            .showMessageDialog(null, "You seclected: " + number + " player(s). Select now deck size.");
                    System.out.println(number + " player(s) selected");

                    OptionDeckSize optionDeckSize = new OptionDeckSize();
                    int result = JOptionPane.showConfirmDialog(null, optionDeckSize, "Select Deck Size", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) { // als de user OK klikt krijgt hij hier het geselecteerde radio button
                        // hier call van de getter method voor het geselcteerde radio button tekst

                        String selectedButtonText = optionDeckSize.getSelectedButtonText();
                        if(selectedButtonText.equals("Small Deck (27 cards)")) {
                            deckSize = "small";
                        }
                        else{
                            deckSize = "big";
                        }
                        System.out.println(selectedButtonText);
                    }

                    // maakt een nieuw panel aan met loop van textfields
                    OptionPlayerNames optionPlayerNames = new OptionPlayerNames(number);
                    JPanel panel = optionPlayerNames.getPanel();
                    int names = JOptionPane.showConfirmDialog(null, panel,errorMessage + "Please enter player names:",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (names == JOptionPane.OK_OPTION) {
                        playerNames.clear();
                        for(int i = 0; i<number; i++){
                            String playerName = optionPlayerNames.playerNames.get(i).getText();
                            System.out.println("playername: " + playerName);
                            System.out.println("playername.lenth: " + playerName.length());

                            if(playerName.length() < 1){
                                playerName = "anon" + i;
                            }
                            if(playerName.length() > 8){
                                playerName = playerName.substring(0, 8)  +"~";
                            }
                            playerNames.add(playerName.replaceAll(" ", "_"));
                        }

                        System.out.print(playerNames);

                    } else{
                        JOptionPane.showMessageDialog(null, "Going back to main screen");
                        return;
                    }

                    int startGame = JOptionPane.showConfirmDialog(null, "Would you like to start the game with these settings?", "Start Game?", JOptionPane.YES_NO_OPTION);

                    if ((startGame == JOptionPane.YES_OPTION)) {


                        //BUGFIX///////////////////////
                        controller.startGame(deckSize, playerNames, number);
                    } else {
                        int finalOption = JOptionPane.showConfirmDialog(null, "Close application?",
                                "Final Option", JOptionPane.YES_NO_OPTION);
                        if (finalOption == JOptionPane.YES_OPTION){
                            System.exit(0);
                        }
                    }

                    errorMessage = ""; // geen error meer
                }
            } catch (NumberFormatException ne) {
                // text is geen integer
                errorMessage = "The text you typed is not a number.\n";
            }
        } while (!errorMessage.isEmpty());

    }

    public int getNumber(){
        return number;
    }
    public ArrayList<String> getNames(){
        return playerNames;
    }

    public String getDeckSize(){
        return deckSize;
    }




}
