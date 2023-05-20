package Main;

import ClientSide.NetworkHandler.LoginHandler;
import ClientSide.View.CLI.LogInCLI;
import ClientSide.View.GUI.GameGUI;
import ClientSide.View.GUI.LobbyGUI;
import ClientSide.View.GUI.LoginGUI;
import Networking.ClientManager;

import java.io.IOException;
import java.net.Socket;

public class mainProvaGUI {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Socket socket = new Socket("localhost", 59090 );

        // this is the clientmanager which aim is to manage the connection client-server ( for the client )
        //ClientManager client = new ClientManager(socket);

        // this is the login handler which will manage the login page of the new client connected
        //LoginHandler loginhandler = new LoginHandler(client);
        //client.setLoginHandler(loginhandler);

        // this is the login gui
        //LoginGUI logingui = new LoginGUI(loginhandler);
        //loginhandler.setGui(logingui);

        // start the thread of the client
        //client.start();

        LoginGUI loginGUI = new LoginGUI();
        //GameGUI gameGUI = new GameGUI();
        //LobbyGUI lobbyGUI = new LobbyGUI();

    }
}
