package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Menu {

    private JMenuBar menuBar = new JMenuBar();
    Controller controller;
    OptionNewGame actionListener;

    public Menu(Controller controller) {

        //aanmaken van de menuItems
        this.controller = controller;
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem quitM = new JMenuItem("Quit", KeyEvent.VK_Q);
        JMenu scores = new JMenu("Scores");
        JMenuItem highScores = new JMenuItem("High Scores");
        JMenu helpM = new JMenu("Help");
        JMenuItem instructions = new JMenuItem("Instructions");
        JMenu info = new JMenu("Info");
        JMenuItem credits = new JMenuItem("Credits");

        // assembleert de componenten tot een menu
        menuBar.add(file);
        file.add(newGame);
        file.add(quitM);
        menuBar.add(scores);
        scores.add(highScores);
        menuBar.add(helpM);
        helpM.add(instructions);
        menuBar.add(info);
        info.add(credits);

        // aanmaken van de eventListeners

            // event listener op de newGame knop van het menu
            actionListener = new OptionNewGame(newGame, controller);
            newGame.addActionListener(actionListener);



        highScores.addActionListener(e -> {

                JOptionPane
                        .showMessageDialog(null, controller.getScores());



        });

        //action quit het game
        quitM.addActionListener(e -> {
            int quitGame = JOptionPane.showConfirmDialog(null, "Would you like to close the application?",
                    "Exit Game?", JOptionPane.YES_NO_OPTION);
            if ((quitGame == JOptionPane.YES_OPTION)) {
                System.exit(0);
                System.out.println("\"\t\tMYFRAME, menu, quit, ik exit het game en schrijf resultaten weg");
            }
        });



        // de instructions action haalt de pdf uit de source en opent die
        instructions.addActionListener(e -> {
            URI uri = null;
            try {
                uri = getClass().getResource("/model").toURI();
            } catch (URISyntaxException exc) {
                exc.printStackTrace();
            }
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystem zipfs = null;
            try {
                zipfs = FileSystems.newFileSystem(uri, env);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {


                //URL url = getClass().getResource("../images/Instructions.pdf");
                uri = getClass().getResource("/images/Instructions.pdf").toURI();
                Path path = Paths.get(uri);
                //File pdfFile = new File(url.getPath());
                File pdfFile = new File(path.toString());

            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }



        } catch (Exception el) {
                el.printStackTrace();
            }

            try {
                zipfs.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        });

        // de credits toont de credits
        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final ImageIcon creditsIcon = new ImageIcon(getClass().getResource("/images/credits 2.png"));
                JOptionPane pane = new JOptionPane(creditsIcon, JOptionPane.PLAIN_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Credits");
                dialog.setBounds(500, 250, 550, 350);//waar en hoe groot
                dialog.setVisible(true);
            }
        });

    }


    // maakt een getter van de menubar om te kunnen aanroepen in de view
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public int getNumber(){ return actionListener.getNumber();}

    public ArrayList<String> getNames(){
        return actionListener.getNames();
    }

    //hieronder aanpassing laatste bug
    public String getDeckSize(){
        return actionListener.getDeckSize();
    }




}

