package ClientSide.View;

import ClientSide.NetworkHandler.LobbyViewObserver;

public class LobbyView {
    private LobbyViewObserver obs;

    /**
     * Overview: LobbyView constructor
     */
    public LobbyView(LobbyViewObserver observer){
        obs = observer;
    }
}
