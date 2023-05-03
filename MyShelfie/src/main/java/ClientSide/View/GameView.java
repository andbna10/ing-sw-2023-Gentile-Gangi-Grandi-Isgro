package ClientSide.View;

import ClientSide.NetworkHandler.GameViewObserver;

public class GameView {
    private GameViewObserver obs;

    /**
     * Overview: GameView observer
     */
    public GameView(GameViewObserver observer){ obs = observer; }
}
