package Model;

import java.util.ArrayList;

public class Bookshelf {
    private ItemTile[][] gameTiles = new ItemTile[6][5];
    private Boolean isFull;

    /**
     * Overview: bookshelf constructor
     */
    public Bookshelf(){ isFull = false; }

    /**
     * Overview: check if the player can complete the move on column 'j'
     */
    public Boolean canInsert(int nTiles, int j){

        int i = 0; //busy height of the column

        while(gameTiles[i][j] != null) i++;
        return (nTiles + i <= 5); //logical condition, either true or false
    }

    /**
     * Overview: insert tiles in the bookshelf, let's suppose that the tiles input is already ordered
     */
    public void setTiles(int columnIndex, ArrayList<ItemTile> tiles){
        int j=0;
        for (int i=gameTiles.length-1; i>= 0; i--){
            if(gameTiles[i][columnIndex] == null) {
                gameTiles[i][columnIndex] = tiles.get(j);
                j++;
            }
        }
    }

    /**
     * Overview: get tile of index i, j
     */
    public ItemTile getTile(int i, int j) {
        return gameTiles[i][j];
    }


    /**
     * Overview: bookshelf getter
     */
    public StringBuffer getGameTiles(){
        StringBuffer out = new StringBuffer();
        for(int i=0; i<gameTiles.length; i++){
            for(int j=0; j<gameTiles[0].length; j++){
                if(gameTiles[i][j] != null){
                    out.append(gameTiles[i][j].getId());
                } else {
                    out.append("null");
                }
                out.append(", ");
            }
            out.append("\n");
        }
        return out;

        /*
        return gameTiles;
         */
    }


    /**
     * Overview: isFull getter
     */
    public Boolean getIsFull(){ return isFull; }

    /**
     * Overview: check if a column of the booksheld is full
     */
    public Boolean columnisFull(int index){
        for(int i=0; i<gameTiles.length; i++)
            if(gameTiles[i][index] == null) return false;

        return true;
    }

    /**
     * Overview: check if the bookshelf is full
     */
    public Boolean bookshelfIsFull(){
        for(int i=0; i<gameTiles.length;i++)
            for(int j=0; i<gameTiles[0].length;j++)
                if(gameTiles[i][j] != null) return false;

        return true;
    }

}
