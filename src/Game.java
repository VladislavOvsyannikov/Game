public class Game {
    public static void main(String[] args){

        int n = 10; int m = 10;
        int k = 5;
        int depth = 3;
        boolean isComputerFirst = false;

        GameField game = new GameField(n,m,k,depth);
        game.gameStart(isComputerFirst);
//        System.err.println("\n\nGameOver");
    }
}
