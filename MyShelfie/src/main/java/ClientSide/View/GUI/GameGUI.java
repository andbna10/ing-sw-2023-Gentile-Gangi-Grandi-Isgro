package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;
import ServerSide.Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameGUI {

    private LoginHandler handler;

    public GameGUI(LoginHandler handler) { this.handler = handler; }

    public GameGUI() throws IOException {
        JFrame gameFrame = new JFrame("MyShelfie");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        JLabel bgLabel = new JLabel(bg);

        ImageIcon gameIcon = new ImageIcon("MyShelfie/src/main/resources/Title 2000x2000px.png");
        Image scaledIcon = gameIcon.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        gameFrame.setIconImage(scaledIcon);

        ImageIcon boardImage = new ImageIcon("MyShelfie/src/main/resources/livingroom.png");
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(600,600, Image.SCALE_SMOOTH);
        JLabel boardLabel = new JLabel(new ImageIcon(scaledBoardImage));

        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(400,400, Image.SCALE_SMOOTH);
        JLabel shelfLabel = new JLabel(new ImageIcon(scaledShelf));


        JTable boardTable = new JTable(9,9);
        boardTable.setRowHeight(60);
        ((DefaultTableCellRenderer)boardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        boardTable.setOpaque(false);
        boardTable.setShowGrid(false);
        boardTable.setDefaultEditor(Object.class, null);

        //board to try the code
        BoardCell[][] board = new BoardCell[9][9];
        for(int i=0; i<board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new BoardCell(Status.IN);
            }
        }
        board[0][0].setTile(new ItemTile(ItemType.BOOKS));
        board[5][4].setTile(new ItemTile(ItemType.FRAMES));
        board[2][3].setTile(new ItemTile(ItemType.GAMES));
        board[7][5].setTile(new ItemTile(ItemType.TROPHIES));

        for (int column = 0; column < 9; column++) {
            boardTable.getColumnModel().getColumn(column).setCellRenderer(new ImageTableCellRenderer(board));
        }



        boardTable.setBounds(58,60,540,540);
        shelfLabel.setBounds(1000,30,400,400);
        boardLabel.setBounds(30,30,600,600);
        boardLabel.add(boardTable);
        bgLabel.add(boardTable);
        bgLabel.add(boardLabel);
        bgLabel.add(shelfLabel);
        gameFrame.add(bgLabel);


        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);
    }

    private static class ImageTableCellRenderer extends JLabel implements TableCellRenderer {
        private Image[][] images = new Image[9][9];

        public ImageTableCellRenderer(BoardCell[][] board) throws IOException {
            for (int i=0; i<board.length; i++) {
                for (int j = 0; j < board.length; j++){
                    if(board[i][j].getTile()!=null && board[i][j].getStatus() == Status.IN) {

                        switch (board[i][j].getTile().getType()) {
                            case CATS -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Gatti1.1.png"));
                            case BOOKS -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Libri1.1.png"));
                            case GAMES -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Giochi1.1.png"));
                            case FRAMES -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Cornici1.1.png"));
                            case TROPHIES -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Trofei1.1.png"));
                            case PLANTS -> images[i][j] = ImageIO.read(new File("MyShelfie/src/main/resources/Piante1.1.png"));
                            //default -> ;
                        }
                    }
                }
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel renderer = new JLabel();

            Image image = images[row][column];

            if (image != null) {
                ImageIcon imageIcon = new ImageIcon(image);
                Image scaledImage = imageIcon.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(), Image.SCALE_SMOOTH);
                renderer.setIcon(new ImageIcon(scaledImage));
            }

            return renderer;
        }
    }
}