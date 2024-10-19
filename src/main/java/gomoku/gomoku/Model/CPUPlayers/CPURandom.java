package gomoku.gomoku.Model.CPUPlayers;

import java.util.List;
import java.util.Random;

import gomoku.gomoku.Model.Board;

public class CPURandom extends CPUPlayer {

    @Override
    public String play(Board board) {
        List<String> availableMoves = board.getAvailableMoves();

        Random random = new Random();
        String move = availableMoves.get(random.nextInt(availableMoves.size()));

        return move;
    }
    
}
