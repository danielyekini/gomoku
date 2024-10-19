package gomoku.gomoku.Services;

import java.util.List;

import gomoku.gomoku.Model.Board;

public interface IProximityService {
    public List<String> getAvailableMoves(Board board, String[] areaPoints);
    public String[] getProximityArea(Board board, String playerLastMove);
    public String[] expandProximityArea(String[] proximityArea);
}
