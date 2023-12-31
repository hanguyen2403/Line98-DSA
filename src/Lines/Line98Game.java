/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to set state of game*/
package Lines;


//import Controls.MouseHandler;
import javax.swing.*;
import java.awt.*;

public class Line98Game  {
	public static StateSetting setting = new StateSetting();
	public static State start = new StartFrame();
	public static State game = new LineFrame();
	public static State end = new EndFrame();

	public Line98Game() {
	}
  
	public static void setStartState() {
		setting.setState(start);
		setting.applyState();
	}

	public static void setGameState() {
		setting.setState(game);
		setting.applyState();
	}

	public static void setEndState() {
		setting.setState(end);
		setting.applyState();
	}
}

