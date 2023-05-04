package ServerSide.Controller;

import java.util.HashMap;


public class LobbyManager {
    private HashMap<String, LobbyController> lobbies;

    /**
     * Overview: constructor LobbyManager
     */
    public LobbyManager(){ lobbies = new HashMap<>(); }

    /**
     * Overview: method aimed to create a Lobby
     */
    public void createLobby(String id){
        LobbyController lobby = new LobbyController(id);
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
