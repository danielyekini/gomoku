package gomoku.gomoku.api;

import gomoku.gomoku.util.enums.WinType;

public class GameState {
    private final int[][] board;
    private final int currentPlayer;
    private final String winType;

    public GameState(int[][] board, int currentPlayer, WinType winType) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.winType = winType.name();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinType() {
        return winType;
    }
}