package ClientSide.NetworkHandler;

import ClientSide.Cli.LogInCLI;
import ClientSide.Cli.ReconnectCLI;
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

}
