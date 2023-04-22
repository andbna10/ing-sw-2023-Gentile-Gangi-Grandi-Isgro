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
    public LobbyController(ServerManager servermanager){
        this.model = new Lobby();
        this.virtualview = new VirtualLobbyView(this.model, servermanager);
        this.virtualview.setLobbyViewObserver(this);
    }

    @Override
    /**
     * Overview: add a player in the lobby
     */
    public void addPlayer(String username){
        Player player = new Player(username);
        if(model.getPlayers().size() < 4){
            model.setPlayer(player);
            if(model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4){
                if(!model.getReadyToPlay()){
                    model.setReadyToPlay(true);
                }
            }
        }
    }

    /**
     * Overview: VirtualView getter
     */
    public VirtualLobbyView getVirtualview(){ return this.virtualview; }
}
