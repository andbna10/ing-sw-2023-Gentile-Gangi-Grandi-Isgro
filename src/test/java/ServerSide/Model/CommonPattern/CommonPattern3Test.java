package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern3Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var shelf1 = new Bookshelf();
        var shelf2 = new Bookshelf();
        var shelf3 = new Bookshelf();
        var cp3 = new CommonPattern3();

        //vertical test
        /*
         0 1 2 3 4
        | |F| | | |
        | |F| | | |
        |B|F|G| |P|
        |B|F|G| |P|
        |B| |G| |P|
        |B| |G| |P|
         */
        for(int i=2; i<6; i++) {
            shelf.setTile(i, 0, ItemType.BOOKS);
            shelf.setTile(i, 2, ItemType.GAMES);
            //shelf.setTile(i, 1, ItemType.FRAMES);
            shelf.setTile(i, 4, ItemType.PLANTS);
        }
        shelf.setTile(0, 1, ItemType.FRAMES);
        shelf.setTile(1, 1, ItemType.FRAMES);
        shelf.setTile(2, 1, ItemType.FRAMES);
        shelf.setTile(3, 1, ItemType.FRAMES);


        //horizontal test
        for(int j=0; j<4; j++) {
            shelf1.setTile(0, j, ItemType.BOOKS);
            shelf1.setTile(2, j, ItemType.GAMES);
            shelf1.setTile(1, j, ItemType.FRAMES);
            shelf1.setTile(4, j, ItemType.PLANTS);
        }

        //mix
        /*
         0 1 2 3 4
        | |B|B|B|B|
        | |G|G|G|G|
        | |F| | |P|
        | |F| | |P|
        | |F| | |P|
        | |F| | |P|
         */
        for(int j=1; j<5; j++) {
            shelf2.setTile(0, j, ItemType.BOOKS);
            shelf2.setTile(1, j, ItemType.GAMES);
        }
        for(int i=2; i<6; i++) {
            shelf2.setTile(i, 1, ItemType.FRAMES);
            shelf2.setTile(i, 4, ItemType.PLANTS);
        }

        //mix
        /*
         0 1 2 3 4
        | |B|B|B|B|
        | | | | | |
        |F|F|F|F|P|
        | |F| | |P|
        | |F| | |P|
        | |F| | |P|
         */
        for(int j=1; j<5; j++) {
            shelf3.setTile(0, j, ItemType.BOOKS);
        }
        shelf3.setTile(2, 0, ItemType.FRAMES);
        shelf3.setTile(2, 3, ItemType.FRAMES);
        shelf3.setTile(2, 2, ItemType.FRAMES);
        for(int i=2; i<6; i++) {
            shelf3.setTile(i, 1, ItemType.FRAMES);
            shelf3.setTile(i, 4, ItemType.PLANTS);
        }


        assertTrue(cp3.validated(shelf));
        assertTrue(cp3.validated(shelf1));
        assertTrue(cp3.validated(shelf2));
        assertTrue(cp3.validated(shelf3));
    }

}