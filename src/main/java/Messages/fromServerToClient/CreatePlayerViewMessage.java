package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class CreatePlayerViewMessage extends Message {

    /**
     * Overview: constructor CreatePlayerViewMessage
     */
    public CreatePlayerViewMessage(){
        super(null);
        super.setType(MessageType.CREATEPLAYERVIEW);
    }

}
