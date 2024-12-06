package gomoku.gomoku.util.configure;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Model.Players.Player;

public interface GameConfig {

    public Board getBoard();
    
    public Player getPlayer1();

    public Player getPlayer2();
}
