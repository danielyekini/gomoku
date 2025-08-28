package gomoku.gomoku.api;

import org.springframework.stereotype.Service;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.enums.WinType;

@Service
public class GameService {
    private Board board = new Board();
    private int currentPlayer = 1;
    private WinType winType = WinType.NOWIN;

    public GameState getState() {
        return new GameState(board.getGrid(), currentPlayer, winType);
    }

    public GameState newGame() {
        board = new Board();
        currentPlayer = 1;
        winType = WinType.NOWIN;
        return getState();
    }

    public GameState play(int x, int y) {
        if (winType != WinType.NOWIN) {
            return getState();
        }

        char col = (char) ('A' + x);
        int row = board.getGridSize() - y;
        String pos = "" + col + row;
        if (board.placePosition(currentPlayer, pos)) {
            winType = board.checkWin();
            if (winType == WinType.NOWIN) {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }
        return getState();
    }
}