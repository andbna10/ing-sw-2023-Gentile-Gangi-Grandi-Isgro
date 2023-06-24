package ServerSide.Controller;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemTile;
import ServerSide.Model.Player;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyShelfieTest {

    @Test
    void selectPersonalGoalsTest() {
        var ms = new MyShelfie();
        var pl = new Player("Giuvann", true, "1", null);
        var output = ms.selectPersonalGoals(3);
        pl.setGoal(output.get(1));
        System.out.println(pl.getGoal().toString());
    }


}