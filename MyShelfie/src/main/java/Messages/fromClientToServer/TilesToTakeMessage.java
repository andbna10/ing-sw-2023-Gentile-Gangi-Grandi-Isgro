package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class TilesToTakeMessage extends Message {
    private int[] toTake;
    private int[] order;
    private int column;

    /**
     * Overvuew: TilesToTakeMessage constructor
     */
    public TilesToTakeMessage(int[] toTake, int[] order, int column, String sender){
        super(sender);
        super.setType(MessageType.TILESTOTAKE);
        this.toTake=toTake;
        this.order=order;
        this.column=column;
    }

    /**
     * Overview: toTake getter
     */
    public int[] getToTake() {return toTake;}

    /**
     * Overview: order getter
     */
    public int[] getOrder() {return order;}

    /**
     * Overview: column getter
     */
    public int getColumn() {return column;}
}
