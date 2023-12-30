/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to control the mouse of end screen*/
package Lines;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EndFrame extends JPanel implements MouseListener, MouseMotionListener, State {
    private BufferedImage backgroundImage,playImage,exitImage;
    JFrame frame = new JFrame();
    private boolean isClicked;
    private Point mousePos = new Point(-1, -1);
    private Rectangle area,area2;
    private int play,exit,state;
    private static Line98Game command = new Line98Game(); //Command design pattern
    
    //Tạo command
    public EndFrame() {};

    public EndFrame(Rectangle area, Rectangle area2, JFrame jFrame) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.area=area;
        this.frame=jFrame;
        this.area2=area2;
        state=1;
        play=2;
        exit=3;
        // Load the background image

        	backgroundImage = LoadSave.GetImage(LoadSave.END_SCENE);
        	playImage = LoadSave.GetImage(LoadSave.PLAY_END_SCENE);
        	exitImage = LoadSave.GetImage(LoadSave.EXIT_END_SCENE);
    }

    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    public void mousePressed(MouseEvent e) {
        if (area.contains(e.getPoint())) {
            handleMouseEvent(e);
        } else if (area2.contains(e.getPoint())) {
            System.exit(0);

        }
    }
    public void handleMouseEvent(MouseEvent e) {
        frame.dispose();
        startNewGame();
    }
    private void startNewGame() {
    	command.setGameState();
    }
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    public void mouseExited(MouseEvent e) {
        // Do nothing
    }

    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        if (area.contains(point)) {
            state=play;
            repaint();
        } else if (area2.contains(point)) {
            state=exit;
            repaint();
        } else{
            state=1;
            repaint();
        }
    }
    public void mouseDragged(MouseEvent e) {
        mousePos = e.getPoint();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if(state==1) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        if(state==play){
            g.drawImage(playImage, 0, 0, getWidth(), getHeight(), this);
        }
        if(state==exit){
            g.drawImage(exitImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

	@Override
	public void handleRequest() {
        EndFrame end = new EndFrame( new Rectangle(290 - 30, 400, 450, 50),new Rectangle(370 - 30, 490, 250, 50),frame);
        end.setPreferredSize(new Dimension(960, 640));

        frame.setVisible(true);
        frame.add(end, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Constant.TITLE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
	}
}
