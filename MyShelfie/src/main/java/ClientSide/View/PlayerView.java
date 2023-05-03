package ClientSide.View;

import ClientSide.NetworkHandler.PlayerViewObserver;

public class PlayerView {
    private PlayerViewObserver obs;

    /**
     * Overview: PlayerView constructor
     */
    public PlayerView(PlayerViewObserver observer){ this.obs = observer; }
}
