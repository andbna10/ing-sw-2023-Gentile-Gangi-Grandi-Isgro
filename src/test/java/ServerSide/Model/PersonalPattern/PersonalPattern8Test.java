package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern8Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p8 = new PersonalPattern8();

        shelf.setTile(4,4, ItemType.PLANTS);
        shelf.setTile(3,1, ItemType.FRAMES);
        shelf.setTile(4,2, ItemType.CATS);
        shelf.setTile(3,2, ItemType.BOOKS);;
        shelf.setTile(0,4, ItemType.GAMES);
        shelf.setTile(1,1, ItemType.TROPHIES);

        assertEquals(p8.validated(shelf), 0);

        var shelf2 = new Bookshelf();

        shelf2.setTile(3,0, ItemType.PLANTS);
        shelf2.setTile(5,3, ItemType.GAMES);
        shelf2.setTile(2,2, ItemType.TROPHIES);

        assertEquals(p8.validated(shelf2), 4);
    }

}