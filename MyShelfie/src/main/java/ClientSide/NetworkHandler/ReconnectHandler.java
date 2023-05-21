package ClientSide.NetworkHandler;

import ClientSide.View.CLI.ReconnectCLI;
import Messages.fromClientToServer.PostUserReconMessage;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

public class ReconnectHandler {

    private ClientManager manager;
    private ClientManagerGUI managergui;
    private ReconnectCLI cli;

    /**
     * Overview: LoginHandler constructor1 cli
     */
    public ReconnectHandler(ClientManager manager){
        this.manager = manager;
    }

    /**
     * Overview: LoginHandler constructor2 gui
     */
    public ReconnectHandler(ClientManagerGUI manager){
        this.managergui = manager;
    }

    /**
     * Overview: method to ask discon attribute status
     */
    public void sendpostuserreconmessage(String arg){
        PostUserReconMessage message = new PostUserReconMessage(arg);
        manager.sendMessage(message);
    }


}
