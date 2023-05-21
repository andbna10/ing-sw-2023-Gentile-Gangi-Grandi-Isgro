package ClientSide.View.CLI;

import ClientSide.NetworkHandler.LoginHandler;

import java.util.Scanner;


// POTREMMO CREARE UNA CLASSE PADRE PER LE CLI POI VEDIAMO
public class LogInCLI {
    private LoginHandler handler;

    /**
     * Overview: LogInCLI constructor
     */
    public LogInCLI(LoginHandler handler){ this.handler = handler; }

    public void loginprocedure() {
        Scanner scanner = new Scanner(System.in);
        String sender = "prova";

        String input;

        // print the menu in the cli
        System.out.print("COMMAND MENU\n" +
                "* 1.create game  : generate a new game \n" +
                "* 2.join game    : join a game with the id\n" +
                "* 3.random match : join random online lobby\n" +
                "Insert a number according to your choice!\n");

        System.out.print("> \n");
        do {
            input = scanner.nextLine();
            if(!input.equals("1") && !input.equals("2") && !input.equals("3")){
                System.out.println("Input not valid, try again!");
            }
        } while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

        switch (input) {
            case "1" -> {

                System.out.println("enter username:");
                String username = scanner.nextLine();


                //istanzia una nuova partita
                handler.creategamemessage(sender, username);

                // vedere se posso leggere l'ultima cosa scritta su system out per richiamare la CLI se l'user era occupato


            }
            case "2" -> {

                System.out.println("enter username:");
                String username = scanner.nextLine();
                System.out.println("enter the game id:");
                String gameid = scanner.nextLine();
                //System.out.println("joining the game");
                handler.entergamemessage(sender, username, gameid);
                // vedere se posso leggere l'ultima cosa scritta su system out per richiamare la CLI se l'user era occupato
            }
            case "3" -> {
                System.out.println("enter username:");
                String username = scanner.nextLine();
                handler.entergamemessage(sender, username, "random");
            }
        }
    }
}
