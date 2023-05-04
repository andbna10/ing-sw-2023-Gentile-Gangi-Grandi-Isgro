package ServerSide.VirtualView;

public interface LobbyObserver {

    // questi messaggi devono essere mandati a tutti i player, quindi a tutti i manager
    void updatelobbyviewmessage(String[] usernames, String id);

    void gamecanstartmessage();
}
