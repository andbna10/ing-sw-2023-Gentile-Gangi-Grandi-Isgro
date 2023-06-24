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
     * @param discon boolean handling disconnection
     * @param isOwner boolean specifying the player whether is owner of the lobby ir not
     * @param winner winner usename
     * @param players ArrayList of the Player objects
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
     * Overview: print usernams with points at the end of the game
     * @param players ArrayList of the Player objects
     * @return StringBuffer containing players' username and relative points at the end of the game
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
