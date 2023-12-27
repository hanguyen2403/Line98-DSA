package Lines;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.Objects;

import java.awt.event.*;
import java.io.*;
public class LineFrame implements State {
    private static boolean running;
    public JButton button[][] = new JButton[10][10];
    public JButton undo;		//nut undo
    public Balls b = new Balls();
    public JLabel score;
    public Icon icon[] = new Icon[22];

    public int x,y; //toạ độ của bóng
    private Background background;

    public MyPanel panel;
    public JFrame frame = new JFrame();

    private static Line98Game command = new Line98Game(); //Command design pattern
    //----------------------------------------------------------------------------------------
    //Tạo command
    public LineFrame() {};
    //Khởi tạo
    public void createLineFrame(){

        //7 qua bong to
        icon[1] = new ImageIcon(LoadSave.BIG1);
        icon[2] = new ImageIcon(LoadSave.BIG2);
        icon[3] = new ImageIcon(LoadSave.BIG3);
        icon[4] = new ImageIcon(LoadSave.BIG4);
        icon[5] = new ImageIcon(LoadSave.BIG5);
        icon[6] = new ImageIcon(LoadSave.BIG6);
        icon[7] = new ImageIcon(LoadSave.BIG7);

        //7 qua bong nho
        icon[8] = new ImageIcon(LoadSave.SMALL1);
        icon[9] = new ImageIcon(LoadSave.SMALL2);
        icon[10] = new ImageIcon(LoadSave.SMALL3);
        icon[11] = new ImageIcon(LoadSave.SMALL4);
        icon[12] = new ImageIcon(LoadSave.SMALL5);
        icon[13] = new ImageIcon(LoadSave.SMALL6);
        icon[14] = new ImageIcon(LoadSave.SMALL7);

        //7 qua bong nhay
        icon[15] = new ImageIcon(LoadSave.D1);
        icon[16] = new ImageIcon(LoadSave.D2);
        icon[17] = new ImageIcon(LoadSave.D3);
        icon[18] = new ImageIcon(LoadSave.D4);
        icon[19] = new ImageIcon(LoadSave.D5);
        icon[20] = new ImageIcon(LoadSave.D6);
        icon[21] = new ImageIcon(LoadSave.D7);

        icon[0] = new ImageIcon(LoadSave.PISKET);
        
		//Khởi tạo nút undo
        undo = new JButton();
        undo.setBounds(715, 486, 100, 50);
        undo.setText("Undo: "+b.UndoTimes);
        undo.setFont(new Font("Arial", Font.BOLD, 16));
        undo.setForeground(Color.RED);
        
        //Khởi tạo điểm trò chơi
        score = new JLabel("Score: "+b.Score);
        score.setBounds(715, 48, 200, 50);
        score.setFont(new Font("Arial", Font.BOLD, 26));
        score.setForeground(Color.PINK);
        


//        setLayout(new GridLayout(Constant.Row,Constant.Column)); // set 10x10 ô button

        frame.setLayout(new BorderLayout()); // set Layout để chèn background
        //Khởi tạo button
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++){
                button[i][j]=new JButton(icon[0]);
                button[i][j].setBounds(67+j*52 ,48+i*52 , 50 , 50 ); //vị trí mỗi button
//                add(button[i][j]);
            }
        //Thêm button vào 10x10 ô
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++)
                frame.add(button[i][j]);
        //Thêm nút undo
        frame.add(undo);
        setUndoButton();
        

        x = y = -1;
        panel = new MyPanel();
        
        setButton();

