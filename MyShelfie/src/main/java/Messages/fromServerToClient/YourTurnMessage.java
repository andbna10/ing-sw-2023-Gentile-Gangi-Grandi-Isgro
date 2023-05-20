package Messages.fromServerToClient;

import Messages.Message;
import Messages.MessageType;
import ServerSide.Model.ItemTile;

import java.util.ArrayList;

public class YourTurnMessage extends Message {
    private String message;
    private String[][] bookshelf;
    private ArrayList<String[][]> bookshelfList;
    private ArrayList<String> usernames;
    private Boolean upddatedBookshelf;

    /**
     * Overview: YourTurnMessage constructor1: bookshelf start of the turn
     */
    public YourTurnMessage(ItemTile[][] bookshelf, ArrayList<ItemTile[][]> bookshelflist, ArrayList<String> usernames){
        super(null);
        this.bookshelfList = new ArrayList<>();
        this.usernames = usernames;
        super.setType(MessageType.YOURTURN);
        this.message = " ---------------- It's your turn ---------------- ";
        this.bookshelf=bookshelfToString(bookshelf);
        for(int i=0; i< bookshelflist.size(); i++){
            this.bookshelfList.add(bookshelfToString(bookshelflist.get(i)));
        }
        this.upddatedBookshelf = false;
    }

    /**
     * Overview: YourTurnMessage constructor2: updated bookshelf end of the turn
     */
    public YourTurnMessage(ItemTile[][] bookshelf, Boolean updatedBookshelf){
        super(null);
        this.bookshelfList = null;
        this.usernames = null;
        super.setType(MessageType.YOURTURN);
        this.message = "Your updated bookshelf";
        this.bookshelf=bookshelfToString(bookshelf);
        this.upddatedBookshelf = updatedBookshelf;
    }

    /**
     * Overview: get the message
     */
    public String getMessage(){ return this.message; }

    /**
     * Overview: string bookshelf getter
     */
    public String[][] getBookshelf(){return bookshelf;}

    /**
     * Overview: String bookshelflist getter
     */
    public ArrayList<String[][]> getBookshelfList(){ return this.bookshelfList; }

    /**
     * Overview: String usernamesList getter
     */
    public ArrayList<String> getUsernames(){ return this.usernames; }

    /**
     * Overview: converts an itemtiles bookshelf to a string bookshelf so it can be print by the cli
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
     * Overview: updatedBookshelf getter
     */
    public Boolean getUpddatedBookshelf(){ return this.upddatedBookshelf; }
}
