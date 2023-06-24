package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern10Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p10 = new PersonalPattern10();

        shelf.setTile(5,3, ItemType.PLANTS);
        shelf.setTile(4,1, ItemType.FRAMES);
        shelf.setTile(3,3, ItemType.CATS);
        shelf.setTile(2,0, ItemType.BOOKS);;
        shelf.setTile(1,1, ItemType.GAMES);
        shelf.setTile(0,4, ItemType.TROPHIES);

        assertEquals(p10.validated(shelf), 12);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(5,0, ItemType.GAMES);
        shelf2.setTile(2,0, ItemType.TROPHIES);

        assertEquals(p10.validated(shelf2), 0);
    }

}