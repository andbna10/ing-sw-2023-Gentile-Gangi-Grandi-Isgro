package Model;

import java.util.ArrayList;

public class BoardGame {
    private static BoardGame instance;
    private BoardCell[][] board = new BoardCell[9][9];
    private int emptyCells;

    /**
     * constructor of a singleton boardgame
     */
    private BoardGame(int nPlayers){
        int[][] outofgame;
        int[][] outofgame4 =
                {{0,0},{0,1},{0,2},{0,5},{0,6},{0,7},{0,8},
                        {1,0},{1,1},{1,2},{1,6},{1,7},{1,8},
                        {2,0},{2,1},{2,7},{2,8},
                        {3,0},
                        {5,8},
                        {6,0},{6,1},{6,7},{6,8},
                        {7,0},{7,1},{7,2},{7,6},{7,7},{7,8},
                        {8,0},{8,1},{8,2},{8,3},{8,6},{8,7},{8,8}};

        int[][] outofgame3 =
                {{0,0},{0,1},{0,2},{0,4},{0,5},{0,6},{0,7},{0,8},
                        {1,0},{1,1},{1,2},{1,5},{1,6},{1,7},{1,8},
                        {2,0},{2,1},{2,7},{2,8},
                        {3,0},{3,1},
                        {4,0},{4,8},
                        {5,7},{5,8},
                        {6,0},{6,1},{6,7},{6,8},
                        {7,0},{7,1},{7,2},{7,3},{7,6},{7,7},{7,8},
                        {8,0},{8,1},{8,2},{8,3},{8,4},{8,6},{8,7},{8,8}};

        int[][] outofgame2 =
                {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},
                        {1,0},{1,1},{1,2},{1,5},{1,6},{1,7},{1,8},
                        {2,0},{2,1},{2,2},{2,6},{2,7},{2,8},
                        {3,0},{3,1},{3,8},
                        {4,0},{4,8},
                        {5,0},{5,7},{5,8},
                        {6,0},{6,1},{6,2},{6,6},{6,7},{6,8},
                        {7,0},{7,1},{7,2},{7,3},{7,6},{7,7},{7,8},
                        {8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7},{8,8}};
        if(nPlayers == 2){
            outofgame = outofgame2;
        } else if(nPlayers == 3){
            outofgame = outofgame3;
        } else {
            outofgame = outofgame4;
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

        if(nPlayers == 2){
            emptyCells = 29;
        } else if( nPlayers == 3){
            emptyCells = 37;
        } else {
            emptyCells = 45;
        }
    }

    /**
     * Overview: function called by the constructor of the class to initialize the board
     */
    private boolean isInGroup(int i, int j, int[][] group){
        for(int[] pair: group){
            if(i == pair[0] && j == pair[1]){
                return true;
            }
        }
        return false;
    }

    /**
     * Overview: This method should check whether an instance of the class has been created and return it if it exists, or create a new instance if it does not exist (it's a singleton)
     */
    public static BoardGame getInstance(int nPlayers){
        if(instance == null){
            instance = new BoardGame(nPlayers);
        }
        return instance;
    }

    // to change if we want a terminal representation of the matrix
    /**
     * Overview: board getter
     */
    public BoardCell[][] getBoard(){ return board; }

    /**
     * Overview: insert tiles in the board
     */
    public void setTiles(ArrayList<ItemTile> tiles){
        int z=0;
        for(int i=0; i<board[0].length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j].getTile() == null && board[i][j].getStatus()== Status.IN){
                    board[i][j].setTile(tiles.get(z));
                    z++;
                }
            }
        }
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
     * Overview: check the presence of a tile in a cell
     */
    public Boolean isInTile(int x, int y){
        if(board[x][y].getTile() != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overview: return the number of empty cells in the board game
     */
    public int getEmptyCells(){ return emptyCells; }


}