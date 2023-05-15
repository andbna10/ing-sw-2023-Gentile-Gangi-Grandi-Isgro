package ClientSide.NetworkHandler;

import ClientSide.View.CLI.LogInCLI;
import ClientSide.View.GUI.LoginGUI;
import Messages.fromClientToServer.CreateGameMessage;
import Messages.fromClientToServer.EnterGameMessage;
import Networking.ClientManager;

public class LoginHandler implements LoginViewObserver{
    private ClientManager manager;
    private LogInCLI cli;

    private LoginGUI gui;

    public LoginGUI getGui() {
        return gui;
    }

    public void setGui(LoginGUI gui) {
        this.gui = gui;
    }

    /**
     * Overview: LoginHandler constructor
     */
    public LoginHandler(ClientManager manager){
        this.manager = manager;
        // qua si dovrebber poter lanciare il metodo che runnare la view (?)
    }

    /**
     * Overview: cli setter
     */
    public void setCli(LogInCLI cli){ this.cli = cli; }

    /**
     * Overview: cli getter
     */
    public LogInCLI getCli(){ return this.cli; }

    /**
     * Overview: method aimed to create a CreateGameMessage
     */
    public void creategamemessage(String sender, String username){
        CreateGameMessage message = new CreateGameMessage(username, sender);
        manager.sendMessage(message);
    }

    /**
     * Overview: method aimed to create a EnterGameMessage
     */
    public void entergamemessage(String sender, String username, String id){
        EnterGameMessage message = new EnterGameMessage(username, sender, id);
        manager.sendMessage(message);
    }

}
