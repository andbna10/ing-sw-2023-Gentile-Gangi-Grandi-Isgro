package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class SendDisconMessage extends Message {

    private Boolean status;

    /**
     * Overview: constructor CreateGameMessage
     * @param arg boolean specifying the status of the disconnection
     */
    public SendDisconMessage(Boolean arg){
        super(null);
        super.setType(MessageType.SENDDISCON);
        this.status = arg;
    }

    /**
     * Overview: status attribute getter
     */
    public Boolean getStatus(){return this.status;}

}
