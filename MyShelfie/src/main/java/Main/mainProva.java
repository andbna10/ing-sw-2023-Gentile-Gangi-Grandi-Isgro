package Main;
import Controller.GameController;

import java.util.Random;
public class mainProva {
    public static void main(String[] args) {
        String[] usernames = {"tizio", "caio", "sempronio"};
        GameController gc1 = new GameController(usernames);
        gc1.startGame();
        int index = gc1.getModel().getCurrentTurnPlayer();
        System.out.println(gc1.getModel().getPlayers().get(index).getUsername());

    }
}


