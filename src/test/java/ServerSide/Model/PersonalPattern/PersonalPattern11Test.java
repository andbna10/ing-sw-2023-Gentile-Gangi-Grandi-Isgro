package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern11Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p11 = new PersonalPattern11();

        shelf.setTile(0,2, ItemType.PLANTS);
        shelf.setTile(3,2, ItemType.FRAMES);
        shelf.setTile(4,4, ItemType.CATS);
        shelf.setTile(1,1, ItemType.BOOKS);;
        shelf.setTile(1,2, ItemType.GAMES);
        shelf.setTile(5,3, ItemType.TROPHIES);

        assertEquals(p11.validated(shelf), 9);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(2,0, ItemType.GAMES);
        shelf2.setTile(2,2, ItemType.TROPHIES);

        assertEquals(p11.validated(shelf2), 1);
    }

}