//        panel.setBounds(64+(Constant.Column-1)*52,45+(Constant.Row-1)*52, 52, 52);

        background = new Background();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Constant.TITLE);

        frame.add(score);
        frame.add(panel);
        frame.add(background);
        frame.pack();
        frame.setSize(960,640);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setUndoButton() {
    	undo.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			b.Undo();
    			undo.setText("Undo: "+b.UndoTimes);
                score.setText("Score: "+b.Score);
    			DrawBall();
    		}
    	});
		
	}

	public void setButton(){
        for (int i=0;i<Constant.Row;i++)
            for (int j=0;j<Constant.Column;j++)
                // thực hiện lệnh bấm cho ô
                button[i][j].addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        for (int i=0;i<Constant.Row;i++)
                            for(int j=0;j<Constant.Column;j++)
                                if(ae.getSource()==button[i][j]){
                                    Icon n = button[i][j].getIcon();

                                    if(x!=i&&y!=j&&(n==icon[1]||n==icon[2]||n==icon[3]
                                            ||n==icon[4]||n==icon[5]||n==icon[6]||n==icon[7]))
                                    {
                                        x=i;
                                        y=j;

                                    }
                                    else if(x==i&&y==j){
                                        x=y=-1;

                                    }
                                    else if(x>-1&&y>-1&&(n==icon[0]||n==icon[8]||n==icon[9]||n==icon[10]
                                            ||n==icon[11]||n==icon[12]||n==icon[13]||n==icon[14])){

                                        if(b.BFS(x,y,i,j)){

                                            b.saveUndo();//luu lai trang thai truoc khi di chuyen

                                            System.out.println("start "+x+ " "+ y + " end: "+  i+ " "+j);
                                            panel.paint(panel.getGraphics(),x,y, button[x][y].getIcon());
                                            panel.remove(panel.getGraphics());
                                                    // vẽ đường đi ngắn nhất
                                            try { for (int ex = 1; ex < b.nCountPath - 2 ; ex++){

                                                        int startx = b.pathBall[ex].x;
                                                        int starty = b.pathBall[ex].y;
                                                        int nextx = b.pathBall[ex+1].x;
                                                        int nexty = b.pathBall[ex+1].y;


                                                            panel.paint(panel.getGraphics(),nextx,nexty, button[x][y].getIcon());
                                                            System.out.println("from: "+startx + " "+ starty+" to: "+ nextx+" "+ nexty+" ball value start: "+ b.ball[startx][starty]+" ball value next: "+ b.ball[nextx][nexty]);
                                                            Thread.sleep(150);
                                                            panel.paint(panel.getGraphics(),nextx,nexty, button[nextx][nexty].getIcon());
                                                            DrawBall();
                                                        }
                                                    }catch(Exception e){}
                                            try{moveBall(x,y,i,j);}catch(Exception e){}
                                            DrawBall();
//                                                    b.ball[i][j] -= 14;
//                                                    button[i][j].setIcon(icon[b.ball[i][j]]);
                                            if(b.cutBall()==false)b.create3NewBalls();
                                            b.cutBall();
                                            //displayNextBall();//hien thi 3 mau sap xuat hien
                                            DrawBall();
                                            x=y=-1;
                                        }
                                    }

                                    bounceBall();//nhay bong
                                    score.setText("Score: "+b.Score);
                                    try{
                                        stopGame();//dung` tro choi neu cac o da~ day` bong
                                    }catch(IOException e){}

                                }

                    }

                });

    }
    public void moveBall(int i,int j,int ii,int jj)throws Exception{

        b.ball[ii][jj] = b.ball[i][j]-14;//[j] - 14
        b.ball[i][j] = 0;
//        for(int k=0;k<22;k++)
//            if(button[i][j].getIcon()==icon[k]) {
//                button[ii][jj].setIcon(icon[k-14]);//icon[k-14]);
//                System.out.println(k);
//            }
//        button[i][j].setIcon(icon[0]);
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
    public void stopGame()    throws IOException{
    	int n = 0;
        for (int i = 0; i < Constant.Row; i++){
            for (int j = 0; j < Constant.Column; j++){
                if (b.ball[i][j] == 0)
                	n++;
            }
        }
        if (n >= 3)
        	return;
        frame.dispose();
        command.setEndState();
        
//        JFrame frame = new JFrame();
//        EndFrame end = new EndFrame( new Rectangle(290 - 30, 400, 450, 50),new Rectangle(370 - 30, 490, 250, 50),frame);
//        end.setPreferredSize(new Dimension(960, 640));
//
//        frame.setVisible(true);
//        frame.add(end, BorderLayout.CENTER);
//        frame.pack();
//
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setTitle(Constant.TITLE);
//        frame.setLocationRelativeTo(null);
//
//        frame.setVisible(true);
        
//        if(b.GameOver==true){
//            //topScores.readFile();
//            boolean k2 =false;//kiem tra diem xem co luu vao TopScores khong
//            for(int i=0;i<10;i++)
////                if(topScores.player[i].scores<player.scores){
////                    k2 = true;
////                    break;
////                }
//            if(k2==true){
//                player.setName();
//                topScores.add(player);
//                topScores.showTopScores();
//                startGame();
//            }else {
//                GameOver = new JFrame(" GameOver !");
//                JButton msg1 = new JButton(" TRO CHOI KET THUC !");
//                JButton msg2 = new JButton(" Ban Ghi Duoc "+player.scores+" Diem");
//                GameOver.add(msg1);
//                GameOver.add(msg2);
//                GameOver.setLayout(new GridLayout(2,1));
//                GameOver.setSize(290,150);
//                GameOver.setResizable(false);
//                GameOver.show();
//                GameOver.addWindowListener( new WindowAdapter() {// tao game moi khi tat cua so
//                    public void windowClosing(WindowEvent e){
//                        startGame();
//                    }
//                });
//            }
//        }
    }

	@Override
	public void handleRequest() {
		System.out.println("wadasas");
        try {
            LineFrame lineFrame = new LineFrame();
            lineFrame.createLineFrame();
            lineFrame.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
