package Server.Model;

import Server.VirtualView.GameObserver;
import Server.VirtualView.VirtualGameView;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private BoardGame board;
    private ArrayList<CommonGoalCard> commonGoals;
    private int[] order;
    private int currentTurnPlayer;
    private Boolean isFinished;
    private GameObserver obs;
    // vedere se mettere come observers del game anche i virtualplayerview


    /**
     * Overview: Game constructor
     */
    public Game(Lobby lobby, ArrayList<CommonGoalCard> commongoals, int firstToPlay){
        this.players = lobby.getPlayers();
        this.commonGoals = new ArrayList<>();
        this.order = new int[lobby.getUsernames().length];

        // initialization of the board of the game
        board = BoardGame.getInstance(this.players.size());

        // initialization of the commongoals
        for(int i=0; i<commongoals.size(); i++){
            this.commonGoals.add(i, commongoals.get(i));
        }

        // initialization of the order
        for(int i=0; i<players.size(); i++){
            if(firstToPlay >= players.size()){
                firstToPlay = firstToPlay - players.size();
            }
            order[i] = firstToPlay;
            firstToPlay++;
        }

        // initialization of the currentTurnPlayer
        currentTurnPlayer = order[0];

        // initialization of isFinish
        isFinished = false;
    }

    /**
     * Overview: method aimed to add Game observers
     */
    public void setGameObserver(GameObserver observer){
        obs = observer;
        // qui il virtual game view è settato come game observer,
        // da qui possiamo notificarlo (gamevirtualview) per creare il messaggio gamehasstarted da inviare al client
    }

    /**
     * Overview: method aimed to notify the observer about the start of the game
     */
    public void notifyObserver(){
        obs.notifythestartofthegame();
    }

    /**
     * Overview: commongoals getter
     */
    public ArrayList<CommonGoalCard> getCommonGoals(){ return commonGoals; }

    /**
     * Overview: order getter
     */
    public int[] getOrder(){ return order; }

    /**
     * Overview: currentTurnPlayer getter
     */
    public int getCurrentTurnPlayer(){ return currentTurnPlayer; }

    /**
     * Overview: isFinished getter
     */
    public Boolean getIsFinished(){ return isFinished; }

    /**
     * Overview: players getter
     */
    public ArrayList<Player> getPlayers(){ return players; }

    /**
     * Overview: board getter
     */
    public BoardGame getBoard(){ return board; }

}
