package ServerSide.VirtualView;

import ServerSide.Model.BoardCell;
import ServerSide.Model.Player;
import ServerSide.Model.ScoringToken;

import java.util.ArrayList;

public interface GameObserver {

    void notifythestartofthegame(int numPlayers, BoardCell[][] board, int common1, int common2, ScoringToken one, ScoringToken two);
    void notifytheendofaturn(BoardCell[][] board, String username);
    void notifyObserverCommon(int common, int newPoints, String username, ScoringToken token);
    void notifyObserverLastTurn(String username);
    void notifyEnd(String winner, ArrayList<Player> players, boolean discon, String id);

}
