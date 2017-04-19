public class Game {
    public static void main(String[] args){

        int n = 5; int m = 5;
        int k = 5;
        int depth = 1;
        boolean isComputerFirst = false;

        GameField game = new GameField(n,m,k,depth);
        game.gameStart(isComputerFirst);
//        System.err.println("\n\nGameOver");
    }
}
