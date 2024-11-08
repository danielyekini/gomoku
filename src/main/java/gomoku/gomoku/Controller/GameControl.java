package gomoku.gomoku.Controller;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.configure.*;

import java.util.List;

public class GameControl {
    Menu menu;
    Board board;
    Player p1;
    Player p2;

    public GameControl() {
        this.menu = new Menu();
    }

    public void start() {
        GameConfig config = menu.getConfig();
        configureGame(config);
    }

    private void configureGame(GameConfig config) {
        if (config instanceof PlayConfig) {
            executePlay((PlayConfig) config);
        } else if (config instanceof SimulateConfig) {
            executeSimulate((SimulateConfig) config);
        } else if (config instanceof TrainConfig) {
            executeTrain((TrainConfig) config);
        } else {
            throw new IllegalArgumentException("Unknown GameConfig type");
        }
    }

    private void executePlay(PlayConfig config) {
        // Initalise new board object
        board = new Board();

        // Assign players
        p1 = config.getPlayer1();
        p2 = config.getPlayer2();;

        // Run game
        while (board.checkWin() == -1) {
            board.printBoard();
            board.placePosition(p1.number, p1.play(board));
            board.printBoard();
            board.placePosition(p2.number, p2.play(board));
        }
    }

    private void executeSimulate(SimulateConfig config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeSimulate'");
    }

    private void executeTrain(TrainConfig config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTrain'");
    }
}
