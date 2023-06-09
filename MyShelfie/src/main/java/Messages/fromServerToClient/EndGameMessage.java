package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.Player;

import java.util.ArrayList;

public class EndGameMessage extends Message {

    boolean discon;
    private String message;
    private StringBuffer output;
    private Boolean isOwner;

    /**
     * Overview: EndGameMessage constructor
     */
    public EndGameMessage(String winner, ArrayList<Player> players, boolean discon, Boolean isOwner){
        super(null);
        super.setType(MessageType.ENDGAME);
        this.discon = discon;
        this.isOwner = isOwner;
        output = print(players);
        message = "\nThe winner is "+winner;
    }

    /**
     * Overview: print usernams, points
     */
    public StringBuffer print(ArrayList<Player> players){
        StringBuffer output = new StringBuffer();
        for(Player p: players){
            output.append(p.getUsername()).append(": ").append(p.getPoints()).append("\n");
        }
        return output;
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return message; }

    /**
     * Overview: get the output
     */
    public StringBuffer getOutput(){ return output; }

    /**
     * Overview: discon state getter
     */
    public boolean getDiscon(){ return this.discon; }

    /**
     * Overview: isOwner getter
     */
    public Boolean getIsOwner(){ return this.isOwner; }
}
