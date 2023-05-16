package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.BoardCell;
import ServerSide.Model.Status;

public class BoardRestoredMessage extends Message {
    private String message;
    private String[][] board;

    /**
     * Overview: BoardRestoredMessage constructor
     */
    public BoardRestoredMessage(BoardCell[][] board){
        super(null);
        super.setType(MessageType.BOARDRESTORED);
        message="\nThe Board has been restored!";
        this.board = boardToString(board);
    }

    /**
     * Overview: boarcell converter to string
     */
    public String[][] boardToString(BoardCell[][] board) {
        String[][] stringMatrix = new String[board[0].length][board.length];
        for (int i=0; i<board[0].length; i++) {
            for (int j=0; j<board.length;j++) {
                if(board[i][j].getStatus() == Status.IN){
                    switch (board[i][j].getTile().getType()) {
                        case CATS -> stringMatrix[i][j]="C";
                        case BOOKS -> stringMatrix[i][j]="B";
                        case GAMES -> stringMatrix[i][j]="G";
                        case FRAMES -> stringMatrix[i][j]="F";
                        case TROPHIES -> stringMatrix[i][j]="T";
                        case PLANTS -> stringMatrix[i][j]="P";
                        default -> stringMatrix[i][j]="*";
                    }
                }else{
                    stringMatrix[i][j]=(" ");
                }
            }
        }
        return stringMatrix;
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return this.message; }

    /**
     * Overview: board getter
     */
    public String[][] getBoard(){ return board; }
}
