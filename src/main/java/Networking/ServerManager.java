package Networking;

import Messages.fromClientToServer.*;
import Messages.Message;
import Messages.fromServerToClient.*;
import ServerSide.Model.Player;
import ServerSide.VirtualView.VirtualGameView;
import ServerSide.VirtualView.VirtualLobbyView;
import ServerSide.VirtualView.VirtualPlayerView;
import ServerSide.Controller.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

public class ServerManager extends Thread{
    private Socket clientsocket;
    private Boolean readerThreadActive;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private LobbyManager lobbymanager;

    // reference to the Virtual View classes
    private VirtualLobbyView lobbyview;
    private VirtualGameView gameview;
    private VirtualPlayerView playerview;
    private ListNode ref;
    private Server server;

    private Message lastMessage;



    private String username = null;

    // number of times recovery loop is iterated
    private int closeTimeout = 10;

    /**
     * Overview: sets closingTimeout, default value 10
     */
    public void setCloseTimeout(int arg){this.closeTimeout = arg;}

    /**
     * Overview: procedure to recovery lost connection with client before closing definitely
     * @author Mirko Gentile
     * @return (true) if connection was recovered
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public boolean recoveryConnection() throws InterruptedException {
        boolean ret = false;
        Message arg = null;

        for(int i = 0; i < closeTimeout; i++) {

            try {
                Thread.sleep(0);
                arg = (Message) in.readObject();

                if (arg != null) {
                    //handleMessage(arg);
                    readerThreadActive = false;
                }

                ret = true;
                break;
            } catch (EOFException | SocketException e) {
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

    /**
     * Overview: Overview of run method to handle receiving and sending messages through the socket
     */
    @Override
    public void run(){
        System.out.println("server manager is running");

        Thread.currentThread().setName("Manager " + username);

        while(!isInterrupted() && !clientsocket.isClosed()){

            // receiving
            if(!readerThreadActive) {
                readerThreadActive = true;
                Thread readerthread = new Thread(() -> {

                    Thread.currentThread().setName("Listener " + username);

                    try {
                        Message message = (Message) in.readObject();
                        //System.out.println(message);

                        if (message != null) {
                            handleMessage(message);
                            readerThreadActive = false;
                        }

                    } catch (EOFException | SocketException e) { //gestione disconnessione client

                        try {

                            boolean ok = false;
                            if(username != null) {
                                server.setDiscon(true);
                                server.setDisconRef(ref);
                                //System.out.println("player disconnected, connection recovery procedure launched\n");

                                //lancio procedura
                                ok = recoveryConnection();
                            }

                            //chiusura ed eliminazione riferimenti
                            if (!ok) {
                                server.setDiscon(false);
                                server.setDisconRef(null);
                                ref.close();
                            }

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
     * @author Andrea Isgrò
     * @author Franceco Gangi
     * @author Simone Grandi
     * @author Mirko Gentile
     * @param message upcoming message to be handled
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
                    //System.out.println("--------------------------- ENTERING THE LOBBY CREATION PROCEDURE ---------------------------");

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
                int x = lobbymanager.checkInGame(entergamemessage.getId());
                if(x >=0){
                    AccessDeniedMessage accessdeniedmessage = new AccessDeniedMessage(x);
                    sendMessage(accessdeniedmessage);
                    break;
                }
                if(lobbymanager.checkUsername(entergamemessage.getUsername())) {
                    UsernameUsedMessage toSend = new UsernameUsedMessage();
                    sendMessage(toSend);
                    break;
                } else {
                    //System.out.println("--------------------------- ENTERING THE ENTER EXISTING LOBBY PROCEDURE ---------------------------");

                    this.username = entergamemessage.getUsername();

                    //random match
                    if(entergamemessage.getId().equals("random")){
                        if(lobbymanager.checkRandomLobbies() != null){
                            // there exist a random lobby that has room
                            this.lobbyview = lobbymanager.getLobby(lobbymanager.checkRandomLobbies()).getVirtualView();
                            player = new Player(entergamemessage.getUsername(), false, lobbymanager.checkRandomLobbies(), this);
                            this.playerview = (VirtualPlayerView) player.getObs();
                            this.lobbyview.getObs().addPlayer(player);
                            break;
                        } else {
                            // there not exist a random lobby with room
                            String id = UUID.randomUUID().toString();
                            lobbymanager.createLobby("random - "+id);
                            this.lobbyview = lobbymanager.getLobby("random - "+id).getVirtualView();
                            player = new Player(entergamemessage.getUsername(), true, "random - "+id, this);
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
                StartGameMessage startgamemessage = (StartGameMessage) message;
                if(lobbyview.getObs().getModel().getId().startsWith("random -")){
                    if(lobbyview.getObs().getModel().getPlayers().size() == lobbyview.getObs().getModel().getFixedNPlayers()){
                        GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                        setGameView(gamecontroller.getVirtualView());
                    } else {
                        LobbyChangedMessage toSend = new LobbyChangedMessage();
                        sendMessage(toSend);
                        lobbyview.getObs().getModel().notifyObserverPlayerAdded(startgamemessage.getIdLobby(), false);
                    }
                } else {
                    if(lobbyview.getObs().getModel().getPlayers().size()>1 && lobbyview.getObs().getModel().getPlayers().size()<=4){
                        GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                        setGameView(gamecontroller.getVirtualView());
                    } else {
                        LobbyChangedMessage toSend = new LobbyChangedMessage();
                        sendMessage(toSend);
                        lobbyview.getObs().getModel().notifyObserverPlayerAdded(startgamemessage.getIdLobby(), false);
                    }
                }
                break;

            // n players input
            case NPLAYERSINPUT:
                NPlayersInputMessage nplayersinputmessage = (NPlayersInputMessage) message;
                lobbyview.getObs().modifyfixednplayers(nplayersinputmessage.getN());
                break;

            //tiles draft
            case TILESTOTAKE:
                if(!gameview.getObs().getModel().getEnded()){
                    TilesToTakeMessage tilesToTakeMessage = (TilesToTakeMessage) message;
                    // check the goodness of the player move
                    if(gameview.getObs().verifyTurn(tilesToTakeMessage.getToTake(), tilesToTakeMessage.getColumn(), this)<3){
                        // if something wrong
                        RepeatTurnMessage toSend = new RepeatTurnMessage(gameview.getObs().verifyTurn(tilesToTakeMessage.getToTake(), tilesToTakeMessage.getColumn(), this));
                        sendMessage(toSend);
                        break;
                    } else {
                        // if all is good
                        playerview.getObs().playTurn(tilesToTakeMessage.getToTake(), tilesToTakeMessage.getOrder(), tilesToTakeMessage.getColumn());
                        // your turn, but only for updating the bookshelf (I'm using the same message of "yourTurnMessage")
                        YourTurnMessage toSend = new YourTurnMessage(playerview.getObs().getModel().getBookshelf().getGameTiles(), true);
                        this.sendMessage(toSend);
                        //check goals and bookshelf
                        playerview.getObs().check(gameview.getObs().getModel().getCommonGoals(), lobbyview.getObs().getModel().getId());


                        // advancing in the turn order of the game in the case this is the last turn
                        boolean ok = false;
                        if (gameview.getObs().getModel().getIsLastTurnStarted()) {
                            int advanceFinishOutput = gameview.getObs().getModel().advanceFinish();
                            if (advanceFinishOutput == 0) {
                                gameview.getObs().endGame(false, lobbyview.getObs().getModel().getId());

                                // bring players again in the lobby
                                lobbyview.getObs().getModel().notifyObserverPlayerAdded(lobbyview.getObs().getModel().getId(), false);
                                // tell the owner a new game can start
                                lobbyview.getObs().notifyOwner();
                            } else if( advanceFinishOutput == 1){
                                ok = true;
                            }
                        } else {
                            // advancing in the turn order of the game
                            ok = gameview.getObs().getModel().advance();
                        }
                        // calling the turn of the next player
                        if (!gameview.getObs().getModel().getEnded() && !ok) {
                            gameview.getObs().callTurn();
                        }
                        break;
                    }
                } else {
                    break;
                }

            case BACKTOTHELOBBY:
                // GUI exclusive

                // bring players again in the lobby
                lobbyview.getObs().getModel().notifyObserverPlayerAdded(lobbyview.getObs().getModel().getId(), true);
                // tell the owner a new game can start
                lobbyview.getObs().notifyOwner();
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
     * @author Andrea Isgrò
     * @author Mirko Gentile
     * @param message message to be sent
     */
    public void sendMessage(Message message){
        lastMessage = message;

        try {
            out.writeObject(message);
            out.flush();
        } catch (SocketException e) {

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
     * @author Mirko Gentile
     */
    public void resetSocket(Socket arg1, ObjectOutputStream arg2, ObjectInputStream arg3) {
        this.clientsocket = arg1;
        this.out = arg2;
        this.in = arg3;
    }

    /**
     * Overview: method aimed to send the last message
     * @author Mirko Gentile
     */
    public void sendLastMsg() {
        try {
            lastMessage.setLast();
            out.writeObject(lastMessage);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overview: lobbyview getter
     */
    public VirtualLobbyView getLobbyview() { return lobbyview; }

    /**
     * Overview: lobbyManager getter
     */
    public LobbyManager getLobbymanager(){ return this.lobbymanager; }

    /**
     * Overview: GameView getter
     */
    public VirtualGameView getGameview(){ return this.gameview; }

    /**
     * Overview: LobbyView getter
     */
    public VirtualLobbyView getLobbyView(){ return lobbyview; }

    /**
     * Overview: server getter
     */
    public Server getServer() {return this.server;}

}
