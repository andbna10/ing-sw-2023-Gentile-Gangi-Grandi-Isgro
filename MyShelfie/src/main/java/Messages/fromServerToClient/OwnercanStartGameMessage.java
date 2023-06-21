package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class OwnercanStartGameMessage extends Message {
    private String id;

    /**
     * Overview: id getter
     */
    public String getId() {
        return id;
    }

    /**
     * Overview: OwnercanStartGameMessage constructor
     * @param id lobby id
     */
    public OwnercanStartGameMessage(String id){
        super(null);
        super.setType(MessageType.OWNERCANSTARTGAME);
        this.id = id;
    }
}
