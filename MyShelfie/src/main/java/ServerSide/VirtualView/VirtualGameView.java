package ServerSide.VirtualView;
import Messages.fromServerToClient.GameHasStartedMessage;
import Networking.ServerManager;
import ServerSide.Model.*;

import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameVViewObserver obs;
    private ArrayList<ServerManager> managers;

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model){
        System.out.println("entrato in VVGame");
        model.setGameObserver(this);
        this.managers = new ArrayList<>();
    }

    /**
     * Overview: method aimed to add a server manager
     */
    public void setManager(ServerManager manager){ this.managers.add(manager); }

    /**
     * Overview: method aimed to add a VirtualGameView observer
     */
    public void setGameViewObserver(GameVViewObserver observer){
        obs = observer;
    }

    @Override
    /**
     * Overview: method aimed to create the message to notify the start of the game
     */
    public void notifythestartofthegame(BoardCell[][] board){
        GameHasStartedMessage message = new GameHasStartedMessage(board);
        for(ServerManager manager: this.managers) {
            manager.setIsMessage(true);
            manager.setMessage(message);
        }
    }
}
