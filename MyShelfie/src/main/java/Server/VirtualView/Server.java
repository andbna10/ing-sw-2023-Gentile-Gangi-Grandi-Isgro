package Server.VirtualView;

import Client.NetworkHandler.ClientManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private int port;
    private ServerSocket serversocket;
    private Socket clientsocket;
    private VirtualGameView gameview;
    private ArrayList<VirtualPlayerView> playersview;

    /**
     * Overview: constructor of the server class aimed to construct the socket for communication server-client
     */
    public Server(int port){
        this. playersview = new ArrayList<>();
        this.port = port;
    }

    /**
     * Overview: method aimed to set the virtual game view
     */
    public void setGameview(VirtualGameView view){ this.gameview = view; }

    /**
     * Overview: method aimed to add a Virtual Player view
     */
    public void addPlayerView(VirtualPlayerView view){ this.playersview.add(view); }

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

                    // new thread to manage the client connection
                    ClientManager client = new ClientManager(clientsocket);
                    ServerManager server = new ServerManager(clientsocket);
                    ScheduledExecutorService hearthbeatProcedure = Executors.newSingleThreadScheduledExecutor();
                    hearthbeatProcedure.scheduleAtFixedRate(() ->{
                        try {
                            client.hearthbeat();
                            server.hearthbeat();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, 0, 5, TimeUnit.SECONDS);
                    //client.start();

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
