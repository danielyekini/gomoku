package gomoku.gomoku.Model;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

public class Board {

    private int[][] grid;
    private int[] lastPos;
    private int gridSize;
    private List<String> availableMoves;

    public Board() {
        this.gridSize = 15;
        this.grid = new int[gridSize][gridSize];
        this.lastPos = new int[2];
        this.availableMoves = setAvailableMoves();
    }
    
    public void printBoard() {
        int row = 15;
        String gap = "";

        for (int i = 0; i < grid.length; i++) {
            if (row < 10) {
                gap = " ";
            }
            String currRowString = gap + row + " " + Arrays.toString(grid[i]);
            System.out.println(currRowString);
            row--;
        }

        System.out.print("    ");

        for (int i = 0; i < grid.length; i++) {
            System.out.print(Character.toChars('A' + i));
            System.out.print("  ");
        }
        System.out.println("\n");
    }
   
    private int toX(char letter) {
        int posX = Character.toUpperCase(letter) - 'A';

        if (posX < 0 || posX > gridSize-1) {
            return -1;
        }
        return posX;
    }

    private int toY(String number) {
        int posY = gridSize - Integer.parseInt(number);

        if (posY < 0 || posY > gridSize-1) {
            return -1;
        }

        return posY;
    }
    
    private int[] toAxis(String position) {
        int posX = toX(position.charAt(0));
        int posY = toY(position.substring(1));
        return new int[] {posX, posY};
    }

    public boolean posAvailable(int posX, int posY) {
        return grid[posY][posX] == 0;
    }

    private List<String> setAvailableMoves() {
        List<String> availableMoves = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 0) {
                    char letter = 'A';
                    letter+=x;
                    sb.append(letter);
                    sb.append(gridSize - y);
                    availableMoves.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }

        return availableMoves;
    }

    public List<String> getAvailableMoves() {
        return availableMoves;
    }

    public int[] getLastPos() { return lastPos; }

    public int getGridSize() { return gridSize; }
    
    public boolean placePosition(int player, String position ) {
        // Check if player number is valid
        if (player < 1 || player > 2) {
            return false;
        }

        if (position.length() < 2 || position.length() > 3) {
            return false;
        }

        int[] axis = toAxis(position);
        int posX = axis[0];
        int posY = axis[1];

        // Check if x and y coordinates are valid
        if (posX == -1 || posY == -1) {
            return false;
        }

        // Check if position is available before placing piece
        if (posAvailable(posX, posY) && grid[lastPos[1]][lastPos[0]] != player) {
            grid[posY][posX] = player;
            availableMoves.remove(position.toUpperCase());
            lastPos = axis;
            return true;
        } else {
            System.out.println("\nPosition Taken!\n");
        }

        return false;
    }

    private boolean checkHorizontal(int lastPlayer) {
        int start = (lastPos[0]-4 < 0) ? 0 : lastPos[0]-4;
        int end = (lastPos[0]+4 > 14) ? 14 : lastPos[0]+4;
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (grid[lastPos[1]][i] == lastPlayer) {
                count++;
                if (count == 5) {
                    System.out.println("\nHorizontal Win by player " + lastPlayer + "\n");
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }

    private boolean checkVertical(int lastPlayer) {
        int start = (lastPos[1]-4 < 0) ? 0 : lastPos[1]-4;
        int end = (lastPos[1]+4 > 14) ? 14 : lastPos[1]+4;
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (grid[i][lastPos[0]] == lastPlayer) {
                count++;
                if (count == 5) {
                    System.out.println("\nVertical Win by player " + lastPlayer + "\n");
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }

    private boolean checkDiagonal1(int lastPlayer) {
        int startX = (lastPos[0]-4 < 0) ? 0 : lastPos[0]-4;
        int endX = (lastPos[0]+4 > 14) ? 14 : lastPos[0]+4;
        int startY = (lastPos[1]-4 < 0) ? 0 : lastPos[1]-4;
        int endY = (lastPos[1]+4 > 14) ? 14 : lastPos[1]+4;
        int count = 0;

        while (startX <= endX && startY <= endY) {
            if (grid[startY][startX] == lastPlayer) {
                count++;
                if (count == 5) {
                    System.out.println("\nDiagonal Win: Left to right by player " + lastPlayer + "\n");
                    return true;
                }
            } else {
                count = 0;
            }
            startX++;
            startY++;
        }

        return false;
    }

    private boolean checkDiagonal2(int lastPlayer) {
        int startX = (lastPos[0]+4 > 14) ? 14 : lastPos[0]+4;
        int endX = (lastPos[0]-4 < 0) ? 0 : lastPos[0]-4;
        int startY = (lastPos[1]-4 < 0) ? 0 : lastPos[1]-4;
        int endY = (lastPos[1]+4 > 14) ? 14 : lastPos[1]+4;
        int count = 0;

        while (startX >= endX && startY <= endY) {
            if (grid[startY][startX] == lastPlayer) {
                count++;
                if (count == 5) {
                    System.out.println("\nDiagonal Win: Right to left by player " + lastPlayer + "\n");
                    return true;
                }
            } else {
                count = 0;
            }
            startX--;
            startY++;
        }

        return false;
    }

    public int checkWin() {
        // int[][] searchRadius = getSearchRadius();
        int lastPlayer = grid[lastPos[1]][lastPos[0]];

        if (lastPlayer == 0) {
            return -1;
        }

        if (checkHorizontal(lastPlayer) || checkVertical(lastPlayer) || checkDiagonal1(lastPlayer) || checkDiagonal2(lastPlayer)) {
            return 1;
        }

        if (availableMoves.size() == 0) {
            return 0;
        }

        return -1;
    }
}
