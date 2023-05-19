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

        do {

            System.out.print("COMMAND MENU\n" +
                    "* create game  : generate a new game \n" +
                    "* join game    : join a game with the id\n" +
                    "* random match : join random online lobby\n");

            System.out.print("> \n");
            input = scanner.nextLine();

        } while (!input.equals("create game") && !input.equals("join game") && !input.equals("random match"));

        switch (input) {
            case "create game" -> {

                System.out.println("enter username:");
                String username = scanner.nextLine();

                //System.out.println("starting new game");
                //istanzia una nuova partita

                handler.creategamemessage(sender, username);

                // vedere se posso leggere l'ultima cosa scritta su system out per richiamare la CLI se l'user era occupato


            }
            case "join game" -> {

                System.out.println("enter username:");
                String username = scanner.nextLine();
                System.out.println("enter the game id:");
                String gameid = scanner.nextLine();
                //System.out.println("joining the game");
                handler.entergamemessage(sender, username, gameid);
                // vedere se posso leggere l'ultima cosa scritta su system out per richiamare la CLI se l'user era occupato
            }
            case "random match" -> {
                System.out.println("enter username:");
                String username = scanner.nextLine();
                handler.entergamemessage(sender, username, "online");
            }
        }
    }
}
