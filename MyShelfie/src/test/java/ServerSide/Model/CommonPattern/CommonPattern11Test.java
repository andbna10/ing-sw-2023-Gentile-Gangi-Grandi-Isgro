package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern11Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp11 = new CommonPattern11();

        shelf.setTile(1,0, ItemType.TROPHIES);
        shelf.setTile(2,1, ItemType.TROPHIES);
        shelf.setTile(3,2, ItemType.TROPHIES);
        shelf.setTile(4,3, ItemType.TROPHIES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertTrue(cp11.validated(shelf));

        shelf.setTile(1,0, ItemType.TROPHIES);
        shelf.setTile(2,1, ItemType.TROPHIES);
        shelf.setTile(3,2, ItemType.TROPHIES);
        shelf.setTile(4,3, ItemType.PLANTS);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertFalse(cp11.validated(shelf));
    }

}