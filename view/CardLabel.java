package view;

import controller.Controller;
import model.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class CardLabel extends JLabel {

    private boolean clicked = false;



    /*
    onderstaande constructor gebruikt twee args ipv 1, nl. het kaart object van de kaart die getoond moet worden, en de controller
    vermits de controller nodig is om aan te roepen wanneer de kaart gelikt wordt. 1 v/d argumeten van de kaart moet dus op termijn
    het image pad worden. Verder nog niets mee gedaan.
     */
    public CardLabel(Card card, Controller controller)
    {
        //final Card card = cardx;
        setPreferredSize(new Dimension(145, 190));
        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(getClass().getResource(card.getImage()).toURI()));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }



        ImageIcon imageIcon = new ImageIcon(getClass().getResource(card.getImage()));
        setIcon(imageIcon);

        if(card.isHint()){
            setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,
                    Color.green, Color.black));
        }





        addMouseListener(
                new MouseListener()
                {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!clicked) {
                        /*voorlopig background verandering (ter test) en aanroepen van functionaliteit
                        * mbt checken op set */
                            System.out.println("\t\t\t\tCARDBUTTON : mouseClicked in button : " + card.toString() + ", roep nu in controller pickCard methode aan");
                            try {
                                controller.pickCard(card);
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                            //hieronder, als er wordt gekliked komt er een border met shadow achtig ding
                            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                                    Color.PINK, Color.RED));

                            clicked = true;
                        }
                        else{
                            /*wat er (voorlopig) moet gebeuren bij deselecteren van een kaart*/
                            System.out.println("\t\t\t\tCARDBUTTON : AGAIN mouseClicked in button : " + card.toString() + ", roep nu in controller removePickedCard methode aan");
                            setBorder(BorderFactory.createEmptyBorder());
                                    controller.removePickedCard(card);
                            clicked = false;
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) { }

                    @Override
                    public void mouseReleased(MouseEvent e) { }

                    @Override
                    public void mouseEntered(MouseEvent e) { }

                    @Override
                    public void mouseExited(MouseEvent e) { }
                });

        System.out.println("\t\t\t\tCARDBUTTON : ik toon een kaart-button en voeg een listener toe voor muisklik");
    }
}


