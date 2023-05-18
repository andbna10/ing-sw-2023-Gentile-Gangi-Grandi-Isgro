package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern12Test {
    @Test
    void validatedTest() {
        var shelf1 = new Bookshelf();
        var shelf2 = new Bookshelf();
        var cp12 = new CommonPattern12();

        for (int i=1; i<5; i++)
            shelf1.setTile(i,0, ItemType.TROPHIES);
        for (int j=0; j<5; j++)
            shelf1.setTile(5,j, ItemType.PLANTS);
        shelf1.setTile(2,1, ItemType.PLANTS);
        shelf1.setTile(3,1, ItemType.BOOKS);
        shelf1.setTile(3,2, ItemType.FRAMES);
        shelf1.setTile(4,1, ItemType.BOOKS);
        shelf1.setTile(4,2, ItemType.GAMES);
        shelf1.setTile(4,3, ItemType.CATS);

        assertTrue(cp12.validated(shelf1));

        for (int i=1; i<5; i++)
            shelf2.setTile(i,4, ItemType.TROPHIES);
        for (int j=0; j<5; j++)
            shelf2.setTile(5,j, ItemType.PLANTS);
        shelf2.setTile(2,3, ItemType.PLANTS);
        shelf2.setTile(3,3, ItemType.BOOKS);
        shelf2.setTile(3,2, ItemType.FRAMES);
        shelf2.setTile(4,1, ItemType.BOOKS);
        shelf2.setTile(4,2, ItemType.GAMES);
        shelf2.setTile(4,3, ItemType.CATS);

        assertTrue(cp12.validated(shelf2));
    }

}