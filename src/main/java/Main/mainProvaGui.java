package Main;

import ClientSide.NetworkHandler.LoginHandler;
import ClientSide.View.CLI.LogInCLI;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

import java.io.IOException;
import java.net.Socket;

public class mainProvaGui {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 59090);

        // this is the clientmanager which aim is to manage the connection client-server ( for the client )
        ClientManagerGUI client = new ClientManagerGUI(socket);

        // this is the login handler which will manage the login page of the new client connected
        LoginHandler loginhandler = new LoginHandler(client);
        client.setLoginHandler(loginhandler);

        // start the thread of the client
        client.start();

    }
}


