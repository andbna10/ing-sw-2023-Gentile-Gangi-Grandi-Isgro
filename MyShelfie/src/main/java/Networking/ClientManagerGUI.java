package Networking;

import ClientSide.View.CLI.ReconnectCLI;
import ClientSide.NetworkHandler.*;
import Messages.Message;
import Messages.PingMessage;
import Messages.fromClientToServer.CloseRecoveryMessage;
import Messages.fromClientToServer.NPlayersInputMessage;
import Messages.fromServerToClient.SendDisconMessage;
import Messages.fromServerToClient.*;

import java.io.*;
import java.net.Socket;

public class ClientManagerGUI extends Thread{
    private Socket serversocket;
    private Boolean readerThreadActive;
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;

    // reference to the NetworkHandler classes
    private LobbyHandler lobbyhandler;
    private LoginHandler loginHandler;
    private PlayerHandler playerhandler;
    private GameHandler gamehandler;

    /**
     * ClientHandler constructor
     */
    public ClientManagerGUI(Socket socket) throws IOException {
        readerThreadActive = false;
        serversocket = socket;
        this.objectWriter = new ObjectOutputStream(socket.getOutputStream());
        this.objectReader = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Overview: loginhandler setter
     */
    public void setLoginHandler(LoginHandler loginhandler){ this.loginHandler = loginhandler; }

    /**
     * Overview: loginhandler getter
     */
    public LoginHandler getLoginHandler(){ return this.loginHandler; }

    @Override
    /**
     * Overview: run del thread
     */
    public void run(){
        //System.out.println("client manager is running");
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
                    // new LogInCLI(loginHandler).loginprocedure(); DA SOSTITUIRE CON LA GUI VERSIONOE
                }
                break;


            case RECONNECTED:
                ReconnectedMessage reconmsg = (ReconnectedMessage) message;

                LoginHandler tmp = new LoginHandler(this);
                //tmp.setCli(new LogInCLI(tmp));
                this.loginHandler = tmp;
                this.lobbyhandler = new LobbyHandler(this, (reconmsg.getUsernames()));
                this.gamehandler = new GameHandler(this, null);
                this.playerhandler = new PlayerHandler(this);

                sendMessage(new CloseRecoveryMessage());
                break;

            // update the lobby view
            case CREATELOBBYVIEW:
                CreatelobbyViewMessage createlobbyviewmessage = (CreatelobbyViewMessage) message;
                if (lobbyhandler == null) {
                    this.lobbyhandler = new LobbyHandler(this, createlobbyviewmessage.getUsernames());
                } else {
                    // here the last player added to the lobby is passed as parameter to the addPlayer() method
                    if(!message.getLast())
                        this.lobbyhandler.addPlayer(createlobbyviewmessage.getUsernames().get(createlobbyviewmessage.getUsernames().size() - 1));
                }
                lobbyhandler.getGui().updateTextArea(createlobbyviewmessage.getId(), createlobbyviewmessage.getUsernames(), createlobbyviewmessage.getOwner());
                break;

            // create the Game View
            case GAMEHASSTARTED:
                GameHasStartedMessage gamehasstartedmessage = (GameHasStartedMessage) message;
                lobbyhandler.getGui().closeLobbyWindow();
                gamehandler = new GameHandler(this, gamehasstartedmessage.getMessage());
                playerhandler = new PlayerHandler(this);
                playerhandler.setGui(gamehandler.getGui());
                // render the initial set up
                gamehandler.getGui().InitialSetUpRenderer(gamehasstartedmessage.getBoard(), gamehasstartedmessage.getPersonalPatternNumber(), gamehasstartedmessage.getCommon1(), gamehasstartedmessage.getCommon2(), gamehasstartedmessage.getPointsCom1(), gamehasstartedmessage.getPointsCom2());
                break;

            // create the Player View
            case CREATEPLAYERVIEW:
                break;

            // username in use
            case USERNAMEUSED:
                UsernameUsedMessage usernameusedmessage = (UsernameUsedMessage) message;
                loginHandler.getGui().validityCheck(usernameusedmessage.getMessage());
                loginHandler.runLoginGui();
                break;

            // ask for n players
            case ASKNPLAYERS:
                AskNPlayersMessage asknplayersmessage = (AskNPlayersMessage) message;
                int n = loginHandler.getGui().insertNumber(asknplayersmessage.getMessage());
                NPlayersInputMessage messagex = new NPlayersInputMessage(n, "prova");
                sendMessage(messagex);
                break;

            case OWNERCANSTARTGAME:
                OwnercanStartGameMessage ownercanstartgamemessage = (OwnercanStartGameMessage) message;
                lobbyhandler.getGui().buttonClickable(ownercanstartgamemessage.getId());
                //lobbyhandler.getCli().ownercanstart();
                break;

            case YOURTURN:
                YourTurnMessage yourturnmessage = (YourTurnMessage) message;
                //genericCLI.printMessage(yourturnmessage.getMessage());
                if(yourturnmessage.getUpddatedBookshelf()){
                    //playerhandler.getCli().printBookshelf(yourturnmessage.getBookshelf());
                    break;
                } else {
                    // here the player sees the opponents' bookshlef
                    for(int i=0; i<yourturnmessage.getBookshelfList().size(); i++){
                        //playerhandler.getCli().printOpponent(yourturnmessage.getBookshelfList().get(i), yourturnmessage.getUsernames().get(i));
                    }

                    // here the player can see its current bookshelf
                    //playerhandler.getCli().yourTurn(yourturnmessage.getBookshelf());

                    // player called to perform a move
                    /*TilesToTakeMessage messageToTake = new TilesToTakeMessage(playerhandler.getCli().getTotake(), playerhandler.getCli().getOrder(), playerhandler.getCli().getColumn(), "prova");
                    sendMessage(messageToTake);*/
                    break;
                }

                // access to the lobby denied
            case ACCESSDENIED:
                AccessDeniedMessage accessdeniedmessage = (AccessDeniedMessage) message;
                loginHandler.getGui().validityCheck(accessdeniedmessage.getMessage());
                loginHandler.runLoginGui();
                break;

            // beeing notified about the end of a turn
            case ENDTURN:
                EndTurnMessage endturnmessage = (EndTurnMessage) message;
                /*genericCLI.printMessage(endturnmessage.getMessage());
                gamehandler.getCli().printBoard(endturnmessage.getBoard());*/
                break;

            case REPEATTURN:
                RepeatTurnMessage repeatturnmessage = (RepeatTurnMessage) message;
                /*genericCLI.printMessage(repeatturnmessage.getMessage());
                playerhandler.getCli().yourTurn(null);
                TilesToTakeMessage messageToTake = new TilesToTakeMessage(playerhandler.getCli().getTotake(), playerhandler.getCli().getOrder(), playerhandler.getCli().getColumn(), "prova");
                sendMessage(messageToTake);*/
                break;

            case BOARDRESTORED:
                BoardRestoredMessage boardrestoredmessage = (BoardRestoredMessage) message;
                /*genericCLI.printMessage(boardrestoredmessage.getMessage());
                gamehandler.getCli().printBoard(boardrestoredmessage.getBoard());*/
                break;

            case NOTIFYCHECKCOMMON:
                NotifyCheckCommonMessage notifycheckcommonmessage = (NotifyCheckCommonMessage) message;
                //genericCLI.printMessage(notifycheckcommonmessage.getMessage());
                //notifycheckcommonmessage.getNewTokenPoints() -> is used to retrieve the new points to show for the token of the accomplished commongoal
                break;

            case LASTTURNTRIGGERED:
                LastTurnTriggeredMessage lastturntriggeredmessage = (LastTurnTriggeredMessage) message;
                //genericCLI.printMessage(lastturntriggeredmessage.getMessage());
                break;

            case ENDGAME:
                EndGameMessage endgamemessage = (EndGameMessage) message;
                /*genericCLI.printOutputEndGame(endgamemessage.getOutput());
                genericCLI.printMessage(endgamemessage.getMessage());*/
                break;

            case LOBBYSIZECHANGED:
                LobbyChangedMessage lobbychangedmessage = (LobbyChangedMessage) message;
                //genericCLI.printMessage(lobbychangedmessage.getMessage());
                break;

            // heartbeat procedure
            case PING:
                objectWriter.writeObject(new PingMessage("ping", "user0"));
                objectWriter.flush();
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
