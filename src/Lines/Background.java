package Lines;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends JPanel {

    public Background(){
//        this.setPreferredSize(new Dimension(600,600));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        BufferedImage image = LoadSave.GetImage(LoadSave.MAIN_SCENE);
        
//        BufferedImage image = null;
//        try{
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/main.png")));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        //Vẽ background
        g.drawImage(image, 0, 0, this);
    }
}
