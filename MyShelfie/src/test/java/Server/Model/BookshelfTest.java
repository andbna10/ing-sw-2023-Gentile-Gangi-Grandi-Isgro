package Server.Model;

import Server.Controller.MyShelfie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookshelfTest {

    @Test
    void canInsertTest() {
        var shelf = new Bookshelf();
        assertTrue(shelf.canInsert(2, 1));
    }

    @Test
    void setTilesTest() {
        var shelf = new Bookshelf();
        var tiles = new ArrayList<ItemTile>();
        tiles.add(new ItemTile(ItemType.CATS));
        tiles.add(new ItemTile(ItemType.BOOKS));
        tiles.add(new ItemTile(ItemType.CATS));
        shelf.setTiles(0, tiles);
        System.out.println(shelf.getTile(4, 0).getType().toString());
    }
}