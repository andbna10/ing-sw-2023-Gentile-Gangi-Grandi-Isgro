package Messages.fromClientToServer;
import Messages.Message;
import Messages.MessageType;

public class EnterGameMessage extends Message {
    private String username;
    private String id;

    /**
     * Overview: EnterGameMessageconstructor
     */
    public EnterGameMessage(String username, String sender, String id){
        super(sender);
        super.setType(MessageType.ENTERGAME);
        this.username = username;
        this.id = id;
    }

    /**
     * Overview: username getter
     */
    public String getUsername(){ return username; }

    /**
     * Overview: id getter
     */
    public String getId(){ return id; }
}