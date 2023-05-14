package ServerSide.VirtualView;

import Messages.fromServerToClient.AskNPlayersMessage;
import Messages.fromServerToClient.CreatePlayerViewMessage;
import Messages.fromServerToClient.OwnercanStartGameMessage;
import Messages.fromServerToClient.YourTurnMessage;
import Networking.ServerManager;
import ServerSide.Model.ItemTile;

public class VirtualPlayerView implements PlayerObserver{
    private PlayerVViewObserver obs;
    private ServerManager manager;

    /**
     * Overview: VirtualPlayerView constructor
     */
    public VirtualPlayerView(ServerManager manager){
        this.manager = manager;
    }

    public PlayerVViewObserver getObs(){ return this.obs; }

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
        manager.sendMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create the asknpalyersmessage
     */
    public void createasknplayersmessage(){
        AskNPlayersMessage message = new AskNPlayersMessage();
        manager.sendMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create an ownercanstartgamemessage
     */
    public void createownercanstartgamemessage(){
        OwnercanStartGameMessage message = new OwnercanStartGameMessage();
        manager.sendMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create a yourturnmessage
     */
    public void notifyPlayerTurn(ItemTile[][] bookshelf, ItemTile[][] personalGoal){
        YourTurnMessage message = new YourTurnMessage(bookshelf, personalGoal);
        manager.sendMessage(message);
    }





}
