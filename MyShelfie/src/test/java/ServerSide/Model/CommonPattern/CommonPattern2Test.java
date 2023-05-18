package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern2Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp2 = new CommonPattern2();

        shelf.setTile(0,0, ItemType.BOOKS);
        shelf.setTile(1,0, ItemType.TROPHIES);
        shelf.setTile(2,0, ItemType.CATS);
        shelf.setTile(3,0, ItemType.PLANTS);
        shelf.setTile(4,0, ItemType.FRAMES);
        shelf.setTile(5,0, ItemType.GAMES);

        shelf.setTile(0,1, ItemType.BOOKS);
        shelf.setTile(1,1, ItemType.TROPHIES);
        shelf.setTile(2,1, ItemType.CATS);
        shelf.setTile(3,1, ItemType.PLANTS);
        shelf.setTile(4,1, ItemType.FRAMES);
        shelf.setTile(5,1, ItemType.GAMES);

        assertTrue(cp2.validated(shelf));
    }
}