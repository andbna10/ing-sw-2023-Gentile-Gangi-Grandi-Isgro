package ClientSide.Cli;

import ClientSide.NetworkHandler.PlayerHandler;

import java.util.Scanner;

public class PlayerCLI {
    private PlayerHandler handler;
    private int[] totake;
    private int[] order;
    private int column;

    /**
     * Overview: PlayerCLI constructor
     */
    public PlayerCLI(PlayerHandler handler){ this.handler = handler;}

    /**
     * Overview: cli for the player to complete a move
     */
    public void yourTurn(String[][] bookshelf, String[][] personalGoal){
        // implementare cli turno di gioco
        System.out.println("this is your personal goal:");
        printBookshelf(personalGoal);
        System.out.println("this is your bookshelf:");
        printBookshelf(bookshelf);
        System.out.println("pick the tiles you want from the board writing the coordinates");
        //come gestire il limite di tiles prendibili?
        takeTilesCoord();
        System.out.println("insert the order you want to insert them(from first in to last in):");
        takeOrder();
        System.out.println("insert the column you want to insert the tiles:");
        takeColumn();
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

    /**
     * Overview: take the cordinates of the tiles the player wants
     */
    public void takeTilesCoord(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the number of tiles you want to pick: ");
        int size = scanner.nextInt()*2;
        int[] toTake = new int[size];
        System.out.println("Enter in order row cord and column cord:");

        for (int i = 0; i < size; i++) {
            toTake[i] = scanner.nextInt();
        }

        // Print the array
        System.out.println("you take the tiles:");
        int j=0;
        for (int i = 0; i < size; i=i+2) {
            System.out.println("coord " + j + ":" + toTake[i] + " " + toTake[i+1]);
            j++;
        }

        //scanner.close();
        totake=toTake;
    }

    /**
     * Overview: take the order to insert the tiles
     */
    public void takeOrder(){
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[totake.length/2];

        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
        }
        //scanner.close();
        order=input;
    }
    public void takeColumn(){
        Scanner scanner = new Scanner(System.in);
        int column= scanner.nextInt();
        scanner.close();
        this.column=column;
    }

    /**
     * Overview totake getter
     */
    public int[] getTotake() {return totake;}

    /**
     * Overview order getter
     */
    public int[] getOrder() {return order;}

    /**
     * Overview column getter
     */
    public int getColumn() {return column;}
}
