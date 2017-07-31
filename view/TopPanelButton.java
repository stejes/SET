package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TopPanelButton extends JButton {

    private String name;
    Controller controller;

    int position;

    public TopPanelButton(String buttonName, int position, Controller controllerx, TopPanel topPanel) {
        name = buttonName;
        this.position = position;
        setText(name);
        System.out.println("mouseClicked in TopPanel View on playerButton : " + controller);
        this.controller = controllerx;





        addMouseListener(
                new MouseListener() {


                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("mouseClicked in TopPanel View on playerButton : " + name);


                        topPanel.updateSelected(position);
                        //System.out.println(player);
                        controller.setCurrentPlayer(position);
                        //topPanel.initComponents();


                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
    }

    @Override
    public String getName() {
        return name;
    }
}