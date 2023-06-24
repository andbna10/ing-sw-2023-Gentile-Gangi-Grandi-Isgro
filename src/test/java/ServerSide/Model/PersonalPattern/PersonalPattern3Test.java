package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern3Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p3 = new PersonalPattern3();

        shelf.setTile(0,0, ItemType.PLANTS);
        shelf.setTile(1,0, ItemType.FRAMES);
        shelf.setTile(3,1, ItemType.CATS);
        shelf.setTile(0,3, ItemType.BOOKS);;
        shelf.setTile(4,1, ItemType.GAMES);
        shelf.setTile(3,4, ItemType.TROPHIES);

        assertEquals(p3.validated(shelf), 4);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(1,3, ItemType.GAMES);
        shelf2.setTile(3,4, ItemType.TROPHIES);

        assertEquals(p3.validated(shelf2), 2);
    }

}