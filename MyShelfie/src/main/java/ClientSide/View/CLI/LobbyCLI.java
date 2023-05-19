package ClientSide.View.CLI;

import ClientSide.NetworkHandler.LobbyHandler;

import java.util.Scanner;

public class LobbyCLI {
    private LobbyHandler handler;

    /**
     * Overview: LobbyCLI constructor
     */
    public LobbyCLI(LobbyHandler handler){ this.handler = handler; }

    public void ownercanstart(){
        Scanner scanner = new Scanner(System.in);
        String sender = "prova";
        String input;

        System.out.println("You can start the game! Type start.");
        System.out.print("> \n");
        input = scanner.nextLine();

        while (!input.equals("start")){
            System.out.println("Command not found, retry.");
            System.out.print("> \n");
            input = scanner.nextLine();
        }

        System.out.println("Please, enter the id of your lobby:");
        System.out.print("> \n");
        String id = scanner.nextLine();
        handler.startgamemessage(sender, id);


    }
}
