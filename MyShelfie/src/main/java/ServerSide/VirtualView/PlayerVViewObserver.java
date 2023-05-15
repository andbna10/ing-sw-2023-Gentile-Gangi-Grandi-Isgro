package ServerSide.VirtualView;

import ServerSide.Model.Player;

public interface PlayerVViewObserver {

    /**
     * Overview: take the picked tiles from the board and add them to the player bookshelf
     */
    //void pickTakenTiles(int[] toTake);
    void playTurn(int[] toTake, int[] oreder, int column );

    /**
     * player getter
     */
    Player getModel();
}
