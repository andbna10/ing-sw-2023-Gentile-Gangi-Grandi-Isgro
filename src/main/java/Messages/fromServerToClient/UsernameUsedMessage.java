package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class UsernameUsedMessage extends Message {
    private String message;

    /**
     * Overview: UsernameUsedMessage constructor
     */
    public UsernameUsedMessage(){
        super(null);
        super.setType(MessageType.USERNAMEUSED);
        this.message = "This username is already in use";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
