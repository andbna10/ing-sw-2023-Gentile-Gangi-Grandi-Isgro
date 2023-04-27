package Server.VirtualView;
import Messages.fromServerToClient.CreatelobbyViewMessage;
import Messages.fromServerToClient.GameCanStartMessage;
import Networking.ServerManager;
import Server.Model.*;

public class VirtualLobbyView implements LobbyObserver{
    private LobbyVViewObserver obs;
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
    public void setLobbyViewObserver(LobbyVViewObserver observer){ this.obs = observer; }

    /**
     * Overview: Observer getter
     */
    public LobbyVViewObserver getObs(){ return this.obs; }

    @Override
    /**
     * Overview: method aimed to create a CreateLobbyViewMessage
     */
    public void updatelobbyviewmessage(String[] usernames){
        CreatelobbyViewMessage message = new CreatelobbyViewMessage(usernames);
        manager.setIsMessage(true);
        manager.setMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create a GameCanStartMessage
     */
    public void gamecanstartmessage(){
        GameCanStartMessage message = new GameCanStartMessage();
        manager.setIsMessage(true);
        manager.setMessage(message);
    }


}
