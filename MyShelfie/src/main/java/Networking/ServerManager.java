package Networking;

import Messages.fromClientToServer.*;
import Messages.Message;
import Messages.fromServerToClient.AccessDeniedMessage;
import Messages.fromServerToClient.UsernameUsedMessage;
import Messages.fromServerToClient.YourTurnMessage;
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
    private LobbyManager lobbymanager;

    // reference to the Virtual View classes (?)
    private VirtualLobbyView lobbyview;
    private VirtualGameView gameview;
    private VirtualPlayerView playerview;
    private ListNode ref;
    private Server server;

    private String username = null;

    // number of times recovery loop is iterated
    private int closeTimeout = 10;

    /**
     * Overview: sets closingTimeout, default value 10
     */
    public void setCloseTimeout(int arg){this.closeTimeout = arg;}

    /**
     * Overview: procedure to recovery lost connection with client before closing definitely, returns "True" if connection was recovered
     */
    public boolean recoveryConnection() throws InterruptedException {
        boolean ret = false;
        Message arg = null;

        for(int i = 0; i < closeTimeout; i++) {

            try {
                Thread.sleep(1000);
                arg = (Message) in.readObject();

                if (arg != null) {
                    handleMessage(arg);
                    readerThreadActive = false;
                }

                ret = true;
            } catch (EOFException e) {
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }



        }

        return ret;
    }

    /**
     * Overview: ServerVirtualView constructor
     */
    public ServerManager(Socket clientsocket, LobbyManager lobbymanager, ListNode node, Server server, ObjectInputStream in) throws IOException {
        this.lobbymanager = lobbymanager;
        readerThreadActive = false;
        this.clientsocket = clientsocket;
        this.ref = node;
        this.out = node.getWriter();
        this.in = in;
        this.server = server;
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

                    } catch (EOFException e) { //gestione disconnessione client

                        try {

                            Boolean ok = false;
                            if(username != null) {
                                server.setDiscon(true);
                                server.setDisconRef(ref);
                                System.out.println("player disconnected, connection recovery procedure launched\n");

                                //lancio procedura
                                ok = recoveryConnection();
                            }

                            //chiusura ed eliminazione riferimenti
                            if (!ok) ref.close();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                });
                readerthread.start();
            }
        }
    }

    /**
     * Overview: GameVirtualView setter
     */
    public void setGameView(VirtualGameView view){ this.gameview = view; }

    /**
     * Overview: method aimed to handle an upcoming received message
     */
    public void handleMessage(Message message) throws IOException, InterruptedException {
        Player player;
        //System.out.println("there is a message to be read");

        switch(message.getType()) {
            // creation of the lobby
            case CREATEGAME:
                CreateGameMessage creategamemessage = (CreateGameMessage) message;
                if(lobbymanager.checkUsername(creategamemessage.getUsername())){
                    UsernameUsedMessage toSend = new UsernameUsedMessage();
                    sendMessage(toSend);
                } else {
                    System.out.println("--------------------------- ENTERING THE LOBBY CREATION PROCEDURE ---------------------------");

                    this.username = creategamemessage.getUsername();

                    String id = UUID.randomUUID().toString();
                    lobbymanager.createLobby(id);
                    this.lobbyview = lobbymanager.getLobby(id).getVirtualView();
                    player = new Player(creategamemessage.getUsername(), true, id, this);
                    this.playerview = (VirtualPlayerView) player.getObs();
                    this.lobbyview.getObs().addPlayer(player);
                }
                break;

            // entering an already existing lobby
            case ENTERGAME:
                EnterGameMessage entergamemessage = (EnterGameMessage) message;
                if(lobbymanager.checkInGame(entergamemessage.getId())){
                    AccessDeniedMessage accessdeniedmessage = new AccessDeniedMessage();
                    sendMessage(accessdeniedmessage);
                    break;
                }
                if(lobbymanager.checkUsername(entergamemessage.getUsername())) {
                    UsernameUsedMessage toSend = new UsernameUsedMessage();
                    sendMessage(toSend);
                    break;
                } else {
                    System.out.println("--------------------------- ENTERING THE ENTER EXISTING LOBBY PROCEDURE ---------------------------");

                    this.username = entergamemessage.getUsername();

                    //lobby online
                    if(entergamemessage.getId() == "online"){
                        if(lobbymanager.getLobby("online").getModel().getReadyToPlay()){
                            System.out.println("An online game is already started, please try again later!");
                            // qui probabilmente si passa la cli.

                        } else {
                            this.lobbyview = lobbymanager.getLobby(entergamemessage.getId()).getVirtualView();
                            player = new Player(entergamemessage.getUsername(), false, entergamemessage.getId(), this);
                            this.playerview = (VirtualPlayerView) player.getObs();
                            this.lobbyview.getObs().addPlayer(player);
                        }
                        break;
                    } else {
                        this.lobbyview = lobbymanager.getLobby(entergamemessage.getId()).getVirtualView();
                        player = new Player(entergamemessage.getUsername(), false, entergamemessage.getId(), this);
                        this.playerview = (VirtualPlayerView) player.getObs();
                        this.lobbyview.getObs().addPlayer(player);
                        break;
                    }
                }

            // this action has to be made only by the creator of the lobby
            // starting the game
            case STARTGAME:
                System.out.println(" --------------- GAME START ---------------");
                StartGameMessage startgamemessage = (StartGameMessage) message;
                GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                setGameView(gamecontroller.getVirtualView());
                break;

            // n players input
            case NPLAYERSINPUT:
                NPlayersInputMessage nplayersinputmessage = (NPlayersInputMessage) message;
                lobbyview.getObs().modifyfixednplayers(nplayersinputmessage.getN());
                break;

            //tiles draft
            case TILESTOTAKE:
                TilesToTakeMessage tilesToTakeMessage = (TilesToTakeMessage) message;
                playerview.getObs().playTurn(tilesToTakeMessage.getToTake(),tilesToTakeMessage.getOrder(),tilesToTakeMessage.getColumn());
                YourTurnMessage toSend = new YourTurnMessage(playerview.getObs().getModel().getBookshelf().getGameTiles(), true);
                this.sendMessage(toSend);
                playerview.getObs().getModel().getBookshelf().getGameTiles();
                gameview.getObs().getModel().advance();
                gameview.getObs().callTurn();
                break;

            //heartbeat procedure
            case PING:
                //System.out.println("pinged");
                ref.setOk();
                break;
        }
    }

    /**
     * Overview: method aimed to send a message
     */
    public void sendMessage(Message message){
        try {
            out.writeObject(message);
            out.flush();
            //System.out.println("sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Overview: username attribute getter
     */
    public String getUsername() {return this.username;}

    /**
     * Overview: socket references reset after reconnection
     */
    public void resetSocket(Socket arg1, ObjectOutputStream arg2, ObjectInputStream arg3) {
        this.clientsocket = arg1;
        this.out = arg2;
        this.in = arg3;
    }


}
