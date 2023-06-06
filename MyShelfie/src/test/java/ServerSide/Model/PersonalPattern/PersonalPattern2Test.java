package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern2Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p2 = new PersonalPattern2();

        shelf.setTile(0,0, ItemType.PLANTS);
        shelf.setTile(0,2, ItemType.FRAMES);
        shelf.setTile(0,4, ItemType.CATS);
        shelf.setTile(0,3, ItemType.BOOKS);;
        shelf.setTile(4,1, ItemType.GAMES);
        shelf.setTile(5,2, ItemType.TROPHIES);

        assertEquals(p2.validated(shelf), 0);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(2,2, ItemType.GAMES);
        shelf2.setTile(4,3, ItemType.TROPHIES);

        assertEquals(p2.validated(shelf2), 4);
    }

}