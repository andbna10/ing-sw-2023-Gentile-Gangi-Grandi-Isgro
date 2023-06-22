package ServerSide.Model;

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
        assertEquals(shelf.getTile(4, 0).getType(), ItemType.BOOKS);
    }

    @Test
    void emptyShelf(){
        var shelf = new Bookshelf();
        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                shelf.setTile(i,j,ItemType.BOOKS);

        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                assertNotNull(shelf.getTile(i,j));

        shelf.emptyShelf();

        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                assertNull(shelf.getTile(i,j));
    }

}