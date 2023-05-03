package ClientSide.NetworkHandler;

import ClientSide.View.GameView;
import Networking.ClientManager;

public class GameHandler implements GameViewObserver{
    private ClientManager manager;
    private String message;
    private GameView view;

    /**
     * Overview: GameHandler constructor
     */
    public GameHandler(ClientManager manager, String message){
        this.manager = manager;
        this.message = message;
        this.view = new GameView(this);
    }

}
