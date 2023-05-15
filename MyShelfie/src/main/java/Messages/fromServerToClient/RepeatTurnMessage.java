package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class RepeatTurnMessage extends Message {
    private String message;

    /**
     * Overview: RepeatTurnMessage constructor
     */
    public RepeatTurnMessage(){
        super(null);
        super.setType(MessageType.REPEATTURN);
        this.message = "\nPlease, try again.\nRemember that you can pick only tiles which are pickable from the start of the turn and belonging to the same line.\nMoreover check your Bookshelf floor.";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
