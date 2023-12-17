package Lines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    int x,y;
    Timer timer;
    Graphics2D graphics2D;
    MyPanel(){
        this.setBounds(64,45,Constant.Row*52+3,Constant.Column*52+3);
        //this.setPreferredSize(new Dimension(Constant.Row*52,Constant.Column*52));
        this.setBackground(Color.black);
        setVisible(true);
    }
    public void paint(Graphics g,int x, int y){
        graphics2D = (Graphics2D) g;

//        graphics2D.fillRect(0,0,52+3,52+3);
//        graphics2D.fillRect(520 - 52,520 - 52,52+3,52+3);
        graphics2D.setPaint(Color.red);
        this.x = x;
        this.y = y;
        graphics2D.setStroke( new BasicStroke(3));
        graphics2D.drawRect(y*52+1,x*52+1,52,52);


    }

    public void remove(Graphics g){
        graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLACK);
        graphics2D.setStroke( new BasicStroke(3));
        graphics2D.drawRect(y*52+1,x*52+1,52,52);
    }

    public void actionPerformed(ActionEvent ae) {

    }
}
