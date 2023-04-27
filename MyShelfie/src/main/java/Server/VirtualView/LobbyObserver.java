package Server.VirtualView;

import java.util.ArrayList;

public interface LobbyObserver {

    // questi messaggi devono essere mandati a tutti i player, quindi a tutti i manager
    void updatelobbyviewmessage(String[] usernames);

    void gamecanstartmessage();
}
