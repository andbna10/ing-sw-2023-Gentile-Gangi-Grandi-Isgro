package ServerSide.Controller;

import ServerSide.Model.Player;

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

    /**
     * Overview: check for the username existance among active players
     */
    public Boolean checkUsername(String username){
        for(LobbyController lobbyc: lobbies.values()){
            for(Player p: lobbyc.getModel().getPlayers()){
                if(p.getUsername().equals(username)){
                    return true;
                }
            }
        }
        return false;
    }


}
