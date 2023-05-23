package ClientSide.View.GUI;

import ServerSide.Model.BoardCell;
import ServerSide.Model.BoardGame;
import ServerSide.Model.Game;
import ServerSide.Model.Status;

import javax.swing.table.AbstractTableModel;

public class BoardTableModel extends AbstractTableModel {

    private Game model;

    @Override
    public int getRowCount() {
        return 9;
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void setValueAt(BoardCell[][] board) {
        /*
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length;j++) {
                if(board[i][j].getStatus() == Status.IN){
                    switch (board[i][j].getTile().getType()) {
                        case CATS -> ;
                        case BOOKS -> ;
                        case GAMES -> ;
                        case FRAMES -> ;
                        case TROPHIES -> ;
                        case PLANTS ->;
                        default -> ;
                    }
                } else {
                    //not clickable
                }
            }
        }

         */
    }
}
