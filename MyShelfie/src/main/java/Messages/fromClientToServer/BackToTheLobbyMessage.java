package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class BackToTheLobbyMessage extends Message {

    /**
     * Overview: constructor BackToTheLobbyMessage
     * @param sender message sender
     */
    public BackToTheLobbyMessage(String sender){
        super(sender);
        super.setType(MessageType.BACKTOTHELOBBY);
    }
}
