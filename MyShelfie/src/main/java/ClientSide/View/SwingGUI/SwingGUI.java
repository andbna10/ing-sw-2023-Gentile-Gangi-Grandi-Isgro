package ClientSide.View.SwingGUI;

import javax.swing.*;
import java.awt.*;

public class SwingGUI {

    public static void main(String[] args) { SwingGUI gui = new SwingGUI(); }

    public SwingGUI() {

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1140,760);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JLabel createL = new JLabel();
        JLabel joinL = new JLabel();

        JButton createB = new JButton("Create game");
        createB.addActionListener(new CustomActionListener("create", createL));

        JButton joinB = new JButton("Join game");
        joinB.addActionListener(new CustomActionListener("join", joinL));


        panel.add(createB);
        panel.add(joinB);

        ImageIcon img = new ImageIcon("MyShelfie/src/main/resources/Display_1.jpg");
        Image scaledImg = img.getImage().getScaledInstance(600,400,Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));

        mainFrame.add(imgLabel);
        mainFrame.add(panel);


        mainFrame.pack();
        mainFrame.setVisible(true);

    }

}
