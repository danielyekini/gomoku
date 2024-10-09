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
        String userPos;
        board.printBoard();
        while (!board.checkWin()) {
            userPos = user.play();
            board.placePosition(1, userPos);
            board.printBoard();
            userPos = user.play();
            board.placePosition(2, userPos);
            board.printBoard();
        }
        if (!board.checkWin()) {
            System.out.println("\n???\n");
        } else {
            System.out.println("Winner");
        }
        
        
    }
}
