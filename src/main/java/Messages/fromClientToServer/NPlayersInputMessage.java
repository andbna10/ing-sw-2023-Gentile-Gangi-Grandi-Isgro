package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class NPlayersInputMessage extends Message {
    private int n;

    /**
     * Overview: NPlayersInputMessage constructor
     * @param n number of players for a game
     * @param sender message sender
     */
    public NPlayersInputMessage(int n, String sender){
        super(sender);
        super.setType(MessageType.NPLAYERSINPUT);
        this.n = n;
    }

    /**
     * Overview: n getter
     */
    public int getN(){ return this.n; }
}
