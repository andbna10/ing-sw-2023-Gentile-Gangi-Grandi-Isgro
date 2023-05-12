package ClientSide.NetworkHandler;

import ClientSide.Cli.GameCLI;
import ClientSide.View.GameView;
import Networking.ClientManager;

public class GameHandler implements GameViewObserver{
    private ClientManager manager;
    private String message;
    private GameView view;

    private GameCLI cli;

    /**
     * Overview: GameHandler constructor
     */
    public GameHandler(ClientManager manager, String message){
        this.manager = manager;
        this.message = message;
        this.view = new GameView(this);
        this.cli = new GameCLI(this);

    }

    /**
     * Overwiew: cli getter
     */
    public GameCLI getCli(){
        return cli;
    }

}
