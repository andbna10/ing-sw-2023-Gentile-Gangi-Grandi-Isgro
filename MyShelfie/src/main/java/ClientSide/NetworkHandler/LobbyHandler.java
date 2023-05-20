package ClientSide.NetworkHandler;

import ClientSide.View.CLI.LobbyCLI;
import ClientSide.View.GUI.LobbyGUI;
import Messages.fromClientToServer.StartGameMessage;
import Networking.ClientManager;

import javax.swing.*;
import java.util.ArrayList;

public class LobbyHandler implements LobbyViewObserver{
    private ClientManager manager;
    private ArrayList<String> players;
    private LobbyCLI cli;
    private LobbyGUI gui;

    /**
     * Overview: LobbyHandler constructor
     */
    public LobbyHandler(ClientManager manager, ArrayList<String> usernames){
        this.cli = new LobbyCLI(this);
        this.manager = manager;
        this.players = new ArrayList<>();
        this.players.addAll(usernames);
        //runLobbyGUI();
    }

    /**
     * Overview: method aimed to add a player to the list of players
     */
    public void addPlayer(String username){
        this.players.add(username);
    }

    /**
     * Overview: method aimed to start the game
     */
    public void startgamemessage(String sender, String idLobby){
        StartGameMessage message = new StartGameMessage(sender, idLobby);
        manager.sendMessage(message);
    }

    // serve un metodo per gestire il bottone startgame che i giocatori vedono nella lobby view

    /**
     * Overview: cli getter
     */
    public LobbyCLI getCli(){ return this.cli; }

    public LobbyGUI getGui(){ return this.gui; }

    /**
     * Overview: run the lobby GUI
     */
    public void runLobbyGUI() { this.gui = new LobbyGUI(this); }
}
