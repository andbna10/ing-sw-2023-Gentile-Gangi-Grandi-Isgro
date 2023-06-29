package ServerSide.Controller;

import ServerSide.Model.Player;

import java.util.HashMap;
import java.util.Objects;


public class LobbyManager {
    private HashMap<String, LobbyController> lobbies;

    /**
     * Overview: constructor LobbyManager
     * @author Andrea Isgrò
     * @author Francesco Gangi
     */
    public LobbyManager(){
        lobbies = new HashMap<>();
    }

    /**
     * Overview: method aimed to create a Lobby
     * @author Andrea Isgrò
     * @author Francesco Gangi
     * @param id lobby id
     */
    public void createLobby(String id){
        LobbyController lobby = new LobbyController(id);
        lobbies.put(id, lobby);
    }

    /**
     * Overview: get lobby by id
     * @author Andrea Isgrò
     * @param id lobby id
     * @return lobby specified by a given id
     */
    public LobbyController getLobby(String id){
        return lobbies.get(id);
    }

    /**
     * Overview: method aimed to close a lobby
     * @author Andrea Isgrò
     * @param id lobby id
     */
    public void closeLobby(String id){
        lobbies.remove(id);
    }

    /**
     * Overview: check for the username existence among active players
     * @author Andrea Isgrò
     * @param username player username
     * @return (true) if the username already exists, (false) otherwise
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

    /**
     * Overview: check if the players in the lobby are in a game
     * @author Andrea Isgrò
     * @param id lobby id
     * @return (0) if the lobby doesn't exist, (1) if the players are in a game, (-1) otherwise
     */
    public int checkInGame(String id){
        if(id.startsWith("random")){
            return -1;
        }
        if(!lobbies.containsKey(id)){
            return 0;
        }
        if(lobbies.get(id).getModel().getInGame()){
            return 1;
        } else {
            return -1;
        }
    }


    /**
     * Overview: get lobby id by player username
     * @author Mirko Gentile
     * @param user username
     * @return lobby id associated with inserted player username
     */
    public String getIdByUser(String user) {
        String ret = null;
        for(LobbyController lobbyc: lobbies.values()){
            for(Player p : lobbyc.getModel().getPlayers()) {
                if(p.getUsername().equals(user)) {
                    ret = lobbyc.getModel().getId();
                    break;
                }

            }
        }

        return ret;
    }

    /**
     * Overview: method aimed to search random lobbies free to entry
     * @return String of a random lobby that is not full, null otherwise
     */
    public String checkRandomLobbies(){
        for(LobbyController lobbyc: lobbies.values()){
            if(lobbyc.getModel().getId().startsWith("random -")){
                if(!lobbyc.getModel().getReadyToPlay()){
                    return lobbyc.getModel().getId();
                }
            }
        }
        return null;
    }

}
