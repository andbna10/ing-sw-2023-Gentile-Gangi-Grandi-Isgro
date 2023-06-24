package Messages;

import Messages.Message;
import Messages.MessageType;

public class PingMessage extends Message {
    private String message;

    /**
     * Overview: constructor Messages.PingMessage
     */
    public PingMessage(String message, String sender){
        super(sender);
        super.setType(MessageType.PING);
        this.message = message;
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return this.message; }
}
