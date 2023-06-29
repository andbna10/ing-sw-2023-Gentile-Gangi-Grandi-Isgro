package ServerSide.VirtualView;
import Messages.fromServerToClient.CreatelobbyViewMessage;
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
     * Overview: remove manager if players disconnects
     */
    public void removemanager(ServerManager manager){
        for(int i=0; i< managers.size();i++){
            if(this.managers.get(i) == manager){
                this.managers.remove(i);
                break;
            }
        }
    }

    /**
     * Overview: Observer getter
     */
    public LobbyVViewObserver getObs(){ return this.obs; }

    /**
     * Overview: method aimed to create a CreateLobbyViewMessage
     */
    @Override
    public void updatelobbyviewmessage(String[] usernames, String id, String owner, Boolean fromEndStatus){
        CreatelobbyViewMessage message = new CreatelobbyViewMessage(usernames, id, owner, fromEndStatus);
        for(ServerManager manager: this.managers) {
            manager.sendMessage(message);
        }
    }


}
