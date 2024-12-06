package gomoku.gomoku.Model.Players.CPUPlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Services.IProximityService;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.enums.PlayState;

public class CPUProximity extends CPUPlayer {

    private List<String> startArea;
    private boolean firstMove;
    private String lastMove;
    private IProximityService service;

    public CPUProximity(IProximityService proximityService) {
        this.firstMove = true;
        this.startArea = getStartArea();
        this.service = proximityService;
    }

    @Override
    public PlayerResponse play(Board board) {
        Random random = new Random();
        List<String> availableMoves;
        
        if (firstMove) {
            availableMoves = startArea;
            firstMove = false;
        } else {
            // Get proximity area
            String[] proximityArea = service.getProximityArea(board, lastMove);

            // Get the available moves within the proximity area
            availableMoves = service.getAvailableMoves(board, proximityArea);

            // If no moves available, expand proximity area
            while (availableMoves.size() == 0) {
                proximityArea = service.expandProximityArea(proximityArea);
                availableMoves = service.getAvailableMoves(board, proximityArea);
            }
        }

        String move = availableMoves.get(random.nextInt(availableMoves.size()));
        lastMove = move;

        return new PlayerResponse(PlayState.TRYNEXTTURN, move);
    }

    private List<String> getStartArea() {
        List<String> startArea = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                char letter = 'E';
                letter+=x;
                sb.append(letter);
                sb.append(11 - y);
                startArea.add(sb.toString());
                sb.setLength(0);
            }
        }

        return startArea;
    }
    
}
