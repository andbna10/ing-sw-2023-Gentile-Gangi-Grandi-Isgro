package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class AccessDeniedMessage extends Message {
    private String message;

    /**
     * Overview: AccessDeniedMessage constructor
     */
    public AccessDeniedMessage(){
        super(null);
        super.setType(MessageType.ACCESSDENIED);
        this.message = "Players in this lobby are already in a game, try later or insert another lobby id!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
