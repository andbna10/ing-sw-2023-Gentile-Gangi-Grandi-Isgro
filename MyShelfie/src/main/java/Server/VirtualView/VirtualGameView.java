package Server.VirtualView;
import Messages.fromServerToClient.GameHasStartedMessage;
import Networking.ServerManager;
import Server.Model.*;

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
    public void notifythestartofthegame(){
        GameHasStartedMessage message = new GameHasStartedMessage();
        /*for(Player p: this.players){
            p.getManager().setGameView(this);
            p.getManager().setIsMessage(true);
            p.getManager().setMessage(message);
        }*/
    }
}
