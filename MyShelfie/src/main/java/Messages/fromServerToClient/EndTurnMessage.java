package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.BoardCell;
import ServerSide.Model.Status;

public class EndTurnMessage extends Message {
    private String message;

    /**
     * Overview: EndTurnMessage constructor
     */
    public EndTurnMessage(BoardCell[][] board, String username){
        super(null);
        super.setType(MessageType.ENDTURN);
        message = "\n"+username+ " has just ended its turn, this is the updated board!";
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return message; }
}
