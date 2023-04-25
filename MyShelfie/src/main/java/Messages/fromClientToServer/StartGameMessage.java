package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class StartGameMessage extends Message {
    private String idLobby;

    /**
     * Overview: StartGameMessage constructor
     */
    public StartGameMessage(String sender, String id){
        super(sender);
        super.setType(MessageType.STARTGAME);
        this.idLobby = id;
    }

    /**
     * Overview: idLobby getter
     */
    public String getIdLobby(){ return idLobby; }
}
