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
    private JLabel PersonalLabel;
    private JLabel Common1Label;
    private JLabel Common2Label;
    private JLabel token1Label;
    private JLabel token2Label;

    private JLabel bgLabel;
    private JFrame gameFrame;

    public GameGUI(GameHandler gameHandler) throws IOException {
        this.handler=gameHandler;

        // initialiazation of the frame
        gameFrame = new JFrame("MyShelfie");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialization of gbLabel
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        bgLabel = new JLabel(bg);

        // initialization of Title
        ImageIcon gameIcon = new ImageIcon("MyShelfie/src/main/resources/Title 2000x2000px.png");
        Image scaledIcon = gameIcon.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        gameFrame.setIconImage(scaledIcon);

        // initialization of the boardlabel
        ImageIcon boardImage = new ImageIcon("MyShelfie/src/main/resources/livingroom.png");
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(600,600, Image.SCALE_SMOOTH);
        boardLabel = new JLabel(new ImageIcon(scaledBoardImage));

        // initialization of the PersonalShelfLabel
        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(400,400, Image.SCALE_SMOOTH);
        shelfLabel = new JLabel(new ImageIcon(scaledShelf));
        shelfLabel.setBounds(1000,30,400,400);


        bgLabel.add(shelfLabel);
        gameFrame.add(bgLabel);

    }

    /**
     * Overview: method aimed to render the initial setup
     */
    public void InitialSetUpRenderer(String[][] board, int numberPattern, int common1, int common2, int token1, int token2) throws IOException {
        /*fai in modo che se c'era qualcosa prima venga tolto così che a ogni chiamata venga fatto tutto da capo
        * e action listener*/

        // board
        String[] boardcolumn = new String[9];
        for (int column = 0; column < 9; column++) {
            for(int j=0; j<9 ; j++){
                boardcolumn[j]=board[j][column];
            }
            boardTable.getColumnModel().getColumn(column).setCellRenderer(new ImageTableCellRenderer(boardcolumn,column));
        }

        boardTable.setBounds(58,60,540,540);
        boardLabel.setBounds(30,30,600,600);

        boardTable.setRowHeight(60);
        ((DefaultTableCellRenderer)boardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        boardTable.setOpaque(false);
        boardTable.setShowGrid(false);
        boardTable.setDefaultEditor(Object.class, null);

        boardLabel.add(boardTable);
        bgLabel.add(boardTable);
        bgLabel.add(boardLabel);

        // personal goal
        ImageIcon PersonalGoalImage = new ImageIcon("MyShelfie/src/main/resources/personal/Personal_Goals"+numberPattern+".png");
        Image scaledpersonal = PersonalGoalImage.getImage().getScaledInstance(200,300, Image.SCALE_SMOOTH);

        PersonalLabel = new JLabel(new ImageIcon(scaledpersonal));
        PersonalLabel.setBounds(-20, 500, 300, 600);

        bgLabel.add(PersonalLabel);

        // token
        ImageIcon token1image = new ImageIcon("MyShelfie/src/main/resources/scoringTokens/scoring_"+token1+".jpg");
        Image scaledtoke1 = token1image.getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH);
        ImageIcon token2image = new ImageIcon("MyShelfie/src/main/resources/scoringTokens/scoring_"+token2+".jpg");
        Image scaledtoke2 = token2image.getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH);

        token1Label = new JLabel(new ImageIcon(scaledtoke1));
        token1Label.setBounds(345,700, 90, 90);
        token2Label = new JLabel(new ImageIcon(scaledtoke2));
        token2Label.setBounds(545,700, 90, 90);

        bgLabel.add(token1Label);
        bgLabel.add(token2Label);

        // common
        ImageIcon Common1 = new ImageIcon("MyShelfie/src/main/resources/common/"+common1+".jpg");
        Image scaledcommon1 = Common1.getImage().getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon Common2 = new ImageIcon("MyShelfie/src/main/resources/common/"+common2+".jpg");
        Image scaledcommon2 = Common2.getImage().getScaledInstance(180,180, Image.SCALE_SMOOTH);

        Common1Label = new JLabel(new ImageIcon(scaledcommon1));
        Common1Label.setBounds(240, 650, 200, 200 );
        Common2Label = new JLabel(new ImageIcon(scaledcommon2));
        Common2Label.setBounds(440, 650, 200, 200 );

        bgLabel.add(Common1Label);
        bgLabel.add(Common2Label);

        // visibility on the frame
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);


        // questo va spostata e chiamata in seguito all'arrivo del messaggio is your turn
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


    /**
     * Overview: Class for rendering board tiles images
     */
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