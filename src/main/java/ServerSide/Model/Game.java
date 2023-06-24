package ServerSide.Model;

import ServerSide.VirtualView.GameObserver;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private BoardGame board;
    private ArrayList<CommonGoalCard> commonGoals;
    private int[] order;
    private int currentTurnPlayer;
    private Boolean isLastTurnStarted;
    private String winner;
    private GameObserver obs;
    private Boolean ended;


    /**
     * Overview: Game constructor
     * @author Andrea Isgrò
     * @param lobby lobby instance
     * @param commongoals arraylist containing common goals for this match
     * @param firstToPlay index of "first to play" player, the one who starts the round
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

        // initialization isLastTurnStarted
        this.isLastTurnStarted = false;

        //initialization ended
        this.ended = false;
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
    public void notifyObserverTheStart(int common1, int common2, ScoringToken one, ScoringToken two){
        obs.notifythestartofthegame(this.players.size(), board.getBoard(), common1, common2, one, two);
    }

    /**
     * Overview: method aimed to notify the observer about the end of a turn.
     */
    public void notifyObserverEndTurn(String username){
        obs.notifytheendofaturn(board.getBoard(), username);
    }

    /**
     * Overview: method aimed to notify players that a common has been accomplished
     */
    public void notifyObserverCommon(int common, int newPoints, String username){
        obs.notifyObserverCommon(common, newPoints, username, commonGoals.get(common-1).getStack().get(commonGoals.get(common-1).getStack().size()-1));
    }

    /**
     * Overview: method aimed to notify the last turn triggered
     */
    public void notifyObserverLastTurn(String username){
        obs.notifyObserverLastTurn(username);
    }


    /**
     * Overview: commongoals getter
     */
    public ArrayList<CommonGoalCard> getCommonGoals(){ return commonGoals; }

    /**
     * Overview: method aimed to advance turns
     * @author Andrea Isgrò
     * @return boolean aimed to avoid calling the turn procedure for the same player twice
     */
    public boolean advance(){
        int last = getCurrentTurnPlayer();
        for(int i=0; i<this.order.length; i++){
            if(order[i] == currentTurnPlayer){
                if(i == this.order.length-1){
                    currentTurnPlayer = order[0];
                    break;
                }
                currentTurnPlayer = order[i+1];
                break;
            }
        }
        return currentTurnPlayer == last;
    }

    /**
     * Overview: advance towards end game
     * @author Andrea Isgrò
     * @return (0) in case the last turn has ended, (1) in order to avoid calling turn for the same player twice,
     * (2) to let the calling turns procedure continue
     */
    public int advanceFinish(){
        int last = getCurrentTurnPlayer();
        for(int i=0; i< this.order.length; i++){
            if(order[i] == currentTurnPlayer){
                if(i == order.length-1){
                    return 0;
                } else {
                    currentTurnPlayer = order[i+1];
                }
            }
        }
        if(currentTurnPlayer == last){
            return 1;
        } else {
            return 2;
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
     * Overview: winner setter
     */
    public void setWinner(String player){ winner=player;}

    /**
     * Overview: winner getter
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

    /**
     * Overview: isLastTurnStarted setter
     */
    public void setIsLastTurnStarted(Boolean status){ this.isLastTurnStarted = status; }

    /**
     * Overview: isLastTurnStarted getter
     */
    public Boolean getIsLastTurnStarted(){ return this.isLastTurnStarted; }

    /**
     * Overview: ended setter
     */
    public void setEnded(Boolean status){ this.ended = status; }

    /**
     * Overview: ended getter
     */
    public Boolean getEnded(){ return this.ended; }

    /**
     * Overview: method aimed to get all the players' bookshelf but for the bookshelf of the current turn player
     * @author Andrea Isgrò
     * @param player current turn player
     * @return an arraylist with every player's bookshelf except current turn player's one
     */
    public ArrayList<ItemTile[][]> getPlayersBookshelf(Player player){
        ArrayList<ItemTile[][]> output = new ArrayList<>();
        for(Player p: players){
            if(!p.equals(player)){
                output.add(p.getBookshelf().getGameTiles());
            }
        }
        return output;
    }

    /**
     * Overview: method aimed to get all the usernames of the players but for the username of the current turn player
     * @author Andrea Isgrò
     * @param player current turn player
     * @return an arraylist with every player's username except current turn player's one
     */
    public ArrayList<String> getPlayersUsernames(Player player){
        ArrayList<String> output = new ArrayList<>();
        for(Player p: players){
            if(!p.equals(player)){
                output.add(p.getUsername());
            }
        }
        return output;
    }

}
