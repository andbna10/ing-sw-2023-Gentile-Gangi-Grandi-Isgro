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
        serversocket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        this.objectWriter = new ObjectOutputStream(socket.getOutputStream());
        this.objectReader = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    /**
     * Overview: run del thread
     */
    public void run(){
        System.out.println("client manager is running");
        while(!isInterrupted()){

            // receiving
            try {
                Message message = (Message)objectReader.readObject();
                System.out.println(message);
                if(message != null) {
                    // update the lobby view
                    if (message.getType() == MessageType.CREATELOBBYVIEW) {
                        CreatelobbyViewMessage createlobbyviewmessage = (CreatelobbyViewMessage) message;
                        if (lobbyhandler == null) {
                            LobbyHandler lobbyhandler = new LobbyHandler(this, createlobbyviewmessage.getUsernames());
                            this.lobbyhandler = lobbyhandler;
                        } else {
                            // here the last player added to the lobby is passed as parameter to the addPlayer() method
                            this.lobbyhandler.addPlayer(createlobbyviewmessage.getUsernames().get(createlobbyviewmessage.getUsernames().size() - 1));
                        }
                    }

                    // game can start (it is always a lobby view update)
                    if (message.getType() == MessageType.GAMECANSTART) {
                        // bisognerebbe tipo chiamare un metodo in LobbyHandler per attivare il bottone start game !!!
                        // (vedere se implementare il fatto che solo il creatore della lobby può cliccarlo)
                        // chi crea la lobby è marchiato come LobbyOwner (nel model )
                    }

                    // create the Game View
                    if (message.getType() == MessageType.GAMEHASSTARTED) {
                        GameHasStartedMessage gamehasstartedmessage = (GameHasStartedMessage) message;
                        GameHandler gamehandler = new GameHandler(this, gamehasstartedmessage.getMessage());
                    }

                    // create the Player View
                    if (message.getType() == MessageType.CREATEPLAYERVIEW) {

                    }
                }
            } catch (IOException | ClassNotFoundException e) {}

            // sending
            if(isMessage && message != null){
                System.out.println("the client has a message to be sent...");
                try {
                    objectWriter.writeObject(message);
                    objectWriter.flush();
                    System.out.println("sent");
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
    public void close() throws IOException{
        reader.close();
        writer.close();
        objectReader.close();
        objectWriter.close();
        System.out.println("lost connection");
        serversocket.close();
    }

    /**
     * Overview: method aimed to notify the manager that there is a message to be sent trhough the socket
     */
    public void setIsMessage(Boolean status){ this.isMessage = status; }

    /**
     * Overview: heartbeat method
     */
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

}
