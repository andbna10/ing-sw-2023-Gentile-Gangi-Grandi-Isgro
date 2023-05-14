package ClientSide.NetworkHandler;

import ClientSide.Cli.LobbyCLI;
import ClientSide.View.LobbyView;
import Messages.fromClientToServer.NPlayersInputMessage;
import Messages.fromClientToServer.StartGameMessage;
import Networking.ClientManager;

import java.util.ArrayList;
import java.util.Scanner;

public class LobbyHandler implements LobbyViewObserver{
    private ClientManager manager;
    private LobbyView view;
    private ArrayList<String> players;
    private LobbyCLI cli;

    /**
     * Overview: LobbyHandler constructor
     */
    public LobbyHandler(ClientManager manager, ArrayList<String> usernames){
        this.cli = new LobbyCLI(this);
        this.manager = manager;
        this.players = new ArrayList<>();
        for(String u: usernames){
            this.players.add(u);
        }
        this.view = new LobbyView(this);
        // metodo per runnare la view (?) e come gli facciamo stampare i giocatori che sono nella lobby?
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
}
