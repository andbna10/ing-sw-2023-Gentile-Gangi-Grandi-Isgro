package Server.VirtualView;
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
}
