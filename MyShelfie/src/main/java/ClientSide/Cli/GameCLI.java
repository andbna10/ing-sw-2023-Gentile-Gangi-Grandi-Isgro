package ClientSide.Cli;

import ClientSide.NetworkHandler.GameHandler;
import ClientSide.NetworkHandler.LobbyHandler;
import ServerSide.Model.BoardCell;

public class GameCLI {
    private GameHandler handler;

    /**
     * Overview: GameCLI constructor
     */
    public GameCLI(GameHandler handler){ this.handler = handler; }

    /**
     * Overview: board printer
     */
    public static void printBoard(String[][] board) {
        int numCols = board[0].length;
        int i=0;

        // Print column headers
        System.out.print("  ");
        for (int j = 0; j < numCols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (String[] row : board) {
            System.out.print(i + " ");
            i++;
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
