package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {

    private LoginHandler handler;

    /**
     * Overview: LoginGUI constructor
     */
    public LoginGUI(LoginHandler handler){ this.handler = handler; }

    public LoginGUI(){
        //window initialization
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new FlowLayout());

        //setting up window icon and background
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/Display_1.jpg");
        Image scaledBg = bg.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(scaledBg));
        ImageIcon loginIcon = new ImageIcon("MyShelfie/src/main/resources/icon.png");
        loginFrame.setIconImage(loginIcon.getImage());

        //create game button
        JButton createB = new JButton("Create game");
        createB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                handler.creategamemessage("prova", username);
            }
        });

        //join game button
        JButton joinB = new JButton("Join game");
        joinB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                if(username != null) {
                    String gameID = JOptionPane.showInputDialog(loginFrame, "Enter game ID:",
                            "Enter game ID", JOptionPane.QUESTION_MESSAGE);
                }
                //handler.entergamemessage("prova", username, gameID);
            }
        });

        //play online button
        JButton onlineB = new JButton("Play online");
        onlineB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                //handler.entergamemessage("prova", username, "online");
            }
        });

        //exit button
        JButton exitB = new JButton("Exit");
        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //buttons panel initialization
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(0,1));

        buttons.add(createB);
        buttons.add(joinB);
        buttons.add(onlineB);
        buttons.add(exitB);

        loginFrame.add(bgLabel);
        loginFrame.setIconImage(loginIcon.getImage());
        loginFrame.add(buttons);
        //loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

}
