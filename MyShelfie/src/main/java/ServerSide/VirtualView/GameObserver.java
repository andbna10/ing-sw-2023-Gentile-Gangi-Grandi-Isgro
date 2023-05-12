package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;

public interface GameObserver {

    void notifythestartofthegame(BoardCell[][] board);

}
