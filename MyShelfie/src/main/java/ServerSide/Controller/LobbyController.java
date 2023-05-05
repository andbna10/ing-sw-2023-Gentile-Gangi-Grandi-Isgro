package ServerSide.Controller;

import ServerSide.Model.Lobby;
import ServerSide.Model.Player;
import ServerSide.VirtualView.LobbyVViewObserver;
import ServerSide.VirtualView.VirtualLobbyView;

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
    public void addPlayer(Player player) throws InterruptedException {
        if(model.getPlayers().size() < 4){
            model.setPlayer(player);
            virtualview.setManager(player.getManager());
            model.notifyObserverPlayerAdded();
            if(model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4){
                if(!model.getReadyToPlay()){
                    System.out.println("ready to play");
                    model.setReadyToPlay(true);
                    Thread.sleep(1000);
                    model.notifyObserverGameCanStart();
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
