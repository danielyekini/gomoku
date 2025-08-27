package gomoku.gomoku.Model.Players.CPUPlayers;

import java.util.List;
import java.util.Random;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.enums.PlayState;

public class CPURandom extends CPUPlayer {

    private int firstTurn = 0;
    private Random random = new Random();

    @Override
    public PlayerResponse play(Board board) {
        List<String> availableMoves = board.getAvailableMoves();

        String move = availableMoves.get(random.nextInt(availableMoves.size()));
        
        if (firstTurn == 0) {
            firstTurn++;
            return new PlayerResponse(PlayState.TRYNEXTTURN, "C15");
        }

        return new PlayerResponse(PlayState.TRYNEXTTURN, move);
    }
    
}
