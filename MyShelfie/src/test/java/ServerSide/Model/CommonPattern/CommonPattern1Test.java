package ServerSide.Model.CommonPattern;

import ServerSide.Controller.MyShelfie;
import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern1Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp1 = new CommonPattern1();
        shelf.setTile(4,0, ItemType.BOOKS);
        shelf.setTile(4,1, ItemType.BOOKS);
        shelf.setTile(5,0, ItemType.BOOKS);
        shelf.setTile(5,1, ItemType.BOOKS);

        shelf.setTile(4,3, ItemType.TROPHIES);
        shelf.setTile(4,4, ItemType.TROPHIES);
        shelf.setTile(5,3, ItemType.TROPHIES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertTrue(cp1.validated(shelf));
    }

}