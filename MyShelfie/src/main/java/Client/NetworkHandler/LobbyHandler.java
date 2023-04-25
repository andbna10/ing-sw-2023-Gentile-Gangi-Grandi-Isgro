package Client.NetworkHandler;

import Client.View.LobbyView;
import Messages.fromClientToServer.StartGameMessage;
import Networking.ClientManager;

import java.util.ArrayList;

public class LobbyHandler implements LobbyViewObserver{
    private ClientManager manager;
    private LobbyView view;
    private ArrayList<String> players;

    /**
     * Overview: LobbyHandler constructor
     */
    public LobbyHandler(ClientManager manager, ArrayList<String> usernames){
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
        manager.setIsMessage(true);
        manager.setMessage(message);
    }

    // serve un metodo per gestire il bottone startgame che i giocatori vedono nella lobby view
}
