package view;

import javax.swing.*;
import java.awt.*;


public class OptionDeckSize extends JPanel {

    private static final String[] BUTTON_TEXTS = {"Small Deck (27 cards)", "Big Deck (81 cards)"};
    private ButtonGroup buttonGroup = new ButtonGroup();

    public OptionDeckSize() {
        setLayout(new GridLayout(0, 1));

        // aanmaken radio buttons en toevoegen aan button group en JPanel
        for (String buttonText : BUTTON_TEXTS) {
            JRadioButton radioBtn = new JRadioButton(buttonText);
            radioBtn.setActionCommand(buttonText); // set ActionCommand
            buttonGroup.add(radioBtn);
            add(radioBtn);
        }
    }

    // getter voor de selected JRadioButton actionCommand tekst

    public String getSelectedButtonText() {
        ButtonModel model = buttonGroup.getSelection();
        if (model == null) { // geen radiobutton geselecteerd
            return "";
        } else {
            return model.getActionCommand();
        }
    }
}





