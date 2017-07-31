package view;

/* De toppanel klasse is de waar het menu komt */

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TopPanel extends JPanel{
    Controller controller;

    ArrayList<TopPanelButton> buttons = new ArrayList<>();
    ArrayList<String> names;
    int number;


    public TopPanel(ArrayList<String> names, Controller controller){
        this.controller = controller;


        this.names = names;
        System.out.println("\t\tTOPPANEL : calling layoutcomp" + buttons.size());
        layoutComponents();



    }

    public TopPanel(ArrayList<String> names, int number, Controller controller){
        this.controller = controller;
        this.number = number;


        this.names = names;
        System.out.println("\t\tTOPPANEL : calling layoutcomp" + buttons.size());
        layoutComponents();



    }

    //initialiseert de componenten
    public void initComponents(int number){
        removeAll();

            for (int i = 0; i < number; i++) {
                System.out.println("\t\tTOPPANEL : making new playerbutton");
                TopPanelButton playerButton = new TopPanelButton(names.get(i), i, controller, this);
                buttons.add(playerButton);
                add(playerButton);
                System.out.println("\t\tTOPPANEL : telling controller to add player");
                controller.addPlayer(names.get(i));
            }


        revalidate();
        repaint();
    }

    //BUGFIX//////////////////
    public void initComponents(){
        removeAll();

        for (int i = 0; i < number; i++) {
            System.out.println("\t\tTOPPANEL : making new playerbutton");
            TopPanelButton playerButton = new TopPanelButton(names.get(i), i, controller, this);
            buttons.add(playerButton);
            add(playerButton);
            System.out.println("\t\tTOPPANEL : telling controller to add player");
            controller.addPlayer(names.get(i));
        }


        revalidate();
        repaint();
    }





    // maakt de layout voor die componenten aan
    private void layoutComponents() {
        setLayout(new FlowLayout());


    }


    public void updateSelected(int position){

        for(int i = 0; i< buttons.size(); i++){

            if(i == position){
                buttons.get(i).setBackground(Color.green);


            }
            else{
                buttons.get(i).setBackground(null);

            }
        }
        revalidate();
        repaint();
    }

    public void unselectAll(){

        for(int i = 0; i< buttons.size(); i++){
            buttons.get(i).setBackground(null);


        }
        revalidate();
        repaint();
    }





}

