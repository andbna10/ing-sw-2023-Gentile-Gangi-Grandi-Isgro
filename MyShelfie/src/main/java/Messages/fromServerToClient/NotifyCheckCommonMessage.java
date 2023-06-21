package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.ScoringToken;

public class NotifyCheckCommonMessage extends Message {
    private String message;
    private int newTokenPoints;
    private int common;

    /**
     * Overview: NotifyCheckCommonMessage constructor
     * @param username username of the player who has accomplished a common goal
     * @param token ScoringToken placed in the new first position of the stack associated to the common goal card
     * @param common int specifying the index of the common
     * @param points int specifying the points the player has obtained
     */
    public NotifyCheckCommonMessage(int common, int points, String username, ScoringToken token){
        super(null);
        super.setType(MessageType.NOTIFYCHECKCOMMON);
        this.newTokenPoints = token.getPoints();
        this.common = common;
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

    /**
     * Overview: get common
     */
    public int getCommon(){ return this.common; }
}
