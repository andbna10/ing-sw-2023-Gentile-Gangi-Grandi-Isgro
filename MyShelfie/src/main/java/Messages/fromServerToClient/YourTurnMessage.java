package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class YourTurnMessage extends Message {
    private String message;

    /**
     * Overview: YourTurnMessage constructor
     */
    public YourTurnMessage(){
        super(null);
        super.setType(MessageType.YOURTURN);
        this.message = "It's your turn";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
