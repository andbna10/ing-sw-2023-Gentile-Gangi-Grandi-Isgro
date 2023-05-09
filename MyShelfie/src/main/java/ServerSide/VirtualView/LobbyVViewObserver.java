package ServerSide.VirtualView;

import ServerSide.Model.Player;

public interface LobbyVViewObserver {
    /**
     * Overview: add a player in the lobby
     */
    void addPlayer(Player player) throws InterruptedException;

    /**
     * Overview: modify the fixed number of players for the online lobby
     */
    void modifyfixednplayers(int n);
}
