package gomoku.gomoku.Services;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

import gomoku.gomoku.Model.Board;

public class ProximityService implements IProximityService {

    @Override
    public List<String> getAvailableMoves(Board board, String[] areaPoints) {
        List<String> availableMoves = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String pointOne = areaPoints[0];
        String pointTwo = areaPoints[1];

        int width = pointTwo.charAt(0) - pointOne.charAt(0) + 1;
        int height = Math.abs(Integer.parseInt(pointTwo.substring(1)) - Integer.parseInt(pointOne.substring(1))) + 1;

        int startCol = pointOne.charAt(0) - 'A';
        int startRow = (Integer.parseInt(pointOne.substring(1)) > Integer.parseInt(pointTwo.substring(1))) ?
        Integer.parseInt(pointOne.substring(1)) : 
        Integer.parseInt(pointTwo.substring(1));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.posAvailable(startCol + x, (board.getGridSize() - startRow) + y)) {
                    char letter = pointOne.charAt(0);
                    letter+=x;
                    sb.append(letter);
                    sb.append(startRow - y);
                    availableMoves.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }

        return availableMoves;
    }

    @Override
    public String[] getProximityArea(Board board, String playerLastMove) {
        String[] proximityArea = new String[2];

        proximityArea[0] = playerLastMove;
        char letter = 'A';
        letter += board.getLastPos()[0];
        proximityArea[1] = "" + letter + (15 - board.getLastPos()[1]);

        Arrays.sort(proximityArea, (a, b) -> a.charAt(0) == b.charAt(0) ? Integer.parseInt(b.substring(1)) - Integer.parseInt(a.substring(1)) : a.charAt(0) - b.charAt(0));

        return proximityArea;
    }

    @Override
    public String[] expandProximityArea(String[] proximityArea) {
        String areaPoint1 = proximityArea[0];
        String areaPoint2 = proximityArea[1];

        char areaPoint1X = areaPoint1.charAt(0);
        areaPoint1X = areaPoint1X > 'A' ? areaPoint1X -= 1 : areaPoint1X;

        int areaPoint1Y = Integer.parseInt(areaPoint1.substring(1));
        areaPoint1Y = areaPoint1Y < 15 ? areaPoint1Y += 1 : areaPoint1Y;

        char areaPoint2X = areaPoint2.charAt(0);
        areaPoint2X = areaPoint2X < 'O' ? areaPoint2X += 1 : areaPoint2X;

        int areaPoint2Y = Integer.parseInt(areaPoint2.substring(1));
        areaPoint1Y = areaPoint1Y > 0 ? areaPoint1Y -= 1 : areaPoint1Y;

        proximityArea[0] = "" + areaPoint1X + areaPoint1Y;
        proximityArea[1] = "" + areaPoint2X + areaPoint2Y;

        return proximityArea;
    }
    
}
