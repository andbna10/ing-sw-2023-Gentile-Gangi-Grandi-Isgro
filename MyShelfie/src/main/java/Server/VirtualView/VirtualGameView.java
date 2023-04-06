package Server.VirtualView;
import Server.Model.*;
import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameViewObserver obs;

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model){
        model.setGameObserver(this);
    }

    /**
     * Overview: method aimed to add a VirtualGameView observer
     */
    public void setGameViewObserver(GameViewObserver observer){
        obs = observer;
    }
}
