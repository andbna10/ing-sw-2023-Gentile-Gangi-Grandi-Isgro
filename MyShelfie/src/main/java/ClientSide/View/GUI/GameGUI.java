package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;
import ServerSide.Model.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameGUI {

    private LoginHandler handler;

    public GameGUI(LoginHandler handler) { this.handler = handler; }

    public GameGUI(){
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

        BoardTableModel model = new BoardTableModel();
        boardTable.setModel(model);


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
}