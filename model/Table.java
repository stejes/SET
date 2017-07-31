package model;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fed on 1/31/15.
 */
public class Table {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Card[]> hints = new ArrayList<Card[]>();
    private Card[] hint = new Card[3];
    //seed generator en seed om bij elke ronde een andere random i'de element van de hints als hint te krijgen
    //maar toch tijdens de ronde gelijk blijft
    Random seedGenerator = new Random();
    int seed;



    public Table() {

        System.out.println("\t\tTABLE : end of table constructor");
        seed = seedGenerator.nextInt();

    }


    public void addCard(Card card){
        cards.add(card);
        System.out.println("\t\tTABLE : received one card from deck and added it to my array");
    }

    public void replaceCard(Card cardToReplace, Card newCard){
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i) == cardToReplace){
                cards.remove(i);
                System.out.println("\t\tTABLE : removed a card from the chosen set from the table");
                if(cards.size() < 12) {
                    cards.add(i, newCard);
                    System.out.println("\t\tTABLE : received one new card from deck and replaced the removed card if size < 12");
                }

                //return;
            }
        seed = seedGenerator.nextInt();
        }
    }

    public void removeCard(Card cardToRemove){
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i) == cardToRemove){
                cards.remove(i);
                System.out.println("\t\tTABLE : removed a card from the chosen set from the table");


                return;
            }

        }
    }




    //checkt de tafel op het voorkomen van mogelijke SETS, voorlopig geen return, nog te bekijken hoe
    //we dat gaan implementeren
    public boolean hasSet() {
        hints.removeAll(hints);
        System.out.println("\t\t\tTABLE : checking if set is possible with dealt cards");
        int sets = 0;
        boolean possible;

        for(int j = 0; j<cards.size() ; j++){
            for(int k = j+1; k < cards.size(); k++){
                for(int l = k+1; l < cards.size(); l++){
                    if( j != k && j != l && k != l){

                        possible = true;

                        for (int i = 0; i < 4; i++) {
                            if(possible) {
                                if (!((cards.get(j).getFeatures()[i] == cards.get(k).getFeatures()[i] && cards.get(k).getFeatures()[i] == cards.get(l).getFeatures()[i])
                                        || (cards.get(j).getFeatures()[i] != cards.get(k).getFeatures()[i] && cards.get(j).getFeatures()[i] != cards.get(l).getFeatures()[i] && cards.get(k).getFeatures()[i] != cards.get(l).getFeatures()[i]))) {
                                    possible = false;
                                }
                            }

                        }
                        if(possible) {
                            System.out.println(String.format("\t\t\tTABLE : possible set: cards %d, %d and %d", j, k, l));
                            Card[] hint = {cards.get(j), cards.get(k), cards.get(l)};
                            hints.add(hint);
                            sets += 1;
                        }
                    }
                }
            }
        }
        //return possible;
        if(sets > 0){
            return true;
        }
        else{return false;}

    }



    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i=0; i<cards.size();i++){
            result += i + ": " + cards.get(i).toString() + "\n";
        }
        return result;
    }


    /*
    Als er een set mogelijk is, haalt hij een van de SET-combinaties op uit de lijst met mogelijke combinaties
    Voorlopig wordt aan de hint array een random element van de mogelijke SET-combinaties gehaald
    markHint wordt dan aangeroepen om in elk card object aan te duiden dat die kaart tot de hint behoort
     */
    public boolean getHint(){


        //unMarkHint();
        if(hasSet()) {
            Random rand = new Random(seed);
            int random = rand.nextInt(hints.size());
            System.out.printf("\t\tTABLE : seed = %d en random = %d \n", seed, random);
            hint = hints.get(random);
            markHint();
            return true;
        }
        return false;

    }

    /*
    voor elk kaart object in de hint array, aangemaakt in getHint() wordt doorgegeven dat het deel uitmaakt van de hint
     */
    public void markHint(){
        for(int i = 0; i<3; i++) {
            hint[i].setHint();
        }
    }

    /*
    tegenovergestelde van markHint, om te vermijden dat er meer dan drie kaarten als hint aangeduid worden bij verschillende
    keren klikken op hint
     */
    public void unMarkHint(){
        for(int i = 0; i<3; i++) {
            if(hint[i] != null) {
                hint[i].unsetHint();
            }
        }
    }

//    public ArrayList<Card[]> getHints() {
//        return hints;
//    }
}
