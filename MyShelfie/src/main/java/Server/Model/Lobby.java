package Server.Model;

import Server.VirtualView.LobbyObserver;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players;
    private Boolean readyToPlay;
    private LobbyObserver obs;
    private Boolean observerSet;

    /**
     * Overview: constructor of the lobby
     */
    public Lobby(){
        this.players = new ArrayList<>();
        this.observerSet = false;
    }

    /**
     * Overview: method aimed to add a player in the lobby
     */
    public void setPlayer(Player player){
        // devo controllare qui se si va oltre 4 giocatori nella lobby?
        this.players.add(player);
    }

    /**
     * Overview: method aimed to change status of the observerSet
     */
    public void setObserverSet(Boolean status){ this.observerSet = status; }

    /**
     * Overview: remove a player with the index
     */
    public void removePlayer(int index){ this.players.remove(index); }

    /**
     * Overview: method aimed to change the status of readyToPlay
     */
    public void setReadyToPlay(Boolean status){ this.readyToPlay = status; }

    /**
     * Overview: method aimed to add Lobby observer
     */
    public void setLobbyObserver(LobbyObserver observer){
        this.obs = observer;
        this.setObserverSet(true);
        notifyObserver();
    }

    /**
     * Overview: the Observer of the Lobby is notified
     */
    public void notifyObserver(){
        //this.obs.createmessage...
    }

    /**
     * Overview: readyToPlay getter
     */
    public Boolean getReadyToPlay(){ return this.readyToPlay; }

    /**
     * Overview: players getter
     */
    public ArrayList<Player> getPlayers(){ return this.players; }
}
