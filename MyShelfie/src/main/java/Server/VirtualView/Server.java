package Server.VirtualView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
                    // here the code is blocked until a client connects

                    System.out.println("Client connected");
                    // here we have to create the game class of the network handler ( e anche quella dei player in teoria) ??
                    // perchè è qui che arrivano i messaggi dal client credo...
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
