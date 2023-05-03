package Networking;

import Messages.fromClientToServer.CreateGameMessage;
import Messages.Message;
import Messages.fromClientToServer.EnterGameMessage;
import Messages.fromClientToServer.StartGameMessage;
import ServerSide.Model.Player;
import ServerSide.VirtualView.VirtualGameView;
import ServerSide.VirtualView.VirtualLobbyView;
import ServerSide.VirtualView.VirtualPlayerView;
import ServerSide.Controller.*;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ServerManager extends Thread{
    private Socket clientsocket;

    private Boolean readerThreadActive;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Boolean isMessage;
    private Message message;
    private LobbyManager lobbymanager;

    // reference to the Virtual View classes (?)
    private VirtualLobbyView lobbyview;
    private VirtualGameView gameview;
    private VirtualPlayerView playerview;
    private ListNode ref;

    /**
     * Overview: ServerVirtualView constructor
     */
    public ServerManager(Socket clientsocket, LobbyManager lobbymanager, ListNode node) throws IOException {
        this.lobbymanager = lobbymanager;
        this.isMessage = false;
        readerThreadActive = false;
        this.message = null;
        this.clientsocket = clientsocket;
        this.ref = node;
        this.out = node.getWriter();
        this.in = new ObjectInputStream(clientsocket.getInputStream());
    }

    @Override
    /**
     * Overview: Overview of run method to handle receiving and sending messages through the socket
     */
    public void run(){
        System.out.println("server manager is running");
        while(!isInterrupted() && !clientsocket.isClosed()){

            // receiving
            if(!readerThreadActive) {
                readerThreadActive = true;
                Thread readerthread = new Thread(() -> {
                    try {
                        Message message = (Message) in.readObject();
                        //System.out.println(message);

                        if (message != null) {
                            handleMessage(message);
                            readerThreadActive = false;
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                readerthread.start();
            }

            // sending
            if(isMessage && message != null){
                System.out.println("the server has a message to be sent...");
                try {
                    this.out.writeObject(this.message);
                    this.out.flush();
                    System.out.println("sent");
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
        in.close();
        out.close();
        System.out.println("lost connection");
        clientsocket.close();
    }

    /**
     * Overview: heartbeat method
     */
    /*
    public Boolean heartbeat() throws IOException {
        if(!clientsocket.isClosed()) {
            writer.println("ping");
            writer.flush();
            //System.out.println("The server has sent the ping");
            String line = reader.readLine();
            //System.out.println(line + " is what I read");
            if(line.equals(null)){
                close();
                return false;
            } else {
                return true;
            }
        }

        return false;
    }
     */

    /**
     * Overview: GameVirtualView setter
     */
    public void setGameView(VirtualGameView view){ this.gameview = view; }

    /**
     * Overview: method aimed to handle an upcoming received message
     */
    public void handleMessage(Message message) throws IOException {
        Player player;
        System.out.println("there is a message to be read");
        // creation of the lobby

        switch(message.getType()) {
            case CREATEGAME:
                System.out.println("--------------------------- ENTERING THE LOBBY CREATION PROCEDURE ---------------------------");
                CreateGameMessage creategamemessage = (CreateGameMessage) message;
                String id = UUID.randomUUID().toString();
                lobbymanager.createlobby(id);
                this.lobbyview = lobbymanager.getLobby(id).getVirtualView();
                player = new Player(creategamemessage.getUsername(), true, id, this);
                this.playerview = (VirtualPlayerView) player.getObs();
                this.lobbyview.getObs().addPlayer(player);
                break;

            // entering an already existing lobby
            case ENTERGAME:
                EnterGameMessage entergamemessage = (EnterGameMessage) message;
                this.lobbyview = lobbymanager.getLobby(entergamemessage.getId()).getVirtualView();
                player = new Player(entergamemessage.getUsername(), false, entergamemessage.getId(), this);
                this.playerview = (VirtualPlayerView) player.getObs();
                this.lobbyview.getObs().addPlayer(player);
                break;

            // this action has to be made only by the creator of the lobby (no perche senno il game view di un altro player non viene settato)
            // starting the game
            case STARTGAME:
                StartGameMessage startgamemessage = (StartGameMessage) message;
                GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                setGameView(gamecontroller.getVirtualView());
                // e agli altri (cioè a chi non starta la partita) come lo si setta? lo faccio dal game controller
                break;

            //heartbeat procedure
            case PING:
                ref.setOk();
                break;
        }
    }



}
