package Server.VirtualView;

import java.util.ArrayList;
import Server.Model.*;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerViewObserver obs;

    /**
     * Overview: constructor of VirtualPlayerView
     */
    public VirtualPlayerView(Player model){
        model.setPlayerObserver(this);
    }

    /**
     * Overview: method aimed to set the VirtualPlayerView
     */
    public void setPlayerViewObserver(PlayerViewObserver observer){ this.obs = observer; }
}
