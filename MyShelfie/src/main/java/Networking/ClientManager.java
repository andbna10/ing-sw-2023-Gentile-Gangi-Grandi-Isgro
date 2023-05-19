package Networking;

import ClientSide.View.CLI.LogInCLI;
import ClientSide.View.CLI.ReconnectCLI;
import ClientSide.NetworkHandler.*;
import Messages.Message;
import Messages.PingMessage;
import Messages.fromClientToServer.CloseRecoveryMessage;
import Messages.fromClientToServer.NPlayersInputMessage;
import Messages.fromClientToServer.TilesToTakeMessage;
import Messages.fromServerToClient.SendDisconMessage;
import Messages.fromServerToClient.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager extends Thread{
    private Socket serversocket;
    private Boolean readerThreadActive;
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;

    // reference to the NetworkHandler classes (?)
    private LobbyHandler lobbyhandler;
    private LoginHandler loginHandler;
    private PlayerHandler playerhandler;
    private GameHandler gamehandler;

    /**
     * ClientHandler constructor
     */
    public ClientManager(Socket socket) throws IOException {
        readerThreadActive = false;
        serversocket = socket;
        this.objectWriter = new ObjectOutputStream(socket.getOutputStream());
        this.objectReader = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Overview: loginhandler setter
     */
    public void setLoginHandler(LoginHandler loginhandler){ this.loginHandler= loginhandler; }

    /**
     * Overview: loginhandler getter
     */
    public LoginHandler getLoginHandler(){ return this.loginHandler; }

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
        }
    }

    /**
     * Overview: method aimed to handle an upcoming received message
     */
    public void handleMessage(Message message) throws IOException {
        //System.out.println("there is a message to be read");

        switch(message.getType()) {
            case SENDDISCON:
                SendDisconMessage sendDisconMessage = (SendDisconMessage) message;

                //need to reconnect case
                if(sendDisconMessage.getStatus()) {
                    new ReconnectCLI(new ReconnectHandler(this)).procedure();
                } else { //ordinary procedure case
                    //calls the login CLI
                    new LogInCLI(loginHandler).loginprocedure();
                }
                break;


            case RECONNECTED:
                ReconnectedMessage reconmsg = (ReconnectedMessage) message;

                LoginHandler tmp = new LoginHandler(this);
                tmp.setCli(new LogInCLI(tmp));
                this.loginHandler = tmp;
                this.lobbyhandler = new LobbyHandler(this, (reconmsg.getUsernames()));
                this.gamehandler = new GameHandler(this, null);
                this.playerhandler = new PlayerHandler(this);

                sendMessage(new CloseRecoveryMessage());

                break;
            // update the lobby view
            case CREATELOBBYVIEW:
                //System.out.println("--------------------------- ENTERING THE CREATE LOBBY VIEW PROCEDURE ---------------------------");
                CreatelobbyViewMessage createlobbyviewmessage = (CreatelobbyViewMessage) message;
                if (lobbyhandler == null) {
                    LobbyHandler lobbyhandler = new LobbyHandler(this, createlobbyviewmessage.getUsernames());
                    this.lobbyhandler = lobbyhandler;
                } else {
                    // here the last player added to the lobby is passed as parameter to the addPlayer() method

                    if(!message.getLast())
                        this.lobbyhandler.addPlayer(createlobbyviewmessage.getUsernames().get(createlobbyviewmessage.getUsernames().size() - 1));
                }
                // da spostare in lobby cli.
                // chiudere la login gui e aprire la lobby gui.
                System.out.println("The id of the lobby is: "+ createlobbyviewmessage.getId());
                for(String s: createlobbyviewmessage.getUsernames()){
                    if(s == createlobbyviewmessage.getOwner()){
                        System.out.println(s+" OWNER");
                        continue;
                    }
                    System.out.println(s);
                }
                //
                break;

            // game can start (it is always a lobby view update)
            case GAMECANSTART:
                //System.out.println("--------------------------- GAME CAN START ---------------------------");
                // bisognerebbe tipo chiamare un metodo in LobbyHandler per attivare il bottone start game !!!
                // (vedere se implementare il fatto che solo il creatore della lobby può cliccarlo)
                // chi crea la lobby è marchiato come LobbyOwner (nel model)

                break;

            // create the Game View
            case GAMEHASSTARTED:
                GameHasStartedMessage gamehasstartedmessage = (GameHasStartedMessage) message;
                gamehandler = new GameHandler(this, gamehasstartedmessage.getMessage());
                playerhandler = new PlayerHandler(this);
                System.out.println(gamehasstartedmessage.getMessage());
                gamehandler.getCli().printBoard(gamehasstartedmessage.getBoard());
                playerhandler.getCli().initialSetUp(gamehasstartedmessage.getPersonalGoal(), gamehasstartedmessage.getCommon1(), gamehasstartedmessage.getCommon2());
                break;

            // create the Player View
            case CREATEPLAYERVIEW:
                break;

            // username in use
            case USERNAMEUSED:
                UsernameUsedMessage usernameusedmessage = (UsernameUsedMessage) message;
                System.out.println(usernameusedmessage.getMessage());
                this.loginHandler.getCli().loginprocedure();
                break;

            // ask for n players
            case ASKNPLAYERS:
                AskNPlayersMessage asknplayersmessage = (AskNPlayersMessage) message;
                System.out.println(asknplayersmessage.getMessage());
                //lobbyhandler.nplayersinputmessage("prova");
                // forse questo lo deve ritornare una classe CLI?? da vedere
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a number: ");
                String input = scanner.nextLine();
                int n = Integer.parseInt(input);
                //
                NPlayersInputMessage messagex = new NPlayersInputMessage(n, "prova");
                sendMessage(messagex);
                break;

            case OWNERCANSTARTGAME:
                OwnercanStartGameMessage ownercanstartgamemessage = (OwnercanStartGameMessage) message;
                lobbyhandler.getCli().ownercanstart();
                break;

            case YOURTURN:
                YourTurnMessage yourturnmessage = (YourTurnMessage) message;
                System.out.println(yourturnmessage.getMessage());
                if(yourturnmessage.getUpddatedBookshelf()){
                    playerhandler.getCli().printBookshelf(yourturnmessage.getBookshelf());
                    break;
                } else {
                    playerhandler.getCli().yourTurn(yourturnmessage.getBookshelf());
                    TilesToTakeMessage messageToTake = new TilesToTakeMessage(playerhandler.getCli().getTotake(), playerhandler.getCli().getOrder(), playerhandler.getCli().getColumn(), "prova");
                    sendMessage(messageToTake);
                    break;
                }

            // access to the lobby denied
            case ACCESSDENIED:
                AccessDeniedMessage accessdeniedmessage = (AccessDeniedMessage) message;
                System.out.println(accessdeniedmessage.getMessage());
                loginHandler.getCli().loginprocedure();
                break;

            // beeing notified about the end of a turn
            case ENDTURN:
                // forse a fine turno di ogni giocatore, bisognerebbe stampare un resoconto di tutto il tavolo, board game e bookshelves.
                // per ora a fine turno di ogni giocatore stampiamo solo la board game aggiornata.
                EndTurnMessage endturnmessage = (EndTurnMessage) message;
                System.out.println(endturnmessage.getMessage());
                gamehandler.getCli().printBoard(endturnmessage.getBoard());
                break;

            case REPEATTURN:
                RepeatTurnMessage repeatturnmessage = (RepeatTurnMessage) message;
                System.out.println(repeatturnmessage.getMessage());
                playerhandler.getCli().yourTurn(null);
                TilesToTakeMessage messageToTake = new TilesToTakeMessage(playerhandler.getCli().getTotake(), playerhandler.getCli().getOrder(), playerhandler.getCli().getColumn(), "prova");
                sendMessage(messageToTake);
                break;

            case BOARDRESTORED:
                BoardRestoredMessage boardrestoredmessage = (BoardRestoredMessage) message;
                System.out.println(boardrestoredmessage.getMessage());
                gamehandler.getCli().printBoard(boardrestoredmessage.getBoard());
                break;

            case NOTIFYCHECKCOMMON:
                NotifyCheckCommonMessage notifycheckcommonmessage = (NotifyCheckCommonMessage) message;
                System.out.println(notifycheckcommonmessage.getMessage());
                break;

            case LASTTURNTRIGGERED:
                LastTurnTriggeredMessage lastturntriggeredmessage = (LastTurnTriggeredMessage) message;
                System.out.println(lastturntriggeredmessage.getMessage());
                break;

            case ENDGAME:
                EndGameMessage endgamemessage = (EndGameMessage) message;
                System.out.println(endgamemessage.getOutput());
                System.out.println(endgamemessage.getMessage());
                break;

            // heartbeat procedure
            case PING:
                objectWriter.writeObject(new PingMessage("ping", "user0"));
                objectWriter.flush();
                //System.out.println("pinged");
                break;



        }
    }

    /**
     * Overview: method aimed to send a message
     */
    public void sendMessage(Message message){
        try {
            objectWriter.writeObject(message);
            objectWriter.flush();
            //System.out.println("sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
