package Lines;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;




public class LoadSave {
	
//	Tổng hợp những file cần import, nếu ko tìm đc file thì chỉ cần chỉnh đg dẫn trong 1 class

	public static final String BIG1 = "Resources/Resources/Balls/big1.png";
	public static final String BIG2 = "Resources/Resources/Balls/big2.png";
	public static final String BIG3 = "Resources/Resources/Balls/big3.png";
	public static final String BIG4 = "Resources/Resources/Balls/big4.png";
	public static final String BIG5 = "Resources/Resources/Balls/big5.png";
	public static final String BIG6 = "Resources/Resources/Balls/big6.png";
	public static final String BIG7 = "Resources/Resources/Balls/big7.png";
	
	public static final String SMALL1 = "Resources/Resources/Balls/small1.png";
	public static final String SMALL2 = "Resources/Resources/Balls/small2.png";
	public static final String SMALL3 = "Resources/Resources/Balls/small3.png";
	public static final String SMALL4 = "Resources/Resources/Balls/small4.png";
	public static final String SMALL5 = "Resources/Resources/Balls/small5.png";
	public static final String SMALL6 = "Resources/Resources/Balls/small6.png";
	public static final String SMALL7 = "Resources/Resources/Balls/small7.png";
	
	public static final String D1 = "Resources/Resources/Balls/d1.gif";
	public static final String D2 = "Resources/Resources/Balls/d2.gif";
	public static final String D3 = "Resources/Resources/Balls/d3.gif";
	public static final String D4 = "Resources/Resources/Balls/d4.gif";
	public static final String D5 = "Resources/Resources/Balls/d5.gif";
	public static final String D6 = "Resources/Resources/Balls/d6.gif";
	public static final String D7 = "Resources/Resources/Balls/d7.gif";
	
	public static final String PISKET = "Resources/Resources/Piskel.png";
	
	public static final String END_SCENE = "/Resources/gameover/scene.png";
	public static final String PLAY_END_SCENE = "/Resources/gameover/play.png";
	public static final String EXIT_END_SCENE = "/Resources/gameover/exit.png";
	
	public static final String START_SCENE = "/Resources/start/scene.png";
	public static final String PLAY_START_SCENE = "/Resources/start/play.png";
	public static final String EXIT_START_SCENE = "/Resources/start/exit.png";
	
	public static final String MAIN_SCENE = "/Resources/main.png";
	

	
	public static BufferedImage GetImage(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream(fileName);
		try {
			img = ImageIO.read(Objects.requireNonNull(is));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

}
