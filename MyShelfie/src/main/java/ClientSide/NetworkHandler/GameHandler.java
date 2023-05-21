package ClientSide.NetworkHandler;

import ClientSide.View.CLI.GameCLI;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

public class GameHandler implements GameViewObserver{
    private ClientManager manager;
    private ClientManagerGUI managergui;
    private String message;
    //private GameView view;

    private GameCLI cli;

    /**
     * Overview: GameHandler constructor1 cli
     */
    public GameHandler(ClientManager manager, String message){
        this.manager = manager;
        this.message = message;
        //this.view = new GameView(this);
        this.cli = new GameCLI(this);
    }

    /**
     * Overview: GameHandler constructor2 gui
     */
    public GameHandler(ClientManagerGUI manager, String message){
        this.managergui = manager;
        this.message = message;
        //this.view = new GameView(this);
    }

    /**
     * Overwiew: cli getter
     */
    public GameCLI getCli(){
        return cli;
    }

}
