package Networking;

import Client.NetworkHandler.LoginHandler;
import Server.Controller.LobbyManager;
import Server.VirtualView.VirtualGameView;
import Server.VirtualView.VirtualPlayerView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private int port;
    private ServerSocket serversocket;
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
                    Socket clientsocket = serversocket.accept();
                    System.out.println("Client connected");

                    // initialization of the manager whose aim is to manage the new client connection with the server ( for the server )
                    ServerManager server = new ServerManager(clientsocket, lobbymanager);

                    System.out.println("I'm starting the heartbeatprocedure - server");
                    ScheduledExecutorService hearthbeatProcedure = Executors.newSingleThreadScheduledExecutor();
                    hearthbeatProcedure.scheduleAtFixedRate(() ->{
                        try {
                            server.heartbeat();
                            /*PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
                            //TimeUnit.SECONDS.sleep(5);
                            out.println("porco demonio");
                            System.out.println("Data sent");*/
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, 5, 5, TimeUnit.SECONDS);

                    // here we start the thread aka manager with which client and server exchange messages
                    //server.start();


                    // prova - così funziona
                    /*PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
                    //TimeUnit.SECONDS.sleep(5);
                    out.println("porco demonio");
                    System.out.println("Data sent");*/


                    // probabilmente non serve
                    /*ScheduledExecutorService hearthbeatProcedure = Executors.newSingleThreadScheduledExecutor();
                    hearthbeatProcedure.scheduleAtFixedRate(() ->{
                        try {
                            client.hearthbeat();
                            server.hearthbeat();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, 0, 5, TimeUnit.SECONDS);*/
                    //


                    } catch(IOException e){
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // potrebbe non servire perchè le socket le chiudo dai manager se serve
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
