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

    // QUESTO METODO VA RESO PIU BELLO
    @Override
    /**
     * Overview: add a player in the lobby
     */
    public void addPlayer(Player player) throws InterruptedException {
        Boolean added = false;
        if(model.getPlayers().size() < 4){
            if(model.getId() == "online"){
                if(model.getPlayers().size() == 0){
                    player.updateLobby(true, "online");
                    model.setPlayer(player);
                    added = true;
                    virtualview.setManager(player.getManager());
                    Thread.sleep(1000);
                    model.notifyObserverPlayerAdded(model.getId());
                    // here we ask the owner of the lobby for the length of the lobby itself
                    Thread.sleep(1000);
                    player.getObs().createasknplayersmessage();
                }
            }
            if(!added) {
                model.setPlayer(player);
                virtualview.setManager(player.getManager());
                model.notifyObserverPlayerAdded(model.getId());
            }

            if(model.getId() == "online"){
                if (model.getPlayers().size() == model.getFixedNPlayers()){
                    model.setReadyToPlay(true);
                    Thread.sleep(1000);
                    model.notifyObserverGameCanStart();
                    Thread.sleep(1000);
                    // get the owner and notify
                    for(Player client: model.getPlayers()){
                        if(client.getLobby().get(model.getId())){
                            Thread.sleep(1000);
                            client.notifyOwner();
                        }
                    }
                }
            } else {
                if (model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4) {
                    if (!model.getReadyToPlay()) {
                        model.setReadyToPlay(true);
                        Thread.sleep(1000);
                        model.notifyObserverGameCanStart();
                        // get the owner and notify
                        for(Player client: model.getPlayers()){
                            if(client.getLobby().get(model.getId())){
                                Thread.sleep(1000);
                                client.notifyOwner();
                            }
                        }
                    }
                }
            }



        }
    }

    @Override
    /**
     * Overview: modify the fixed number of players for the online lobby
     */
    public void modifyfixednplayers(int n){
        model.setFixedNPlayers(n);
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
