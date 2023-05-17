package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.Player;

import java.util.ArrayList;

public interface GameObserver {

    void notifythestartofthegame(BoardCell[][] board, int common1, int common2);
    void notifytheendofaturn(BoardCell[][] board, String username);
    void noitfyObserverCommon(int common, int newPoints, String username);
    void noitfyObserverLastTurn(String username);
    void notifyEnd(String winner, ArrayList<Player> players);

}
