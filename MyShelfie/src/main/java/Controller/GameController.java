package Controller;
import Model.*;
import java.util.Random;

import java.util.ArrayList;

public class GameController {
    private Game model;
    private MyShelfie gameName;
    private ArrayList<PlayerController> players;

    /**
     * Overview: GameController constructor
     */
    public GameController(String[] usernamePlayers){
        gameName = new MyShelfie();
        model = new Game(usernamePlayers, gameName.selectCommonGoals(), this.selectFirstToPlay());
        for(int i=0; i<usernamePlayers.length; i++){
            this.players.add(new PlayerController(model.getPlayers().get(i), this));
        }
    }

    /**
     * Overview: select first to play
     */
    public int selectFirstToPlay(){
        Random r = new Random();
        int index = r.nextInt(players.size());
        return index;
    }

    /**
     * Overview: associate scoring tokens to selected common goal cards
     */
    public void associateScoringTokens(int nPlayers){
        Random r = new Random();
        ArrayList<ScoringToken> scoringtokens = gameName.selectScoringToken(nPlayers);
        for(int i=0; i<scoringtokens.size();i++){
            if(scoringtokens.get(i).getRoman() == Roman.I){
                model.getCommonGoals().get(0).setElementStack(scoringtokens.get(i));
            } else {
                model.getCommonGoals().get(1).setElementStack(scoringtokens.get(i));
            }
        }
    }

    /**
     * Overview: set personal goal cards for players
     */
    public void setPersonalGoals(){
        ArrayList<PersonalGoalCard> personalgoals = gameName.selectPersonalGoals(this.players.size());
        for(int i=0; i<this.players.size(); i++){
            this.players.get(i).setGoal(personalgoals.get(i));
        }
    }

    /**
     * Overview: restore the board with tiles in empty board cells
     */
    public void restoreBoard(){
        ArrayList<ItemTile> tiles = gameName.selectItemTiles(model.getBoard().getEmptyCells());
        model.getBoard().setTiles(tiles);
    }

    /**
     * Overview: start the game
     */
    // to be implemented (qui si deve completare lo stato del game e far partire i turni dei giocatori)

    /**
     * Overview: end game
     */
    // to be implemented (qui si deve vedere chi ha fatto piu punti)



}
