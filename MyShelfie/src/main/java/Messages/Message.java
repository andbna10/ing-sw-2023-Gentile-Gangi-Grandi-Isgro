package Messages;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private String sender;
    private MessageType type;

    /**
     * Overview: Message constructor
     */
    public Message(String sender){
        this.sender = sender;
    }

    /**
     * Overview: abstract method
     */
    public MessageType getType(){ return this.type; };

    /**
     * Overview: method aimed to set the type
     */
    public void setType(MessageType type){ this.type = type; }
}
