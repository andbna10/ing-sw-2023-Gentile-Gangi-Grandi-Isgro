package Server.VirtualView;
import Messages.fromServerToClient.GameHasStartedMessage;
import Server.Model.*;

public class VirtualGameView implements GameObserver{
    private GameVViewObserver obs;

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model){
        model.setGameObserver(this);
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
    public void notifythestartofthegame(){
        GameHasStartedMessage message = new GameHasStartedMessage();
        // salvati i manager di tutti i giocatori che entrano
    }
}
