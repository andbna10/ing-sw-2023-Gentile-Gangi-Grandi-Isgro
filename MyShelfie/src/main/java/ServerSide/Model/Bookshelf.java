package ServerSide.Model;

import java.util.ArrayList;

public class Bookshelf {
    private ItemTile[][] gameTiles = new ItemTile[6][5];
    private boolean[][] visited = new boolean[6][5]; //all default false
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
        for (int i=gameTiles.length-1; i>=0 && j<tiles.size(); i--){
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
        for (ItemTile[] gameTile : gameTiles) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if (gameTile[j] != null) {
                    out.append(gameTile[j].getId());
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
     * Overview: check if a column of the bookshelf is full
     */
    public Boolean columnIsFull(int index){
        for (ItemTile[] gameTile : gameTiles) if (gameTile[index] == null) return false;
        return true;
    }

    /**
     * Overview: check if the bookshelf is full
     */
    public Boolean bookshelfIsFull(){
        for(int i=0; i<gameTiles.length; i++)
            for(int j=0; i<gameTiles[0].length; j++)
                if(gameTiles[i][j] != null) return false;

        return true;
    }

    /**
     * Overview: visited matrix getter
     */
    public boolean getVisited(int i,int j) { return visited[i][j]; }

    /**
     * Overview: visited matrix setter
     */
    public void setVisited(int i,int j) { visited[i][j] = true; }

}
