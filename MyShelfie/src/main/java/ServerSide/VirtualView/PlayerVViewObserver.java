package ServerSide.VirtualView;

public interface PlayerVViewObserver {

    /**
     * Overview: take the picked tiles from the board and add them to the player bookshelf
     */
    //void pickTakenTiles(int[] toTake);
    void playTurn(int[] toTake, int[] oreder, int column );
}
