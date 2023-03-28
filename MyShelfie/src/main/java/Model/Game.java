package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private BoardGame board;
    private ArrayList<CommonGoalCard> commonGoals;
    private int[] order = new int[commonGoals.size()];
    private int currentTurnPlayer;
    private Boolean isLastTurn;

    /**
     * Overview: Game constructor
     */
    public Game(String[] usernamePlayers, ArrayList<CommonGoalCard> commongoals, int firstToPlay){
        // initialization of players
        for(int i=0; i<usernamePlayers.length; i++){
            this.players.add(i, new Player(usernamePlayers[i]));
        }

        // initialization of the board of the game
        board = BoardGame.getInstance(this.players.size());

        // initialization of the commongoals
        for(int i=0; i<commongoals.size(); i++){
            this.commonGoals.add(i, commongoals.get(i));
        }

        // initialization of the order
        for(int i=0; i<usernamePlayers.length; i++){
            if(firstToPlay >= usernamePlayers.length){
                firstToPlay = firstToPlay - usernamePlayers.length;
            }
            order[i] = firstToPlay;
            firstToPlay++;
        }

        // initialization of the currentTurnPlayer
        currentTurnPlayer = order[0];

        // initialization of isLastturn
        isLastTurn = false;
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
     * Overview: islastTurn getter
     */
    public Boolean getIsLastTurn(){ return isLastTurn; }

    /**
     * Overview: players getter
     */
    public ArrayList<Player> getPlayers(){ return players; }

}
