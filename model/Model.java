package model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

/**
 * Created by fed on 2/6/15.
 */
public class Model {

    //private String gameConfiguration;
    Deck deck; //= new Deck("big");
    Table table; // = new Table();
    ArrayList<Card> pickedCards = new ArrayList<>();
    ArrayList<Player> players  = new ArrayList<>();
    private boolean isFinished = false;
    private Player activePlayer;
    //int aantal = 0;
    private boolean penalty;
    private int penaltyValue = 20;
    private String deckSize;

    long startTime;
    float elapsedTime;
    float elapsedSeconds;

    public Model()
    {
        System.out.println("\t\tMODEL : end of model constructor.");
    }



//    public String getGameConfiguration()
//    {
//        return gameConfiguration;
//    }

    /* hieronder: start het spel dmv niuew deck en table, en returnt de twaalf kaarten aan de controller*/
    public void startGame(String deckSize){
        players.clear();
        this.deckSize = deckSize;
        isFinished = false;
        System.out.println("\tMODEL : I'm starting the game");
        System.out.println("\tMODEL : making new deck, shuffling and asking the initial 12 cards");
        deck = new Deck(deckSize);
        deck.shuffle();
        table = new Table();
        deck.makeTable(table);
        System.out.println("\tMODEL : finished setting up the game");

        //voorlopig maak ik hier 8 spelers aan voor testing purposes
//        for(int i = 0; i<8; i++){
//            String name = "Player" + (i+1);
//            players.add(new Player(name));
//        }


    }

    //BUGFIX///////////////////////eigenlijk moet model hier playerobjects aanmaken ipv topppanel tijdens aanmaken buttons
    public void startGame(String deckSize, ArrayList<String> names, int number){
        players.clear();
        this.deckSize = deckSize;
        isFinished = false;
        System.out.println("\tMODEL : I'm starting the game");
        System.out.println("\tMODEL : making new deck, shuffling and asking the initial 12 cards");
        deck = new Deck(deckSize);
        deck.shuffle();
        table = new Table();
        deck.makeTable(table);
        System.out.println("\tMODEL : finished setting up the game");

        //voorlopig maak ik hier 8 spelers aan voor testing purposes
//        for(int i = 0; i<8; i++){
//            String name = "Player" + (i+1);
//            players.add(new Player(name));
//        }


    }

    /*methode die wordt aangeroepen bij het klikken op een kaart in de view,
    voegt de geselecteerde kaart toe aan pickedCards
     */
    public void pickCard(Card card){
        pickedCards.add(card);
        System.out.println("\tMODEL : adding selected card to pickedCards");
    }

    /*methode om bij deselecteren van een kaart deze te verwijderen uit de pickedCards*/
    public void removePickedCard(Card card){
        for(int i = 0; i < pickedCards.size(); i++){
            if (pickedCards.get(i) == card){
                pickedCards.remove(i);
                System.out.println("\tMODEL : removing deselected card from pickedCards");
            }
        }
        penalty = false;
    }

    /*checkt of de pickedCards een set vormen*/
    public boolean isSet(){

        System.out.println("\tMODEL : checking if picked cards are a set or not");
        for(int i = 0; i < 4; i++) {
            if (!((pickedCards.get(0).getFeatures()[i] == pickedCards.get(1).getFeatures()[i] && pickedCards.get(0).getFeatures()[i] == pickedCards.get(2).getFeatures()[i])
                    || (pickedCards.get(0).getFeatures()[i] != pickedCards.get(1).getFeatures()[i] && pickedCards.get(0).getFeatures()[i] != pickedCards.get(2).getFeatures()[i]  && pickedCards.get(1).getFeatures()[i] != pickedCards.get(2).getFeatures()[i]))) {
                System.out.println("\tMODEL : picked cards are NOT a set");
                return false;
            }
        }
        System.out.println("\tMODEL : picked cards are a set");
        return true;

    }

    /*na klikken op knop NO SET vragen aan tafel of er sets mogelijk zijn, indien nee, laat deck kaarten toevoegen*/
    public boolean noSet(){

        if(table.hasSet()){
            System.out.println("\tMODEL : should not add except when testing (check comments)");
            //deck.addCards(table); //om te testen, zet deze uit commentaar en return op true, anders te moeilijk om te wachten op een tafel zonder sets
            return false;

        }
        else {
            System.out.println(deckSize);
            if(deck.getCounter() == 81 && deckSize.equals("big") || deck.getCounter() == 27 && deckSize.equals("small")){
                System.out.println("\tMODEL : game is finished");
                isFinished = true;
                return true;
            }
            else{
                System.out.println("\tMODEL :should add");
                deck.addCards(table);
                return true;
            }
        }

    }

