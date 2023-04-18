package Controller;
import Model.*;
import Model.PersonalPattern.PersonalPattern1;

import java.util.ArrayList;

public class PlayerController {
    private Player model;
    private GameController game;

    /**
     * Overview: PlayerController constructor
     */
    public PlayerController(Player player, GameController game){
        this.model = player;
        this.game = game;
    }

    /**
     * Overview: set the goal card in the model for the corresponding player
     */
    public void setGoal(PersonalGoalCard goal){
        model.setGoal(goal);
    }

    /**
     * Overview: insert taken tiles in the bookshelf
     */
    public void feedColumn(int column, ArrayList<ItemTile> tiles){
        if(model.getBookshelf().canInsert(tiles.size(), column)){
            model.getBookshelf().setTiles(column, tiles);
        }
    }

    /**
     * Overview: method aimed to check the accomplishment of a common goal, it returns true if the goal has been reached
     */
    public Boolean checkCommonGoal(Bookshelf bookshelf, CommonGoalCard goal){
        int ok = goal.validated(bookshelf);
        if(ok == 1){
            // to do (queste cose forse potrebbero essere fatte durante il turno fuori da questo metodo ma in base al suo output)
            // viene rilasciato la prima tessera scoring token associata a quel common goal
            // aggiungere i rispettivi punti al giocatore
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overview: method aimed to check the accomplishment level of a personal goal, it returns the points achieved till now
     */
    public int checkPersonalGoal(Bookshelf bookshelf, PersonalGoalCard goal){
        int points = goal.validated(bookshelf);
        return points;
    }

    /**
     * Overview: method aimed to let the player play a turn
     */
    public void play(){
        System.out.println("mossa");

        // to do
        /*
        while(!ok){
            pick
            ifPickable{ok true}
        }
        togliere la tiles dal board game (gestire anche l'ingame delle tiles)
        dopo questo, ho una lista di tiles e le devo mettere nella bookshelf (feedcolumn())
        controllo dei goals
        controllo sulla bookshlef
        aggiornamento punti e stato in generale delle classi modello (tiles in gioco, obiettivi raggiunti)


         */
    }

    /**
     * Overview: tiles draft
     */

    public void pickTiles () {


    }


}
