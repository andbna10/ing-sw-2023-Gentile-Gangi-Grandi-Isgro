package ClientSide.NetworkHandler;

import ClientSide.Cli.ReconnectCLI;
import Messages.fromClientToServer.PostUserReconMessage;
import Networking.ClientManager;

public class ReconnectHandler {

    private ClientManager manager;
    private ReconnectCLI cli;

    /**
     * Overview: LoginHandler constructor
     */
    public ReconnectHandler(ClientManager manager){
        this.manager = manager;
    }

    /**
     * Overview: method to ask discon attribute status
     */
    public void sendpostuserreconmessage(String arg){
        PostUserReconMessage message = new PostUserReconMessage(arg);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }


}