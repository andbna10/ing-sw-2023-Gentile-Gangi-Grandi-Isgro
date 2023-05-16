package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;

public interface PlayerObserver {

    void createplayerviewmessage();

    void createasknplayersmessage();

    void createownercanstartgamemessage();
    void notifyPlayerTurn(ItemTile[][] bookshelf);
    void notifyPlayerBoardRestored(BoardCell[][] board);
}