    public boolean isFinished(){
        return isFinished;
    }


    /*roept basically via table.getHint table.hasSet aan,
    nog uit te werken hoe we dit doorgeven aan de view*/
    public void getHint(){
        System.out.println("\tMODEL : getting hint from the table");
        if(table.getHint()){
            penalty = true;
        }
        else{
            deck.addCards(table);
        }
    }

    public void unMarkHint(){
        table.unMarkHint();
    }

    public ArrayList<Card> getCards(){ //getter voor de tafelkaarten
        return table.getCards();
    }

    /*wordt aangeroepen na het vormen van een set om deze kaarten te vervangen door nieuwe door deck*/
    public void replaceCards(){
//        if((deck.getCounter() == 81 && deckSize.equals("big") || deck.getCounter() == 27 && deckSize.equals("small")) && table.getCards().size() == 0){
//            endGame();
//        }
        System.out.println("\tMODEL : telling deck to put three new cards on table that were taken away ");
        deck.replaceCards(table, pickedCards);

//        if((deck.getCounter() == 81 && deckSize.equals("big") || deck.getCounter() == 27 && deckSize.equals("small")) && table.getCards().size() == 0){
//            endGame();
//        }
        if(table.getCards().size() == 0){
            isFinished = true;
        }


    }

    /*returned de kaarten die op dat moment "geselecteerd" zijn*/
    public ArrayList<Card> getPickedCards() {//getter voor de pickedCards
        return pickedCards;
    }

    /*maak de array van geselecteerde kaarten terug leeg, bv. omdat ze geen set vormen*/
    public void clearPickedCards(){
        pickedCards.clear();
    }

    /*reset de timer*/
    public void setStartTime(){
        this.startTime = System.currentTimeMillis();
    }

    /*returnt de tijd tussen begin van de ronde en nu*/
    public float getElapsedSeconds(){
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = elapsedTime/1000;
        return elapsedSeconds;
    }

    /*zet een van de spelers van het spel in de variabele currentPlayer, enkel deze zal punten krijgen bij SET*/
    public void setCurrentPlayer(int playerInt){

        System.out.printf("\tMODEL : setting which player is currently active, namely: %s \n", players.get(playerInt).toString());
        activePlayer = players.get(playerInt);

    }

    /*score verhoogt de score van de actieve speler in activePlayer met het aantal dat is vastgelegd in de speler klasse
    * en verhoogt de tijd van de speler met de tijd sinds het begin van deze ronde*/
    public void score(){

        float time = getElapsedSeconds();

        if(players.size() == 1){
            if(penalty){
                players.get(0).addScore(time + penaltyValue);
            }
            else{
                players.get(0).addScore(time);
            }
        }

        else{
            if(activePlayer != null){
                activePlayer.addScore(time);
                activePlayer = null;
            }
            else{
                System.out.println("You have to select a player");
            }
        }

        ////ng niet zeker van


//        if(activePlayer != null) {
//            if(penalty && players.size() > 1) {
//                activePlayer.addScore(time + penaltyValue);
//                activePlayer = null;
//            }
//        }
//        else{
//            System.out.println("You have to select a player");
//        }

        //System.out.printf("\tMODEL : score nu: %d / %.2f \n", playerScore, playerTime);
        //activePlayer.addScore(getElapsedSeconds());
        //System.out.printf("\tMODEL : " + activePlayer + " got 3 points + " + time + " seconds");
    }

