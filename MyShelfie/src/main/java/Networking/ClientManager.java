package Networking;

import Client.NetworkHandler.GameHandler;
import Client.NetworkHandler.LobbyHandler;
import Client.NetworkHandler.LoginHandler;
import Messages.Message;
import Messages.MessageType;
import Messages.fromServerToClient.CreatelobbyViewMessage;
import Messages.fromServerToClient.GameHasStartedMessage;

import java.awt.desktop.SystemSleepEvent;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientManager extends Thread{
    private Socket serversocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Boolean isMessage;
    private Message message;
    //private ObjectInputStream in;
    //private ObjectOutputStream out;

    // reference to the NetworkHandler classes (?)
    private LobbyHandler lobbyhandler;
    private LoginHandler loginHandler;

    /**
     * ClientHandler constructor
     */
    public ClientManager(Socket socket) throws IOException {
        isMessage = false;
        message = null;
        serversocket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
        // chiedere se è giusto
        //this.out = new ObjectOutputStream(socket.getOutputStream());
        //this.in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    /**
     * Overview: run del thread
     */
    public void run(){
        System.out.println("client manager is running");

    }

    /**
     * Overview: method aimed to set a proper message as the attirbute
     */
    public void setMessage(Message message){ this.message = message; }

    /**
     * Overview: method aimed to close resources
     */
    public void close() throws IOException{
        //reader.close();
        //writer.close();
        //in.close();
        //out.close();
        serversocket.close();
    }

    /**
     * Overview: method aimed to notify the manager that there is a message to be sent trhough the socket
     */
    public void setIsMessage(Boolean status){ this.isMessage = status; }

    /**
     * Overview: heartbeat method
     */
    /*public void heartbeatsending() throws IOException {
        System.out.println("sending");
        writer.write("ping");
        writer.flush();
        System.out.println("the client has sent the ping message");
        //serversocket.setSoTimeout(10000);
            String response = reader.readLine();
            System.out.println("the response is "+response);
            if(!response.equals("ping")){
                close();
            }
    }*/

    public void heartbeat() throws IOException{
        /*System.out.println("sending");
        writer.write("ping");
        System.out.println("the client has sent the ping");*/
        String line = reader.readLine();
        System.out.println(line+" is what I read");
    }


}
