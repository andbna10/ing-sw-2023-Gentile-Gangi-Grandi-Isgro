package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;

public interface GameObserver {

    void notifythestartofthegame(BoardCell[][] board, int common1, int common2);
    void notifytheendofaturn(BoardCell[][] board);

}
