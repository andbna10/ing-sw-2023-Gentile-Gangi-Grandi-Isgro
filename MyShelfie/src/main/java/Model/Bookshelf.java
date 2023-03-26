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
     * Overview: bookshlef getter
     */
    public StringBuffer getGameTiles(){
        StringBuffer out = new StringBuffer();
        for(int i=0; i<gameTiles.length; i++){
            for(int j=0; j<gameTiles[0].length; j++){
                if(gameTiles[i][j] != null){
                    out.append(gameTiles[i][j].getId());
                } else {
                    out.append(gameTiles[i][j]);
                }
                out.append(",");
            }
            out.append("\n");
        }
        return out;

        /*
        return gameaTiles;
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
        for(int i=0; i<gameTiles.length; i++){
            if(gameTiles[i][index] != null){
                continue;
            } else {
                return false;
            }
        }
       return true;
    }

    /**
     * Overview: check if the bookshelf is full
     */
    public Boolean bookshlefisFull(){
        for(int i=0; i<gameTiles.length;i++){
            for(int j=0; i<gameTiles[0].length;j++){
                if(gameTiles[i][j] != null){
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Overview: insert tiles in the bookshelf, let's suppose that the tiles input is already in order
     */
    public void setTiles(int columnIndex, int rowIndex, ArrayList<ItemTile> tiles){
        int j=0;
        for (int i=rowIndex; i>= rowIndex - tiles.size(); i--){
            gameTiles[i][columnIndex] = tiles.get(j);
            j++;
        }
    }



}
