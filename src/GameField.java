import java.util.Objects;
import java.util.Scanner;

public class GameField {

    private String[][] field;
    private static int k;
    private int depth;

    public GameField(int n,int m,int k,int depth){
        this.field = new String[n][m];
        this.k = k;
        this.depth = depth;
    }

    public void gameStart(boolean t){
        if (t) field[field.length/2][field[0].length/2]="O";
        drawField(field);
        while (!isEnd(field)){
            Scanner player = new Scanner(System.in);
            System.out.println("Enter your step:  ");
            int step1 = player.nextInt();
            int step2 = player.nextInt();
            field[step1][step2]="X";
            drawField(field);

            if (!isEnd(field)) {
                ComputerStep computer = new ComputerStep(field.length, field[0].length, depth, k);
                computer.computerStep(field,0,1);
                drawField(field);
            }
        }
    }

    public static boolean isEnd(String[][] field){
        boolean res = isFullField(field) || isWin(field,k);
        return res;
    }

    private static boolean isWin(String[][] field, int k){
        boolean res = isWinRow(field,k) || isWinColumn(field,k) || isWinDiag(field,k);
        return res;
    }

    private static boolean isWinRow(String[][] field, int k){
        int w = 1;
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length-1; j++) {
                if (Objects.equals(field[i][j], field[i][j + 1])&&field[i][j]!=null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        return  false;
    }

    private static boolean isWinColumn(String[][] field, int k){
        int w = 1;
        for (int i = 0; i < field[0].length; i++){
            for (int j = 0; j < field.length-1; j++) {
                if (Objects.equals(field[j][i], field[j+1][i])&&field[j][i]!=null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        return false;
    }

    private static boolean isWinDiag(String[][] field, int k){
        int w = 1;
        int n=field.length;
        int m=field[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j<n-1&&i<m-j-1&&Objects.equals(field[j][j+i], field[j+1][j+i+1]) && field[j][j+i] != null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        w = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i<n-j-1&&j<m-1&&Objects.equals(field[j+i][j], field[j+i+1][j+1]) && field[j+i][j] != null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        w = 1;
        for (int i = m-1; i>=0; i--) {
            for (int j = 0; j < n; j++) {
                if (i>=j+1&&j<n-1&&Objects.equals(field[j][i-j], field[j+1][i-j-1]) && field[j][i-j] != null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        w = 1;
        for (int i = 1; i<n; i++) {
            for (int j = 1; j < m; j++) {
                if (i<n-j-1&&Objects.equals(field[i+j][m-j], field[i+j+1][m-j-1]) && field[i+j][m-j] != null) {
                    w+=1;
                }else{
                    w=1;
                }
                if (w==k) return true;
            }
        }
        return false;
    }

    private static boolean isFullField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == null)
                    return false;
            }
        }
        return true;
    }

    private void drawField(String[][] field){
        System.out.print("    ");
        for (int i = 0; i < field[0].length; i++ ){
            System.out.print(i+" ");
        }
        System.out.println(" "); System.out.println(" ");
        for (int i = 0; i < field.length; i++) {
            System.out.print(i+"   ");
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == null) {
                    System.out.print(" "+" ");
                }else{
                    System.out.print(field[i][j]+" ");
                }
            }
            System.out.println(" ");
        }
    }
}