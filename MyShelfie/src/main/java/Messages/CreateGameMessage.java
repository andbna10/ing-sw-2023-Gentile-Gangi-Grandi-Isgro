package Messages;

public class CreateGameMessage extends Message{
    private String username;
    private MessageType type;

    /**
     * Overview: constructor CreateGameMessage
     */
    public CreateGameMessage(String username, String sender){
        super(sender);
        this.username = username;
        setType(MessageType.CREATEGAME);
    }

    /**
     * Overview: method aimed to set the type of the message
     */
    public void setType(MessageType type){  this.type = type; }
}
