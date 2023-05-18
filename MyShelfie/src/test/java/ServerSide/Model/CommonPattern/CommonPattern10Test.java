package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern10Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp10 = new CommonPattern10();

        shelf.setTile(3,0, ItemType.TROPHIES);
        shelf.setTile(5,0, ItemType.TROPHIES);
        shelf.setTile(5,2, ItemType.TROPHIES);
        shelf.setTile(3,2, ItemType.TROPHIES);
        shelf.setTile(0,1, ItemType.TROPHIES);

        assertFalse(cp10.validated(shelf));

        shelf.setTile(3,0, ItemType.TROPHIES);
        shelf.setTile(5,0, ItemType.TROPHIES);
        shelf.setTile(5,2, ItemType.TROPHIES);
        shelf.setTile(3,2, ItemType.TROPHIES);
        shelf.setTile(4,1, ItemType.TROPHIES);

        assertTrue(cp10.validated(shelf));

    }

}