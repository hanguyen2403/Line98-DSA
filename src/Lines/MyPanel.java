/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to show moving path of balls */
package Lines;

//import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.awt.image.BufferedImageOp;
//import java.io.IOException;
//import java.util.Objects;

public class MyPanel extends JPanel implements ActionListener {

    int x,y;

    Icon icon;
    Graphics2D graphics2D;
    MyPanel(){
        this.setBounds(64,45,Constant.Row*52+3,Constant.Column*52+3);
        this.setBackground(Color.gray);
        setVisible(true);
    }
    //vẽ ô di chuyển cho bóng
    public void paint(Graphics g,int x, int y,Icon icon){
        graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.red);
        this.x = x;
        this.y = y;

        Image image = ((ImageIcon) icon).getImage();
        graphics2D.drawImage(image,y*52+2 ,x*52+2,52-4,52-4,null );
    }
    // vẽ ô đen trùng với backgrournd của button
    public void remove(Graphics g){
        graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLACK);
        graphics2D.setStroke( new BasicStroke(3));
        graphics2D.fillRect(y*52+2,x*52+2,52-4,52-4);
    }
    public void actionPerformed(ActionEvent ae) {

    }
}
