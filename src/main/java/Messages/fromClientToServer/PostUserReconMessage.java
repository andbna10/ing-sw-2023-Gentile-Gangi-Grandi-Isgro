package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class PostUserReconMessage extends Message {

    private String user;

    /**
     * Overview: constructor PostUserReconMessage
     * @param arg player's username
     */
    public PostUserReconMessage(String arg){
        super(null);
        super.setType(MessageType.POSTUSERRECON);
        this.user = arg;
    }


    /**
     * Overview: user attribute Getter
     */
    public String getUser() {return this.user;}
}
