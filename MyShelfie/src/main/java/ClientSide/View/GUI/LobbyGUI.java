package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LobbyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyGUI {

    private LobbyHandler handler;
    private LoginGUI loginGUI;
    private JTextArea textArea;


    /**
     * Overview: LobbyGUI constructor
     */
    public LobbyGUI(LobbyHandler lobbyHandler) {

        this.handler = lobbyHandler;

        //window initialization
        JFrame loginFrame = new JFrame("Lobby Dialog");
        JPanel textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.getCaret().setSelectionVisible(true);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        JScrollPane textPane = new JScrollPane(textArea);
        textPanel.add(textPane, BorderLayout.CENTER);

        ImageIcon parq = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        Image scaledParq = parq.getImage().getScaledInstance(650,700, Image.SCALE_SMOOTH);
        JLabel parqLabel = new JLabel(new ImageIcon(scaledParq));

        //setting up window icon and background
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/Display_1.jpg");
        Image scaledBg = bg.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(scaledBg));
        ImageIcon loginIcon = new ImageIcon("MyShelfie/src/main/resources/icon.png");
        loginFrame.setIconImage(loginIcon.getImage());

        bgLabel.setBounds(25,25,600,400);

        textArea.setLineWrap(true);

        textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        textArea.setFont(textArea.getFont().deriveFont(15f));

        textPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textPanel.setBounds(25, 450, 600, 220);

        parqLabel.add(textPanel);
        parqLabel.add(bgLabel);
        loginFrame.add(parqLabel);

        loginFrame.setSize(760,650);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

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
}
