package Lines;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background extends JPanel {

    public Background(){
//        this.setPreferredSize(new Dimension(600,600));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        BufferedImage image = null;

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Vẽ background
        g.drawImage(image, 0, 0, this);
    }
}
