package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class GameCanStartMessage extends Message {

    /**
     * Overview GameCanStartMessage construcotr
     */
    public GameCanStartMessage(){
        super(null);
        super.setType(MessageType.GAMECANSTART);
    }
}
