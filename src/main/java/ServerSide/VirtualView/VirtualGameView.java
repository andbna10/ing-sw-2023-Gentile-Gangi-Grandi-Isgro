package ServerSide.VirtualView;
import Messages.fromServerToClient.*;
import Networking.ServerManager;
import ServerSide.Model.*;

import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameVViewObserver obs;
    private ArrayList<ServerManager> managers;
    private ArrayList<Player> players;

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
     * Overview: remove manager if players disconnects
     */
    public void removemanager(ServerManager manager){
        for(int i=0; i< managers.size();i++){
            if(this.managers.get(i) == manager){
                this.managers.remove(i);
                break;
            }
        }
        for(Player p: players){
            if(p.getManager() == manager){
                players.remove(p);
            }
        }
    }

    /**
     * Overview: method aimed to add a VirtualGameView observer
     */
    public void setGameViewObserver(GameVViewObserver observer){
        obs = observer;
    }

    /**
     * Overview: method aimed to create the message to notify the start of the game
     */
    @Override
    public void notifythestartofthegame(int numPlayers, BoardCell[][] board, int common1, int common2, ScoringToken one, ScoringToken two){
        for(ServerManager manager: this.managers) {
            GameHasStartedMessage message = null;
            for(Player p: this.players){
                if(manager == p.getManager()){
                    message = new GameHasStartedMessage(numPlayers, board, p.getGoal().getPersonalGoal().getGameTiles(), common1, common2, one, two, p.getGoal().getPatternNumber());
                }
            }
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: method aimed to create the message to notify the end of the game
     */
    @Override
    public void notifyEnd(String winner, ArrayList<Player> players, boolean discon, String id){
        for(ServerManager manager: this.managers) {
            Boolean flag = false;
            for(Player p: players){
                if(p.getManager() == manager) {
                        if (p.getLobby().get(id)) {
                            EndGameMessage message = new EndGameMessage(winner, players, discon, true);
                            manager.sendMessage(message);
                            flag = true;
                        }
                }
            }
            if(!flag){
                EndGameMessage message = new EndGameMessage(winner, players, discon, false);
                manager.sendMessage(message);
            }
        }
    }

    /**
     * Overview: method aimed to create a meessage to notify players about the end of a turn
     */
    @Override
    public void notifytheendofaturn(BoardCell[][] board, String username){
        EndTurnMessage message = new EndTurnMessage(board, username);
        for(ServerManager manager: this.managers){
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: method aimed to create a message to notify players that a common has been accomplished
     */
    @Override
    public void notifyObserverCommon(int common, int newPoints, String username, ScoringToken token){
        NotifyCheckCommonMessage message = new NotifyCheckCommonMessage(common, newPoints, username, token);
        for(ServerManager manager: this.managers){
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: method aimed to create a message to notify players about the last turn triggered
     */
    @Override
    public void notifyObserverLastTurn(String username){
        LastTurnTriggeredMessage message = new LastTurnTriggeredMessage(username);
        for(ServerManager manager: this.managers){
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: observer getter
     */
    public GameVViewObserver getObs(){ return this.obs; }
}
