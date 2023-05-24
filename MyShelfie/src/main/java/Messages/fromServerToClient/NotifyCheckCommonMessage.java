package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.ScoringToken;

public class NotifyCheckCommonMessage extends Message {
    private String message;
    private int newTokenPoints;

    /**
     * Overview: NotifyCheckMessage constructor
     */
    public NotifyCheckCommonMessage(int common, int points, String username, ScoringToken token){
        super(null);
        super.setType(MessageType.NOTIFYCHECKCOMMON);
        this.newTokenPoints = token.getPoints();
        message = username+ " has accomplished common "+common+", obtaining "+points+" points!";
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return message; }

    /**
     * Overview: get newTokenPoints
     */
    public int getNewTokenPoints(){ return this.newTokenPoints; }
}
