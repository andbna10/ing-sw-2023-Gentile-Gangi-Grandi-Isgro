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
    public LoginGUI(LoginHandler handler) { this.handler = handler; }

    public LoginGUI() {
        //window initialization
        JFrame loginFrame = new JFrame("Login Dialog");
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.getCaret().setSelectionVisible(true);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        JScrollPane textPane = new JScrollPane(textArea);
        textPanel.add(textPane, BorderLayout.CENTER);

        ImageIcon parq = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        Image scaledParq = parq.getImage().getScaledInstance(850,700, Image.SCALE_SMOOTH);
        JLabel parqLabel = new JLabel(new ImageIcon(scaledParq));

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

                //textArea.append("The username is: " + username + "\n");

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

        //random match button
        JButton randomB = new JButton("Random match");
        randomB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                        "Enter username", JOptionPane.QUESTION_MESSAGE);

                //handler.entergamemessage("prova", username, "random");
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
        buttons.add(randomB);
        buttons.add(exitB);


        bgLabel.setBounds(40,25,600,400);
        buttons.setBounds(670,130,150,150);

        textArea.setLineWrap(true);

        textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        textArea.setFont(textArea.getFont().deriveFont(15f));

        textPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textPanel.setBounds(25, 450, 800, 220);

        parqLabel.add(textPanel);
        parqLabel.add(buttons);
        parqLabel.add(bgLabel);
        loginFrame.add(parqLabel);

        loginFrame.setSize(760,650);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

}