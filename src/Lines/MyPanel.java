package Lines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyPanel extends JPanel {

    int x,y;
    MyPanel(){

        this.setPreferredSize(new Dimension(Constant.Row*52,Constant.Column*52));
        this.setBackground(Color.black);
        setVisible(true);
    }
    public void paint(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.fillRect(x,y,52,52);
    }
    public void actionPerformed(ActionEvent ae) {

    }
}
