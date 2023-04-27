package Server.VirtualView;

import java.net.Socket;

import Messages.fromServerToClient.CreatePlayerViewMessage;
import Networking.ServerManager;
import Server.Model.*;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerVViewObserver obs;
    private ServerManager manager;

    /**
     * Overview: VirtualPlayerView constructor
     */
    public VirtualPlayerView(ServerManager manager){
        this.manager = manager;
    }

    /**
     * Overview: method aimed to set the VirtualPlayerView
     */
    public void setPlayerViewObserver(PlayerVViewObserver observer){ this.obs = observer; }

    @Override
    /**
     * Overview: method aimed to create the createplayerviewmessage
     */
    public void createplayerviewmessage(){
        CreatePlayerViewMessage message = new CreatePlayerViewMessage();
        manager.setIsMessage(true);
        manager.setMessage(message);
    }
}
