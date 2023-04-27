package Client.View;

import Client.NetworkHandler.LobbyViewObserver;

public class LobbyView {
    private LobbyViewObserver obs;

    /**
     * Overview: LobbyView constructor
     */
    public LobbyView(LobbyViewObserver observer){
        obs = observer;
    }
}
