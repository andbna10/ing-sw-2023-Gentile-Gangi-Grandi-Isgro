package Server.VirtualView;
import Server.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class VirtualGameView implements GameObserver{
    private GameViewObserver obs;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    //private boolean connected;

    /**
     * Overview: constructor of the virtual game view
     */
    public VirtualGameView(Game model, Socket socket){
        this.socket = socket;
        model.setGameObserver(this);
        try{
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }

        // start hearthbeat ???
    }

    /**
     * Overview: method aimed to add a VirtualGameView observer
     */
    public void setGameViewObserver(GameViewObserver observer){
        obs = observer;
    }
}
