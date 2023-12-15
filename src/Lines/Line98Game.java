package Lines;

/*Name: Group 17
  Nguyễn Khánh Hà - ITCSIU21004
  Huỳnh Lâm Đăng Khoa - ITCSIU21138
  Nguyễn Bình Phương Huy - ITCSIU21189
  Trần Thanh Nguyên - ITCSIU21093
  Purpose: This is a class to create the starting screen*/

//import Controls.MouseHandler;
import javax.swing.*;
import java.awt.*;

public class Line98Game extends JFrame {
//    SoundManager sound = new SoundManager("src/resources/Sound/welcome.wav");
//    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));
    public Line98Game() {
        StartFrame panel = new StartFrame( new Rectangle(320 - 30, 400, 250, 50),new Rectangle(320 - 30, 490, 250, 50),this);
        panel.setPreferredSize(new Dimension(960, 640));

        setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        sound.playSound("src/resources/Sound/welcome.wav");
        setResizable(false);
        setTitle(Constant.TITLE);
//        setIconImage(logo.getImage());
        setLocationRelativeTo(null);

        // Add the background to the content pane
        setVisible(true);
    }
}

