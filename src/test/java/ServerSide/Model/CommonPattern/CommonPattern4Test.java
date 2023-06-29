package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern4Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var shelf4 = new Bookshelf();
        var cp4 = new CommonPattern4();

        for (int i = 2; i < 4; i++)
            shelf.setTile(i, 0, ItemType.BOOKS);
        for (int i = 4; i < 6; i++) {
            shelf.setTile(i,0, ItemType.TROPHIES);
            shelf.setTile(i,2, ItemType.PLANTS);
            shelf.setTile(i,1,ItemType.TROPHIES);
            shelf.setTile(i,3,ItemType.GAMES);
            shelf.setTile(i,4,ItemType.FRAMES);
        }

                /*
         0 1 2 3 4
        |B|B| |T|T|
        | | | | |T|
        | | | | | |
        | | | | | |
        | | | | |P|
        |C|C|C| |P|
        8 punti
         */
        shelf4.setTile(5,0,ItemType.CATS);
        shelf4.setTile(5,1,ItemType.CATS);
        shelf4.setTile(5,2,ItemType.CATS);//+2 couples*/
        shelf4.setTile(5,4,ItemType.PLANTS);
        shelf4.setTile(4,4,ItemType.PLANTS);//+1
        shelf4.setTile(0,0,ItemType.BOOKS);
        shelf4.setTile(0,1,ItemType.BOOKS);//+1
        shelf4.setTile(0,3,ItemType.TROPHIES);
        shelf4.setTile(0,4,ItemType.TROPHIES);
        shelf4.setTile(1,4,ItemType.TROPHIES);//+2

        assertTrue(cp4.validated(shelf));

        assertTrue(cp4.validated(shelf4));
    }
}