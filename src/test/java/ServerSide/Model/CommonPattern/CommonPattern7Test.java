package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern7Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp7 = new CommonPattern7();

        shelf.setTile(5,0, ItemType.FRAMES);
        shelf.setTile(5,1, ItemType.BOOKS);
        shelf.setTile(5,2, ItemType.PLANTS);
        shelf.setTile(5,3, ItemType.FRAMES);
        shelf.setTile(5,4, ItemType.FRAMES);

        shelf.setTile(4,0, ItemType.BOOKS);
        shelf.setTile(4,1, ItemType.BOOKS);
        shelf.setTile(4,2, ItemType.GAMES);
        shelf.setTile(4,3, ItemType.GAMES);
        shelf.setTile(4,4, ItemType.GAMES);

        shelf.setTile(3,0, ItemType.FRAMES);
        shelf.setTile(3,1, ItemType.FRAMES);
        shelf.setTile(3,2, ItemType.FRAMES);
        shelf.setTile(3,3, ItemType.FRAMES);
        shelf.setTile(3,4, ItemType.FRAMES);

        shelf.setTile(2,0, ItemType.CATS);
        shelf.setTile(2,1, ItemType.CATS);
        shelf.setTile(2,2, ItemType.CATS);
        shelf.setTile(2,3, ItemType.CATS);
        shelf.setTile(2,4, ItemType.CATS);

        assertTrue(cp7.validated(shelf));
    }

}