package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fed on 2/6/15.
 */
public class Controller {
    private Model model;
    private View view;

    /*bij maken van controller, maakt deze ook een model en een view aan*/
    public Controller(){

        model = new Model();
        System.out.println("CONTROLLER : making new View");
        view = new View(this);
        System.out.println("CONTROLLER : end of controller constructor");
    }

    /*laat model alles doen om het spel te starten en geeft de resulterende tafelkaarten aan de view
    * wordt aangeroepen in menu>startgame of met button in left panel*/
    public void startGame(){
        view.setEnabled(true);// maakt de buttons van no set en hint bruikbaar
        String deckSize = view.getDeckSize(); //haalt de info over decksize via view op, die de speler in dialog heeft ingegeven

        System.out.println("CONTROLLER : asking model to setup game and return the cards");
        model.startGame(deckSize); //geeft decksize aan model en zegt om te beginnen
        System.out.println("CONTROLLER : giving the cards to the view for display");
        view.showTable(model.getCards()); //vraagt view de initiele kaarten te tonen op scherm, die hij uit de model krijgt
        //view.setEnabled(true);
        view.makeTop(); //zegt tegen view dat de buttons van de spelers mogen getoond worden

        model.setStartTime(); //start timer van de ronde
        System.out.printf("CONTROLLER :  timer restarted, now at: %.1f seconds \n", getElapsedSeconds());
        view.resetBottomScore();


    }

    //BUGFIX///////////////////
    public void startGame(String deckSize, ArrayList<String> names, int number){
        view.setEnabled(true);// maakt de buttons van no set en hint bruikbaar
        //String deckSize = view.getDeckSize(); //haalt de info over decksize via view op, die de speler in dialog heeft ingegeven

        System.out.println("CONTROLLER : asking model to setup game and return the cards");
        model.startGame(deckSize, names, number); //geeft decksize aan model en zegt om te beginnen
        System.out.println("CONTROLLER : giving the cards to the view for display");
        view.showTable(model.getCards()); //vraagt view de initiele kaarten te tonen op scherm, die hij uit de model krijgt
        //view.setEnabled(true);
        view.makeTop(names, number); //zegt tegen view dat de buttons van de spelers mogen getoond worden

        model.setStartTime(); //start timer van de ronde
        System.out.printf("CONTROLLER :  timer restarted, now at: %.1f seconds \n", getElapsedSeconds());
        view.resetBottomScore();


    }


    /*geeft door aan model welke kaart er geselecteerd werd, als er drie kaarten
    * geselecteerd zijn laat hij model checken op set. Indien ja, laat model nieuwe kaarten
    * halen ter vervanging van de opgeraapte. In beide gevallen laat hij view de kaarten refreshen zodat
    * alle achtergrondwijzigingen etc weg zijn en laat hij model ook alle opgeraapte kaarten verwijderen uit
    * pickedCards
    * wordt aangeroepen vanuit cardlabel klasse*/
    public void pickCard(Card card) throws URISyntaxException {

        System.out.println("CONTROLLER : telling model to add selected card to pickedCards");
        model.pickCard(card); //geeftde kaart aan de model en laat model nodige dingen uitvoeren
        /*als in model drie kaarten in pickedcards zitten wordt de test uitgevoerd op set

         */
        if(model.getPickedCards().size() == 3) {
            System.out.println("CONTROLLER : there are three picked cards, asking model if they form a set");
            if (model.isSet()) {
                model.score();
                System.out.printf("CONTROLLER : seconds before guess: %.2f \n", model.getElapsedSeconds());
                model.setStartTime(); //reset de timer voor de volgende ronde
                System.out.println("CONTROLLER : asking model to replace the 3 cards");
                model.replaceCards();
                view.tempScore(model.getPlayers());
                if(model.isFinished()){
                    view.setEnabled(false);
                    String score = model.endGame();
                    view.showScore(score);


                    //
                }
            }
            System.out.println("CONTROLLER : making view redraw the table");
            view.showTable(model.getCards());//refreshen van de view met de geupdate kaarten uit de model
            model.setStartTime();
            model.clearPickedCards();
            view.unselectPlayers();
            model.unSelectCurrentPlayer();
        }
    }

    /*laat model een kaart verwijderen uit pickedCards die in de UI gedeselecteerd is*/
    public void removePickedCard(Card card){
        System.out.println("CONTROLLER : telling model to remove deselected card from pickedCards");
        model.removePickedCard(card);
    }

    /*haalt hint uit de model

     */
    public void getHint(){
        System.out.println("CONTROLLER : asking model for hint");
        model.getHint();

        view.showTable(model.getCards());//refreshen
        model.unMarkHint();
        view.unselectPlayers();
        model.unSelectCurrentPlayer();
    }

    /*bij klik op de no set knop laat hij indien nodig de view drie extra kaarten van het model tonen
    waardoor dus het totaal aantal kaarten vergroot (dit gebeurt in model), als er geen set is wordt er een
    hint getoond in console vermits table.hasSet dat altijd doet
     */
    public boolean noSet() throws URISyntaxException {
        System.out.println("CONTROLLER : asking model to add cards if no set, otherwise give hint");
        boolean noSet = model.noSet();
        if(model.isFinished()){
            view.setEnabled(false);
            String scores =  model.endGame();
            view.showScore(scores);
            //hier nog code toevoegen ook de view het game te doen eindigen

        }

        else if(noSet/*model.noSet()*/) {
            view.showTable(model.getCards());
        }
        view.unselectPlayers();
        model.unSelectCurrentPlayer();
        return noSet;


    }

    /*bij klikken op een van de spelerbuttons in topppanelbutton wordt de gereturnde int daarvan gebruikt
    om die speler actief te maken in de model
     */
    public void setCurrentPlayer(int playerInt){
        System.out.println("CONTROLLER : telling model to make active player: " + playerInt);
        model.setCurrentPlayer(playerInt);
    }

    /*simpelweg een getter voor de elapsed seconds*/
    public float getElapsedSeconds(){
        return model.getElapsedSeconds();
    }

    public void addPlayer(String player) {
        System.out.println("CONTROLLER : telling model to add player");
        model.addPlayer(player);
    }

    public String getScores(){
        return model.getScores();
    }







}
