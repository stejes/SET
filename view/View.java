package view;


import controller.Controller;
import model.Card;
import model.Player;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {
    Controller controller;
    CenterPanel centerPanel; // heb object type moeten veranderen van Jpanel naar CenterPanel, anders kon ik er geen methodes van aanroepen, moet ook hier staan ipv  in de methode, anders niet beschikbaar voor andere methoden
    LeftPanel leftPanel;
    RightPanel rightPanel;
    TopPanel topPanel;
    BottomPanel bottomPanel;
    Menu menu;

    /* constructor roept superconstructor met titel van het venster */
    public View(Controller controller) {

        super("Set Game");
        this.controller = controller;
        initComponents();
        layoutComponents();
        setVisible(true);
        System.out.println("\t\tMYFRAME : end of myFrame constructor.");

        //voegt de launch instructies op het game on top
        LaunchInstructions launchInstructions = new LaunchInstructions();
        launchInstructions.getLaunchInstructions();

    }

    /* Aanroepen van methode initComponents voor het opbouwen en initialiseren van de grafische componenten */
    public void initComponents(){

        //voegt splash screen
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.getSplashScreen();

        //voegt menu toe
        menu = new Menu(controller);
        setJMenuBar(menu.getMenuBar());

        // screen settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1050,750);
        setLocation(200,50);


    }

    /* Aanroepen van layoutComponents */
    public void layoutComponents(){

        //maakt de hoofdview aan met alle onderliggende panels en lay dezen out

        centerPanel = new CenterPanel(controller); // heb object type moeten veranderen van Jpanel naar CenterPanel, anders kon ik er geen methodes van aanroepen
        add(centerPanel, BorderLayout.CENTER);
        bottomPanel = new BottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        rightPanel = new RightPanel(controller);
        add(rightPanel, BorderLayout.EAST);
        leftPanel = new LeftPanel(controller);
        add(leftPanel, BorderLayout.WEST);


    }



    public void showTable(ArrayList<Card> cards){
        System.out.println("\t\tVIEW : I am showing the cards on the console");
        for(int i = 0; i < cards.size(); i++){
            System.out.println("\t\tVIEW : " + i + " " + cards.get(i) + " " + cards.get(i).getImage());
        }
        System.out.println("\t\tVIEW : I am asking centerpanel to show the cards");
        centerPanel.showCards(cards);

    }

    public void setEnabled(boolean enabled){
        rightPanel.setEnabled(enabled);
        rightPanel.layoutComponents();
    }

    public void makeTop(){
        if(topPanel != null) {
            remove(topPanel);
        }
        System.out.println(leftPanel);
        System.out.println(leftPanel.getNumber());
        int number = leftPanel.getNumber();
        if(number == 0){ //als spel wordt aangemaakt in menu is number 0 in leftpanel, moet dus hier gehaald worden
            number = menu.getNumber();
        }
        ArrayList<String> names = leftPanel.getNames();
        if(names.size() == 0){ // idem als bij number hierboven
            names = menu.getNames();
        }
        System.out.println("\t\tVIEW : making toppanel");
        topPanel = new TopPanel(names, controller);
        add(topPanel, BorderLayout.NORTH);
        System.out.println("\t\tVUEW : calling topPanel.initcomponents");
        topPanel.initComponents(number);
        revalidate();
        repaint();
    }

    public void makeTop(ArrayList<String> names, int number){
        if(topPanel != null) {
            remove(topPanel);
        }
        System.out.println(leftPanel);
        System.out.println(leftPanel.getNumber());
        //int number = leftPanel.getNumber();
        //if(number == 0){ //als spel wordt aangemaakt in menu is number 0 in leftpanel, moet dus hier gehaald worden
        //    number = menu.getNumber();
        //}
        //ArrayList<String> names = leftPanel.getNames();
        //if(names.size() == 0){ // idem als bij number hierboven
        //    names = menu.getNames();
        //}
        System.out.println("\t\tVIEW : making toppanel");
        topPanel = new TopPanel(names, number, controller);
        add(topPanel, BorderLayout.NORTH);
        System.out.println("\t\tVUEW : calling topPanel.initcomponents");
        topPanel.initComponents();
        revalidate();
        repaint();
    }

    public void unselectPlayers(){
        topPanel.unselectAll();
    }

    public String getDeckSize(){

        //aanpassing wegens bug////////////////////////////

        if(leftPanel.getNumber() == 0){
            return menu.getDeckSize();
        }
        else {
            return leftPanel.getDeckSize();
        }
    }

    public void showScore(String scores){
        JOptionPane
                .showMessageDialog(null, scores);
    }

    public void tempScore(ArrayList<Player> players){
        bottomPanel.showScore(players);
    }

    public void resetBottomScore(){
        bottomPanel.resetScore();
    }



//
}

