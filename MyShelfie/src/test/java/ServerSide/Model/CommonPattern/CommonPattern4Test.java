package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern4Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp4 = new CommonPattern4();

        for (int i = 2; i < 4; i++)
            shelf.setTile(i, 0, ItemType.BOOKS);
        for (int i = 4; i < 6; i++) {
            shelf.setTile(i,0, ItemType.TROPHIES);
            shelf.setTile(i,2, ItemType.PLANTS);
            shelf.setTile(i,1,ItemType.TROPHIES);
            shelf.setTile(i,3,ItemType.GAMES);
            shelf.setTile(i,4,ItemType.FRAMES);
        }

        assertTrue(cp4.validated(shelf));
    }
}