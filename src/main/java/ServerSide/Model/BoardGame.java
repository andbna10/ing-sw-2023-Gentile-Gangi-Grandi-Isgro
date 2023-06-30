package ServerSide.Model;

import java.util.ArrayList;

public class BoardGame {
    private BoardCell[][] board = new BoardCell[9][9];
    private int emptyCells;

    /**
     * constructor of a boardgame
     * @param nPlayers number of players playing this match
     * @author Andrea Isgrò
     * @author Francesco Gangi
     */
    public BoardGame(int nPlayers){
        int[][] outofgame;

        if(nPlayers == 2) {
            outofgame = new int[][] {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},
                    {1,0},{1,1},{1,2},{1,5},{1,6},{1,7},{1,8},
                    {2,0},{2,1},{2,2},{2,6},{2,7},{2,8},
                    {3,0},{3,1},{3,8},
                    {4,0},{4,8},
                    {5,0},{5,7},{5,8},
                    {6,0},{6,1},{6,2},{6,6},{6,7},{6,8},
                    {7,0},{7,1},{7,2},{7,3},{7,6},{7,7},{7,8},
                    {8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7},{8,8}};
        } else if(nPlayers == 3) {
            outofgame = new int[][] {{0,0},{0,1},{0,2},{0,4},{0,5},{0,6},{0,7},{0,8},
                    {1,0},{1,1},{1,2},{1,5},{1,6},{1,7},{1,8},
                    {2,0},{2,1},{2,7},{2,8},
                    {3,0},{3,1},
                    {4,0},{4,8},
                    {5,7},{5,8},
                    {6,0},{6,1},{6,7},{6,8},
                    {7,0},{7,1},{7,2},{7,3},{7,6},{7,7},{7,8},
                    {8,0},{8,1},{8,2},{8,3},{8,4},{8,6},{8,7},{8,8}};
        } else {
            outofgame = new int[][] {{0,0},{0,1},{0,2},{0,5},{0,6},{0,7},{0,8},
                    {1,0},{1,1},{1,2},{1,6},{1,7},{1,8},
                    {2,0},{2,1},{2,7},{2,8},
                    {3,0},
                    {5,8},
                    {6,0},{6,1},{6,7},{6,8},
                    {7,0},{7,1},{7,2},{7,6},{7,7},{7,8},
                    {8,0},{8,1},{8,2},{8,3},{8,6},{8,7},{8,8}};
        }

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(isInGroup(i, j, outofgame)){
                    board[i][j] = new BoardCell(Status.OUT);
                } else {
                    board[i][j] = new BoardCell(Status.IN);
                }
            }
        }

        if(nPlayers == 2) {
            emptyCells = 29;
        } else if(nPlayers == 3) {
            emptyCells = 37;
        } else {
            emptyCells = 45;
        }
    }


    /**
     * Overview: function called by the constructor of the class to initialize the board
     * @param i row index
     * @param j column index
     * @param group array containing every couple of indexes (i,j) excluded from this board instance
     */
    private boolean isInGroup(int i, int j, int[][] group){
        for(int[] pair: group){
            if(i == pair[0] && j == pair[1]){
                return true;
            }
        }
        return false;
    }

    // to change if we want a terminal representation of the matrix
    /**
     * Overview: board getter
     */
    public BoardCell[][] getBoard(){ return board; }

    /**
     * Overview: insert tiles in the board
     * @author Andrea Isgrò
     * @author Mirko Gentile
     * @param tiles arraylist containing the tiles to be inserted
     */
    public void setTiles(ArrayList<ItemTile> tiles){
        int z=0;
        for(int i=0; i<board[0].length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j].getTile() == null && board[i][j].getStatus() == Status.IN){
                    board[i][j].setTile(tiles.get(z));
                    z++;
                }
            }
        }
        this.emptyCells = 0;
    }

    /**
     * Overview: tile getter from the board
     */
    public ItemTile getTile(int x, int y){
        if(isInTile(x,y)){
            return board[x][y].getTile();
        } else {
            return null;
        }
    }

    /**
     * Overview: method aimed to increment emptyCells attribute
     */
    public void increment(){ this.emptyCells++; }

    /**
     * Overview: check the presence of a tile in a cell
     * @author Andrea Isgrò
     * @author Francesco Gangi
     * @param x row index
     * @param y column index
     * @return boolean value based on the presence (true) or the absence (false) of the tile in position (x,y)
     */
    public Boolean isInTile(int x, int y){
        return board[x][y].getTile() != null;
    }

    /**
     * Overview: return the number of empty cells in the board game
     * @author Andrea Isgrò
     * @return the number of empty cells in this board game
     */
    public int getEmptyCells(){ return emptyCells; }


}