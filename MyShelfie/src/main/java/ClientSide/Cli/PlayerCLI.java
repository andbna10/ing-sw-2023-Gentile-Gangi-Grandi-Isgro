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
    public void yourTurn(String[][] bookshelf, String[][] personalGoal, int common1, int common2){
        // implementare cli turno di gioco
        System.out.println("this is your personal goal:");
        printBookshelf(personalGoal);
        System.out.println("this is the first common goal:");
        printCommon(common1);
        System.out.println("this is the second common goal:");
        printCommon(common2);
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
     * print common goal
     */
    public void printCommon(int patternNumber){
        switch (patternNumber) {
            case 1 -> {
                System.out.println("""
                        ==
                        ==
                        X2""");
                System.out.println("""
                        description: Two groups each containing 4 tiles of
                        the same type in a 2x2 square. The tiles
                        of one square can be different from
                        those of the other square.
                        """);
            }
            case 2 -> {
                for (int i = 0; i < 6; i++)
                    System.out.println("!=");
                System.out.println("X2");
                System.out.println("""
                        description: Two lines each formed by 5 different
                        types of tiles. One line can show the
                        same or a different combination of the
                        other line.""");
            }
            case 3 -> {
                System.out.println("""
                        =
                        =
                        =
                        =
                        X4""");
                System.out.println("""
                        description: Four groups each containing at least
                        4 tiles of the same type (not necessarily
                        in the depicted shape).
                        The tiles of one group can be different
                        from those of another group.""");
            }
            case 4 -> {
                System.out.println("""
                        =
                        =
                        X6""");
                System.out.println("""
                        description: Six groups each containing at least
                        2 tiles of the same type (not necessarily
                        in the depicted shape).
                        The tiles of one group can be different
                        from those of another group.""");
            }
            case 5 -> System.out.println("""
                    description: Three columns each formed by 6 tiles\s
                    of maximum three different types. One
                    column can show the same or a different
                    combination of another column""");
            case 6 -> {
                for (int i = 0; i < 5; i++)
                    System.out.print("!=");
                System.out.println("\nX2");
                System.out.println("""
                        description: Two lines each formed by 5 different
                        types of tiles. One line can show the
                        same or a different combination of the
                        other line.""");
            }
            case 7 -> System.out.println("""
                    description: Four lines each formed by 5 tiles of
                    maximum three different types. One
                    line can show the same or a different
                    combination of another line.""");
            case 8 -> {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if ((i == 0 || i == 5) && (j == 0 || j == 4))
                            System.out.print("=");
                        else if (i == 0 || i == 5) System.out.print("-");
                        else if (j == 0 || j == 4) System.out.print("|");
                        else System.out.print(" ");
                    }
                    System.out.println();
                }
                System.out.println("description: Four tiles of the same type in the four\n" +
                        "corners of the bookshelf");
            }
            case 9 -> System.out.println("""
                    description: Eight tiles of the same type. There’s no
                    restriction about the position of these
                    tiles.\s""");
            case 10 -> {
                System.out.println("""
                        = =
                         =\s
                        = =""");
                System.out.println("description: Five tiles of the same type forming an X.");
            }
            case 11 -> {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (i == j) System.out.print("=");
                        else System.out.print(" ");
                    }
                    System.out.println();
                }
                System.out.println("description: Five tiles of the same type forming a diagonal. ");
            }
            case 12 -> {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (i <= j) System.out.print("#");
                        else System.out.print(" ");
                    }
                    System.out.println();
                }
                System.out.println("""
                        description: Five columns of increasing or decreasing
                        height. Starting from the first column on
                        the left or on the right, each next column
                        must be made of exactly one more tile.
                        Tiles can be of any type.\s""");
            }
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
