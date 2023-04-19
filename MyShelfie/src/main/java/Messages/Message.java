package Messages;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private String sender;

    /**
     * Message constructor
     */
    public Message(String sender){
        this.sender = sender;
    }
}
