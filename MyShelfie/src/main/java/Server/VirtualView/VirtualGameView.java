package Server.VirtualView;
import Messages.fromServerToClient.GameHasStartedMessage;
import Server.Model.*;

import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameVViewObserver obs;
    private ArrayList<Player> players;

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model, ArrayList<Player> players){
        model.setGameObserver(this);
        this.players = new ArrayList<>();
        this.players = players;
    }

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
        for(Player p: this.players){
            p.getManager().setGameView(this);
            p.getManager().setIsMessage(true);
            p.getManager().setMessage(message);
        }
    }
}
