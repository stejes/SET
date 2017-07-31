package model;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by fed on 1/31/15.
 */
public class Deck {
    private Card[] cards;
    private int counter;
    //private String deckSize;

    //genereert de kaarten met alle mogelijke combinaties van features
    //NOG TE DOEN: de verkleinde versie van het deck
    public Deck(String size) {

        if (size.equals("big")){
            cards = new Card[81];
            counter = 0;

                int i = 0;
                for(int j = 0; j < 3; j++){
                    for(int k = 0; k < 3; k++){
                        for(int l = 0; l < 3; l++){
                            for(int m = 0; m < 3; m++){
                                cards[i] = new Card(Color.values()[j], Shape.values()[l], Shading.values()[k], Number.values()[m], "/images/test/r_c_" + (i+1) + ".jpg.png"); //maakt een nieuwe kaart aan met 1 van 3*3*3*3 combinaties van features
                                i++;

                            }
                        }

                    }
                }

        }
        else if(size.equals("small")){
            cards = new Card[27];
            counter = 0;

            int i = 0;
            for(int j = 0; j < 3; j++){
                //for(int k = 0; k < 3; k++){
                    for(int l = 0; l < 3; l++){
                        for(int m = 0; m < 3; m++){
                            cards[i] = new Card(Color.values()[j], Shape.values()[l], Shading.values()[2], Number.values()[m], "/images/cards/" + (i+1) + ".png"); //maakt een nieuwe kaart aan met 1 van 3*3*3*3 combinaties van features
                            i++;

                        }
                    }


            }
        }

        //counter = 0;
        System.out.println("\t\tDECK : end of deck constructor");
    }

    public void makeTable(Table table) { //geeft 16 kaarten aan de tafel bij start van het spel
        for(int i = 0; i < 12; i++){
            table.addCard(cards[counter]);
            counter++;
        }
        System.out.println("\t\tDECK : took the initial 12 cards from deck");
    }

    public void replaceCards(Table table, ArrayList<Card> cardsToReplace) { //vervang de opgepikte kaarten
        System.out.println("\t\tDECK : replacing removed cards on TABLE");

        //counter += 3;

        for (int i = 0; i < 3; i++) {
            if(counter < cards.length) {

            table.replaceCard(cardsToReplace.get(i), cards[counter]);
            counter++;
            }
            else{
                table.removeCard(cardsToReplace.get(i));
            }
        }
    }

    public void addCards(Table table){//wanneer no set klikt en er is geen set, voegt hij er drie toe
        for(int i = 0; i < 3; i++){
            if(counter < cards.length) {
                table.addCard(this.cards[counter]);

                counter++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(Arrays.asList(cards));
    } //schudt de kaarten

//    public Card[] getCards() {
//        return cards;
//    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i<cards.length; i++) result += i + ": " + cards[i].toString() + "\n";
        return result;
    }
}
