package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class LaunchInstructions extends JFrame {

    JFrame instructions = new JFrame("Launch");

    public LaunchInstructions() {


        // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(instructions,
                "",
                "",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(getClass().getResource("/images/launch_instructions.PNG")));

//        JFrame.exit(0);
    }

    public JFrame getLaunchInstructions(){
        return instructions;
    }
}
