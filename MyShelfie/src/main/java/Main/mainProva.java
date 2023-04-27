package Main;

import Client.NetworkHandler.LoginHandler;
import Networking.ClientManager;

import java.io.IOException;
import java.net.Socket;

public class mainProva {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("192.168.1.50", 59090 );){

            // this is the clientmanager which aim is to manage the connection client-server ( for the client )
            ClientManager client = new ClientManager(socket);

            // this is the login handler which will manage the login page of the new client connected
            LoginHandler loginhandler = new LoginHandler(client);
            client.start();
        }
    }
}


