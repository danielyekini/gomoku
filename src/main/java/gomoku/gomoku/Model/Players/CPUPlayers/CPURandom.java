package gomoku.gomoku.Model.Players.CPUPlayers;

import java.util.List;
import java.util.Random;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.enums.PlayState;

public class CPURandom extends CPUPlayer {

    @Override
    public PlayerResponse play(Board board) {
        List<String> availableMoves = board.getAvailableMoves();

        Random random = new Random();
        String move = availableMoves.get(random.nextInt(availableMoves.size()));

        return new PlayerResponse(PlayState.TRYNEXTTURN, move);
    }
    
}
