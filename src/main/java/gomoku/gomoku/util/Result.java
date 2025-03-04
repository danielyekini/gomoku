package gomoku.gomoku.util;

import gomoku.gomoku.Model.Players.Player;
import gomoku.gomoku.util.enums.WinType;

public class Result {

    private WinType win;
    private Player winner;
    
    public Result(WinType winType, Player winner) {
        this.win = winType;
        this.winner = winner;
    }

    public WinType getWinDetails() {
        return win;
    }

    public Player getWinner() {
        return winner;
    }
}
