package ServerSide.Model;

import ServerSide.VirtualView.LobbyObserver;
import ServerSide.VirtualView.VirtualPlayerView;

import java.util.ArrayList;

public class Lobby {
    private String id;
    private ArrayList<Player> players;
    private Boolean readyToPlay;
    private LobbyObserver obs;

    /**
     * Overview: constructor of the lobby
     */
    public Lobby(String id){
        this.id = id;
        this.players = new ArrayList<>();
        this.readyToPlay = false;
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
    public void removePlayer(int index){ this.players.remove(index); }

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
    public void notifyObserverPlayerAdded(){
        String[] usernames = getUsernames();
        this.obs.updatelobbyviewmessage(usernames, this.id);
    }

    /**
     * Overview: the Observer of the lobby is notified about the possibility to start the game
     */
    public void notifyObserverGameCanStart(){
        System.out.println("creation of the message");
        this.obs.gamecanstartmessage();
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


}
