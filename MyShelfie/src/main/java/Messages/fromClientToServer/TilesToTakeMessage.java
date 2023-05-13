package Messages.fromClientToServer;

import Messages.Message;
import Messages.MessageType;

public class TilesToTakeMessage extends Message {
    private int[] toTake;
    private int[] order;
    private int column;

    public TilesToTakeMessage(int[] toTake, int[] order, int column, String sender){
        super(sender);
        super.setType(MessageType.TILESTOTAKE);
        this.toTake=toTake;
        this.order=order;
        this.column=column;
    }

    public int[] getToTake() {return toTake;}

    public int[] getOrder() {return order;}

    public int getColumn() {return column;}
}
