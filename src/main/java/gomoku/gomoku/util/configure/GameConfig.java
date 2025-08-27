package gomoku.gomoku.util.configure;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Model.Players.Player;

public interface GameConfig {

    public Board initializeBoard();
    
    public Player initializePlayer1();

    public Player initializePlayer2();
}
