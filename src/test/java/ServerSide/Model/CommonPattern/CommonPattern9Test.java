package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern9Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var shelf1 = new Bookshelf();
        var cp9 = new CommonPattern9();

        shelf.setTile(0,3, ItemType.TROPHIES);
        shelf.setTile(4,4, ItemType.TROPHIES);
        shelf.setTile(0,2, ItemType.TROPHIES);
        shelf.setTile(3,0, ItemType.GAMES);
        shelf.setTile(5,1, ItemType.TROPHIES);
        shelf.setTile(2,2, ItemType.TROPHIES);
        shelf.setTile(1,1, ItemType.TROPHIES);
        shelf.setTile(1,4, ItemType.TROPHIES);

        shelf1.setTile(0,0, ItemType.TROPHIES);
        shelf1.setTile(4,4, ItemType.TROPHIES);
        shelf1.setTile(0,2, ItemType.TROPHIES);
        shelf1.setTile(3,0, ItemType.TROPHIES);
        shelf1.setTile(5,1, ItemType.TROPHIES);
        shelf1.setTile(2,2, ItemType.TROPHIES);
        shelf1.setTile(5,4, ItemType.TROPHIES);
        shelf1.setTile(1,4, ItemType.TROPHIES);

        assertFalse(cp9.validated(shelf));
        assertTrue(cp9.validated(shelf1));
    }

}