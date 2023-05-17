package Messages;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private String sender;
    private MessageType type;

    private boolean last = false;

    /**
     * Overview: Message constructor
     */
    public Message(String sender){
        this.sender = sender;
    }

    /**
     * Overview: type getter
     */
    public MessageType getType(){ return this.type; };

    /**
     * Overview: method aimed to set the type
     */
    public void setType(MessageType type){ this.type = type; }

    /**
     * Overview: last setter
     */
    public void setLast() {
        this.last = true;
    }

    /**
     * Overview: last getter
     */
    public boolean getLast() {
        return this.last;
    }
}


