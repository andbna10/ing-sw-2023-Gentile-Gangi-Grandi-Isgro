package ServerSide.VirtualView;

import Messages.fromServerToClient.AskNPlayersMessage;
import Messages.fromServerToClient.CreatePlayerViewMessage;
import Messages.fromServerToClient.OwnercanStartGameMessage;
import Networking.ServerManager;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerVViewObserver obs;
    private ServerManager manager;

    /**
     * Overview: VirtualPlayerView constructor
     */
    public VirtualPlayerView(ServerManager manager){
        this.manager = manager;
    }

    /**
     * Overview: method aimed to set the VirtualPlayerView
     */
    public void setPlayerViewObserver(PlayerVViewObserver observer){ this.obs = observer; }

    @Override
    /**
     * Overview: method aimed to create the createplayerviewmessage
     */
    public void createplayerviewmessage(){
        CreatePlayerViewMessage message = new CreatePlayerViewMessage();
        manager.setIsMessage(true);
        manager.setMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create the asknpalyersmessage
     */
    public void createasknplayersmessage(){
        AskNPlayersMessage message = new AskNPlayersMessage();
        manager.setIsMessage(true);
        manager.setMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create a ownercanstartgamemessage
     */
    public void createownercanstartgamemessage(){
        OwnercanStartGameMessage message = new OwnercanStartGameMessage();
        manager.setIsMessage(true);
        manager.setMessage(message);
    }



}
