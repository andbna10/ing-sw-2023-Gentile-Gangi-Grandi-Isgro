package ServerSide.Model;

import ServerSide.VirtualView.GameObserver;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private BoardGame board;
    private ArrayList<CommonGoalCard> commonGoals;
    private int[] order;
    private int currentTurnPlayer;
    private Boolean isFinished;
    private String winner;
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
    public void notifyObserverTheStart(){
        obs.notifythestartofthegame(board.getBoard());
    }

    /**
     * Overview: method aimed to notify the observer about the end of a turn.
     */
    public void notifyObserverEndTurn(){
        obs.notifytheendofaturn(board.getBoard());
    }

    /**
     * Overview: commongoals getter
     */
    public ArrayList<CommonGoalCard> getCommonGoals(){ return commonGoals; }

    /**
     * Overview: method aimed to advance turns
     */
    public void advance(){
        for(int i=0; i< this.order.length; i++){
            if(order[i] == currentTurnPlayer){
                if(i == this.order.length-1){
                    currentTurnPlayer = order[0];
                    System.out.println("current turn player changed");
                    break;
                }
                currentTurnPlayer = order[i+1];
                System.out.println("current turn player changed");
                break;
            }
        }
    }

    /**
     * Overview: index order getter
     */
    public int getOrder(int i){ return order[i]; }

    /**
     * Overview: currentTurnPlayer getter
     */
    public int getCurrentTurnPlayer(){ return currentTurnPlayer; }

    /**
     * Overview: isFinished getter
     */
    public Boolean getIsFinished(){ return isFinished; }

    /**
     * Overview: winner setter
     */
    public void setWinner(String player){ winner=player;}

    /**
     * Overview: winneer getter
     */
    public String getWinner(){ return winner; }

    /**
     * Overview: players getter
     */
    public ArrayList<Player> getPlayers(){ return players; }

    /**
     * Overview: board getter
     */
    public BoardGame getBoard(){ return board; }



}
