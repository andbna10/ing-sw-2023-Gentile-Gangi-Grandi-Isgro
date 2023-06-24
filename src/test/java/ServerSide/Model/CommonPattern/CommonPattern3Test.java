package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern3Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp3 = new CommonPattern3();

        for(int i=2; i<6; i++) {
            shelf.setTile(i, 0, ItemType.BOOKS);
            shelf.setTile(i, 2, ItemType.GAMES);
            shelf.setTile(i, 1, ItemType.FRAMES);
            shelf.setTile(i, 4, ItemType.PLANTS);
        }

        assertTrue(cp3.validated(shelf));
    }

}