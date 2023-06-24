package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern7Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p7 = new PersonalPattern7();

        shelf.setTile(2,1, ItemType.PLANTS);
        shelf.setTile(1,3, ItemType.FRAMES);
        shelf.setTile(0,0, ItemType.CATS);
        shelf.setTile(5,2, ItemType.BOOKS);;
        shelf.setTile(4,4, ItemType.GAMES);
        shelf.setTile(1,1, ItemType.TROPHIES);

        assertEquals(p7.validated(shelf), 9);

        var shelf2 = new Bookshelf();

        /*
        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(5,0, ItemType.GAMES);
        shelf2.setTile(2,0, ItemType.TROPHIES);
         */

        assertEquals(p7.validated(shelf2), 0);
    }

}