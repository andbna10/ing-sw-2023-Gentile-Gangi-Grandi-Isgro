package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class NotifyCheckCommonMessage extends Message {
    private String message;

    /**
     * Overview: NotifyCheckMessage constructor
     */
    public NotifyCheckCommonMessage(int common, int points, String username){
        super(null);
        super.setType(MessageType.NOTIFYCHECKCOMMON);
        message = username+ " has accomplished common "+common+", obtaining "+points+" points!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return message; }
}
