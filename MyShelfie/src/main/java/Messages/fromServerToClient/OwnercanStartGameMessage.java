package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class OwnercanStartGameMessage extends Message {

    /**
     * Overview: OwnercanStartGameMessage constructor
     */
    public OwnercanStartGameMessage(){
        super(null);
        super.setType(MessageType.OWNERCANSTARTGAME);
    }
}
