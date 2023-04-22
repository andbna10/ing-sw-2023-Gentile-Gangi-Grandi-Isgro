package Server.VirtualView;

import java.net.Socket;

import Server.Model.*;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerVViewObserver obs;
    private Socket socket;

    /**
     * Overview: constructor of VirtualPlayerView
     */
    public VirtualPlayerView(Player model){
        model.setPlayerObserver(this);
    }

    /**
     * Overview: method aimed to set the VirtualPlayerView
     */
    public void setPlayerViewObserver(PlayerVViewObserver observer){ this.obs = observer; }
}
