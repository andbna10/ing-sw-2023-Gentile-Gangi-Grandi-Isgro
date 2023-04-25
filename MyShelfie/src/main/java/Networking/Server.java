package Networking;

import Client.NetworkHandler.LoginHandler;
import Server.Controller.LobbyManager;
import Server.VirtualView.VirtualGameView;
import Server.VirtualView.VirtualPlayerView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int port;
    private ServerSocket serversocket;
    private Socket clientsocket;
    private LobbyManager lobbymanager;

    /**
     * Overview: constructor of the server class aimed to construct the socket for communication server-client
     */
    public Server(int port){
        this.lobbymanager = new LobbyManager();
        this.port = port;
    }

    /**
     * Overview: method aimed to create a serversocket object on the specific port. it enters a loop to continuously listen for incoming clients connections.
     */
    public void start() throws IOException{
        try{
            this.serversocket = new ServerSocket(port);
            serversocket.setSoTimeout(20000);
            System.out.println("The server is running on port "+this.port);

            // "while" loop for accepting client connections
            while(true){
                System.out.println("The server started listening");
                try{
                    clientsocket = serversocket.accept();
                    System.out.println("Client connected");

                    // here we need to create the network handler class (login handler) which creates then the View class (login view)

                    // initialization of managers to manage the new client connection with the server
                    ClientManager client = new ClientManager(clientsocket);
                    ServerManager server = new ServerManager(clientsocket, lobbymanager);
                    LoginHandler loginhandler = new LoginHandler(client);
                    client.start();
                    server.start();
                    /*ScheduledExecutorService hearthbeatProcedure = Executors.newSingleThreadScheduledExecutor();
                    hearthbeatProcedure.scheduleAtFixedRate(() ->{
                        try {
                            client.hearthbeat();
                            server.hearthbeat();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, 0, 5, TimeUnit.SECONDS);*/
                    } catch(IOException e){
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void stop() throws IOException{
        try{
            if(serversocket != null){
                serversocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
