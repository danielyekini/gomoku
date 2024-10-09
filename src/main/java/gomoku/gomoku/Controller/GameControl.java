package gomoku.gomoku.Controller;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.CPUPlayer;
import gomoku.gomoku.Model.CPUPlayers.CPURandom;

public class GameControl {

    Board board;
    CPUPlayer cpu;
    User user;

    public GameControl() {
        board = new Board();
        user = new User();
        cpu = new CPURandom();
    }

    public static void menu() {}

    public void start() {
        board.printBoard();
        while (!board.checkWin()) {
            String userPos = user.play();
            board.placePosition(1, userPos);
            board.printBoard();
            String cpuPos = cpu.play(board);
            board.placePosition(2, cpuPos);
            board.printBoard();
        }
        if (!board.checkWin()) {
            System.out.println("\n???\n");
        } else {
            System.out.println("Winner");
        }
        
        
    }
}
