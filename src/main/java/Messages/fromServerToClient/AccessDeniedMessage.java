package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class AccessDeniedMessage extends Message {
    private String message;

    /**
     * Overview: AccessDeniedMessage constructor
     * @param x switch int for different access denied occurred
     */
    public AccessDeniedMessage(int x){
        super(null);
        super.setType(MessageType.ACCESSDENIED);
        switch (x){
            case 0 -> this.message = "There is no lobby with that id, try again!";
            case 1 -> this.message = "Players in this lobby are already in a game, try later or insert another lobby id!";
        }

    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
