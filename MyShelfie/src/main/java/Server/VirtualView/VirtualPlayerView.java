package Server.VirtualView;

import java.net.Socket;

import Server.Model.*;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerVViewObserver obs;

    /**
     * Overview: method aimed to set the VirtualPlayerView
     */
    public void setPlayerViewObserver(PlayerVViewObserver observer){ this.obs = observer; }
}
