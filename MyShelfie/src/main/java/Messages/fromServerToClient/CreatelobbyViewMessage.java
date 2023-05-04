package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreatelobbyViewMessage extends Message {
    private ArrayList<String> usernames;
    private String id;

    /**
     * Overview: constructor CreatelobbyViewMessage
     */
    public CreatelobbyViewMessage(String[] usernames, String id){
        super(null);
        super.setType(MessageType.CREATELOBBYVIEW);
        this.usernames = new ArrayList<>();
        this.id = id;
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
}
