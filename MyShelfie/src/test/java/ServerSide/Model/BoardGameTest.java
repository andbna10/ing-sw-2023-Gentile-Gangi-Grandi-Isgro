package ServerSide.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {

    @Test
    void setTilesTest() {

        // 2 players --------------- //
        var board = new BoardGamePublic(2);
        ArrayList<ItemTile> tiles = new ArrayList<>();

        for(int i=0; i<81; i++)
            tiles.add(new ItemTile(ItemType.CATS));

        //tiles.add(new ItemTile(ItemType.CATS));
        //tiles.add(new ItemTile(ItemType.GAMES));
        //tiles.add(new ItemTile(ItemType.BOOKS));

        board.setTiles(tiles);

        //assertEquals(board.getBoard()[2][3].getTile().getType(), tiles.get(0).getType());

        assertTrue(board.isInTile(7,4));
        assertNull(board.getBoard()[0][3].getTile());
        assertNull(board.getBoard()[0][0].getTile());

        // 3 players --------------- //
        var board2 = new BoardGamePublic(3);
        ArrayList<ItemTile> tiles2 = new ArrayList<>();

        for(int i=0; i<81; i++)
            tiles2.add(new ItemTile(ItemType.CATS));

        board2.setTiles(tiles2);

        assertFalse(board2.isInTile(7,6));
        assertNotNull(board2.getBoard()[0][3].getTile());


        // 4 players --------------- //
        var board3 = new BoardGamePublic(4);
        ArrayList<ItemTile> tiles3 = new ArrayList<>();

        for(int i=0; i<81; i++)
            tiles3.add(new ItemTile(ItemType.CATS));


        board3.setTiles(tiles3);

        assertNotNull(board3.getBoard()[0][4].getTile());
    }

}