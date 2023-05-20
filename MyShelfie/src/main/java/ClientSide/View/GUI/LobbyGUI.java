package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LobbyHandler;
import ClientSide.NetworkHandler.LoginHandler;

import javax.swing.*;
import java.awt.*;

public class LobbyGUI {

    private LobbyHandler handler;

    /**
     * Overview: LobbyGUI constructor
     */
    public LobbyGUI(LobbyHandler handler){ this.handler = handler; }

    public LobbyGUI() {
        JFrame lobbyFrame = new JFrame("Lobby Dialog");
        lobbyFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        lobbyFrame.add(textArea, BorderLayout.CENTER);

        //handler = new LobbyHandler(textArea);

        lobbyFrame.setSize(600,400);
        lobbyFrame.setLocationRelativeTo(null);
        lobbyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lobbyFrame.setVisible(false);
    }

}
