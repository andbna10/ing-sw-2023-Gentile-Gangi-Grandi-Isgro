package Server.VirtualView;
import Server.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
