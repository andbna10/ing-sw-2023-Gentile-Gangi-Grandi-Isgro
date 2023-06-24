package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern5Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp5 = new CommonPattern5();

        shelf.setTile(0,0, ItemType.TROPHIES);
        shelf.setTile(1,0, ItemType.PLANTS);
        shelf.setTile(2,0, ItemType.TROPHIES);
        shelf.setTile(3,0, ItemType.GAMES);
        shelf.setTile(4,0, ItemType.GAMES);
        shelf.setTile(5,0, ItemType.TROPHIES);

        shelf.setTile(0,4, ItemType.TROPHIES);
        shelf.setTile(1,4, ItemType.TROPHIES);
        shelf.setTile(2,4, ItemType.TROPHIES);
        shelf.setTile(3,4, ItemType.GAMES);
        shelf.setTile(4,4, ItemType.GAMES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        shelf.setTile(0,3, ItemType.TROPHIES);
        shelf.setTile(1,3, ItemType.TROPHIES);
        shelf.setTile(2,3, ItemType.TROPHIES);
        shelf.setTile(3,3, ItemType.TROPHIES);
        shelf.setTile(4,3, ItemType.TROPHIES);
        shelf.setTile(5,3, ItemType.TROPHIES);

        assertTrue(cp5.validated(shelf));
    }

}