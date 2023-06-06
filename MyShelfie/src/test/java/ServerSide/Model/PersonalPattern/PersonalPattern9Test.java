package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern9Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p9 = new PersonalPattern9();

        shelf.setTile(4,4, ItemType.PLANTS);
        shelf.setTile(3,1, ItemType.FRAMES);
        shelf.setTile(2,2, ItemType.CATS);
        shelf.setTile(3,4, ItemType.BOOKS);;
        shelf.setTile(0,4, ItemType.GAMES);
        shelf.setTile(1,1, ItemType.TROPHIES);

        assertEquals(p9.validated(shelf), 4);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(0,2, ItemType.GAMES);
        shelf2.setTile(4,1, ItemType.TROPHIES);

        assertEquals(p9.validated(shelf2), 2);
    }

}