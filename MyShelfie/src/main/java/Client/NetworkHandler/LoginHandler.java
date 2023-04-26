package Client.NetworkHandler;

import Client.View.LoginView;
import Messages.fromClientToServer.CreateGameMessage;
import Messages.fromClientToServer.EnterGameMessage;
import Networking.ClientManager;

public class LoginHandler implements LoginViewObserver{
    private ClientManager manager;
    private LoginView view;

    /**
     * Overview: LoginHandler constructor
     */
    public LoginHandler(ClientManager manager){
        this.manager = manager;
        this.view = new LoginView(this); // da modificare quando cominceremo a implementare jswing
        // qua si dovrebber poter lanciare il metodo che runnare la view (?)
    }

    /**
     * Overview: method aimed to create a CreateGameMessage
     */
    public void creategamemessage(String sender, String username){
        CreateGameMessage message = new CreateGameMessage(username, sender);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }

    /**
     * Overview: method aimed to create a EnterGameMessage
     */
    public void entergamemessage(String sender, String username, String id){
        EnterGameMessage message = new EnterGameMessage(username, sender, id);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }
}
