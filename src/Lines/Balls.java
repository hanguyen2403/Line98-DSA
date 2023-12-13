package Lines;

import java.util.Random;

public class Balls {

    public class point{     //lưu toạ độ trong bảng
        public int x,y; //toạ độ x, y
        public point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int ball[][] =  new int[Constant.Row][Constant.Column]; //trạng thái của các bóng trong bảng 10x10
    public int balltmp[][] = new int[Constant.Row][Constant.Column]; //trạng thái của bóng sau mỗi bước 10x10
    public point[] pathBall = new point[Constant.Row*Constant.Column]; //lưu đường đi của bóng
    public int[] nextColor = new int[3]; //ba màu kế tiếp
    public int[] nextColortmp = new int[3]; //ba màu kế tiếp sau mỗi bước
    public int nCountPath;
    public int Score;
    public boolean GameOver;

    public void startGame(){
        //khởi tạo vị trí bóng ban đầu
        for (int i = 0; i<Constant.Row; i++)
            for (int j = 0; j<Constant.Column;j++)
                ball[i][j] = 0;
        Score = 0;
        GameOver = false;

        int x,y;
        Random random = new Random();
        point[] EmptyCell = new point[3];
        //khởi tạo 3 bóng lớn
        if (CheckEmptyCell(3,EmptyCell)){
            for (int i = 0; i < 3; i++){
                x = EmptyCell[i].x;
                y = EmptyCell[i].y;
                ball[x][y] = random.nextInt(Constant.MaxColor) + 1; //(1 -> 7) số hiển thị màu
            }
        } else System.out.println("Game Over!");
        //khởi tạo 3 bóng nhỏ
        if (CheckEmptyCell(3,EmptyCell))
            for (int i = 0; i < 3; i++){
                x = EmptyCell[i].x;
                y = EmptyCell[i].y;
                ball[x][y] = random.nextInt(Constant.MaxColor)+Constant.MaxColor+1; //(8->14) số hiển thị màu
            }
        else System.out.println("Game Over!");

        //Lưu trạng thái của bảng
        for (int i = 0; i < Constant.Row; i++)
            for (int j = 0; j < Constant.Column; j++)
                balltmp[i][j] = ball[i][j];

        //Lưu màu của bóng
        createNew3Color();
        for (int i = 0; i < 3; i++){
            nextColortmp[i] = nextColor[i];
        }
    }
    public void createNew3Color(){
        Random random = new Random();
        for (int i = 0; i < 3; i++){
            nextColor[i] = random.nextInt(Constant.MaxColor)+1;
        }
    }
    //Kiểm tra ô trống
    public boolean CheckEmptyCell(int nBall,point[] ResultBall){
        int CountEmptyCell = 0;
        point[] CheckEmptyCell = new point[Constant.Row*Constant.Column];
        boolean[] BoolCheckEmptyCell = new boolean[Constant.Row*Constant.Column];

        for (int i = 0; i < Constant.Row;i++)
            for (int j = 0; j < Constant.Column; j++){
                if (ball[i][j] == 0){
                    CheckEmptyCell[CountEmptyCell] = new point(i,j);
                    BoolCheckEmptyCell[CountEmptyCell++] = true;
                } else BoolCheckEmptyCell[CountEmptyCell] = false;
            }
        if (CountEmptyCell < nBall) return false;

        //Gán random 3 bóng vào 3 ô trống
        Random random = new Random();
        int count = 0;
        while (count < nBall){
            int x = random.nextInt(CountEmptyCell);
            if (BoolCheckEmptyCell[x]){
                ResultBall[count++] = CheckEmptyCell[x];
                BoolCheckEmptyCell[x] = false;
            }
        }
        return true;
    }
    //Tạo 3 bóng mới
    public void create3NewBalls(){
        //Chuyển bóng nhỏ thành bóng to
        for (int i = 0; i < Constant.Row; i++){
            for (int j = 0; j < Constant.Column; j++){
                if (ball[i][j] > Constant.MaxColor)  //bóng nhỏ
                    ball[i][j] -= Constant.MaxColor; //bóng to
            }
        }
        //Tạo 3 bóng nhỏ
        int x, y;
        point[] EmptyCell = new point[3];
        Random random =  new Random();
        if (CheckEmptyCell(3,EmptyCell))
            for (int i = 0; i < 3; i++){
                x = EmptyCell[i].x;
                y = EmptyCell[i].y;
                ball[x][y] = nextColor[i] + Constant.MaxColor;
            }
        //khởi tạo 3 màu mới
        createNew3Color();
    }
}
