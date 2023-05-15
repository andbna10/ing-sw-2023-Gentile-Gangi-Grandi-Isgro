package ServerSide.VirtualView;
import Messages.fromServerToClient.EndTurnMessage;
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
            manager.sendMessage(message);
        }
    }

    @Override
    /**
     * Overview: method aimed to create a meessage to notify players about the end of a turn
     */
    public void notifytheendofaturn(BoardCell[][] board){
        EndTurnMessage message = new EndTurnMessage(board);
        for(ServerManager manager: this.managers){
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: observer getter
     */
    public GameVViewObserver getObs(){ return this.obs; }
}
