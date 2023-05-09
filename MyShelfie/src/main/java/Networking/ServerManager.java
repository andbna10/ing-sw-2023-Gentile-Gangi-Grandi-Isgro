package Networking;

import Messages.fromClientToServer.CreateGameMessage;
import Messages.Message;
import Messages.fromClientToServer.EnterGameMessage;
import Messages.fromClientToServer.NPlayersInputMessage;
import Messages.fromClientToServer.StartGameMessage;
import Messages.fromServerToClient.UsernameUsedMessage;
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
                    } catch (EOFException e) {

                        message = null;

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                readerthread.start();
            }

            // sending
            if(isMessage && message != null){
                //System.out.println("the server has a message to be sent...");
                try {
                    this.out.writeObject(this.message);
                    this.out.flush();
                    //System.out.println("sent");
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
    /*public void close() throws IOException{
        in.close();
        out.close();
        System.out.println("lost connection");
        clientsocket.close();
    }*/

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
    public void handleMessage(Message message) throws IOException, InterruptedException {
        Player player;
        //System.out.println("there is a message to be read");

        switch(message.getType()) {
            // creation of the lobby
            case CREATEGAME:
                CreateGameMessage creategamemessage = (CreateGameMessage) message;
                if(lobbymanager.checkUsername(creategamemessage.getUsername())){
                    UsernameUsedMessage toSend = new UsernameUsedMessage();
                    this.setIsMessage(true);
                    this.setMessage(toSend);
                } else {
                    System.out.println("--------------------------- ENTERING THE LOBBY CREATION PROCEDURE ---------------------------");

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
                if(lobbymanager.checkUsername(entergamemessage.getUsername())) {
                    UsernameUsedMessage toSend = new UsernameUsedMessage();
                    this.setIsMessage(true);
                    this.setMessage(toSend);
                    break;
                } else {
                    System.out.println("--------------------------- ENTERING THE ENTER EXISTING LOBBY PROCEDURE ---------------------------");
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
                StartGameMessage startgamemessage = (StartGameMessage) message;
                GameController gamecontroller = new GameController(startgamemessage.getIdLobby(), lobbymanager);
                setGameView(gamecontroller.getVirtualView());
                break;

            // n players input
            case NPLAYERSINPUT:
                NPlayersInputMessage nplayersinputmessage = (NPlayersInputMessage) message;
                lobbyview.getObs().modifyfixednplayers(nplayersinputmessage.getN());
                break;

            //heartbeat procedure
            case PING:
                //System.out.println("pinged");
                ref.setOk();
                break;
        }
    }



}