    /*bij einde van spel, sorteert de spelers en print ze naar de console*/
    public String endGame() throws URISyntaxException {
        List<String> lines = new ArrayList<>();
        String result = String.format("");
        if(players.size() >1) {
            Collections.sort(players);

            for (int i = 0; i < players.size(); i++) {
                String line = "";
                line += players.get(i).getScore();
                line += ": ";
                line += players.get(i).getName();
                lines.add(line);
                result += String.format("%s: \t\t%4d en %7.2f \n", players.get(i).getName(), players.get(i).getScore(), players.get(i).getTime());

                System.out.println("\tMODEL : endgame: Player " + players.get(i).getName() + " score: " + players.get(i).getScore() + " and " + players.get(i).getTime() + " seconds");

            }
//            for(String l: lines){
//                //String result = "";
//                String lineName;
//                String lineScore;
//                lineName = l.split(": ")[1];
//                lineScore = l.split(": ")[0];
//                result += String.format("%s:\t\t %s\n", lineName, lineScore);
//            }
        }
        System.out.println("playersize: " + players.size());

        if(players.size() == 1) {

            URI uri = null;
            try {
                uri = getClass().getResource("/model").toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystem zipfs = null;
            try {
                zipfs = FileSystems.newFileSystem(uri, env);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Path path;
            float time = players.get(0).getTime();
            String name = players.get(0).getName();
            result += String.format("Your score: %.2f seconds (including penalties) \n \n High score list: \n\n", time);
            if(deckSize.equals("big")) {
                path = Paths.get(this.getClass().getResource("/model/score.txt").toURI());
            }
            else{
                path = Paths.get(this.getClass().getResource("/model/scoreSmall.txt").toURI());
            }
            if(Files.notExists(path)){
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            String text = players.get(0).getScore() + ":::::" + players.get(0).getName();
//            List<String> dinges = new ArrayList<>();
//            dinges.add(text);
//            try {
//                Files.write(path, dinges, Charset.defaultCharset(), StandardOpenOption.APPEND);
//                System.out.println("writing " + dinges);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            try {
                 lines = Files.readAllLines(path, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("hieronder contents file");
            for(String l:lines){

                System.out.println(l);
            }
            int insertLine;

            boolean found = false;
            for(int i = 0; i< lines.size(); i++){
                System.out.println("het ding uit de file is: " + lines.get(i));
                System.out.println("het ding uit de file is: " + lines.get(i).split(": ")[0]);
               if(Float.parseFloat(lines.get(i).split(": ")[0]) > time && !found){
                   insertLine = i;
                   lines.add(insertLine, String.valueOf(time) + ": " + name);
                   lines.remove(10);
                   found = true;
               }
            }
//            if(insertLine < 101) {
//                lines.add(insertLine, String.valueOf(time) + ":::::" + name);
//                lines.remove(10);
//            }
            System.out.println("voor delete");
            try {
                System.out.println("try delete");
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("try create");
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                System.out.println("try write");
                Files.write(path, lines, Charset.defaultCharset(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }



            System.out.println("hieronder contents file");
            for(String l:lines){

                System.out.println(l);
            }
            for(String l: lines){
                //String result = "";
                String lineName;
                float lineScore;
                lineName = l.split(": ")[1];
                lineScore = Float.parseFloat(l.split(": ")[0]);
                result += String.format("%1s: %-1.2f \n", lineName, lineScore);
            }
            try {
                zipfs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    return result;

    }

    public void addPlayer(String name){
        //String name = "Player" + (aantal+1);
        //aantal++;
        System.out.println("\tMODEL : making new player: " + name);
      players.add(new Player(name));
    }

    public void unSelectCurrentPlayer(){
        activePlayer = null;
    }

    public String getScores(){
        List<String> lines = new ArrayList<>();
        String result = "High score for big deck: \n\n";

        ///////////voor de JAR file moet dit er allemaal bij
        URI uri = null;
//        try {
//            uri = getClass().getResource("/").toURI();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        ;

        try {
            uri = getClass().getResource("/model/score.txt").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        FileSystem zipfs = null;
        try {
            zipfs = FileSystems.newFileSystem(uri, env);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ////// tot hier, dan de uri overal aanpassen


        //Path path;
        //path = Paths.get("src/model/score.txt");
        try {
//            lines = Files.readAllLines(path, Charset.defaultCharset());

            ////////ook dit is voor jar, idem odneraan bij small
            uri = getClass().getResource("/model/score.txt").toURI();


//            lines = Files.readAllLines(
//                    Paths.get(this.getClass().getResource("/model/score.txt").toURI()), Charset.defaultCharset());

            lines = Files.readAllLines(
                    Paths.get(uri), Charset.defaultCharset());

        } catch (IOException e) {
            e.printStackTrace();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for(String l: lines){
            //String result = "";
            String lineName;
            float lineScore;
            lineName = l.split(": ")[1];
            lineScore = Float.parseFloat(l.split(": ")[0]);
            result += String.format("%-15s: %.2f\n", lineName, lineScore);
        }
        result += "\n \nHigh score for small deck: \n\n";
        //path = Paths.get("src/model/scoreSmall.txt");
        try {
//            lines = Files.readAllLines(path, Charset.defaultCharset());
            uri = getClass().getResource("/model/scoreSmall.txt").toURI();
//            try {
//                zipfs = FileSystems.newFileSystem(uri, env);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            lines = Files.readAllLines(
                            Paths.get(uri), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for(String l: lines){
            //String result = "";
            String lineName;
            float lineScore;
            lineName = l.split(": ")[1];
            lineScore = Float.parseFloat(l.split(": ")[0]);
            result += String.format("%-15s: %-20.2f\n", lineName, lineScore);
        }

        try {
            zipfs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
