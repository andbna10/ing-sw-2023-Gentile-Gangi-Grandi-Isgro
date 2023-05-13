package ClientSide.Cli;

import ClientSide.NetworkHandler.PlayerHandler;

public class PlayerCLI {
    private PlayerHandler handler;

    /**
     * Overview: PlayerCLI constructor
     */
    public PlayerCLI(PlayerHandler handler){ this.handler = handler;}

    /**
     * Overview: cli for the player to complete a move
     */
    public void yourTurn(String[][] bookshelf){
        // implementare cli turno di gioco
        System.out.println("this is your bookshelf:");
        printBookshelf(bookshelf);

    }

    /**
     * bookshelf printer
     */
    public static void printBookshelf(String[][] bookshelf) {
        // Print column headers
        System.out.print(" ");
        for (int j = 0; j < bookshelf[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (String[] row : bookshelf) {
            System.out.print("|");
            for (String itemtile : row) {
                System.out.print(itemtile + "|");
            }
            System.out.println();
        }
    }
}
