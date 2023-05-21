package ClientSide.NetworkHandler;

import ClientSide.View.CLI.PlayerCLI;
import ClientSide.View.GUI.GameGUI;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

public class PlayerHandler implements PlayerViewObserver{
    private ClientManager manager;
    private ClientManagerGUI managergui;
    private GameGUI gui;
    private PlayerCLI cli;

    // attributes

    /**
     * Overview: gui setter
     */
    public void setGui(GameGUI gui) {
        this.gui = gui;
    }

    /**
     * Overview: PlayerHandler constructor1 cli
     */
    public PlayerHandler(ClientManager manager){
        this.manager = manager;
        //this.view = new PlayerView(this);
        this.cli = new PlayerCLI(this);
    }

    /**
     * Overview: PlayerHandler constructor2 gui
     */
    public PlayerHandler(ClientManagerGUI manager){
        this.managergui = manager;
        //this.view = new PlayerView(this);
    }

    /**
     * Overview: clio getter
     */
    public PlayerCLI getCli(){ return cli; }
}
