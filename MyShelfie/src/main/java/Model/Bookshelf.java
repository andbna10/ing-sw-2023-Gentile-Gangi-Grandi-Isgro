package Model;

import java.util.ArrayList;

public class Bookshelf {
    private ItemTile[][] gameTiles = new ItemTile[6][5];
    private boolean[][] visited= new boolean[6][5]; //all default false
    private Boolean isFull;

    private Boolean commonOne;

    private Boolean commonTwo;

    /**
     * Overview: bookshelf constructor
     */
    public Bookshelf(){
        isFull = false;
        commonOne = false;
        commonTwo = false;
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
     * Overview: get commonOne
     */
    public Boolean getCommonOne(){ return commonOne; }

    /**
     * Overview: get commonTwo
     */
    public Boolean getCommonTwo(){ return commonTwo; }

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
    public Boolean bookshelfIsFull(){
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
     * Overview: check if the player can complete the move
     */
    public Boolean canInsert(int nTiles, int column){
        if(columnisFull(column)){
            return false;
        } else {
            int count = 0;
            for(int i = gameTiles.length-1; i >= 0; i--){
                if(gameTiles[i][column] == null){
                    count++;
                }
            }
            if(count >= nTiles){
                return true;
            } else {
                return false;
            }
        }
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
            } else { continue; }
        }
    }

    /**
     * Overview: get tile of index i, j
     */
    public ItemTile getTile(int i, int j) {
        return gameTiles[i][j];
    }

    /**
     * Overview: visited matrix getter
     */
    public boolean getVisited(int i,int j){return visited[i][j];}

    /**
     * Overview: visited matrix setter
     */
    public void setVisited(int i,int j){ visited[i][j]=true;}

}
