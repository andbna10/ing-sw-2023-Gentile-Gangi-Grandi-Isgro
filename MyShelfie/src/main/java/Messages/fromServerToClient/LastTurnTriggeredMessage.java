package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class LastTurnTriggeredMessage extends Message {
    private String message;

    /**
     * Overview: LastTurnTriggeredMessage constructor
     * @param username username of the player who has fulfilled his bookshelf first
     */
    public LastTurnTriggeredMessage(String username){
        super(null);
        super.setType(MessageType.LASTTURNTRIGGERED);
        message = username+" has fulfilled the bookshelf!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
