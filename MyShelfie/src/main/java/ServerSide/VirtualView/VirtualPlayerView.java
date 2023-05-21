package ServerSide.VirtualView;

import Messages.fromServerToClient.*;
import Networking.ServerManager;
import ServerSide.Model.BoardCell;
import ServerSide.Model.ItemTile;

import java.util.ArrayList;

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
    public void createownercanstartgamemessage(String id){
        OwnercanStartGameMessage message = new OwnercanStartGameMessage(id);
        manager.sendMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to create a yourturnmessage
     */
    public void notifyPlayerTurn(ItemTile[][] bookshelf, ArrayList<ItemTile[][]> bookshelfList, ArrayList<String> usernames){
        YourTurnMessage message = new YourTurnMessage(bookshelf, bookshelfList, usernames);
        manager.sendMessage(message);
    }

    @Override
    /**
     * Overview: method aimed to notify the player that the board has been restored
     */
    public void notifyPlayerBoardRestored(BoardCell[][] board){
        BoardRestoredMessage message = new BoardRestoredMessage(board);
        manager.sendMessage(message);
    }






}
