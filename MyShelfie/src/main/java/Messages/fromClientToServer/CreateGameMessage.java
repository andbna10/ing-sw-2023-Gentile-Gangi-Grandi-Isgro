package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class CreateGameMessage extends Message {
    private String username;

    /**
     * Overview: constructor CreateGameMessage
     */
    public CreateGameMessage(String username, String sender){
        super(sender);
        super.setType(MessageType.CREATEGAME);
        this.username = username;
    }

    /**
     * Overview: username getter
     */
    public String getUsername(){ return username; }

}
