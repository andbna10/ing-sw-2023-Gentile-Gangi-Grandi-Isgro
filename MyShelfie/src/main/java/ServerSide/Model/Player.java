package ServerSide.Model;

import Networking.ServerManager;
import ServerSide.VirtualView.PlayerObserver;
import ServerSide.VirtualView.VirtualPlayerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int id;
    private HashMap<String, Boolean> lobby;
    private static int numPlayers = 0;
    private String username;
    private Boolean inGame;
    private int points;
    private PersonalGoalCard goal;
    private Bookshelf bookshelf;
    private ArrayList<ItemTile> pickedTiles = new ArrayList<ItemTile>();
    private PlayerObserver obs;
    private ServerManager manager;

    /**
     * Player constructor
     */
    public Player(String username, Boolean islobbyowner, String idlobby, ServerManager manager){
        lobby = new HashMap<>();
        lobby.put(idlobby, islobbyowner);
        this.username = username;
        id = numPlayers;
        numPlayers++;
        points = 0;
        inGame = false;
        bookshelf = new Bookshelf();
        this.manager = manager;
        obs = new VirtualPlayerView(this.manager);
    }

    /**
     * Overview: username getter
     */
    public String getUsername(){ return username;}

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

    /**
     * Overview: points getter
     */
    public int getPoints(){ return points; }

    /**
     * Overview: add points to the total points of the player
     */
    public void addPoints(int newpoints){points+=newpoints;}

    /**
     * Overview: get goal
     */
    public PersonalGoalCard getGoal(){ return goal; }

    /**
     * Overview: get bookshelf
     */
    public Bookshelf getBookshelf(){ return bookshelf; }

    /**
     * Overview: get pickedTiles
     */
    public ArrayList<ItemTile> getPickedTiles(){return pickedTiles;}

    /**
     * Overview: set the goal
     */
    public void setGoal(PersonalGoalCard goal){ this.goal = goal; }

    /**
     * Overview: obs getter
     */
    public PlayerObserver getObs(){ return obs; }

    /**
     * Overview: method aimed to change the status of inGame
     */
    public void setInGame(Boolean status){
        this.inGame = status;

        // if status is true, we can let notify the player virtual view for the creation of the createPlayerViewMessage
        if(status){
            obs.createplayerviewmessage();
        }
    }

    /**
     * Overview: ServerManager getter
     */
    public ServerManager getManager(){ return this.manager; }

    /**
     * Overview: method aimed to change the ownership of the lobby
     */
    public void updateLobby(Boolean key, String id){
        lobby.remove(id);
        lobby.put(id, key);
    }

    /**
     * Overview: lobby getter
     */
    public HashMap<String, Boolean> getLobby(){ return this.lobby; }

    /**
     * Overview: the observer of the player is notified about the possibility to start the game
     */
    public void notifyOwner(){
        this.obs.createownercanstartgamemessage();
    }

    /**
     * Overview: notify the player to move
     */
    public void notifyPlayerTurn(){
        obs.notifyPlayerTurn(bookshelf.getGameTiles());
    }

    /**
     * Overview: notify the player the board has been restored
     */
    public void notifyPlayerBoardRestored(BoardCell[][] board){
        obs.notifyPlayerBoardRestored(board);
    }

}
