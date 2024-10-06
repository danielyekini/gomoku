package gomoku.gomoku.Model;

import java.util.Arrays;

public class Board {

    private int[][] grid;
    private int[] lastPos;
    private int gridSize;

    public Board() {
        this.gridSize = 15;
        this.grid = new int[gridSize][gridSize];
        this.lastPos = new int[2];
    }
    
    private int toX(char letter) {
        return Character.toUpperCase(letter) - 'A';
    }

    private int[] toAxis(String position) {
        int posX = toX(position.charAt(0));
        int posY;
        if (position.length() == 3) {
            int tens = Character.getNumericValue(position.charAt(1))*10;
            tens += Character.getNumericValue(position.charAt(2));
            posY = 15 - tens;
        } else {
            posY = 15 - Character.getNumericValue(position.charAt(1));
        }
        return new int[] {posX, posY};
    }

    private boolean posAvailable(int posX, int posY) {
        return grid[posY][posX] == 0;
    }

    public void gridSize(int size) {
        gridSize = size;
    }

    public boolean placePosition(int player, String position ) {
        int[] axis = toAxis(position);
        lastPos = axis;
        int posX = axis[0];
        int posY = axis[1];

        // Check if position is available before placing piece
        if (posAvailable(posX, posY)) {
            grid[posY][posX] = player;
            return true;
        }

        return false;
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

    public boolean checkWin() {
        // int[][] searchRadius = getSearchRadius();
        int lastPlayer = grid[lastPos[1]][lastPos[0]];
        int count = 0;

        // Check for horizontal win
        int start = (lastPos[0]-4 < 0) ? 0 : lastPos[0]-4;
        int end = (lastPos[0]+4 < 0) ? 0 : lastPos[0]+4;

        for (int i = start; i < end; i++) {
            if (grid[lastPos[1]][i] == lastPlayer) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check for vertical win
        start = (lastPos[1]-4 < 0) ? 0 : lastPos[1]-4;
        end = (lastPos[1]+4 < 0) ? 0 : lastPos[1]+4;

        for (int i = start; i < end; i++) {
            if (grid[i][lastPos[0]] == lastPlayer) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }
}
