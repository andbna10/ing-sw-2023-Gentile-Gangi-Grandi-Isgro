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

        bgLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon gameIcon = new ImageIcon("MyShelfie/src/main/resources/Title 2000x2000px.png");
        Image scaledIcon = gameIcon.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
        gameFrame.setIconImage(scaledIcon);

        ImageIcon boardImage = new ImageIcon("MyShelfie/src/main/resources/livingroom.png");
        Image scaledBoardImage = boardImage.getImage().getScaledInstance(500,500, Image.SCALE_SMOOTH);
        JLabel boardLabel = new JLabel(new ImageIcon(scaledBoardImage));

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        bgLabel.add(boardLabel, gbc);
        gameFrame.add(bgLabel);

        

        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);
    }
}

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
