package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern8Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp8 = new CommonPattern8();

        shelf.setTile(0,0, ItemType.TROPHIES);
        shelf.setTile(0,4, ItemType.TROPHIES);
        shelf.setTile(5,0, ItemType.TROPHIES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertTrue(cp8.validated(shelf));
    }

}