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
    public LobbyController(ServerManager servermanager, String id){
        this.model = new Lobby(id);
        this.virtualview = new VirtualLobbyView(this.model, servermanager);
        this.virtualview.setLobbyViewObserver(this);
    }

    @Override
    /**
     * Overview: add a player in the lobby
     */
    public void addPlayer(Player player){
        //Player player = new Player(username, isLobbyOwner, idLobby);
        if(model.getPlayers().size() < 4){
            model.setPlayer(player);
            model.notifyObserverPlayerAdded();
            if(model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4){
                if(!model.getReadyToPlay()){
                    model.setReadyToPlay(true);
                    model.notifyObserverGameCanStart();
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
