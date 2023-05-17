package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.BoardCell;
import ServerSide.Model.Status;

public class EndTurnMessage extends Message {
    private String message;
    private String[][] board;

    /**
     * Overview: EndTurnMessage constructor
     */
    public EndTurnMessage(BoardCell[][] board, String username){
        super(null);
        super.setType(MessageType.ENDTURN);
        message = username+ " has just ended its turn, this is the updated board!";
        this.board=boardToString(board);
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return message; }

    /**
     * Overview: boarcell converter to string
     */
    public String[][] boardToString(BoardCell[][] board) {
        String[][] stringMatrix = new String[board[0].length][board.length];
        for (int i=0; i<board[0].length; i++) {
            for (int j=0; j<board.length;j++) {
                if(board[i][j].getStatus() == Status.IN){
                    if(board[i][j].getTile() == null){
                        stringMatrix[i][j]=("*");
                        continue;
                    }
                    switch (board[i][j].getTile().getType()) {
                        case CATS -> stringMatrix[i][j]="C";
                        case BOOKS -> stringMatrix[i][j]="B";
                        case GAMES -> stringMatrix[i][j]="G";
                        case FRAMES -> stringMatrix[i][j]="F";
                        case TROPHIES -> stringMatrix[i][j]="T";
                        case PLANTS -> stringMatrix[i][j]="P";
                        //default -> stringMatrix[i][j]="*";
                    }
                }else{
                    stringMatrix[i][j]=(" ");
                }
            }
        }
        return stringMatrix;
    }

    /**
     * Overview: board getter
     */
    public String[][] getBoard(){ return this.board; }
}
