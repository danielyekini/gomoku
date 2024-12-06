package gomoku.gomoku.Model.Players;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.PlayerResponse;

public abstract class Player {

    public int number;
    
    public abstract PlayerResponse play(Board state);
    
}
