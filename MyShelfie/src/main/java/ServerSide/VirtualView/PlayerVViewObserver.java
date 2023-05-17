package ServerSide.VirtualView;

import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.Player;

import java.util.ArrayList;

public interface PlayerVViewObserver {

    /**
     * Overview: take the picked tiles from the board and add them to the player bookshelf
     */
    //void pickTakenTiles(int[] toTake);
    void playTurn(int[] toTake, int[] oreder, int column );

    /**
     * Overview: player getter
     */
    Player getModel();

    /**
     * Overview: check goals and bookshelf
     */
    void check(ArrayList<CommonGoalCard> commongoals);
}
