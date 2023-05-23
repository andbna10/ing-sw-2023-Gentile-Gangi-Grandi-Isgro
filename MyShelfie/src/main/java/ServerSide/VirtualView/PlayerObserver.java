package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;

import java.util.ArrayList;

public interface PlayerObserver {

    void createplayerviewmessage();

    void createasknplayersmessage();

    void createownercanstartgamemessage(String id);
    void notifyPlayerTurn(BoardCell[][] board, ItemTile[][] bookshelf, ArrayList<ItemTile[][]> bookshelfList, ArrayList<String> usernames);
    void notifyPlayerBoardRestored(BoardCell[][] board);
}
