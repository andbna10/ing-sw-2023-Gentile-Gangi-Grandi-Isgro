package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;

public class RepeatTurnMessage extends Message {
    private String message;

    /**
     * Overview: RepeatTurnMessage constructor
     * @param type int specifying the actual type of problem in the played turn procedure
     */
    public RepeatTurnMessage(int type){
        super(null);
        super.setType(MessageType.REPEATTURN);
        switch(type){
            case 0 -> message = "\nSelected tiles are not pickable!";
            case 1 -> message = "\nSelected tiles are pickable but not consecutive!";
            case 2 -> message = "\nYou have not enough room in the bookshelf!";
        }
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }
}
