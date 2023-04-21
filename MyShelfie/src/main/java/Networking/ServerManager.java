package Networking;

import Messages.fromClientToServer.CreateGameMessage;
import Messages.Message;
import Messages.MessageType;
import Server.VirtualView.VirtualGameView;
import Server.VirtualView.VirtualLobbyView;
import Server.VirtualView.VirtualPlayerView;
import Server.Controller.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager extends Thread{
    private Socket clientsocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Boolean isMessage;
    private Message message;

    // reference to the Virtual View classes (?)
    private VirtualLobbyView lobbyview;
    private VirtualGameView gameview;
    private ArrayList<VirtualPlayerView> playerviews;

    /**
     * Overview: ServerVirtualView constructor
     */
    public ServerManager(Socket clientsocket) throws IOException {
        this.isMessage = false;
        this.clientsocket = clientsocket;
        this.reader = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
        this.writer = new PrintWriter(this.clientsocket.getOutputStream(), true);
        this.in = new ObjectInputStream(this.clientsocket.getInputStream());
        this.out = new ObjectOutputStream(this.clientsocket.getOutputStream());
    }

    @Override
    /**
     * Overview: Overview of run method to handle receiving and sending messages through the socket
     */
    public void run(){
        while(!isInterrupted()){
            // hearthbeat
            try {
                hearthbeat();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // receiving
            try {
                Message message = (Message)in.readObject();
                // creation of the lobby
                if(message.getType() == MessageType.CREATEGAME){
                    CreateGameMessage creategamemessage = (CreateGameMessage) message;
                    LobbyController lobbycontroller = new LobbyController(this);
                    this.lobbyview = lobbycontroller.getVirtualview();
                    this.lobbyview.getObs().addPlayer(creategamemessage.getUsername());
                }
                // here I would insert all the other cases
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // sending
            if(isMessage){
                try {
                    this.out.writeObject(this.message);
                    this.out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                setIsMessage(false);
                setMessage(null);
            }
        }
    }

    /**
     * Overview: method aimed to notify the manager that there is a message to be sent trhough the socket
     */
    public void setIsMessage(Boolean status){ this.isMessage = status; }

    /**
     * Overview: method aimed to set a proper message as the attirbute
     */
    public void setMessage(Message message){ this.message = message; }

    /**
     * Overview: method aimed to close resources
     */
    public void close() throws IOException{
        reader.close();
        writer.close();
        clientsocket.close();
    }

    /**
     * Overview: hearthbeat method
     */
    public void hearthbeat() throws IOException {
        try{
            writer.println("ping");
            clientsocket.setSoTimeout(10000);
            String response = reader.readLine();
            if(response == null){
                close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
