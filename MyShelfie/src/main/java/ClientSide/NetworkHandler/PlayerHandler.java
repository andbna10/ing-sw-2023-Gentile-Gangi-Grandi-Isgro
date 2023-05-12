package ClientSide.NetworkHandler;

import ClientSide.Cli.PlayerCLI;
import ClientSide.View.PlayerView;
import Networking.ClientManager;

public class PlayerHandler implements PlayerViewObserver{
    private ClientManager manager;
    private PlayerView view;
    private PlayerCLI cli;

    // attributes

    /**
     * Overview: PlayerHandler constructor
     */
    public PlayerHandler(ClientManager manager){
        this.manager = manager;
        this.view = new PlayerView(this);
        this.cli = new PlayerCLI(this);
    }

    /**
     * Overview: clio getter
     */
    public PlayerCLI getCli(){ return cli; }
}
