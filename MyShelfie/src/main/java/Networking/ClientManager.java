package Networking;

import Client.NetworkHandler.LobbyHandler;
import Client.NetworkHandler.LoginHandler;
import Messages.Message;
import Messages.MessageType;
import Messages.fromServerToClient.CreatelobbyViewMessage;

import java.io.*;
import java.net.Socket;

public class ClientManager extends Thread{
    private Socket serversocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Boolean isMessage;
    private Message message;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // reference to the NetworkHandler classes (?)
    private LobbyHandler lobbyhandler;
    private LoginHandler loginHandler;

    /**
     * ClientHandler constructor
     */
    public ClientManager(Socket socket) throws IOException {
        this.isMessage = false;
        this.serversocket = socket;
        this.reader = new BufferedReader(new InputStreamReader(this.serversocket.getInputStream()));
        this.writer = new PrintWriter(this.serversocket.getOutputStream(), true);
        this.in = new ObjectInputStream(this.serversocket.getInputStream());
        this.out = new ObjectOutputStream(this.serversocket.getOutputStream());
    }

    @Override
    /**
     * Overview: run del thread
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

                // update the lobby view
                if(message.getType() == MessageType.CREATELOBBYVIEW){
                    CreatelobbyViewMessage createlobbyviewmessage = (CreatelobbyViewMessage) message;
                    if(lobbyhandler == null) {
                        LobbyHandler lobbyhandler = new LobbyHandler(this, createlobbyviewmessage.getUsernames());
                        this.lobbyhandler = lobbyhandler;
                    } else {
                        this.lobbyhandler.addPlayer(createlobbyviewmessage.getUsernames().get(createlobbyviewmessage.getUsernames().size()-1));
                    }
                }

                // game can start ( it is always a lobby view update)
                if(message.getType() == MessageType.GAMECANSTART){
                    // bisognerebbe tipo chiamare un metodo in LobbyHandler per attivare il bottone start game !!!
                    // (vedere se il implementare il fatto che solo il creatore della lobby può cliccarlo)
                    // chi crea la lobby è marchiato come LobbyOwner (nel model )
                }
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
        in.close();
        out.close();
        serversocket.close();
    }

    /**
     * Overview: method aimed to notify the manager that there is a message to be sent trhough the socket
     */
    public void setIsMessage(Boolean status){ this.isMessage = status; }

    /**
     * Overview: hearthbeat method
     */
    public void hearthbeat() throws IOException {
        try{
            writer.println("ping");
            serversocket.setSoTimeout(10000);
            String response = reader.readLine();
            if(response == null){
                close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
