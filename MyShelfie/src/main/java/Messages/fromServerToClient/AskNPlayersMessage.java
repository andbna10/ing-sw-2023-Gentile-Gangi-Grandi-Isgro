package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class AskNPlayersMessage extends Message {
    private String message;

    /**
     * Overview: Constructor AskNPlayersMessage
     */
    public AskNPlayersMessage(){
        super(null);
        super.setType(MessageType.ASKNPLAYERS);
        this.message = "You are the owner of the lobby, select the number of players!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
