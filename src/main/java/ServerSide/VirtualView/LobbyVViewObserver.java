package ServerSide.VirtualView;

import ServerSide.Model.Lobby;
import ServerSide.Model.Player;

public interface LobbyVViewObserver {
    /**
     * Overview: add a player in the lobby
     */
    void addPlayer(Player player) throws InterruptedException;

    /**
     * Overview: modify the fixed number of players for the random lobby
     */
    void modifyfixednplayers(int n);

    /**
     * Overview: model getter
     */
    Lobby getModel();

    /**
     * Overview: notify owner game can start
     */
    void notifyOwner();
}
