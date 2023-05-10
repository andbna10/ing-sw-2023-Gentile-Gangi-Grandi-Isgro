package ClientSide.Cli;

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

        System.out.println("You can start the game, enter START!");
        System.out.print("> \n");
        String input = scanner.nextLine();

        if(input.equals("start")){
            System.out.println("Please, enter the id of your lobby:");
            System.out.print("> \n");
            String id = scanner.nextLine();
            handler.startgamemessage(sender, id);
        }


    }
}
