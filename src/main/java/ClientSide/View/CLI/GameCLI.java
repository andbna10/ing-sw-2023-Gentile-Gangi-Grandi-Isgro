package ClientSide.View.CLI;

import ClientSide.NetworkHandler.GameHandler;

import java.util.Scanner;

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

    /**
     * Overview: method aimed to let the owner get all the players back to the lobby
     */
    public void backToTheLobby(){
        System.out.println("Type 'back' to let all the players come back to the lobby");
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.nextLine();
        while (!input.equals("back")){
            System.out.println("Command not found, retry!");
            System.out.print("> \n");
            input = scanner.nextLine();
        }
        handler.createBackToTheLobbyMessage();
    }
}
