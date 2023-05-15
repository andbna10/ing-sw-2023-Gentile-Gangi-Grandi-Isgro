package Main;

import ClientSide.View.CLI.LogInCLI;
import ClientSide.NetworkHandler.LoginHandler;
import Networking.ClientManager;

import java.io.*;
import java.net.Socket;

public class mainProva {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 59090 );

        // this is the clientmanager which aim is to manage the connection client-server ( for the client )
        ClientManager client = new ClientManager(socket);

        // this is the login handler which will manage the login page of the new client connected
        LoginHandler loginhandler = new LoginHandler(client);
        client.setLoginHandler(loginhandler);

        // this is the login cli
        LogInCLI logincli = new LogInCLI(loginhandler);
        loginhandler.setCli(logincli);

        // start the thread of the client
        client.start();

        // call the login CLI
        //logincli.loginprocedure();

    }
}


