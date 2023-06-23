package ClientSide.NetworkHandler;

import ClientSide.View.CLI.LogInCLI;
import ClientSide.View.GUI.LoginGUI;
import Messages.fromClientToServer.CreateGameMessage;
import Messages.fromClientToServer.EnterGameMessage;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

import java.io.IOException;

public class LoginHandler implements LoginViewObserver{
    private ClientManager manager;
    private ClientManagerGUI managergui;
    private LogInCLI cli;
    private LoginGUI gui;

    /**
     * Overview: LoginHandler constructor1 cli
     */
    public LoginHandler(ClientManager manager){
        this.manager = manager;
    }

    /**
     * Overview: LoginHandler constructor2 gui
     */
    public LoginHandler(ClientManagerGUI manager) throws IOException {
        this.managergui = manager;
        runLoginGui();
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
     * Overview: gui getter
     */
    public LoginGUI getGui() {
        return gui;
    }

    /**
     * Overview: method aimed to create a CreateGameMessage
     */
    public void creategamemessage(String sender, String username){
        CreateGameMessage message = new CreateGameMessage(username, sender);
        if(manager == null){
            managergui.sendMessage(message);
        } else {
            manager.sendMessage(message);
        }

    }

    /**
     * Overview: method aimed to create a EnterGameMessage
     */
    public void entergamemessage(String sender, String username, String id){
        EnterGameMessage message = new EnterGameMessage(username, sender, id);
        if(manager == null){
            managergui.sendMessage(message);
        } else {
            manager.sendMessage(message);
        }
    }

    /**
     * Overview: call the login gui
     */
    public void runLoginGui() throws IOException {
        this.gui = new LoginGUI(this);
    }

}
