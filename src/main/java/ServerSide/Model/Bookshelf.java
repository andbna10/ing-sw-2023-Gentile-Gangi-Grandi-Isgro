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
     * @author Simone Grandi
     */
    public Bookshelf(){
        isFull = false;
        commonOne = false;
        commonTwo = false;
    }

    /**
     * Overview: method aimed to make the bookshelf empty
     * @author Andrea Isgrò
     */
    public void emptyShelf(){
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                gameTiles[i][j] = null;
            }
        }
    }

    /**
     * Overview: check if the player can complete the move on column 'j'
     * @author Andrea Isgrò
     * @author Mirko Gentile
     * @param nTiles number of tiles to be inserted
     * @param j column index
     * @return true or false based on the possibility or not for tiles insertion in the desired column
     */
    public Boolean canInsert(int nTiles, int j){

        int num = 0;
        for(int i=0; i<5; i++){
            if(gameTiles[i][j] == null) num++;
        }

        return nTiles <= num;
    }

    /**
     * Overview: insert tiles in the bookshelf, let's suppose that the tiles input is already ordered
     * @author Mirko Gentile
     * @author Francesco Gangi
     * @param columnIndex column index
     * @param tiles arraylist containing the tiles to be inserted
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
     * Overview: method aimed to insert in a specific position of the bookshelf a given tile, knowing the type. Needed for goals creation.
     * @author Simone Grandi
     * @param i row index
     * @param j column index
     * @param type item tile's type
     */
    public void setTile(int i, int j, ItemType type){
        gameTiles[i][j]= new ItemTile(type);
    }


    /**
     * Overview: get tile of index i, j
     */
    public ItemTile getTile(int i, int j) {
        return gameTiles[i][j];
    }


    /**
     * Overview: returns the array with the bookshelf's tiles
     */
    public ItemTile[][] getGameTiles(){ return gameTiles; }


    /**
     * Overview: bookshelf getter
     * @deprecated
     */
    @Deprecated
    public StringBuffer getGameTilesBuffer(){
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
    }


    /**
     * Overview: isFull getter
     * @deprecated
     */
    @Deprecated
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
     * Overview: set commonOne
     */
    public void setCommonOne(Boolean status){ this.commonOne = status; }

    /**
     * Overview: set commonTwo
     */
    public void setCommonTwo(Boolean status){ this.commonTwo = status; }

    /**
     * Overview: check if a column of the bookshelf is full
     * @deprecated
     */
    @Deprecated
    public Boolean columnIsFull(int index){
        for (ItemTile[] gameTile : gameTiles) if (gameTile[index] == null) return false;
        return true;
    }

    /**
     * Overview: check if the bookshelf is full
     */
    public Boolean bookshelfIsFull(){
        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                if(gameTiles[i][j] == null) return false;

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
