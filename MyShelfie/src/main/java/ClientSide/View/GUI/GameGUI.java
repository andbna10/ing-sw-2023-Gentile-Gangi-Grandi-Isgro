package ClientSide.View.GUI;

import ClientSide.NetworkHandler.GameHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameGUI {

    private GameHandler handler;
    private JTable boardTable = new JTable(9,9);
    private ArrayList<JTable> shelfTables;
    private ArrayList<JLabel> shelfLabels;
    private ArrayList<JTextField> shelfTexts;
    private JLabel boardLabel;
    private JLabel PersonalLabel;
    private JLabel Common1Label;
    private JLabel Common2Label;
    private JLabel token1Label;
    private JLabel token2Label;
    private JLabel bgLabel;
    private JFrame gameFrame;
    // objects to be used to perform turn in server side
    private int[] totake;
    private int[] order;
    private int column;
    // objects to be used to perform turn in client side
    private int nTiles;
    private int taken;
    private int index;
    private JTextArea textArea = new JTextArea();
    private int previousrow;
    private int previouscolumn;

    public GameGUI(GameHandler gameHandler) throws IOException {
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.getCaret().setSelectionVisible(true);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textArea.setFont(textArea.getFont().deriveFont(15f));
        this.shelfTables = new ArrayList<>();
        this.shelfLabels = new ArrayList<>();
        this.shelfTexts = new ArrayList<>();
        this.handler=gameHandler;

        // initialization of the frame
        gameFrame = new JFrame("MyShelfie");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialization of gbLabel
        ImageIcon bg = new ImageIcon("MyShelfie/src/main/resources/sfondo parquet.jpg");
        bgLabel = new JLabel(bg);
        bgLabel.add(textArea);

        // initialization of Title
        ImageIcon gameIcon = new ImageIcon("MyShelfie/src/main/resources/Title 2000x2000px.png");
        Image scaledIcon = gameIcon.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        gameFrame.setIconImage(scaledIcon);

        // initialization of the boardlabel
        ImageIcon boardImage = new ImageIcon("MyShelfie/src/main/resources/livingroom.png");
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(600,600, Image.SCALE_SMOOTH);
        boardLabel = new JLabel(new ImageIcon(scaledBoardImage));
        boardTable.setBounds(58,60,540,540);
        boardLabel.setBounds(30,30,600,600);
        boardTable.setRowHeight(60);
        ((DefaultTableCellRenderer)boardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        boardTable.setOpaque(false);
        boardTable.setShowGrid(false);
        boardTable.setDefaultEditor(Object.class, null);


        // initialization of the PersonalShelfLabel
        int bookshelfsize=310;
        int xgap = 38;
        int ygap = 21;
        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(bookshelfsize,bookshelfsize, Image.SCALE_SMOOTH);
        shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
        shelfLabels.get(0).setBounds(770,30,bookshelfsize,bookshelfsize);
        shelfTables.add(new JTable(6,5));
        shelfTables.get(0).setBounds(770 + xgap, 30 + ygap, bookshelfsize+10, bookshelfsize+10);
        shelfTables.get(0).setRowHeight(7*6);
        shelfTexts.add(new JTextField());
        shelfTexts.get(0).setBounds(875, 340, 100, 50);
        bgLabel.add(shelfTexts.get(0));
        //set column width
        for (int i = 0; i < shelfTables.get(0).getColumnCount(); i++) {
            shelfTables.get(0).getColumnModel().getColumn(i).setMaxWidth(10*5);
        }
        ((DefaultTableCellRenderer)shelfTables.get(0).getDefaultRenderer(Object.class)).setOpaque(false);
        shelfTables.get(0).setOpaque(false);
        shelfTables.get(0).setShowGrid(false);
        shelfTables.get(0).setDefaultEditor(Object.class, null);
        bgLabel.add(shelfLabels.get(0));
        bgLabel.add(shelfTables.get(0));



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
     * Overview: method to end the game and back to the lobby
     */
    public void backToTheLobby(String message){
        int option = JOptionPane.showOptionDialog(null, message, "End of the game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Back to the lobby"}, "Back to the lobby");

        if(option == 0){
            handler.createBackToTheLobbyMessage();
        }
    }

    /**
     * Overview: method aimed to remove event listeners
     */
    public void removeListeners(){ boardTable.removeMouseListener(listener); }

    /**
     * Overview: method aimed to continue the turn procedure after having selected the tiles
     */
    public synchronized void continueTurnProcedure(){
        // forming the order
        showMessage("Look at the indexes of the selected tiles and insert once at a time the order of tiles to fill the column!");
        for(int j = 0; j< nTiles; j++){
            int x = -1;
            do{
                try{
                    x = Integer.parseInt(JOptionPane.showInputDialog(gameFrame, "Enter the "+j+"-th index", "Enter an index", JOptionPane.QUESTION_MESSAGE));
                } catch (NumberFormatException e){}
                this.order[j] = x;
            } while ((x < 0 || x >= nTiles) || (j!=0 && this.order[j]==this.order[j-1]));
        }

        // asking for the column
        do{
            try {
                this.column = Integer.parseInt(JOptionPane.showInputDialog(gameFrame, "Enter the index of the column (from 0 to 4)", "Enter the column index", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e){}
        } while(this.column < 0 || this.column >= 5);

        // remove listeners
        removeListeners();

        // notify the client
        notifyAll();
    }

    /**
     * Overview: method aimed to print in the textarea the result of the game
     */
    public void endgame(StringBuffer output){
        textArea.setText("");
        textArea.append(output.toString().replaceAll("\n", System.lineSeparator()));
    }

    /**
     * Overview: listener used to let the client perform the selection of the tiles
     */
    MouseListener listener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = boardTable.getSelectedRow();
            int column = boardTable.getSelectedColumn();
            if (row != -1 && column != -1) {
                if(row != previousrow || column != previouscolumn) {
                    String labelText = "Taken Tile " + taken + " at: Row = " + row + ", Column = " + column;
                    textArea.append(labelText + "\n");
                    totake[index] = row;
                    totake[index + 1] = column;
                    taken++;

                    previousrow = row;
                    previouscolumn = column;

                    if (taken == nTiles) {
                        continueTurnProcedure();
                    }
                    index = index + 2;
                }
            }
        }
    };

    /**
     * Overview: method aimed to let the client perform a move
     */
    public void performTurn(){
        this.taken = 0;
        this.index = 0;
        this.nTiles = 0;
        textArea.setText("");

        //show a text with coordinates of a clicked cell
        textArea.setBounds(250,790,379,162);
        bgLabel.add(textArea);

        // ask the client how many tiles he's wondering to pick
        do {
            try {
                this.nTiles = Integer.parseInt(JOptionPane.showInputDialog(gameFrame, "How many tiles do you want to pick?", "Enter number of tiles", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {}
        } while (this.nTiles<1 || this.nTiles>3);

        showMessage("Let's click on tile to select it!");
        this.totake = new int[nTiles*2];
        // doing for synchronization with clientManagerGUI
        Arrays.fill(totake, -1);
        this.order = new int[nTiles];
        // doing for synchronization with clientManagerGUI
        Arrays.fill(order, -1);
        this.column = -1;

        // adding event listener and forming int[] totake
        boardTable.addMouseListener(listener);

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
            shelfTexts.get(i).setText(username);
        }

        shelfTables.get(i).revalidate();
        shelfTables.get(i).repaint();
        bgLabel.revalidate();
    }

    /**
     * Overview: method aimed to close the frame
     */
    public void close(){
        gameFrame.setVisible(false);
        gameFrame.dispose();
    }

    /**
     * Overview: update board game
     */
    public void updateBoard(String[][] board) throws IOException {
        String[] boardcolumn = new String[9];
        for (int column = 0; column < 9; column++) {
            for(int j=0; j<9 ; j++){
                boardcolumn[j]=board[j][column];
            }
            boardTable.getColumnModel().getColumn(column).setCellRenderer(new ImageTableCellRenderer(boardcolumn,column));
        }

        boardTable.setRowHeight(60);
        ((DefaultTableCellRenderer)boardTable.getDefaultRenderer(Object.class)).setOpaque(false);
        boardTable.setOpaque(false);
        boardTable.setShowGrid(false);
        boardTable.setDefaultEditor(Object.class, null);

        boardLabel.add(boardTable);
        bgLabel.add(boardTable);
        bgLabel.add(boardLabel);

        boardLabel.revalidate();
        boardLabel.repaint();
        bgLabel.revalidate();
    }

    /**
     * Overview: method aimed to update the scoring token of the i-th common goal
     */
    public void UpdateToken(int points, int common){
        ImageIcon tokenimage = new ImageIcon("MyShelfie/src/main/resources/scoringTokens/scoring_"+points+".jpg");
        Image scaledtoken = tokenimage.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(scaledtoken);
        switch (common) {
            case 1 -> {
                token1Label.setIcon(resizedImage);
                token1Label.revalidate();
            }
            case 2 -> {
                token2Label.setIcon(resizedImage);
                token2Label.revalidate();
            }
        }
         bgLabel.revalidate();
    }

    /**
     * Overview: method aimed to render the initial setup
     */
    public void InitialSetUpRenderer(int numPlayers, String[][] board, int numberPattern, int common1, int common2, int token1, int token2) throws IOException {
        // board
        updateBoard(board);

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
        token1Label.setBounds(335,670, 90, 90);
        token2Label = new JLabel(new ImageIcon(scaledtoken2));
        token2Label.setBounds(535,670, 90, 90);

        bgLabel.add(token1Label);
        bgLabel.add(token2Label);

        // common
        ImageIcon Common1 = new ImageIcon("MyShelfie/src/main/resources/common/"+common1+".jpg");
        Image scaledcommon1 = Common1.getImage().getScaledInstance(180,119, Image.SCALE_SMOOTH);
        ImageIcon Common2 = new ImageIcon("MyShelfie/src/main/resources/common/"+common2+".jpg");
        Image scaledcommon2 = Common2.getImage().getScaledInstance(180,119, Image.SCALE_SMOOTH);

        Common1Label = new JLabel(new ImageIcon(scaledcommon1));
        Common1Label.setBounds(240, 608, 200, 200);
        Common2Label = new JLabel(new ImageIcon(scaledcommon2));
        Common2Label.setBounds(440, 608, 200, 200);

        bgLabel.add(Common1Label);
        bgLabel.add(Common2Label);

        //initialization of the others players
        int bookshelfsize=310;
        int xgap = 38;
        int ygap = 21;
        ImageIcon shelfImage = new ImageIcon("MyShelfie/src/main/resources/bookshelf_orth.png");
        Image scaledShelf = shelfImage.getImage().getScaledInstance(bookshelfsize,bookshelfsize, Image.SCALE_SMOOTH);

        shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
        shelfLabels.get(1).setBounds(1090,30,bookshelfsize,bookshelfsize);
        shelfTables.add(new JTable(6,5));
        shelfTexts.add(new JTextField());
        shelfTexts.get(1).setBounds(1195, 340, 100, 50);
        shelfTables.get(1).setBounds(1090+xgap, 30+ygap, bookshelfsize+10, bookshelfsize+10);
        shelfTables.get(1).setRowHeight(7*6);
        //set column width
        for (int i = 0; i < shelfTables.get(1).getColumnCount(); i++) {
            shelfTables.get(1).getColumnModel().getColumn(i).setMaxWidth(10*5);
        }
        ((DefaultTableCellRenderer)shelfTables.get(1).getDefaultRenderer(Object.class)).setOpaque(false);
        shelfTables.get(1).setOpaque(false);
        shelfTables.get(1).setShowGrid(false);
        shelfTables.get(1).setDefaultEditor(Object.class, null);

        bgLabel.add(shelfLabels.get(1));
        bgLabel.add(shelfTables.get(1));
        bgLabel.add(shelfTexts.get(1));

        if(numPlayers>2) {
            shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
            shelfLabels.get(2).setBounds(770, 450, bookshelfsize, bookshelfsize);
            shelfTables.add(new JTable(6,5));
            shelfTexts.add(new JTextField());
            shelfTexts.get(2).setBounds(875, 760, 100, 50);
            shelfTables.get(2).setBounds(770 + xgap, 450 + ygap, bookshelfsize+10, bookshelfsize+10);
            shelfTables.get(2).setRowHeight(7*6);
            //set column width
            for (int i = 0; i < shelfTables.get(2).getColumnCount(); i++) {
                shelfTables.get(2).getColumnModel().getColumn(i).setMaxWidth(10*5);
            }
            ((DefaultTableCellRenderer)shelfTables.get(2).getDefaultRenderer(Object.class)).setOpaque(false);
            shelfTables.get(2).setOpaque(false);
            shelfTables.get(2).setShowGrid(false);
            shelfTables.get(2).setDefaultEditor(Object.class, null);

            bgLabel.add(shelfLabels.get(2));
            bgLabel.add(shelfTables.get(2));
            bgLabel.add(shelfTexts.get(2));
        }
        if(numPlayers>3) {
            shelfLabels.add(new JLabel(new ImageIcon(scaledShelf)));
            shelfLabels.get(3).setBounds(1090, 450, bookshelfsize, bookshelfsize);
            shelfTables.add(new JTable(6,5));
            shelfTexts.add(new JTextField());
            shelfTexts.get(3).setBounds(1195, 760, 100, 50);
            shelfTables.get(3).setBounds(1090 + xgap, 450 + ygap, bookshelfsize+10, bookshelfsize+10);
            shelfTables.get(3).setRowHeight(7*6);
            //set column width
            for (int i = 0; i < shelfTables.get(3).getColumnCount(); i++) {
                shelfTables.get(3).getColumnModel().getColumn(i).setMaxWidth(10*5);
            }
            ((DefaultTableCellRenderer)shelfTables.get(3).getDefaultRenderer(Object.class)).setOpaque(false);
            shelfTables.get(3).setOpaque(false);
            shelfTables.get(3).setShowGrid(false);
            shelfTables.get(3).setDefaultEditor(Object.class, null);

            bgLabel.add(shelfLabels.get(3));
            bgLabel.add(shelfTables.get(3));
            bgLabel.add(shelfTexts.get(3));
        }

        // visibility on the frame
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);

    }

    /**
     * Overview totake getter
     */
    public int[] getTotake() {return totake;}

    /**
     * Overview: order getter
     */
    public int[] getOrder() {return order;}

    /**
     * Overview: column getter
     */
    public int getColumn() {return column;}


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
                Image scaledImage = imageIcon.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth()-border, table.getRowHeight(), Image.SCALE_SMOOTH);
                renderer.setIcon(new ImageIcon(scaledImage));
                //allineamento per far si che l'immagine riscalata venga centrata nella cella
                //renderer.setHorizontalAlignment(SwingConstants.WEST);
                renderer.setVerticalAlignment(SwingConstants.BOTTOM);
            }else renderer.setIcon(null);
            //l'else dovrebbe fare in modo che quando vado a sovrascrivere le immagini mi tolga quelle che sono state prese(da testare)

            return renderer;
        }
    }
}