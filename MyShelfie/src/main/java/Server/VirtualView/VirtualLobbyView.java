package Server.VirtualView;
import Messages.fromServerToClient.CreatelobbyViewMessage;
import Networking.ServerManager;
import Server.Model.*;
import Server.Controller.*;

public class VirtualLobbyView implements LobbyObserver{
    private LobbyViewObserver obs;
    private ServerManager manager;

    /**
     * Overview: constructor of the virtual lobby view
     */
    public VirtualLobbyView(Lobby model, ServerManager servermanager){
        model.setLobbyObserver(this);
        this.manager = servermanager;
    }

    /**
     * Overview: method aimed to add the lobby view observer
     */
    public void setLobbyViewObserver(LobbyViewObserver observer){ this.obs = observer; }

    /**
     * Overview: Observer getter
     */
    public LobbyViewObserver getObs(){ return this.obs; }

    /**
     * Overview: method aimed to create a CreateLobbyViewMessage
     */
    public void createlobbyviewmessage(String username){
        CreatelobbyViewMessage message = new CreatelobbyViewMessage(username);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }
}
