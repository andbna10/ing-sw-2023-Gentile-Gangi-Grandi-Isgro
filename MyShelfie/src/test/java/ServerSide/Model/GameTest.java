package ServerSide.Model;

import ServerSide.Model.CommonPattern.CommonPattern1;
import ServerSide.Model.CommonPattern.CommonPattern7;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void gameTest() {
        var lobby = new Lobby("simone");
        var commons = new ArrayList<CommonGoalCard>();
        commons.add(new CommonPattern1());
        commons.add(new CommonPattern7());
        var game = new Game(lobby, commons, 1);
        int[] order = {1,2,3,4};
        var currentPlayer = 0;

        assertTrue(game.advance());

    }
}