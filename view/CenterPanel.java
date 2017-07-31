package view;

import controller.Controller;
import model.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


/* CenterPanel klasse is waar de kaarten op tafel komen /*

 */


public class CenterPanel extends JPanel{

    Controller controller;


    public CenterPanel(Controller controller){
        this.controller = controller;
        layoutComponents();
    }



    private void layoutComponents(){
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 6, 5, 5)); // dimensie, dimensie, spatie, spatie
        centerPanel.setPreferredSize(new Dimension(500,500));//blijven samen bij fullscreen

        for (int i=0; i<4; i++) {

            JPanel column = new JPanel();
            column.setLayout(new GridLayout(3, 1, 5, 5));
            for (int j = 1; j < 4; j++) {

                /* Je moet een label gebruiken om een image full te displayen in de Jlabel
                Eerst moet je de image opladen als buffered image
                daarna moet je dat image toevoegen aan een icon
                en de icon toevoegen aan de label
                 */
                JLabel cardLabel = new JLabel();
                cardLabel.setSize(150,200); //deze zet de size van de label
                /*Buffered*/Image img = null;
//                try {
//                    //img = ImageIO.read(new File(getClass().getResource("/images/backSet.png").toURI()));
//                    //img = new Image(getClass().getResource("/images/backSet.png"));
//                    img = null;
//                } catch (IOException | URISyntaxException e) {
//                    e.printStackTrace();
//                }

                Image dimg = null;
                if (img != null) {
                    dimg = img.getScaledInstance(cardLabel.getWidth(), cardLabel.getHeight(),
                            Image.SCALE_SMOOTH);
                }
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/backSet.png"));
                cardLabel.setIcon(imageIcon);
                column.add(cardLabel);
//
            }
            add(column);


        }


    }

    /*heb onderstaande code voorlopig veranderd om de buttons top-down en dan left-right te krijgen ipv
    omgekeerd, is iets simpeler om te testen, zal misschien wel efficientere manier voor zijn
     */
    public void showCards(ArrayList<Card> cards){

        this.removeAll();//removet de oude content
        setLayout(new GridBagLayout());
        boolean isContent = true;
        for (int i=0; i<6; i++) {

            JPanel column = new JPanel();
            column.setLayout(new GridLayout(3, 1, 5, 5));
            for (int j = 1; j < 4; j++) {
                if((j+i*3-1) < cards.size()) {
                    CardLabel cardButton = new CardLabel(cards.get((j+i*3-1)), controller);
                    column.add(cardButton);


                }
                else{isContent = false;}
            }
            if(isContent) {
                add(column);
            }

        }
        this.validate();//begrijp nog niet waarom, maar zonder dit werkt het niet
        this.repaint();//"refresht" de content van de panel

        System.out.println("\t\t\tCENTERPANEL : ik toon (hopelijk) de (nieuwe) kaarten in de GUI");

    }


}

