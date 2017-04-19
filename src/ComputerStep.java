public class ComputerStep {

    private int maxDepth;
    private int k,n,m;
    private int p,q;

    public ComputerStep(int n, int m, int depth, int k){
        this.maxDepth = depth;
        this.k = k;
        this.n = n;
        this.m = m;
    }

    public void computerStep(String[][] field, int depth, int player){
        int a = -1;
        int b = -1;
        int maxScore = Integer.MIN_VALUE;
        String[][] moves = new String[n][m];
        nearCell(field,moves);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (moves[i][j] != null) {
                    int score = MAX(field, depth, player, i, j);
                    System.out.println(score);
                    if (score > maxScore) {
                        maxScore = score;
                        a = i;
                        b = j;
                    }
                }
            }
        }
        field[a][b]="O";
    }

    public int MAX(String[][] field, int depth, int player, int I, int J){
        if (GameField.isEnd(field)||depth == maxDepth) {
            return value(I, J, field, player);
        }
        int score = Integer.MIN_VALUE;
        String[][] movesMax = new String[n][m];
        nearCell(field,movesMax);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (movesMax[i][j] != null) {
                    if (player==1) field[i][j] = "O"; else field[i][j] = "X";
                    int s = MIN(field, depth+1 ,-player, i, j);
                    if (s > score) score = s;
                    field[i][j] = null;
                }
            }
        }
        return score;
    }

    public int MIN(String[][] field, int depth, int player, int I, int J){
        if (GameField.isEnd(field)||depth == maxDepth) {
            return -value(I, J, field, player);
        }
        int score = Integer.MAX_VALUE;
        String[][] movesMin = new String[n][m];
        nearCell(field,movesMin);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (movesMin[i][j] != null) {
                    if (player==1) field[i][j] = "O"; else field[i][j] = "X";
                    int s = MAX(field, depth+1 , -player, i, j);
                    if (s < score) score = s;
                    field[i][j] = null;
                }
            }
        }
        return score;
    }

    private int value(int i, int j, String[][] field, int player){
        if(player == 1) {
            int value = 0;

            p = 1;
            q = 0;
            checkHorizon(i, j, field);
            value = value + G(p) + Q(q);

            p = 1;
            q = 0;
            checkVertical(i, j, field);
            value = value + G(p) + Q(q);

            p = 1;
            q = 0;
            checkDiag1(i, j, field);
            value = value + G(p) + Q(q);

            p = 1;
            q = 0;
            checkDiag2(i, j, field);
            value = value + G(p) + Q(q);

            return value;

        } else {
            int value = 0;

            p = 0;
            q = 1;
            checkHorizon(i, j, field);
            value = value + G(p) + Q(q);

            p = 0;
            q = 1;
            checkVertical(i, j, field);
            value = value + G(p) + Q(q);

            p = 0;
            q = 1;
            checkDiag1(i, j, field);
            value = value + G(p) + Q(q);

            p = 0;
            q = 1;
            checkDiag2(i, j, field);
            value = value + G(p) + Q(q);

            return -value;
        }
    }

    private void checkDiag1(int i, int j, String[][] field){
        int p1=0;
        while (i-p1-1>=0&&j-p1-1>=0&&field[i-p1-1][j-p1-1]=="O"){
            p1++;
        }
        int p2=0;
        while (i+p2+1<field.length&&j+p2+1<field[0].length&&field[i+p2+1][j+p2+1]=="O"){
            p2++;
        }
        p=p+p1+p2;

        int q1=0;
        while (i-q1-1>=0&&j-q1-1>=0&&field[i-q1-1][j-q1-1]=="X"){
            q1++;
        }
        int q2=0;
        while (i+q2+1<field.length&&j+q2+1<field[0].length&&field[i+q2+1][j+q2+1]=="X"){
            q2++;
        }
        q=q+q1+q2;
    }

    private void checkDiag2(int i, int j, String[][] field){
        int p1=0;
        while (i-p1-1>=0&&j+p1+1<field[0].length&&field[i-p1-1][j+p1+1]=="O"){
            p1++;
        }
        int p2=0;
        while (i+p2+1<field.length&&j-p2-1>=0&&field[i+p2+1][j-p2-1]=="O"){
            p2++;
        }
        p=p+p1+p2;

        int q1=0;
        while (i-q1-1>=0&&j+q1+1<field[0].length&&field[i-q1-1][j+q1+1]=="X"){
            q1++;
        }
        int q2=0;
        while (i+q2+1<field.length&&j-q2-1>=0&&field[i+q2+1][j-q2-1]=="X"){
            q2++;
        }
        q=q+q1+q2;
    }

    private void checkVertical(int i, int j, String[][] field){
        int p1=0;
        while (i+p1+1<field.length&&field[i+p1+1][j]=="O"){
            p1++;
        }
        int p2=0;
        while (i-p2-1>=0&&field[i-p2-1][j]=="O"){
            p2++;
        }
        p=p+p1+p2;

        int q1=0;
        while (i+q1+1<field.length&&field[i+q1+1][j]=="X"){
            q1++;
        }
        int q2=0;
        while (i-q2-1>=0&&field[i-q2-1][j]=="X"){
            q2++;
        }
        q=q+q1+q2;
    }

    private void checkHorizon(int i, int j, String[][] field){
        int p1=0;
        while (j+p1+1<field[0].length&&field[i][j+p1+1]=="O"){
            p1++;
        }
        int p2=0;
        while (j-p2-1>=0&&field[i][j-p2-1]=="O"){
            p2++;
        }
        p=p+p1+p2;

        int q1=0;
        while (j+q1+1<field[0].length&&field[i][j+q1+1]=="X"){
            q1++;
        }
        int q2=0;
        while (j-q2-1>=0&&field[i][j-q2-1]=="X"){
            q2++;
        }
        q=q+q1+q2;
    }

    private int G(int k) {
        return f(k + 2);
    }

    private int Q(int k) {
        return f(k + 2);
    }

    private int f(int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        if (k == 1)
            return k;
        return k * f(k - 1);
    }

    private void nearCell(String[][] field, String[][] moves){
        for (int i = 0; i<moves.length; i++){
            for (int j = 0; j<moves[0].length; j++){
                if (field[i][j]==null) {
                    if (j < field[0].length - 1 &&field[i][j + 1] != null) {
                        moves[i][j] = "+";
                    }
                    if (i>0 && j < field[0].length - 1&&field[i-1][j + 1] != null) {
                        moves[i][j] = "+";
                    }
                    if (i>0&&field[i-1][j] != null) {
                        moves[i][j] = "+";
                    }
                    if (i>0&&j>0&&field[i-1][j-1] != null) {
                        moves[i][j] = "+";
                    }
                    if (j>0&&field[i][j-1] != null) {
                        moves[i][j] = "+";
                    }
                    if (i<field.length-1&&j>0&&field[i+1][j-1] != null) {
                        moves[i][j] = "+";
                    }
                    if (i<field.length-1&&field[i+1][j] != null) {
                        moves[i][j] = "+";
                    }
                    if (i<field.length-1 && j < field[0].length - 1&&field[i+1][j+1] != null) {
                        moves[i][j] = "+";
                    }
                }
            }
        }
    }
}
