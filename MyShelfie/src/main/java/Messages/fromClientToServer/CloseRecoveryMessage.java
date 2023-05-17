package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class CloseRecoveryMessage extends Message {


    /**
     * Overview: constructor
     */
    public CloseRecoveryMessage() {
        super("");
        super.setType(MessageType.CLOSERECOVERY);
    }

}
