package Networking;

import Messages.fromClientToServer.CreateGameMessage;
import Messages.Message;
import Messages.MessageType;
import Messages.fromClientToServer.EnterGameMessage;
import Messages.fromClientToServer.StartGameMessage;
import Server.Model.Player;
import Server.VirtualView.VirtualGameView;
import Server.VirtualView.VirtualLobbyView;
import Server.VirtualView.VirtualPlayerView;
import Server.Controller.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ServerManager extends Thread{
    private Socket clientsocket;
    //private BufferedReader reader;
    //private PrintWriter writer;
    //private ObjectInputStream in;
    //private ObjectOutputStream out;
    private Boolean isMessage;
    private Message message;
    private LobbyManager lobbymanager;

    // reference to the Virtual View classes (?)
    private VirtualLobbyView lobbyview;
    private VirtualGameView gameview;

    // forse non una lista, perchè per ogni giocatore avremo un server manager e un client manager personali
    // credo che si mettono tutti, poi nell'utilizzo durante la partita si usano in base al sender del messaggio e si modificano le classi corrispondenti
    private VirtualPlayerView playerview;

    /**
     * Overview: ServerVirtualView constructor
     */
    public ServerManager(Socket clientsocket, LobbyManager lobbymanager) throws IOException {
        this.lobbymanager = lobbymanager;
        this.isMessage = false;
        this.clientsocket = clientsocket;
        //this.playerviews = new ArrayList<>(); // forse non serve la lista per tutti ma uno solo
        //this.writer = new PrintWriter(clientsocket.getOutputStream(), true);
        //this.reader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        //this.out = new ObjectOutputStream(clientsocket.getOutputStream());
        //this.in = new ObjectInputStream(clientsocket.getInputStream());
    }

    @Override
    /**
     * Overview: Overview of run method to handle receiving and sending messages through the socket
     */
    public void run(){
        System.out.println("server manager is running");
        while(!isInterrupted()){
            // receiving
            /*try {
                Message message = (Message)in.readObject();

                // creation of the lobby
                if(message.getType() == MessageType.CREATEGAME){
                    CreateGameMessage creategamemessage = (CreateGameMessage) message;

                    String id = UUID.randomUUID().toString();
                    lobbymanager.createlobby(id);
                    this.lobbyview = lobbymanager.getLobby(id).getVirtualView();
                    Player player = new Player(creategamemessage.getUsername(), true, id, this);
                    this.playerview = (VirtualPlayerView) player.getObs();
                    this.lobbyview.getObs().addPlayer(player);
                 }

                // entering an already existing lobby
                if(message.getType() == MessageType.ENTERGAME){
                    EnterGameMessage entergamemessage = (EnterGameMessage) message;
                    this.lobbyview = lobbymanager.getLobby(entergamemessage.getId()).getVirtualView();
                    Player player = new Player(entergamemessage.getUsername(), false, entergamemessage.getId(), this);
                    this.playerview = (VirtualPlayerView) player.getObs();
                    this.lobbyview.getObs().addPlayer(player);
                }

                // this action has to be made only by the creator of the lobby (no perche senno il game view di un altro player non viene settato)
                // starting the game
                if(message.getType() == MessageType.STARTGAME){
                    StartGameMessage startgamemessage = (StartGameMessage) message;
                    GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                    setGameView(gamecontroller.getVirtualView());
                    // e agli altri (cioè a chi non starta la partita) come lo si setta? lo faccio dal game controller
                }

            } catch (IOException | ClassNotFoundException e) {
                //throw new RuntimeException(e);
                continue;
            }*/

            // sending
            /*if(isMessage){
                try {
                    this.out.writeObject(this.message);
                    this.out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                setIsMessage(false);
                setMessage(null);
            }*/
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
        //reader.close();
        //writer.close();
        //in.close();
        //out.close();
        clientsocket.close();
    }

    /**
     * Overview: heartbeat method
     */
    public void heartbeat() throws IOException {
        PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
        out.println("porco demonio\n");
        System.out.println("Data sent");
        //System.out.println("sending");
        /*writer.write("ping");
        System.out.println("the server has sent the ping");*/
        /*String line = reader.readLine();
        System.out.println(line+" is what I read");*/
    }

    /*public void heartbeatreceiving() throws IOException {
        System.out.println("receiving");
        String response = reader.readLine();
        System.out.println("the response is "+response);
        if(!response.equals("ping")){
            close();
        }
    }*/

    /**
     * Overview: GameVirtualView setter
     */
    public void setGameView(VirtualGameView view){ this.gameview = view; }



}
