package Controller;
import Model.*;

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
}
