package Server.Controller;

import Networking.ServerManager;
import Server.Model.Lobby;
import Server.Model.Player;
import Server.VirtualView.LobbyVViewObserver;
import Server.VirtualView.VirtualLobbyView;

public class LobbyController implements LobbyVViewObserver {
    private Lobby model;
    private VirtualLobbyView virtualview;

    /**
     * Overview: LobbyController constructor
     */
    public LobbyController(String id){
        this.model = new Lobby(id);
        this.virtualview = new VirtualLobbyView(this.model);
        this.virtualview.setLobbyViewObserver(this);
    }

    @Override
    /**
     * Overview: add a player in the lobby
     */
    public void addPlayer(Player player){
        if(model.getPlayers().size() < 4){
            System.out.println("I'm notifying for the player added to the lobby");
            model.setPlayer(player);
            virtualview.setManager(player.getManager());
            model.notifyObserverPlayerAdded();
            if(model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4){
                if(!model.getReadyToPlay()){
                    model.setReadyToPlay(true);
                    //model.notifyObserverGameCanStart(); è chiamato direttamente dal model, quando il ReadytoPlay viene settato a true
                }
            }
        }
    }

    /**
     * Overview: VirtualView getter
     */
    public VirtualLobbyView getVirtualView(){ return this.virtualview; }

    /**
     * Overview: model getter
     */
    public Lobby getModel(){ return this.model; }
}
