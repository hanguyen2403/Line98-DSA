/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to apply undo feature of game*/
package Lines;

class Link {
    public int[][] ball = new int[Constant.Row][Constant.Column];
    public int[] color = new int[3];
    public long score;
    public Link next;
    public Link previous;

    public Link(int[][] b, int[] c, long s) {
        for (int i = 0; i < Constant.Row; i++)
            for (int j = 0; j < Constant.Column; j++)
                ball[i][j] = b[i][j];

        for (int k = 0; k < 3; k++)
            color[k] = c[k];

        score = s;
    }

}
