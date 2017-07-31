package view;


import javax.swing.*;
import java.awt.*;

/* De klasse SplashScreen maakt een SplashScreen voor het spel */

public class SplashScreen {

    JWindow splashScreen = new JWindow();//geen border en knoppen etc

    public SplashScreen() {

        try {
            ///////!!!!! voor de JAR moet de path absoluut zijn (binnenin de jar)
            ImageIcon splash = new ImageIcon(this.getClass().getResource("/images/splash.gif"));
            ImageIcon loaderBar = new ImageIcon(this.getClass().getResource("/images/loader.gif"));
            JLabel splashLabel = new JLabel();
            JLabel loaderLabel = new JLabel();
            JLabel loading = new JLabel("Loading");
            splashLabel.setIcon(splash);
            loaderLabel.setIcon(loaderBar);
            Box box = Box.createVerticalBox();
            box.add(splashLabel);
            splashLabel.setAlignmentX(Component.CENTER_ALIGNMENT);//alle componenten horizontaal gecentreerd
            box.add(Box.createVerticalStrut(80));
            box.add(loaderLabel);
            loaderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(loading);
            loading.setAlignmentX(Component.CENTER_ALIGNMENT);
            splashScreen.getContentPane().add(box);
            splashScreen.getContentPane().setBackground(new Color(178, 156, 221));
        } catch (Exception e) {
            e.printStackTrace();
        }
        splashScreen.setBounds(500, 150, 450, 450);//waar en hoe groot
        splashScreen.setVisible(true);
        try {
            Thread.sleep(2100);//pas aan om te testen!!!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.setVisible(false);
    }
    public JWindow getSplashScreen() {
        return splashScreen;
    }
}

