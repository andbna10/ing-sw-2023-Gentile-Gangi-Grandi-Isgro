package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern11Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var shelf2 = new Bookshelf();
        var shelf3 = new Bookshelf();
        var shelf4 = new Bookshelf();
        var cp11 = new CommonPattern11();

        shelf.setTile(1,0, ItemType.TROPHIES);
        shelf.setTile(2,1, ItemType.TROPHIES);
        shelf.setTile(3,2, ItemType.TROPHIES);
        shelf.setTile(4,3, ItemType.TROPHIES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertTrue(cp11.validated(shelf));

        shelf2.setTile(1,0, ItemType.TROPHIES);
        shelf2.setTile(2,1, ItemType.TROPHIES);
        shelf2.setTile(3,2, ItemType.TROPHIES);
        shelf2.setTile(4,3, ItemType.PLANTS);
        shelf2.setTile(5,4, ItemType.TROPHIES);

        assertFalse(cp11.validated(shelf2));

        shelf3.setTile(1,0,ItemType.TROPHIES);
        shelf3.setTile(2,1,ItemType.TROPHIES);
        shelf3.setTile(3,2,ItemType.TROPHIES);
        shelf3.setTile(4,3,ItemType.TROPHIES);
        shelf3.setTile(5,4,ItemType.TROPHIES);
        for(int i=0; i<6;i++){
            for(int j=0; j<5; j++){
                if(shelf3.getGameTiles()[i][j] == null){
                    shelf3.setTile(i,j, ItemType.PLANTS);
                }
            }
        }

        assertTrue(cp11.validated(shelf3));

        shelf4.setTile(1,0,ItemType.GAMES);
        shelf4.setTile(5,0,ItemType.GAMES);
        shelf4.setTile(4,1,ItemType.GAMES);
        shelf4.setTile(3,1,ItemType.CATS);
        shelf4.setTile(4,2,ItemType.CATS);
        shelf4.setTile(4,4,ItemType.CATS);
        shelf4.setTile(5,4,ItemType.CATS);
        shelf4.setTile(2,0,ItemType.TROPHIES);
        shelf4.setTile(3,0,ItemType.TROPHIES);
        shelf4.setTile(3,4,ItemType.TROPHIES);
        shelf4.setTile(3,2,ItemType.PLANTS);
        shelf4.setTile(4,0,ItemType.PLANTS);
        shelf4.setTile(5,2,ItemType.BOOKS);
        shelf4.setTile(5,1,ItemType.FRAMES);

        assertFalse(cp11.validated(shelf4));
    }

}