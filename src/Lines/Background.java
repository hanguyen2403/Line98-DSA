/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to draw background of LineFrame*/
package Lines;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends JPanel {

    public Background(){
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        BufferedImage image = LoadSave.GetImage(LoadSave.MAIN_SCENE);
        g.drawImage(image, 0, 0, this);
    }
}
