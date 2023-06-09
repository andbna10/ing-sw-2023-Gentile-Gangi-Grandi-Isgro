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
        Boolean added = false;
        // verficare anche che i giocatori della lobby non siano in partita prima di aggiungere un altro giocatore
        if(model.getPlayers().size() < 4){
            if(model.getId() == "random"){
                if(model.getPlayers().size() == 0){
                    player.updateLobby(true, "random");
                    model.setPlayer(player);
                    added = true;
                    virtualview.setManager(player.getManager());
                    Thread.sleep(1000);
                    model.notifyObserverPlayerAdded(model.getId(), false);
                    // here we ask the owner of the lobby for the length of the lobby itself
                    Thread.sleep(1000);
                    player.getObs().createasknplayersmessage();
                }
            }
            if(!added) {
                model.setPlayer(player);
                virtualview.setManager(player.getManager());
                model.notifyObserverPlayerAdded(model.getId(), false);
            }

            if(model.getId() == "random"){
                if (model.getPlayers().size() == model.getFixedNPlayers()){
                    model.setReadyToPlay(true);
                    // get the owner and notify
                    Thread.sleep(1000);
                    notifyOwner();
                }
            } else {
                if (model.getPlayers().size() >= 2 && model.getPlayers().size() <= 4) {
                    if (!model.getReadyToPlay()) {
                        model.setReadyToPlay(true);
                        // get the owner and notify
                        Thread.sleep(1000);
                        notifyOwner();
                    }
                }
            }
        }
    }

    @Override
    /**
     * Overview: notify owner game can start
     */
    public void notifyOwner(){
        for(Player client: model.getPlayers()){
            if(client.getLobby().get(model.getId())){
                client.notifyOwner(model.getId());
            }
        }
    }


    @Override
    /**
     * Overview: modify the fixed number of players for the random lobby
     */
    public void modifyfixednplayers(int n){
        model.setFixedNPlayers(n);
    }



    /**
     * Overview: VirtualView getter
     */
    public VirtualLobbyView getVirtualView(){ return this.virtualview; }

    @Override
    /**
     * Overview: model getter
     */
    public Lobby getModel(){ return this.model; }


}
