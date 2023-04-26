package Server.VirtualView;

import Server.Model.Player;

public interface LobbyVViewObserver {
    /**
     * Overview: add a player in the lobby
     */
    void addPlayer(Player player);
}
