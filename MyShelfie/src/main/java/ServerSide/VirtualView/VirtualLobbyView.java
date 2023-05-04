package ServerSide.VirtualView;
import Messages.fromServerToClient.CreatelobbyViewMessage;
import Messages.fromServerToClient.GameCanStartMessage;
import Networking.ServerManager;
import ServerSide.Model.*;

import java.util.ArrayList;

public class VirtualLobbyView implements LobbyObserver{
    private LobbyVViewObserver obs;
    private ArrayList<ServerManager> managers;

    /**
     * Overview: constructor of the virtual lobby view
     */
    public VirtualLobbyView(Lobby model){
        model.setLobbyObserver(this);
        this.managers = new ArrayList<>();
    }

    /**
     * Overview: method aimed to add the lobby view observer
     */
    public void setLobbyViewObserver(LobbyVViewObserver observer){ this.obs = observer; }

    /**
     * Overview: method aimed to add a server manager
     */
    public void setManager(ServerManager manager){ this.managers.add(manager); }

    /**
     * Overview: Observer getter
     */
    public LobbyVViewObserver getObs(){ return this.obs; }

    @Override
    /**
     * Overview: method aimed to create a CreateLobbyViewMessage
     */
    public void updatelobbyviewmessage(String[] usernames, String id){
        CreatelobbyViewMessage message = new CreatelobbyViewMessage(usernames, id);
        for(ServerManager manager: this.managers) {
            manager.setIsMessage(true);
            manager.setMessage(message);
        }
    }

    @Override
    /**
     * Overview: method aimed to create a GameCanStartMessage
     */
    public void gamecanstartmessage(){
        GameCanStartMessage message = new GameCanStartMessage();
        for(ServerManager manager: this.managers) {
            System.out.println("player i");
            manager.setIsMessage(true);
            manager.setMessage(message);
        }
    }


}
