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
    public long Score;
    public long ScoreTemp;
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
    //lui lai trang thai truoc cua bang
    public void Undo(){

        for (int i=0;i<Constant.Row;i++)
            for (int j=0;j<Constant.Column;j++)
                ball[i][j]=balltmp[i][j];

        for (int k=0; k < 3; k++)
            nextColor[k]=nextColortmp[k];

        Score = ScoreTemp;
    }

    //-------------------------------------------------------------------
    //luu trang thai truoc cua bang
    public void saveUndo(){

        for (int i=0;i<Constant.Row;i++)
            for (int j=0;j<Constant.Column;j++)
                if (ball[i][j]>2*Constant.MaxColor)
                    balltmp[i][j]=ball[i][j]-Constant.MaxColor;
                else
                    balltmp[i][j]=ball[i][j];
        for (int k=0; k < 3; k++)
            nextColortmp[k]=nextColor[k];

        ScoreTemp = Score;

    }
    public boolean cutBall(){
        int NumCutBall = 0;//So bong bi cut
        int nBall;
        boolean CheckBall[][]=new boolean[Constant.Row][Constant.Column];
        point[]TempBall=new point [Constant.Row];
        point[]CellBall=new point [Constant.Row*Constant.Column];//Mang luu lai toa do cac bong bi cut
        int i, j,nRow, nCol, nCount;

        for (i =0; i < Constant.Row; i++)
            for (j=0; j < Constant.Column; j++)
                CheckBall[i][j] = false;

        for (nRow=0; nRow < Constant.Row; nRow++)
            for (nCol=0; nCol < Constant.Column; nCol++)
                if (ball[nRow][nCol] > 0 && !CheckBall[nRow][nCol]){

                    nBall = ball[nRow][nCol];
                    //Xet' hang` doc
                    i = nRow;
                    j = nCol;
                    while (i > 0 && ball[ i-1][j] == nBall)
                        i--;
                    nCount = 0;
                    while (i < Constant.Row && ball[i][j] == nBall){

                        CheckBall[i][j] = true;
                        TempBall[nCount++] = new point(i ,j);
                        i++;

                    }
                    if (nCount >= 5){
                        for (i=0; i < nCount; i++)
                            CellBall[NumCutBall++] = TempBall[i];

                        Score+=(nCount-4)*nCount;

                    }

                    //Xet' hang` ngang
                    i = nRow;
                    j = nCol;
                    while (j > 0 && ball[i][j-1] == nBall)
                        j--;
                    nCount = 0;
                    while (j < Constant.Column && ball[i][j] == nBall){

                        CheckBall[i][j] = true;
                        TempBall[nCount++] = new point(i ,j);
                        j++;

                    }
                    if (nCount >= 5){
                        for (i=0; i < nCount; i++)
                            CellBall[NumCutBall++] = TempBall[i];

                        Score+=(nCount-4)*nCount;

                    }

                    //Xet hang cheo' trai'
                    i = nRow;
                    j = nCol;
                    while (i > 0 && j > 0 && ball[i-1][j-1] == nBall){

                        i--;
                        j--;

                    }
                    nCount = 0;
                    while (i < Constant.Row &&  j < Constant.Column && ball[i][j] == nBall){

                        CheckBall[i][j] = true;
                        TempBall[nCount++] = new point(i ,j);
                        i++;
                        j++;

                    }
                    if (nCount >= 5){
                        for (i=0; i < nCount; i++)
                            CellBall[NumCutBall++] = TempBall[i];

                        Score+=(nCount-4)*nCount;

                    }
                    //Xet/ hang` cheo' phai
                    i = nRow;
                    j = nCol;
                    while (i > 0 && j+1 < Constant.Column && ball[i-1][j+1] == nBall){

                        i--;
                        j++;

                    }
                    nCount = 0;
                    while (i < Constant.Row &&  j >= 0 && ball[i][j] == nBall){

                        CheckBall[i][j] = true;
                        TempBall[nCount++] = new point(i ,j);
                        i++;
                        j--;

                    }
                    if (nCount >= 5){
                        for (i=0; i < nCount; i++)
                            CellBall[NumCutBall++] = TempBall[i];

                        Score+=(nCount-4)*nCount;

                    }

                }
        for (i=0; i < NumCutBall; i++)
            ball[CellBall[i].x][CellBall[i].y ] = 0;
        if (NumCutBall>0) return true;
        else return false;

    }
    //Luu lai duong di
    public void FindPath(point p, point [][] PathBallTemp)
    {
        if(p.x!=-1 && p.y!=-1)
            if (PathBallTemp[p.x][p.y] != new point(-1,-1))
                FindPath(PathBallTemp[p.x][p.y],PathBallTemp);
        pathBall[nCountPath++]=p;
    }
    public boolean BFS(int si, int sj, int fi, int fj){ // Loang de tim duong di tu (si,sj)-->(fi,fj);

        int [] di = {-1, 1, 0, 0};
        int [] dj = {0 , 0,-1, 1};
        int i, j, k, nCount;
        point pStart, pFinish, pCurrent;
        point [][] Query = new point[2][ Constant.Row * Constant.Column ];//2 hang doi de loang
        point [][] PathBallTemp = new point[ Constant.Row][Constant.Column ];//Mang luu cac o da di qua
        boolean [][]ballCheck=new boolean[Constant.Row][Constant.Column];//Mang danh dau ca o da xet

        pStart = new point(si, sj);//O bat dau
        pFinish = new point(fi, fj);//O ket thuc

        //Cho pSart vao` hang doi
        int nQuery = 1;
        Query[0][0] = pStart;

        //Danh dau cac o da~ co bong
        for (i=0; i < Constant.Row; i++)
            for (j=0; j < Constant.Column; j++)
                if (ball[i][j]>0 && ball[i][j]<8)
                    ballCheck[i][j] = true;
                else ballCheck[i][j] = false;

        ballCheck[pStart.x][pStart.y] = true;
        if (ballCheck[pFinish.x][pFinish.y])
            return false;
        //Loang de tim duong di
        PathBallTemp[si][sj] = new point(-1,-1);
        while (nQuery > 0)
        {
            nCount = 0;
            for (int nLast=0; nLast < nQuery; nLast++)
            {
                pCurrent = Query[0][nLast ];
                //Tim xung quanh 4 huong' cua o (i, j) xem co huong nao` co' the di duoc khong ?
                for (k=0; k < 4; k++)
                {
                    i = pCurrent.x + di[k];
                    j = pCurrent.y + dj[k];
                    if (i >= 0 && i < Constant.Row && j >=0 && j < Constant.Column && !ballCheck[i][j]){

                        Query[1][nCount++] = new point( i, j);
                        ballCheck[i][j] = true;
                        PathBallTemp[i][j] = new point(pCurrent.x, pCurrent.y);// luu ô gốc vào nhánh ô
                        //Tim tay o dich, thi dung tim kiem
                        if (ballCheck[fi][fj]){
                            nCountPath = 0;
                            FindPath(new point(fi,fj),PathBallTemp);
                            return true;
                        }
                    }
                }
            }
            //Bo cac' ptu cua Query[1] vao Query[0] de tiep' tuc loang
            for (k=0; k < nCount; k++)
                Query[0][k] = Query[1][k];
            nQuery = nCount;
        }

        return false;
    }
}
