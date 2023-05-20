package ServerSide.VirtualView;

import ServerSide.Model.Player;

public interface LobbyObserver {

    /**
     * Overview: Notify players of the lobby
     */
    void updatelobbyviewmessage(String[] usernames, String id, String owner);
}
