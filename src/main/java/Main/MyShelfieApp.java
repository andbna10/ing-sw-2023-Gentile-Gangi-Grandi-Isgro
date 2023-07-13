package Main;

import ClientSide.NetworkHandler.LoginHandler;
import ClientSide.View.CLI.LogInCLI;
import Networking.ClientManager;
import Networking.ClientManagerGUI;
import Networking.Server;

import java.io.IOException;
import java.net.Socket;

public class MyShelfieApp {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.err.println("No input parameters.");
            System.exit(1);
        }

        if(! (args[0].equalsIgnoreCase("-cli") || args[0].equalsIgnoreCase("-gui") || args[0].equalsIgnoreCase("-server"))){
            System.err.println("Insert -gui, -cli or -server as first parameter.");
            System.exit(1);
        }

        if(args[0].equalsIgnoreCase("-server") && args.length == 1){
            Server server = new Server(59090);
            server.start();
        } else if((args[0].equalsIgnoreCase("-cli") || args[0].equalsIgnoreCase("-gui")) && args.length == 2) {

            try {
                Socket socket = new Socket(args[1], 59090);

                if (args[0].equalsIgnoreCase("-cli")) {

                    ClientManager client = new ClientManager(socket);

                    LoginHandler loginhandler = new LoginHandler(client);
                    client.setLoginHandler(loginhandler);

                    LogInCLI logincli = new LogInCLI(loginhandler);
                    loginhandler.setCli(logincli);

                    client.start();

                } else if (args[0].equalsIgnoreCase("-gui")) {

                    ClientManagerGUI client = new ClientManagerGUI(socket);

                    LoginHandler loginhandler = new LoginHandler(client);
                    client.setLoginHandler(loginhandler);

                    client.start();

                }

            } catch (IOException e) {
                System.err.println("Error during client initialization.\n" +
                        "Invalid parameters or invalid IP address.");
                System.exit(1);
            }
        } else {
            System.err.println("Invalid parameters.");
            System.exit(1);
        }

    }
}