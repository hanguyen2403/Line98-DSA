package Lines;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.Objects;

import java.awt.event.*;
import java.io.*;
public class LineFrame extends JFrame {
    private static boolean running;
    public JButton button[][] = new JButton[10][10];
    public Balls b = new Balls();
    public JLabel bg;
    public Icon icon[] = new Icon[22];
    public MyPanel panel;
    public int x,y; //toạ độ của bóng
    private Background background;


    //----------------------------------------------------------------------------------------
    //Khởi tạo
    public LineFrame(){

        //7 qua bong to
        icon[1] = new ImageIcon("src/Resources/Balls/big1.png");
        icon[2] = new ImageIcon("src/Resources/Balls/big2.png");
        icon[3] = new ImageIcon("src/Resources/Balls/big3.png");
        icon[4] = new ImageIcon("src/Resources/Balls/big4.png");
        icon[5] = new ImageIcon("src/Resources/Balls/big5.png");
        icon[6] = new ImageIcon("src/Resources/Balls/big6.png");
        icon[7] = new ImageIcon("src/Resources/Balls/big7.png");

        //7 qua bong nho
        icon[8] = new ImageIcon("src/Resources/Balls/small1.png");
        icon[9] = new ImageIcon("src/Resources/Balls/small2.png");
        icon[10] = new ImageIcon("src/Resources/Balls/small3.png");
        icon[11] = new ImageIcon("src/Resources/Balls/small4.png");
        icon[12] = new ImageIcon("src/Resources/Balls/small5.png");
        icon[13] = new ImageIcon("src/Resources/Balls/small6.png");
        icon[14] = new ImageIcon("src/Resources/Balls/small7.png");

        //7 qua bong nhay
        icon[15] = new ImageIcon("src/Resources/Balls/d1.gif");
        icon[16] = new ImageIcon("src/Resources/Balls/d2.gif");
        icon[17] = new ImageIcon("src/Resources/Balls/d3.gif");
        icon[18] = new ImageIcon("src/Resources/Balls/d4.gif");
        icon[19] = new ImageIcon("src/Resources/Balls/d5.gif");
        icon[20] = new ImageIcon("src/Resources/Balls/d6.gif");
        icon[21] = new ImageIcon("src/Resources/Balls/d7.gif");

        icon[0] = new ImageIcon("src/Resources/Piskel.png");

//        setLayout(new GridLayout(Constant.Row,Constant.Column)); // set 10x10 ô button

        setLayout(new BorderLayout()); // set Layout để chèn background
        //Khởi tạo button
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++){
                button[i][j]=new JButton(icon[0]);
                button[i][j].setBounds(64+j*52,45+i*52, 52, 52); //vị trí mỗi button
//                add(button[i][j]);
            }
        //Thêm button vào 10x10 ô
        for (int i=0; i<Constant.Row; i++)
            for(int j=0; j<Constant.Column; j++)
                this.add(button[i][j]);

        x = y = -1;
        setButton();
        panel = new MyPanel();
        panel.setBounds(64+(Constant.Column-1)*52,45+(Constant.Row-1)*52, 52, 52);
        this.add(panel);
        background = new Background();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);
        this.add(background);
        pack();
        setSize(960,640);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setButton(){
        for (int i=0;i<Constant.Row;i++)
            for (int j=0;j<Constant.Column;j++)
                // thực hiện lệnh bấp cho ô
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
                                                    // vẽ đường đi ngắn nhất
                                                    for (int ex = 1; ex < b.nCountPath - 1 ; ex++){

                                                        int startx = b.pathBall[ex].x;
                                                        int starty = b.pathBall[ex].y;
                                                        int nextx = b.pathBall[ex+1].x;
                                                        int nexty = b.pathBall[ex+1].y;
                                                        try {

                                                            System.out.println("from: "+startx + " "+ starty+" to: "+ nextx+" "+ nexty+" ball value: "+ b.ball[nextx][nexty]);
                                                            Thread.sleep(200);
                                                        }catch(Exception e){}
                                                    }
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
//                                    player.scores=(int)b.Score;
//                                    score.setText((int)b.Score+" ");
//                                    try{
//                                        //stopGame();//dung` tro choi neu cac o da~ day` bong
//                                    }catch(IOException e){}

                                }

                    }

                });

    }
    public void moveBall(int i,int j,int ii,int jj)throws Exception{

        b.ball[ii][jj] = b.ball[i][j]-14;//[j] - 14
        b.ball[i][j] = 0;
        DrawBall();
        for(int k=0;k<22;k++)
            if(button[i][j].getIcon()==icon[k]) {
                button[ii][jj].setIcon(icon[k-14]);//icon[k-14]);
                System.out.println(k);
            }
        button[i][j].setIcon(icon[0]);
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
//        if(b.gameover==true){
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
}
