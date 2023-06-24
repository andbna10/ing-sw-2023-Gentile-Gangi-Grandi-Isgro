package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;
import ServerSide.Model.ScoringToken;
import ServerSide.Model.Status;

public class GameHasStartedMessage extends Message {
    private String message;
    private  String[][] board;
    private String[][] personalGoal;
    private int common1;
    private int common2;
    private int personalPatternNumber;
    private int PointsCom1;
    private int PointsCom2;
    private int numPlayers;

    /**
     * Overview: GameHasStartedMessage constructor
     * @param board matrix of BoardCell objects
     * @param numPlayers int specifying the number of players in the game
     * @param personalGoal matrix of ItemTile objects specifying the player's personal goal
     * @param patternNumberPersonal int specifying the personal pattern number
     * @param common1 int specifying the common pattern number of the first common goal
     * @param common2 int specifying the common pattern number of the second common goal
     * @param one Scoring Token in 1st place in the stack associated to the first common goal
     * @param two Scoring Token in 1st place in the stack associated to the second common goal
     */
    public GameHasStartedMessage(int numPlayers, BoardCell[][] board, ItemTile[][] personalGoal, int common1, int common2, ScoringToken one, ScoringToken two, int patternNumberPersonal){
        super(null);
        super.setType(MessageType.GAMEHASSTARTED);
        message = "game has started!";
        this.board=boardToString(board);
        this.personalGoal=bookshelfToString(personalGoal);
        this.common1=common1;
        this.common2=common2;
        this.PointsCom1 = one.getPoints();
        this.PointsCom2 = two.getPoints();
        this.personalPatternNumber = patternNumberPersonal;
        this.numPlayers = numPlayers;
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
     * @param board matrix of BoardCell objects
     * @return matrix of String as conversion of the input
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
     * Overview: converts bookshelf of ItemTile objects to a string bookshelf, so it can be print by the cli
     * @param bookshelf matrix of ItemTile objects
     * @return matrix of String as conversion of the input
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

    /**
     * Overview: points one getter
     */
    public int getPointsCom1(){ return this.PointsCom1; }

    /**
     * Overview: points two getter
     */
    public int getPointsCom2(){ return this.PointsCom2; }

    /**
     * Overview: personal pattern number personal
     */
    public int getPersonalPatternNumber(){ return this.personalPatternNumber; }

    /**
     * Overview: numPlayers getter
     */
    public int getNumPlayers(){ return this.numPlayers; }
}
