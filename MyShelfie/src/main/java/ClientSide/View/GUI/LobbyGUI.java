package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LobbyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LobbyGUI {

    private LobbyHandler handler;
    private LoginGUI loginGUI;
    private JTextArea textArea;
    private JButton startB;
    private JFrame lobbyFrame;

    /**
     * Overview: LobbyGUI constructor
     * @author Francesco Gangi
     * @param lobbyHandler Lobby Handler needed for network communication
     */
    public LobbyGUI(LobbyHandler lobbyHandler) {

        this.handler = lobbyHandler;

        //window initialization
        lobbyFrame = new JFrame("Lobby Dialog");
        JPanel textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.getCaret().setSelectionVisible(true);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        JScrollPane textPane = new JScrollPane(textArea);
        textPanel.add(textPane, BorderLayout.CENTER);

        ImageIcon parq = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        Image scaledParq = parq.getImage().getScaledInstance(650,770, Image.SCALE_SMOOTH);
        JLabel parqLabel = new JLabel(new ImageIcon(scaledParq));

        //setting up window icon and background
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/Display_1.jpg");
        Image scaledBg = bg.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(scaledBg));
        ImageIcon loginIcon = new ImageIcon("MyShelfie/src/main/resources/icon.png");
        lobbyFrame.setIconImage(loginIcon.getImage());

        bgLabel.setBounds(25,25,600,400);

        textArea.setLineWrap(true);

        textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        textArea.setFont(textArea.getFont().deriveFont(15f));

        textPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textPanel.setBounds(25, 450, 600, 220);

        startB = new JButton("START");
        startB.setEnabled(false);
        startB.setBounds(275,700,100,30);

        parqLabel.add(startB);
        parqLabel.add(textPanel);
        parqLabel.add(bgLabel);
        lobbyFrame.add(parqLabel);


        lobbyFrame.setSize(650,800);
        lobbyFrame.setLocationRelativeTo(null);
        lobbyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lobbyFrame.setVisible(true);
        lobbyFrame.pack();
    }

    /**
     * Overview: method for text refreshing in lobby dialog
     * @author Francesco Gangi
     * @param id lobby id
     * @param usernames arraylist containing players' usernames
     * @param owner lobby owner's username
     */
    public void updateTextArea(String id, ArrayList<String> usernames, String owner) {

        textArea.setText("");

        textArea.append("The id of the lobby is: " + id + "\n\n");
        for(String s: usernames){
            if(s == owner){
                textArea.append(s+" OWNER\n");
                continue;
            }
            textArea.append(s + "\n");
        }
    }

    /**
     * Overview: method aimed to make a button clickable when needed
     * @author Francesco Gangi
     * @param id lobby id
     */
    public void buttonClickable(String id) {
        startB.setEnabled(true);
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startgamemessage("prova", id);
            }
        });
    }

    /**
     * Overview: method aimed to close lobby dialog windows
     * @author Francesco Gangi
     */
    public void closeLobbyWindow() {
        lobbyFrame.setVisible(false);
        lobbyFrame.dispose();
    }
}
