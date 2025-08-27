package gomoku.gomoku.util.configure;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Model.Players.Player;
import gomoku.gomoku.Model.Players.User;
import gomoku.gomoku.Model.Players.CPUPlayers.CPUProximity;
import gomoku.gomoku.Model.Players.CPUPlayers.CPURandom;
import gomoku.gomoku.Services.ProximityService;
import gomoku.gomoku.util.enums.PlayerType;

public class PlayConfig implements GameConfig {
    private Board board;
    private PlayerType player1;
    private PlayerType player2;

    public PlayConfig(PlayerType player1, PlayerType player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public Board initializeBoard() {
        this.board = new Board();
        return board;
    }

    @Override
    public Player initializePlayer1() {
        Player p1 = initializePlayer(player1);
        p1.setNumber(1);
        return p1;
    }

    @Override
    public Player initializePlayer2() {
        Player p2 = initializePlayer(player2);
        p2.setNumber(2);
        return p2;
    }

    private Player initializePlayer(PlayerType type) {
        return 
        type == PlayerType.PROXIMITY ? new CPUProximity(new ProximityService()) :
        type == PlayerType.RANDOM ? new CPURandom() :
        new User();
    }
}
