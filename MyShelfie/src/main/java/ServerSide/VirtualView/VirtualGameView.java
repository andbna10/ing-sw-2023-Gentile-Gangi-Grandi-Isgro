package ServerSide.VirtualView;
import Messages.fromServerToClient.EndTurnMessage;
import Messages.fromServerToClient.GameHasStartedMessage;
import Networking.ServerManager;
import ServerSide.Model.*;

import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameVViewObserver obs;
    private ArrayList<ServerManager> managers;
    private ArrayList<Player> players; // lo uso solo per gamehasstarted messages, vedere se tenerlo e togliere i managers (i manager si prendono dai player facilmente)

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model, ArrayList<Player> players){
        model.setGameObserver(this);
        this.managers = new ArrayList<>();
        this.players = players;
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
    public void notifythestartofthegame(BoardCell[][] board, int common1, int common2){
        for(ServerManager manager: this.managers) {
            GameHasStartedMessage message = null;
            for(Player p: this.players){
                if(manager == p.getManager()){
                    message = new GameHasStartedMessage(board, p.getGoal().getPersonalGoal().getGameTiles(), common1, common2);
                }
            }
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
