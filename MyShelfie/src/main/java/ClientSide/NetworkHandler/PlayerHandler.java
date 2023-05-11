package ClientSide.NetworkHandler;

import ClientSide.View.PlayerView;
import Networking.ClientManager;

public class PlayerHandler implements PlayerViewObserver{
    private ClientManager manager;
    private PlayerView view;

    // attributes

    /**
     * Overview: PlayerHandler constructor
     */
    public PlayerHandler(ClientManager manager){
        this.manager = manager;
        this.view = new PlayerView(this);
    }
    /*public static void printBoard(BoardCell[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        int i=0;

        // Print column headers
        System.out.print("  ");
        for (int j = 0; j < numCols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (BoardCell[] row : board) {
            System.out.print(i + " ");
            i++;
            for (BoardCell cell : row) {
                if(cell.getStatus.equals("IN")){
                    switch (cell.getTile().getType()) {
                        case CATS -> System.out.print("C ");
                        case BOOKS -> System.out.print("B ");
                        case GAMES -> System.out.print("G ");
                        case FRAMES -> System.out.print("F ");
                        case TROPHIES -> System.out.print("T ");
                        case PLANTS -> System.out.print("P ");
                        default -> System.out.print("*" + " ");
                    }
                }else{
                    System.out.print("#" + " ");

                }
            }
            System.out.println();
        }
    }

    public static void printBookshelf(ItemTile[][] bookshelf) {
        // Print column headers
        System.out.print("  ");
        for (int j = 0; j < numCols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (ItemTile[] row : bookshelf) {
            System.out.print("|");
            for (ItemTile itemtile : row) {
                switch (itemtile.getType()) {
                    case CATS -> System.out.print("C" + "|");
                    case BOOKS -> System.out.print("B" + "|");
                    case GAMES -> System.out.print("G" + "|");
                    case FRAMES -> System.out.print("F" + "|");
                    case TROPHIES -> System.out.print("T" + "|");
                    case PLANTS -> System.out.print("P" + "|");
                    default -> System.out.print(" " + "|");
                }
            }
            System.out.println();
        }
    }*/
}
