package Client.NetworkHandler;

import Client.View.LobbyView;
import Networking.ClientManager;

import java.util.ArrayList;

public class LobbyHandler implements LobbyViewObserver{
    private ClientManager manager;
    private LobbyView view;
    private ArrayList<String> players;

    /**
     * Overview: LobbyHandler constructor
     */
    public LobbyHandler(ClientManager manager, String username){
        this.manager = manager;
        this.players = new ArrayList<>();
        addPlayer(username);
        this.view = new LobbyView(this);
        // metodo per runnare la view (?) e come gli facciamo stampare i giocatori che sono nella lobby?
    }

    /**
     * Overview: method aimed to add a player to the list of players
     */
    public void addPlayer(String username){
        this.players.add(username);
    }
}
