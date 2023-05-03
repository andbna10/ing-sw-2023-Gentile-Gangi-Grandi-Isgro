package ClientSide.NetworkHandler;

import ClientSide.View.PlayerView;
import Networking.ClientManager;

public class PlayerHandler implements PlayerViewObserver{
    private ClientManager manager;
    private PlayerView view;

    // attributes

    /**
     * Overview: PlayerHandler constructor
     */
    public PlayerHandler(ClientManager manager){
        this.manager = manager;
        this.view = new PlayerView(this);
    }
}
