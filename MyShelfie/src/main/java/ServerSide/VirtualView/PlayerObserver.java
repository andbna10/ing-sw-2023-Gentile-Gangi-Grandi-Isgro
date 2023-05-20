package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;

import java.util.ArrayList;

public interface PlayerObserver {

    void createplayerviewmessage();

    void createasknplayersmessage();

    void createownercanstartgamemessage();
    void notifyPlayerTurn(ItemTile[][] bookshelf, ArrayList<ItemTile[][]> bookshelfList, ArrayList<String> usernames);
    void notifyPlayerBoardRestored(BoardCell[][] board);
}
