package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern12Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p12 = new PersonalPattern12();

        shelf.setTile(1,1, ItemType.PLANTS);
        shelf.setTile(2,2, ItemType.FRAMES);
        shelf.setTile(3,4, ItemType.CATS);
        shelf.setTile(2,0, ItemType.BOOKS);;
        shelf.setTile(4,4, ItemType.GAMES);
        shelf.setTile(3,3, ItemType.TROPHIES);

        assertEquals(p12.validated(shelf), 6);

        var shelf2 = new Bookshelf();

        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(5,0, ItemType.GAMES);
        shelf2.setTile(2,0, ItemType.TROPHIES);

        assertEquals(p12.validated(shelf2), 1);
    }

}