package ClientSide.View.SwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {

    String type;

    JLabel label;

    public CustomActionListener(String type, JLabel label) {
        this.type = type;
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case "create" -> System.out.println("Game created");
            case "join" -> System.out.println("Game joined");
        }
    }
}