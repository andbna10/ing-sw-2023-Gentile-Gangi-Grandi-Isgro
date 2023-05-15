package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {

    private LoginHandler handler;

    public LoginGUI(LoginHandler handler){ this.handler = handler; }

    public LoginGUI(){
        JFrame loginFrame = new JFrame();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new FlowLayout());
        loginFrame.setLocationRelativeTo(null);

        JLabel bgLabel = new JLabel(new ImageIcon("MyShelfie/src/main/resources/Display_1.jpg"));

        JButton createB = new JButton("Create game");
        createB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                handler.creategamemessage("prova", username);
            }
        });

        JButton joinB = new JButton("Join game");
        joinB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                String gameID = JOptionPane.showInputDialog(loginFrame, "Enter game ID:",
                        "Enter game ID", JOptionPane.QUESTION_MESSAGE);

                handler.entergamemessage("prova", username, gameID);
            }
        });

        JButton onlineB = new JButton("Play online");
        onlineB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                handler.entergamemessage("prova", username, "online");
            }
        });

        JButton exitB = new JButton("Exit");
        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,2));

        buttons.add(createB);
        buttons.add(joinB);
        buttons.add(onlineB);
        buttons.add(exitB);

        loginFrame.add(bgLabel);
        loginFrame.add(buttons);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

}
