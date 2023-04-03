package Controller;
import Model.*;

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
     * Overview: method aimed to check the accomplishment of a goal
     */
    public void checkGoal(Bookshelf bookshelf, GoalCard goal){
        //
    }

    /**
     * Overview: method aimed to let the player play a turn
     */
    public void play(){
        //
    }



}
