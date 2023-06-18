package ClientSide.View.CLI;

import ClientSide.NetworkHandler.GameHandler;

public class GameCLI {
    private GameHandler handler;

    /**
     * Overview: GameCLI constructor
     */
    public GameCLI(GameHandler handler){ this.handler = handler; }

    /**
     * Overview: board printer
     * @author Simone Grandi
     * @param board board to be printed
     */
    public void printBoard(String[][] board) {
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
