package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern4Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p4 = new PersonalPattern4();

        shelf.setTile(3,3, ItemType.PLANTS);
        shelf.setTile(2,2, ItemType.FRAMES);
        shelf.setTile(4,2, ItemType.CATS);
        shelf.setTile(4,1, ItemType.BOOKS);;
        shelf.setTile(0,4, ItemType.GAMES);
        shelf.setTile(2,0, ItemType.TROPHIES);

        assertEquals(p4.validated(shelf), 12);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(0,4, ItemType.GAMES);
        shelf2.setTile(2,0, ItemType.TROPHIES);

        assertEquals(p4.validated(shelf2), 2);
    }

}