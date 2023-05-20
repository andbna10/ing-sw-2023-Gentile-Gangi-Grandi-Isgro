package ClientSide.View.GUI;

import ClientSide.NetworkHandler.LoginHandler;
import ServerSide.Model.Game;

import javax.swing.*;
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
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(500,500, Image.SCALE_SMOOTH);
        JLabel boardLabel = new JLabel(new ImageIcon(scaledBoardImage));

        bgLabel.add(boardLabel);
        gameFrame.add(bgLabel);

        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);
    }
}