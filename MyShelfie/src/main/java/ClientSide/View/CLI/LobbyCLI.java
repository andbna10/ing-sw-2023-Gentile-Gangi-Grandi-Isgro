package ClientSide.View.CLI;

import ClientSide.NetworkHandler.LobbyHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LobbyCLI {
    private LobbyHandler handler;

    /**
     * Overview: LobbyCLI constructor
     */
    public LobbyCLI(LobbyHandler handler){ this.handler = handler; }

    public void ownercanstart(String id){
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

        handler.startgamemessage(sender, id);
    }

    /**
     * Overview: print the lobby
     */
    public void printLobby(String id, ArrayList<String> usernames, String owner){
        System.out.println("\nThe id of the lobby is: "+ id);
        for(String s: usernames){
            if(s == owner){
                System.out.println(s+" OWNER");
                continue;
            }
            System.out.println(s);
        }
    }

    /**
     * Overview: ask for the number of players to be in the lobby (random match)
     */
    public int insertNumberPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = 0;
        do{
            boolean flag = false;
            try {
                n = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Please insert a number!");
                flag = true;
                scanner.nextLine();
            }
            if(n<2 || n>4){
                if(!flag) {
                    System.out.println("A game must be composed between 2 and 4 players, try again!");
                }
            }
        } while(n<2 || n>4);
        return n;
    }
}
