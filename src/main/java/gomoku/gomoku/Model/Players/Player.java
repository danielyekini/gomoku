package gomoku.gomoku.Model.Players;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.PlayerResponse;

public abstract class Player {

    private int number;
    
    public abstract PlayerResponse play(Board state);

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int num) {
        this.number = num;
    }
    
}
