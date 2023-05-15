package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;
import ServerSide.Model.Status;

public class GameHasStartedMessage extends Message {
    private String message;
    private  String[][] board;
    private String[][] personalGoal;
    private int common1;
    private int common2;

    /**
     * Overview: GameHasStartedMessage constructor
     */
    public GameHasStartedMessage(BoardCell[][] board, ItemTile[][] personalGoal, int common1, int common2){
        super(null);
        super.setType(MessageType.GAMEHASSTARTED);
        message = "game has started!";
        this.board=boardToString(board);
        this.personalGoal=bookshelfToString(personalGoal);
        this.common1=common1;
        this.common2=common2;
    }

    /**
     * Overview: message getter
     */
    public String getMessage(){ return message; }

    /**
     * Overview: board getter
     */
    public String[][] getBoard(){return board;}

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
     * Overview: string personal goal getter
     */
    public String[][] getPersonalGoal() {return personalGoal;}

    /**
     * overview: common goal getter
     */
    public int getCommon1() {return common1;}

    public int getCommon2() {return common2;}

    /**
     * Overview: converts a intemtiles bookshelf to a string bookshelf so it can be print by the cli
     */
    public String[][] bookshelfToString(ItemTile[][] bookshelf) {
        String[][] stringBookshelf = new String[bookshelf.length][bookshelf[0].length];
        for (int i=0; i<bookshelf.length; i++) {
            for (int j=0; j<bookshelf[0].length;j++) {
                if(bookshelf[i][j]!=null){
                    switch (bookshelf[i][j].getType()) {
                        case CATS -> stringBookshelf[i][j]="C";
                        case BOOKS -> stringBookshelf[i][j]="B";
                        case GAMES -> stringBookshelf[i][j]="G";
                        case FRAMES -> stringBookshelf[i][j]="F";
                        case TROPHIES -> stringBookshelf[i][j]="T";
                        case PLANTS -> stringBookshelf[i][j]="P";
                        //case  -> stringBookshelf[i][j]=" ";
                    }
                }else stringBookshelf[i][j]=" ";
            }
        }
        return stringBookshelf;
    }
}
