package gomoku.gomoku.Model.CPUPlayers;

import gomoku.gomoku.Model.Board;

public abstract class CPUPlayer extends Player {
    
    public abstract String play(Board state);
}
