package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class BackToTheLobbyMessage extends Message {

    /**
     * Overview: constructor BackToTheLobbyMessage
     */
    public BackToTheLobbyMessage(String sender){
        super(sender);
        super.setType(MessageType.BACKTOTHELOBBY);
    }
}
