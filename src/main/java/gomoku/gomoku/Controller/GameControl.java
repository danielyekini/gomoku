package gomoku.gomoku.Controller;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.Services.ProximityService;

public class GameControl {

    Board board;
    CPUPlayer cpu1;
    CPUPlayer cpu2;
    User user;

    public GameControl() {
        board = new Board();
        user = new User();
        cpu1 = new CPURandom();
        cpu2 = new CPUProximity(new ProximityService());
    }

    public static void menu() {}

    public void start() {
        String cpu1Pos;
        String cpu2Pos;
        
        while (board.checkWin() == -1) {
            board.printBoard();
            cpu1Pos = cpu1.play(board);
            board.placePosition(1, cpu1Pos);
            board.printBoard();
            if (board.checkWin() == 1) {
                break;
            }
            cpu2Pos = cpu2.play(board);
            board.placePosition(2, cpu2Pos);
        }
        board.printBoard();
        if (board.checkWin() == 0) {
            System.out.println("\nDraw\n");
        } else {
            System.out.println("Winner");
        }
        
        
    }
}
