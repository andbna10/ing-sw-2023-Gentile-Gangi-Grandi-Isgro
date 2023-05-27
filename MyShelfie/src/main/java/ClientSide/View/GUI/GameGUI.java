package ClientSide.View.GUI;

import ClientSide.NetworkHandler.GameHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameGUI {

    private GameHandler handler;
    private JTable boardTable = new JTable(9,9);
    private ArrayList<JTable> shelfTables;
    private ArrayList<JLabel> shelfLabels;
    private JLabel boardLabel;
    private JLabel PersonalLabel;
    private JLabel Common1Label;
    private JLabel Common2Label;
    private JLabel token1Label;
    private JLabel token2Label;

    private JLabel bgLabel;
    private JFrame gameFrame;

    public GameGUI(GameHandler gameHandler) throws IOException {
        this.shelfTables = new ArrayList<>();
        this.shelfLabels = new ArrayList<>();
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
        int bookshelfsize=300;
        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(bookshelfsize,bookshelfsize, Image.SCALE_SMOOTH);
        shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
        shelfLabels.get(0).setBounds(770,50,bookshelfsize,bookshelfsize);
        shelfTables.add(new JTable(6,5));
        shelfTables.get(0).setBounds(780, 60, bookshelfsize+10, bookshelfsize+10);
        shelfTables.get(0).setRowHeight(60);
        ((DefaultTableCellRenderer)shelfTables.get(0).getDefaultRenderer(Object.class)).setOpaque(false);
        shelfTables.get(0).setOpaque(false);
        shelfTables.get(0).setShowGrid(false);
        shelfTables.get(0).setDefaultEditor(Object.class, null);
        bgLabel.add(shelfLabels.get(0));



        gameFrame.add(bgLabel);
        gameFrame.pack();
    }

    /**
     * Overview: method aimed to show info to the client
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Overview: your turn message update
     */
    public void YourTurnRender(int i, String username, String[][] bookshelf) throws IOException {
        String[] bookshelfcolumn = new String[6];
        for (int column = 0; column < 5; column++) {
            for(int j=0; j<6 ; j++){
                bookshelfcolumn[j]=bookshelf[j][column];
            }
            shelfTables.get(i).getColumnModel().getColumn(column).setCellRenderer(new ImageBookshelfCellRenderer(bookshelfcolumn,column));
            // gestisci l'username
        }
    }

    /**
     * Overview: method aimed to render the initial setup
     */
    public void InitialSetUpRenderer(int numPlayers, String[][] board, int numberPattern, int common1, int common2, int token1, int token2) throws IOException {
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
        Image scaledpersonal = PersonalGoalImage.getImage().getScaledInstance(200,303, Image.SCALE_SMOOTH);

        PersonalLabel = new JLabel(new ImageIcon(scaledpersonal));
        PersonalLabel.setBounds(-20, 500, 300, 600);

        bgLabel.add(PersonalLabel);

        // token
        ImageIcon token1image = new ImageIcon("MyShelfie/src/main/resources/scoringTokens/scoring_"+token1+".jpg");
        Image scaledtoken1 = token1image.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon token2image = new ImageIcon("MyShelfie/src/main/resources/scoringTokens/scoring_"+token2+".jpg");
        Image scaledtoken2 = token2image.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);

        token1Label = new JLabel(new ImageIcon(scaledtoken1));
        token1Label.setBounds(335,700, 90, 90);
        token2Label = new JLabel(new ImageIcon(scaledtoken2));
        token2Label.setBounds(535,700, 90, 90);

        bgLabel.add(token1Label);
        bgLabel.add(token2Label);

        // common
        ImageIcon Common1 = new ImageIcon("MyShelfie/src/main/resources/common/"+common1+".jpg");
        Image scaledcommon1 = Common1.getImage().getScaledInstance(180,119, Image.SCALE_SMOOTH);
        ImageIcon Common2 = new ImageIcon("MyShelfie/src/main/resources/common/"+common2+".jpg");
        Image scaledcommon2 = Common2.getImage().getScaledInstance(180,119, Image.SCALE_SMOOTH);

        Common1Label = new JLabel(new ImageIcon(scaledcommon1));
        Common1Label.setBounds(240, 650, 200, 200);
        Common2Label = new JLabel(new ImageIcon(scaledcommon2));
        Common2Label.setBounds(440, 650, 200, 200);

        bgLabel.add(Common1Label);
        bgLabel.add(Common2Label);

        //initialization of the others players
        int bookshelfsize=300;
        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(bookshelfsize,bookshelfsize, Image.SCALE_SMOOTH);

        shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
        shelfLabels.get(1).setBounds(1090,50,bookshelfsize,bookshelfsize);
        shelfTables.add(new JTable(6,5));
        shelfTables.get(1).setBounds(1100, 65, bookshelfsize+10, bookshelfsize+10);
        shelfTables.get(1).setRowHeight(60);
        ((DefaultTableCellRenderer)shelfTables.get(1).getDefaultRenderer(Object.class)).setOpaque(false);
        shelfTables.get(1).setOpaque(false);
        shelfTables.get(1).setShowGrid(false);
        shelfTables.get(1).setDefaultEditor(Object.class, null);
        bgLabel.add(shelfLabels.get(1));

        if(numPlayers>2) {
            shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
            shelfLabels.get(2).setBounds(770, 350, bookshelfsize, bookshelfsize);
            shelfTables.add(new JTable(6,5));
            shelfTables.get(2).setBounds(780, 360, bookshelfsize+10, bookshelfsize+10);
            shelfTables.get(2).setRowHeight(60);
            ((DefaultTableCellRenderer)shelfTables.get(2).getDefaultRenderer(Object.class)).setOpaque(false);
            shelfTables.get(2).setOpaque(false);
            shelfTables.get(2).setShowGrid(false);
            shelfTables.get(2).setDefaultEditor(Object.class, null);
            bgLabel.add(shelfLabels.get(2));
        }
        if(numPlayers>3) {
            shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
            shelfLabels.get(3).setBounds(1090, 350, bookshelfsize, bookshelfsize);
            shelfTables.add(new JTable(6,5));
            shelfTables.get(3).setBounds(1100, 360, bookshelfsize+10, bookshelfsize+10);
            shelfTables.get(3).setRowHeight(60);
            ((DefaultTableCellRenderer)shelfTables.get(3).getDefaultRenderer(Object.class)).setOpaque(false);
            shelfTables.get(3).setOpaque(false);
            shelfTables.get(3).setShowGrid(false);
            shelfTables.get(3).setDefaultEditor(Object.class, null);
            bgLabel.add(shelfLabels.get(3));
        }

        //show a text with coordinates of a clicked cell
        JLabel textLabel = new JLabel();
        textLabel.setBounds(640,650,300,150);
        bgLabel.add(textLabel);

        // visibility on the frame
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);


        // questo va spostata e chiamata in seguito all'arrivo del messaggio is your turn
        boardTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int row = boardTable.getSelectedRow();
                int column = boardTable.getSelectedColumn();
                if (row != -1 && column != -1) {
                    //System.out.println("Clicked cell coordinates: Row = " + row + ", Column = " + column);
                    String labelText = "Clicked cell coordinates: Row = " + row + ", Column = " + column;
                    //String labelText = textLabel.getText() + "\nClicked cell coordinates: Row = " + row + ", Column = " + column;
                    textLabel.setText(labelText);
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

    /**
     * Overview: Class for rendering bookshelf tiles images
     */
    private static class ImageBookshelfCellRenderer extends JLabel implements TableCellRenderer {
        private Image[][] images = new Image[6][5];

        public ImageBookshelfCellRenderer(String[] bookshelf, int column) throws IOException {
            for (int i=0; i<bookshelf.length; i++) {
                switch (bookshelf[i]) {
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

            int border=10; //offset che tolgo dalla riscalatura dell'immagine sia per larghezza che altezza

            if (image != null) {
                ImageIcon imageIcon = new ImageIcon(image);
                Image scaledImage = imageIcon.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth()-border, table.getRowHeight()-border, Image.SCALE_SMOOTH);
                renderer.setIcon(new ImageIcon(scaledImage));
                //allineamento per far si che l'immagine riscalata venga centrata nella cella
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                renderer.setVerticalAlignment(SwingConstants.CENTER);
            }else renderer.setIcon(null);
            //l'else dovrebbe fare in modo che quando vado a sovrascrivere le immagini mi tolga quelle che sono state prese(da testare)

            return renderer;
        }
    }
}