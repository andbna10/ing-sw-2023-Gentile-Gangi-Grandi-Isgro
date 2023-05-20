package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class LobbyChangedMessage extends Message {
    private String message;

    /**
     * Overview: LobbyChangedMessage constructor
     */
    public LobbyChangedMessage(){
        super(null);
        super.setType(MessageType.LOBBYSIZECHANGED);
        message = "\nThe size of the lobby has recently changed, it's not possible to start a game right now!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return message; }
}
