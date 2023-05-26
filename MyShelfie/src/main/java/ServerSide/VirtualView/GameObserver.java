package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.Player;
import ServerSide.Model.ScoringToken;

import java.util.ArrayList;

public interface GameObserver {

    void notifythestartofthegame(int numPlayers, BoardCell[][] board, int common1, int common2, ScoringToken one, ScoringToken two);
    void notifytheendofaturn(BoardCell[][] board, String username);
    void noitfyObserverCommon(int common, int newPoints, String username, ScoringToken token);
    void noitfyObserverLastTurn(String username);
    void notifyEnd(String winner, ArrayList<Player> players, boolean discon);

}
