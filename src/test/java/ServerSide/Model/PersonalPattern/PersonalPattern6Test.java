package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalPattern6Test {

    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var p6 = new PersonalPattern6();

        shelf.setTile(5,0, ItemType.PLANTS);
        shelf.setTile(4,3, ItemType.FRAMES);
        shelf.setTile(0,4, ItemType.CATS);
        shelf.setTile(2,3, ItemType.BOOKS);;
        shelf.setTile(4,1, ItemType.GAMES);
        shelf.setTile(0,2, ItemType.TROPHIES);

        assertEquals(p6.validated(shelf), 12);

        var shelf2 = new Bookshelf();

        /*
        shelf2.setTile(1,1, ItemType.PLANTS);
        shelf2.setTile(5,0, ItemType.GAMES);
        shelf2.setTile(2,0, ItemType.TROPHIES);
        */

        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                shelf2.setTile(i,j,ItemType.TROPHIES);
            }
        }
        assertEquals(p6.validated(shelf2), 1);
    }

}