package ServerSide.Model;

import Networking.ServerManager;
import ServerSide.VirtualView.LobbyObserver;
import ServerSide.VirtualView.VirtualPlayerView;

import java.util.ArrayList;

public class Lobby {
    private String id;
    private ArrayList<Player> players;
    private Boolean readyToPlay;
    private LobbyObserver obs;
    private Boolean inGame;
    private int fixedNPlayers;

    /**
     * Overview: constructor of the lobby
     */
    public Lobby(String id){
        this.id = id;
        this.players = new ArrayList<>();
        this.readyToPlay = false;
        this.fixedNPlayers = -1;
        this.inGame = false;
    }

    /**
     * Overview: method aimed to add a player in the lobby
     */
    public void setPlayer(Player player){
        // devo controllare qui se si va oltre 4 giocatori nella lobby? per ora io lo controllo nel controller, vedere se spostarlo
        this.players.add(player);
    }

    /**
     * Overview: remove a player with the index
     */
    public Boolean removePlayer(ServerManager manager){
        boolean removedOwner = false;
        for(Player p: players){
            if(p.getManager() == manager){

                if(players.size()>1 && p.getLobby().get(id)){
                    removedOwner = true;
                    for(int i=0; i<players.size();i++){
                        if(players.get(i) == p){
                            players.get(i+1).updateLobby(true,id);
                        }
                    }
                }
                players.remove(p);
                notifyObserverPlayerAdded(id);
                if(players.size() < 2){
                    setReadyToPlay(false);
                } else {
                    for(Player client: players){
                        if(client.getLobby().get(id)){
                            if(removedOwner){
                                client.notifyOwner(id);
                            }
                        }
                    }
                }
                if(players.size() == 0){
                    return true;
                }
                break;

            }
        }
        return false;
    }

    /**
     * Overview: method aimed to change the status of readyToPlay
     */
    public void setReadyToPlay(Boolean status){
        this.readyToPlay = status;
    }

    /**
     * Overview: method aimed to add Lobby observer
     */
    public void setLobbyObserver(LobbyObserver observer){ this.obs = observer; }

    /**
     * Overview: the Observer of the Lobby is notified about the new player in the lobby
     */
    public void notifyObserverPlayerAdded(String id){
        String[] usernames = getUsernames();
        String owner = null;
        for(Player p: players){
            if(p.getLobby().get(id)){
                owner = p.getUsername();
            }
        }
        this.obs.updatelobbyviewmessage(usernames, this.id, owner);
    }

    /**
     * Overview: readyToPlay getter
     */
    public Boolean getReadyToPlay(){ return this.readyToPlay; }

    /**
     * Overview: players getter
     */
    public ArrayList<Player> getPlayers(){ return this.players; }

    /**
     * Overview: method aimed to provide an array with the username of each player
     */
    public String[] getUsernames(){
        String[] usernames = new String[players.size()];
        int i = 0;
        for(Player p: players){
            usernames[i] = p.getUsername();
            i++;
        }
        return usernames;
    }

    /**
     * Overview: method aimed to provide an ArrayList pf the VirtualPlayerView
     */
    public ArrayList<VirtualPlayerView> getPlayerViews(){
        ArrayList<VirtualPlayerView> list = new ArrayList<>();
        for(Player p: players){
            list.add((VirtualPlayerView) p.getObs());
        }
        return list;
    }

    /**
     * Overview: id getter
     */
    public String getId(){ return this.id; }

    /**
     * Overview: get fixed number
     */
    public int getFixedNPlayers(){ return fixedNPlayers; }

    /**
     * Overview: set fixed number
     */
    public void setFixedNPlayers(int n){ this.fixedNPlayers = n; }

    /**
     * Overview: inGame getter
     */
    public Boolean getInGame(){ return this.inGame; }

    /**
     * Overview: inGame setter
     */
    public void setInGame(Boolean status){  this.inGame = status;}


}
