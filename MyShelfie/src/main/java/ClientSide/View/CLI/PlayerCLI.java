package ClientSide.View.CLI;

import ClientSide.NetworkHandler.PlayerHandler;
import ServerSide.Model.ItemTile;

import java.util.InputMismatchException;
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
    public void initialSetUp(String[][] personalGoal, int common1, int common2){
        // implementare cli turno di gioco
        System.out.println("this is your personal goal:");
        printBookshelf(personalGoal);
        System.out.println("\nthis is the first common goal:");
        printCommon(common1);
        System.out.println("\nthis is the second common goal:");
        printCommon(common2);
    }


    /**
     * Overview: bookshelf printer
     * @author Simone Grandi
     * @param bookshelf bookshelf to be printed
     */
    public void printBookshelf(String[][] bookshelf) {
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
     * Overview: print common goal
     * @author Simone Grandi
     * @param patternNumber number of common pattern to be printed
     */
    public void printCommon(int patternNumber){
        switch (patternNumber) {
            case 1 -> {
                System.out.println("""
                        = =
                        = =
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
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (j <= i) System.out.print("#");
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
     * Overview: cli for the player to complete a move
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param bookshelf current turn player's bookshelf
     */
    public void yourTurn(String[][] bookshelf) {
        Scanner scanner = new Scanner(System.in);
        if (bookshelf != null) {
            System.out.println("\nThis is your bookshelf:");
            printBookshelf(bookshelf);
        }

        System.out.println("\nPick the tiles you want from the board writing the coordinates");
        // take tiles coordinates
        System.out.println("Enter the number of tiles you want to pick: ");
        int size;
        do {
            size = -1;
            try {
                size = scanner.nextInt() * 2;
            } catch (InputMismatchException e){
                    System.out.println("Please insert a number!");
                    scanner.nextLine();
            }
            if(size > 6){
                System.out.println("You can select a max of 3 pickable tiles, try again!");
            }
        } while (size > 6 || size == -1);

        int[] toTake = new int[size];
        System.out.println("Enter coordinates of the tiles:");

        for (int i = 0; i < size; i++) {
            do{
                int flag = 0;
                toTake[i] = -1;
                if(i%2==0){
                    System.out.println("\nEnter the row of the tile: ");
                } else {
                    System.out.println("\nEnter the column of the tile: ");
                }
                try {
                    toTake[i] = scanner.nextInt();
                    flag = 2;
                } catch(InputMismatchException e){
                    System.out.println("Please insert a number!");
                    flag = 1;
                    scanner.nextLine();
                }
                if(toTake[i]<0 || toTake[i]>8){
                    if(flag == 2) {
                        System.out.println("Look at the board, there is no row or column like that. Try again!");
                    }
                }
            } while (toTake[i]<0 || toTake[i]>8 || toTake[i] == -1);
        }

        // Print the array
        System.out.println("You've picked these tiles:");
        int j = 0;
        for (int i = 0; i < size; i = i + 2) {
            System.out.println("coord " + j + ":" + toTake[i] + " " + toTake[i + 1]);
            j++;
        }
        totake = toTake;
        //

        System.out.println("Insert the order (the first selected goes in the lowest free cell of your bookshelf's column): ");
        // take the order
        //
        int[] input = new int[totake.length / 2];

        for (int i = 0; i < input.length; i++) {
            do{
                int flag = 0;
                input[i] = -1;
                try {
                    input[i] = scanner.nextInt();
                    flag = 2;
                } catch (InputMismatchException e){
                    System.out.println("Please insert a number!");
                    flag = 1;
                    scanner.nextLine();
                }
                if(input[i] > input.length - 1 || input[i] == -1){
                    if(flag == 2) {
                        System.out.println("You don't have selected " + input[i] + " tiles, try again!");
                    }
                }
            }while(input[i] > input.length - 1 || input[i] == -1);
        }
        order = input;
        //

        System.out.println("Insert the column you want to fill: ");
        // take the column
        do{
            int flag = 0;
            this.column = -1;
            try {
                this.column = scanner.nextInt();
                flag =2;
            } catch (InputMismatchException e){
                System.out.println("Please insert a number!");
                flag = 1;
                scanner.nextLine();
            }
            if(this.column > 4 || this.column ==-1){
                if(flag == 2) {
                    System.out.println("You have only 4 columns, try again!");
                }
            }
        }while(this.column > 4 || this.column == -1);

        //
    }

    /**
     * Overview: print the opponents' bookshelf at the start of the turn
     * @author Andrea Isgrò
     * @param bookshelf opponent's bookshelf
     * @param username opponent's username
     */
    public void printOpponent(String[][] bookshelf, String username){
        System.out.println("\nHere "+username+"'s Bookshelf:");
        printBookshelf(bookshelf);
    }

    /**
     * Overview totake getter
     */
    public int[] getTotake() {return totake;}

    /**
     * Overview: order getter
     */
    public int[] getOrder() {return order;}

    /**
     * Overview: column getter
     */
    public int getColumn() {return column;}

}
