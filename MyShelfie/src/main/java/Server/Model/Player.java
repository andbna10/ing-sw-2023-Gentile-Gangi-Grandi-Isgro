package Server.Model;

import Server.VirtualView.PlayerObserver;
import Server.VirtualView.VirtualPlayerView;

import java.util.HashMap;

public class Player {
    private int id;
    private HashMap<Boolean, String> lobby;
    private static int numPlayers = 0;
    private String username;
    private Boolean inGame;
    private int points;
    private PersonalGoalCard goal;
    private Bookshelf bookshelf;
    private PlayerObserver obs;

    /**
     * Player constructor
     */
    public Player(String username, Boolean islobbyowner, String idlobby){
        lobby = new HashMap<>();
        lobby.put(islobbyowner, idlobby);
        this.username = username;
        id = numPlayers;
        numPlayers++;
        points = 0;
        inGame = false;
        bookshelf = new Bookshelf();
        obs = new VirtualPlayerView();
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
     * Overview: get goal
     */
    public PersonalGoalCard getGoal(){ return goal; }

    /**
     * Overview: get bookshelf
     */
    public Bookshelf getBookshelf(){ return bookshelf; }


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

        // if status is true, we can let notify the player virtual view for the creation of the create PlayerView
        // qui potremmo anche vedere se riusciamo a passare nel server manager di questo giocatore il gameVirtualview
    }
}
