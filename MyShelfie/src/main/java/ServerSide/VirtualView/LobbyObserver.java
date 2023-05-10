package ServerSide.VirtualView;

import ServerSide.Model.Player;

public interface LobbyObserver {

    // questi messaggi devono essere mandati a tutti i player, quindi a tutti i manager
    void updatelobbyviewmessage(String[] usernames, String id, String owner);

    void gamecanstartmessage() throws InterruptedException;
}
