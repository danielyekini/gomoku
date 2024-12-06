package gomoku.gomoku.util.configure;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Model.Players.Player;

public class PlayConfig implements GameConfig {
    private Board board;
    private Player player1;
    private Player player2;

    public PlayConfig(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }
}
