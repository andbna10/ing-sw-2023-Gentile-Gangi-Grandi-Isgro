package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

import java.util.ArrayList;

public class ReconnectedMessage extends Message {

    private Boolean status;

    private ArrayList<String> usernames;

    /**
     * Overview: constructor CreateGameMessage
     */
    public ReconnectedMessage(String[] usernames){
        super(null);
        this.usernames = new ArrayList<>();
        super.setType(MessageType.RECONNECTED);

        for(String s : usernames)
            this.usernames.add(s);

    }

    /**
     * Overview: usernames getter
     */
    public ArrayList<String> getUsernames(){
        return this.usernames;
    }


}
