package Lines;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LineFrame extends JFrame {
    private static boolean running;
    public JButton button[][] = new JButton[10][10];
    public Balls b = new Balls();
    public JLabel bg;
    public Icon icon[] = new Icon[22];

    public int x,y; //toạ độ của bóng
    private Background background;


    //----------------------------------------------------------------------------------------
    //Khởi tạo
    public LineFrame(){

        //7 qua bong to
        icon[1] = new ImageIcon("src/Resources/Balls/big1.png");
        icon[2] = new ImageIcon("src/Resources/Balls/big2.png");
        icon[3] = new ImageIcon("src/Resources/Balls/big3.png");
        icon[4] = new ImageIcon("src/Resources/Balls/big4.png");
        icon[5] = new ImageIcon("src/Resources/Balls/big5.png");
        icon[6] = new ImageIcon("src/Resources/Balls/big6.png");
        icon[7] = new ImageIcon("src/Resources/Balls/big7.png");

        //7 qua bong nho
        icon[8] = new ImageIcon("src/Resources/Balls/small1.png");
        icon[9] = new ImageIcon("src/Resources/Balls/small2.png");
        icon[10] = new ImageIcon("src/Resources/Balls/small3.png");
        icon[11] = new ImageIcon("src/Resources/Balls/small4.png");
        icon[12] = new ImageIcon("src/Resources/Balls/small5.png");
        icon[13] = new ImageIcon("src/Resources/Balls/small6.png");
        icon[14] = new ImageIcon("src/Resources/Balls/small7.png");

        //7 qua bong nhay
        icon[15] = new ImageIcon("src/Resources/Balls/d1.gif");
        icon[16] = new ImageIcon("src/Resources/Balls/d2.gif");
        icon[17] = new ImageIcon("src/Resources/Balls/d3.gif");
        icon[18] = new ImageIcon("src/Resources/Balls/d4.gif");
        icon[19] = new ImageIcon("src/Resources/Balls/d5.gif");
        icon[20] = new ImageIcon("src/Resources/Balls/d6.gif");
        icon[21] = new ImageIcon("src/Resources/Balls/d7.gif");

        icon[0] = new ImageIcon("src/Resources/Piskel.png");

//        setLayout(new GridLayout(Constant.Row,Constant.Column)); // set 10x10 ô button

        setLayout(new BorderLayout()); // set Layout để chèn background
        //Khởi tạo button
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++){
                button[i][j]=new JButton(icon[0]);
                button[i][j].setBounds(180+j*60,i*60, 60, 60); //vị trí mỗi button
//                add(button[i][j]);
            }
        //Thêm button vào 10x10 ô
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++)
                add(button[i][j]);

        x = y = -1;

        background = new Background();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);
        this.add(background);
        pack();
        setSize(960,640);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void DrawBall(){
        for (int i = 0; i < Constant.Row; i++){
            for (int j = 0; j < Constant.Column; j++){
                button[i][j].setIcon(icon[b.ball[i][j]]);
            }
        }
    }
    public void displayNextBall(){
        //...
    }
    public void bounceBall(){
        for(int i = 0; i < Constant.Row; i++)
            for (int j = 0; j < Constant.Column; j++){
                if (b.ball[i][j] > 14) b.ball[i][j] -= 14; //chuyển từ bounce sang đứng yên
            }
        //chuyển ball được chọn từ đứng yên sang bounce
        if(x >= 0 && y>= 0) b.ball[x][y] +=14;
        DrawBall();
    }
    public void startGame(){
        b.startGame();
        DrawBall();
        x = y = -1;
    }
}
