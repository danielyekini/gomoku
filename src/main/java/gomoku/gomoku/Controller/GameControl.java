package gomoku.gomoku.Controller;

import gomoku.gomoku.Model.*;

public class GameControl {

    Board board;
    User user;

    public GameControl() {
        board = new Board();
        user = new User();
    }

    public static void menu() {}

    public void start() {
        int i = 5;
        board.printBoard();
        while (i > 0) {
            String userPos = user.play();
            board.placePosition(1, userPos);
            board.printBoard();
            i--;
        }
        if (!board.checkWin()) {
            System.out.println("\n???\n");
        }
        
        System.out.println("Winner");
    }
}
