package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class GameHasStartedMessage extends Message {
    private String message;

    /**
     * Overview: GameHasStartedMessage constructor
     */
    public GameHasStartedMessage(){
        super(null);
        super.setType(MessageType.GAMEHASSTARTED);
        message = "game has started!";
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return message; }
}
