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
        for(Player p: model.getPlayers()){
            this.virtualview.setManager(p.getManager());
        }
    }

    @Override
    /**
     * Overview: add a player in the lobby
     */
    public void addPlayer(Player player){
        if(model.getPlayers().size() < 4){
            model.setPlayer(player);
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
    public VirtualLobbyView getVirtualview(){ return this.virtualview; }

    /**
     * Overview: model getter
     */
    public Lobby getModel(){ return this.model; }
}
