package ClientSide.View.GUI;

import ClientSide.NetworkHandler.GameHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameGUI {

    private GameHandler handler;
    private JTable boardTable = new JTable(9,9);
    private JLabel shelfLabel;
    private JLabel boardLabel;
    private JLabel bgLabel;
    private JFrame gameFrame;

    public GameGUI(GameHandler gameHandler) throws IOException {
        this.handler=gameHandler;
        gameFrame = new JFrame("MyShelfie");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        bgLabel = new JLabel(bg);

        ImageIcon gameIcon = new ImageIcon("MyShelfie/src/main/resources/Title 2000x2000px.png");
        Image scaledIcon = gameIcon.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        gameFrame.setIconImage(scaledIcon);

        ImageIcon boardImage = new ImageIcon("MyShelfie/src/main/resources/livingroom.png");
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(600,600, Image.SCALE_SMOOTH);
        boardLabel = new JLabel(new ImageIcon(scaledBoardImage));

        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(400,400, Image.SCALE_SMOOTH);
        shelfLabel = new JLabel(new ImageIcon(scaledShelf));



        boardTable.setRowHeight(60);
        ((DefaultTableCellRenderer)boardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        boardTable.setOpaque(false);
        boardTable.setShowGrid(false);
        boardTable.setDefaultEditor(Object.class, null);

        bgLabel.add(shelfLabel);
        gameFrame.add(bgLabel);


    }

    /**
     * method to fill the board
     */
    public void BoardRenderer(String[][] board) throws IOException {
        /*fai in modo che se c'era qualcosa prima venga tolto così che a ogni chiamata venga fatto tutto da capo
        * e action listener*/
        String[] boardcolumn = new String[9];
        for (int column = 0; column < 9; column++) {
            for(int j=0; j<9 ; j++){
                boardcolumn[j]=board[j][column];
            }
            boardTable.getColumnModel().getColumn(column).setCellRenderer(new ImageTableCellRenderer(boardcolumn,column));
        }

        boardTable.setBounds(58,60,540,540);
        shelfLabel.setBounds(1000,30,400,400);
        boardLabel.setBounds(30,30,600,600);
        boardLabel.add(boardTable);
        bgLabel.add(boardTable);
        bgLabel.add(boardLabel);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);

        boardTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int row = boardTable.getSelectedRow();
                int column = boardTable.getSelectedColumn();
                if (row != -1 && column != -1) {
                    System.out.println("Clicked cell coordinates: Row = " + row + ", Column = " + column);
                }
            }
        });
    }



    private static class ImageTableCellRenderer extends JLabel implements TableCellRenderer {
        private Image[][] images = new Image[9][9];

        public ImageTableCellRenderer(String[] board, int column) throws IOException {
            for (int i=0; i<board.length; i++) {
                        switch (board[i]) {
                            case "C" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Gatti1.1.png"));
                            case "B" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Libri1.1.png"));
                            case "G" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Giochi1.1.png"));
                            case "F" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Cornici1.1.png"));
                            case "T" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Trofei1.1.png"));
                            case "P" -> images[i][column] = ImageIO.read(new File("MyShelfie/src/main/resources/Piante1.1.png"));
                            //default -> ;
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
            }else renderer.setIcon(null);
            //l'else dovrebbe fare in modo che quando vado a sovrascrivere le immagini mi tolga quelle che sono state prese(da testare)

            return renderer;
        }
    }
}