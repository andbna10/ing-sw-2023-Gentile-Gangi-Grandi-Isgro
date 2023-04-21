package Client.NetworkHandler;

import Messages.fromClientToServer.CreateGameMessage;
import Networking.ClientManager;

public class LoginHandler implements LoginViewObserver{
    private ClientManager manager;

    /**
     * Overview: LoginHandler constructor
     */
    public LoginHandler(ClientManager manager){
        this.manager = manager;
    }

    /**
     * Overview: method aimed to create a CreateGameMessage
     */
    public void creategamemessage(String sender, String username){
        CreateGameMessage message = new CreateGameMessage(username, sender);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }
}
