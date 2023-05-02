package Main;

import Client.NetworkHandler.LoginHandler;
import Messages.fromClientToServer.CreateGameMessage;
import Networking.ClientManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class mainProva {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 59090 );

        // this is the clientmanager which aim is to manage the connection client-server ( for the client )
        ClientManager client = new ClientManager(socket);

        // this is the login handler which will manage the login page of the new client connected
        LoginHandler loginhandler = new LoginHandler(client);


        /*System.out.println("I'm starting the heartbeat procedure - client");
        ScheduledExecutorService heartbeatProcedure = Executors.newSingleThreadScheduledExecutor();
        heartbeatProcedure.scheduleAtFixedRate(() ->{
            try {
                Boolean ok = client.heartbeat();
                if(!ok){
                    heartbeatProcedure.shutdown();
                }
            } catch (IOException e) {
                System.exit(1);
            }

        }, 0, 5, TimeUnit.SECONDS);*/

        client.start();

        /* prova lettura messaggi - da sostituire con CLI
        TimeUnit.SECONDS.sleep(3);
        loginhandler.creategamemessage("senderprova", "andbna");*/

        //CLI
        Scanner scanner = new Scanner(System.in);
        String sender = "prova";

        System.out.print("command info\n" +
                "* create game : generate a new game \n" +
                "* join game   : join a game with the id\n");

        System.out.print("> ");
        String input = scanner.nextLine();

        if (input.equals("create game")) {

            System.out.println("enter username:");
            String username = scanner.nextLine();
            //controllo che non sia già presente
            System.out.println("starting new game");
            //instanzia una nuova partita
            loginhandler.creategamemessage(sender,username);

        }else if (input.equals("join game")){

            System.out.println("enter username:");
            String username = scanner.nextLine();
            System.out.println("enter the game id:");
            String gameid = scanner.nextLine();
            System.out.println("joining the game");
            loginhandler.entergamemessage(sender,username,gameid);

        }
    }
}


