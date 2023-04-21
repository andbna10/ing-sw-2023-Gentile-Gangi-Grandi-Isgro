package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class CreatelobbyViewMessage extends Message {
    private String username;

    /**
     * Overview: constructor CreatelobbyViewMessage
     */
    public CreatelobbyViewMessage(String username){
        super(null);
        super.setType(MessageType.CREATELOBBYVIEW);
        this.username = username;
    }

    /**
     * Overview: username getter
     */
    public String getUsername(){ return username; }
}
