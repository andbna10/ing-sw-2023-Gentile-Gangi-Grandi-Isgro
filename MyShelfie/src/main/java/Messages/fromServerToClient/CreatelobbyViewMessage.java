package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreatelobbyViewMessage extends Message {
    private ArrayList<String> usernames;
    private String id;
    private String owner;
    private Boolean fromEnd;

    /**
     * Overview: constructor CreatelobbyViewMessage
     */
    public CreatelobbyViewMessage(String[] usernames, String id, String owner, Boolean fromEndStatus){
        super(null);
        super.setType(MessageType.CREATELOBBYVIEW);
        this.owner = owner;
        this.usernames = new ArrayList<>();
        this.id = id;
        this.fromEnd = fromEndStatus;
        for(int i=0; i<usernames.length;i++){
            this.usernames.add(usernames[i]);
        }
    }

    /**
     * Overview: username getter
     */
    public ArrayList<String> getUsernames(){ return usernames; }

    /**
     * Overview: id getter
     */
    public String getId(){ return this.id; }

    /**
     * Overview: owner getter
     */
    public String getOwner(){ return this.owner; }

    /**
     * Overview: fromEnd getter
     */
    public Boolean getFromEnd(){ return this.fromEnd; }
}
