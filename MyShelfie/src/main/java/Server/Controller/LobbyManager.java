package Server.Controller;

import Networking.ServerManager;

import java.util.HashMap;
import java.util.Map;


public class LobbyManager {
    private Map<String, LobbyController> lobbies;

    /**
     * Overview: constructor LobbyManager
     */
    public LobbyManager(){ lobbies = new HashMap<>(); }

    /**
     * Overview: method aimed to create a Lobby
     */
    public void createlobby(ServerManager servermanager, String id){
        LobbyController lobby = new LobbyController(servermanager, id);
        lobbies.put(id, lobby);
    }

    /**
     * Overview: get lobby by id
     */
    public LobbyController getLobby(String id){
        return lobbies.get(id);
    }

    /**
     * Overview: method aimed to close a lobby
     */
    public void closeLobby(String id){ lobbies.remove(id); }


}
