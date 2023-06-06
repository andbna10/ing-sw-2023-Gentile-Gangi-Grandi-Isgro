package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {

    private LoginHandler handler;
    private JFrame loginFrame;

    /**
     * Overview: LoginGUI constructor
     */

    public LoginGUI(LoginHandler handler) {

        this.handler = handler;

        //window initialization
        loginFrame = new JFrame("Login Dialog");
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.getCaret().setSelectionVisible(true);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        JScrollPane textPane = new JScrollPane(textArea);
        textPanel.add(textPane, BorderLayout.CENTER);

        ImageIcon parq = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        Image scaledParq = parq.getImage().getScaledInstance(850,460, Image.SCALE_SMOOTH);
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

                String username;
                boolean nullInput = false;
                do {
                    username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                            "Enter username", JOptionPane.QUESTION_MESSAGE);
                    if(username == null){
                        nullInput = true;
                        break;
                    }

                } while(username.isEmpty() || username.isBlank());

                if(!nullInput){
                    handler.creategamemessage("prova", username);
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                }

            }
        });

        //join game button
        JButton joinB = new JButton("Join game");
        joinB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean nullInput1 = false;
                boolean nullInput2 = false;
                String username;
                do {
                    username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                            "Enter username", JOptionPane.QUESTION_MESSAGE);
                    if(username == null){
                        nullInput1 = true;
                        break;
                    }
                } while (username.isEmpty() || username.isBlank());

                String gameID = null;
                if(!nullInput1) {
                    do {
                        gameID = JOptionPane.showInputDialog(loginFrame, "Enter game ID:",
                                "Enter game ID", JOptionPane.QUESTION_MESSAGE);
                        if(gameID == null){
                            nullInput2 = true;
                            break;
                        }
                    } while (gameID.isEmpty() || gameID.isBlank());
                }

                if(!nullInput1 && !nullInput2) {
                    handler.entergamemessage("prova", username, gameID);
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                }
            }

        });

        //random match button
        JButton randomB = new JButton("Random match");
        randomB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username;
                boolean nullInput = false;
                do {
                    username = JOptionPane.showInputDialog(loginFrame, "Enter username:",
                            "Enter username", JOptionPane.QUESTION_MESSAGE);
                    if(username == null){
                        nullInput = true;
                        break;
                    }

                } while(username.isEmpty() || username.isBlank());

                if(!nullInput){
                    handler.entergamemessage("prova", username, "random");
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                }

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


        parqLabel.add(buttons);
        parqLabel.add(bgLabel);
        loginFrame.add(parqLabel);

        loginFrame.setSize(760,650);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

    public void validityCheck(String message) {
        JOptionPane.showMessageDialog(loginFrame, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    public int insertNumber(String message){
        String number;
        int n = 0;
        boolean nullInput = false;
        do {

            number = JOptionPane.showInputDialog(loginFrame, message, "Insert number", JOptionPane.QUESTION_MESSAGE);

            if(number == null){
                nullInput = true;
                break;
            }

            try {
                n = Integer.parseInt(number);
            } catch (NumberFormatException e){
                nullInput = true;
            }

        } while(n<2 || n>4);

        if(!nullInput)
            return n;

        return insertNumber(message);

    }

}