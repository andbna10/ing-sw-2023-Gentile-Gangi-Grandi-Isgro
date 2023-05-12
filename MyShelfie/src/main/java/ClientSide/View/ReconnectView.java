package ClientSide.View;

import ClientSide.NetworkHandler.ReconnectViewObserver;

public class ReconnectView {

    private ReconnectViewObserver obs;

    public ReconnectView(ReconnectViewObserver observer) {
        this.obs = observer;
    }
}
