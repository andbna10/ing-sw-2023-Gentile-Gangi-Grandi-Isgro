package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern6Test {
    //NOFFUNZIONA
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp6 = new CommonPattern6();

        shelf.setTile(5,0, ItemType.BOOKS);
        shelf.setTile(5,1, ItemType.TROPHIES);
        shelf.setTile(5,2, ItemType.PLANTS);
        shelf.setTile(5,3, ItemType.GAMES);
        shelf.setTile(5,4, ItemType.FRAMES);

        shelf.setTile(4,0, ItemType.BOOKS);
        shelf.setTile(4,1, ItemType.TROPHIES);
        shelf.setTile(4,2, ItemType.PLANTS);
        shelf.setTile(4,3, ItemType.GAMES);
        shelf.setTile(4,4, ItemType.FRAMES);

        assertTrue(cp6.validated(shelf));
    }

}