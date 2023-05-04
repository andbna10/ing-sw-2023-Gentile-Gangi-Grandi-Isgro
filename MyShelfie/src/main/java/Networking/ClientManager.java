package Networking;

import ClientSide.NetworkHandler.GameHandler;
import ClientSide.NetworkHandler.LobbyHandler;
import ClientSide.NetworkHandler.LoginHandler;
import Messages.Message;
import Messages.PingMessage;
import Messages.fromServerToClient.CreatelobbyViewMessage;
import Messages.fromServerToClient.GameHasStartedMessage;
import Messages.fromServerToClient.UsernameUsedMessage;

import java.io.*;
import java.net.Socket;

public class ClientManager extends Thread{
    private Socket serversocket;
    private Boolean isMessage;
    private Boolean readerThreadActive;
    private Message message;
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;

    // reference to the NetworkHandler classes (?)
    private LobbyHandler lobbyhandler;
    private LoginHandler loginHandler;

    /**
     * ClientHandler constructor
     */
    public ClientManager(Socket socket) throws IOException {
        isMessage = false;
        message = null;
        readerThreadActive = false;
        serversocket = socket;
        this.objectWriter = new ObjectOutputStream(socket.getOutputStream());
        this.objectReader = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    /**
     * Overview: run del thread
     */
    public void run(){
        System.out.println("client manager is running");
        while(!isInterrupted()) {

            // receiving
            if (!readerThreadActive) {
                readerThreadActive = true;
                Thread readerThread = new Thread(() -> {
                    try {
                        Message message = (Message) objectReader.readObject();
                        //System.out.println(message);

                        if (message != null) {
                            handleMessage(message);
                            readerThreadActive = false;
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                readerThread.start();
            }


            // sending
            if(isMessage && message != null){
                //System.out.println("the client has a message to be sent...");
                try {
                    objectWriter.writeObject(message);
                    objectWriter.flush();
                    //System.out.println("sent");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                this.setIsMessage(false);
                this.setMessage(null);
            }
        }
    }

    /**
     * Overview: method aimed to set a proper message as the attirbute
     */
    public void setMessage(Message message){ this.message = message; }

    /**
     * Overview: method aimed to close resources
     */
    /*
    public void close() throws IOException{
        objectReader.close();
        objectWriter.close();
        System.out.println("lost connection");
        serversocket.close();
    }
    */

    /**
     * Overview: method aimed to notify the manager that there is a message to be sent trhough the socket
     */
    public void setIsMessage(Boolean status){ this.isMessage = status; }

    /**
     * Overview: heartbeat method
     */
    /*
    public Boolean heartbeat() throws IOException{
        if(!serversocket.isClosed()) {
            writer.println("ping");
            writer.flush();
            //System.out.println("the client has sent the ping");
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
     * Overview: method aimed to handle an upcoming received message
     */
    public void handleMessage(Message message) throws IOException {
        //System.out.println("there is a message to be read");

        switch(message.getType()) {
            // update the lobby view
            case CREATELOBBYVIEW:
                System.out.println("--------------------------- ENTERING THE CREATE LOBBY VIEW PROCEDURE ---------------------------");
                CreatelobbyViewMessage createlobbyviewmessage = (CreatelobbyViewMessage) message;
                System.out.println("The id of the lobby is: "+ createlobbyviewmessage.getId());
                for(String s: createlobbyviewmessage.getUsernames()){
                    System.out.println(s);
                }
                if (lobbyhandler == null) {
                    LobbyHandler lobbyhandler = new LobbyHandler(this, createlobbyviewmessage.getUsernames());
                    this.lobbyhandler = lobbyhandler;
                } else {
                    // here the last player added to the lobby is passed as parameter to the addPlayer() method
                    this.lobbyhandler.addPlayer(createlobbyviewmessage.getUsernames().get(createlobbyviewmessage.getUsernames().size() - 1));
                }
                break;

            // game can start (it is always a lobby view update)
            case GAMECANSTART:
                System.out.println("--------------------------- GAME CAN START ---------------------------");
                // bisognerebbe tipo chiamare un metodo in LobbyHandler per attivare il bottone start game !!!
                // (vedere se implementare il fatto che solo il creatore della lobby può cliccarlo)
                // chi crea la lobby è marchiato come LobbyOwner (nel model )
                break;

            // create the Game View
            case GAMEHASSTARTED:
                GameHasStartedMessage gamehasstartedmessage = (GameHasStartedMessage) message;
                GameHandler gamehandler = new GameHandler(this, gamehasstartedmessage.getMessage());
                break;

            // create the Player View
            case CREATEPLAYERVIEW:
                break;

            // username in use
            case USERNAMEUSED:
                UsernameUsedMessage usernameusedmessage = (UsernameUsedMessage) message;
                System.out.println(usernameusedmessage.getMessage());
                break;

            // heartbeat procedure
            case PING:
                objectWriter.writeObject(new PingMessage("ping", "user0"));
                objectWriter.flush();
                //System.out.println("pinged");
                break;



        }
    }

}
