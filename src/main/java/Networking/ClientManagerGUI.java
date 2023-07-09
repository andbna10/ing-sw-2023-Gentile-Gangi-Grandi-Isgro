package Networking;

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
import java.net.URISyntaxException;

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
    private boolean hasBeenNotified;

    /**
     * ClientHandler constructor
     */
    public ClientManagerGUI(Socket socket) throws IOException {
        hasBeenNotified = false;
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

    /**
     * Overview: thread run
     */
    @Override
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
                    } catch (IOException | ClassNotFoundException | URISyntaxException e) {
                        if(loginHandler != null && loginHandler.getGui() != null) {
                            loginHandler.getGui().showMessage("Server disconnection");
                            loginHandler.getGui().close();
                        }
                        if(gamehandler != null && gamehandler.getGui() != null) {
                            gamehandler.getGui().close();
                        }
                        if(lobbyhandler != null && lobbyhandler.getGui() != null){
                            lobbyhandler.getGui().closeLobbyWindow();
                        }

                        System.exit(0);
                    }
                });
                readerThread.start();
            }
        }
    }

    /**
     * Overview: method aimed to handle an upcoming received message
     * @author Andrea Isgrò
     * @author Franceco Gangi
     * @author Simone Grandi
     * @author Mirko Gentile
     * @param message upcoming message to be handled
     */
    public void handleMessage(Message message) throws IOException, URISyntaxException {
        //System.out.println("there is a message to be read");

        switch(message.getType()) {
            case SENDDISCON:
                /*SendDisconMessage sendDisconMessage = (SendDisconMessage) message;

                //need to reconnect case
                if(sendDisconMessage.getStatus()) {
                    new ReconnectCLI(new ReconnectHandler(this)).procedure();
                } else { //ordinary procedure case
                    //calls the login CLI
                    // new LogInCLI(loginHandler).loginprocedure();
                    //loginHandler.runLoginGui();
                }*/
                break;


            case RECONNECTED:
                /*ReconnectedMessage reconmsg = (ReconnectedMessage) message;

                LoginHandler tmp = new LoginHandler(this);
                //tmp.setCli(new LogInCLI(tmp));
                this.loginHandler = tmp;
                this.lobbyhandler = new LobbyHandler(this, (reconmsg.getUsernames()));
                this.gamehandler = new GameHandler(this, null);
                this.playerhandler = new PlayerHandler(this);

                sendMessage(new CloseRecoveryMessage());*/
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

                if(createlobbyviewmessage.getFromEnd()){
                    gamehandler.getGui().close();
                    lobbyhandler.runLobbyGUI();
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
                gamehandler.getGui().InitialSetUpRenderer(gamehasstartedmessage.getNumPlayers(), gamehasstartedmessage.getBoard(), gamehasstartedmessage.getPersonalPatternNumber(), gamehasstartedmessage.getCommon1(), gamehasstartedmessage.getCommon2(), gamehasstartedmessage.getPointsCom1(), gamehasstartedmessage.getPointsCom2());
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
                if(!hasBeenNotified) {
                    hasBeenNotified = true;
                    lobbyhandler.getGui().buttonClickable(ownercanstartgamemessage.getId());
                }
                break;

            case YOURTURN:
                YourTurnMessage yourturnmessage = (YourTurnMessage) message;
                if(yourturnmessage.getUpddatedBookshelf()){
                    // aimed to update the bookshelf after the turn
                    gamehandler.getGui().YourTurnRender(0, "Your Bookshelf", yourturnmessage.getBookshelf());
                } else {
                    // show message
                    //gamehandler.getGui().showMessage(yourturnmessage.getMessagegui());

                    // here the players sees the updated board
                    gamehandler.getGui().updateBoard(yourturnmessage.getBoard());

                    // here the player sees the opponents' bookshelf
                    for(int i=0; i<yourturnmessage.getBookshelfList().size(); i++){
                        gamehandler.getGui().YourTurnRender(i+1, yourturnmessage.getUsernames().get(i), yourturnmessage.getBookshelfList().get(i));
                    }

                    // here the player can see its current bookshelf
                    //gamehandler.getGui().YourTurnRender(0, "Your Bookshelf", yourturnmessage.getBookshelf());

                    // player called to perform a move
                    gamehandler.getGui().performTurn();
                    synchronized (gamehandler.getGui()){
                        if(gamehandler.getGui().getTotake()[0] == -1 && gamehandler.getGui().getOrder()[0] == -1 && gamehandler.getGui().getColumn() == -1){
                            try{
                                gamehandler.getGui().wait();
                            } catch (InterruptedException e){};
                        }
                    }
                    TilesToTakeMessage messageToTake = new TilesToTakeMessage(gamehandler.getGui().getTotake(), gamehandler.getGui().getOrder(), gamehandler.getGui().getColumn(), "prova");
                    sendMessage(messageToTake);
                }
                break;

            // access to the lobby denied
            case ACCESSDENIED:
                AccessDeniedMessage accessdeniedmessage = (AccessDeniedMessage) message;
                loginHandler.getGui().validityCheck(accessdeniedmessage.getMessage());
                loginHandler.runLoginGui();
                break;

            // being notified about the end of a turn
            case ENDTURN:
                EndTurnMessage endturnmessage = (EndTurnMessage) message;
                gamehandler.getGui().updateBoard(endturnmessage.board());
                break;

            case REPEATTURN:
                RepeatTurnMessage repeatturnmessage = (RepeatTurnMessage) message;
                gamehandler.getGui().showMessage(repeatturnmessage.getMessage());
                // player called to perform a move
                gamehandler.getGui().performTurn();
                synchronized (gamehandler.getGui()){
                    if(gamehandler.getGui().getTotake()[0] == -1 && gamehandler.getGui().getOrder()[0] == -1 && gamehandler.getGui().getColumn() == -1){
                        try{
                            gamehandler.getGui().wait();
                        } catch (InterruptedException e){};
                    }
                }
                TilesToTakeMessage messageToTake = new TilesToTakeMessage(gamehandler.getGui().getTotake(), gamehandler.getGui().getOrder(), gamehandler.getGui().getColumn(), "prova");
                sendMessage(messageToTake);
                break;

            case BOARDRESTORED:
                BoardRestoredMessage boardrestoredmessage = (BoardRestoredMessage) message;
                gamehandler.getGui().showMessage(boardrestoredmessage.getMessage());
                gamehandler.getGui().updateBoard(boardrestoredmessage.getBoard());
                break;

            case NOTIFYCHECKCOMMON:
                NotifyCheckCommonMessage notifycheckcommonmessage = (NotifyCheckCommonMessage) message;
                gamehandler.getGui().showMessage(notifycheckcommonmessage.getMessage());
                gamehandler.getGui().UpdateToken(notifycheckcommonmessage.getNewTokenPoints(), notifycheckcommonmessage.getCommon());
                break;

            case LASTTURNTRIGGERED:
                LastTurnTriggeredMessage lastturntriggeredmessage = (LastTurnTriggeredMessage) message;
                gamehandler.getGui().showMessage(lastturntriggeredmessage.getMessage());
                break;

            case ENDGAME:
                hasBeenNotified = false;
                EndGameMessage endgamemessage = (EndGameMessage) message;
                gamehandler.getGui().endgame(endgamemessage.getOutput());
                if(((EndGameMessage) message).getDiscon()){
                    gamehandler.getGui().showMessage("player quitted, ending match");
                    gamehandler.getGui().close();
                    lobbyhandler.runLobbyGUI();
                    break;
                }
                if(endgamemessage.getIsOwner()){
                    gamehandler.getGui().backToTheLobby(endgamemessage.getMessage());
                } else {
                    gamehandler.getGui().showMessage(endgamemessage.getMessage());
                }
                break;

            case LOBBYSIZECHANGED:
                LobbyChangedMessage lobbychangedmessage = (LobbyChangedMessage) message;
                lobbyhandler.getGui().showMessage(lobbychangedmessage.getMessage());
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
     * @author Andrea Isgrò
     * @param message message to be sent
     */
    public void sendMessage(Message message){
        try {
            objectWriter.writeObject(message);
            objectWriter.flush();
            //System.out.println("sent");
        } catch (IOException e) {
            if(loginHandler.getGui() != null) {
                loginHandler.getGui().showMessage("You cannot proceed...");
            }
        }
    }

}
