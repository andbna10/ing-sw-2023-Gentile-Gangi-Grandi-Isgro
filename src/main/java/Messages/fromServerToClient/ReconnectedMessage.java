package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

import java.util.ArrayList;
import java.util.Arrays;

public class ReconnectedMessage extends Message {
    private Boolean status;

    private ArrayList<String> usernames;

    /**
     * Overview: constructor CreateGameMessage
     * @param usernames array of the players' username
     */
    public ReconnectedMessage(String[] usernames){
        super(null);
        this.usernames = new ArrayList<>();
        super.setType(MessageType.RECONNECTED);

        this.usernames.addAll(Arrays.asList(usernames));

    }

    /**
     * Overview: usernames getter
     */
    public ArrayList<String> getUsernames(){
        return this.usernames;
    }


}
