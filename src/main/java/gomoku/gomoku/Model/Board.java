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
        int posX = Character.toUpperCase(letter) - 'A';

        if (posX < 0 || posX > gridSize-1) {
            return -1;
        }
        return posX;
    }

    private int toY(String number) {
        int posY;
        if (number.length() == 2) {
            int tens = Character.getNumericValue(number.charAt(0))*10;
            tens += Character.getNumericValue(number.charAt(1));
            posY = 15 - tens;
        } else {
            posY = 15 - Character.getNumericValue(number.charAt(0));
        }

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

    private boolean posAvailable(int posX, int posY) {
        return grid[posY][posX] == 0;
    }

    public boolean placePosition(int player, String position ) {
        int[] axis = toAxis(position);
        int posX = axis[0];
        int posY = axis[1];

        if (posX == -1 || posY == -1) {
            return false;
        }

        // Check if position is available before placing piece
        if (posAvailable(posX, posY) && grid[lastPos[1]][lastPos[0]] != player) {
            grid[posY][posX] = player;
            lastPos = axis;
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

    private boolean checkHorizontal(int lastPlayer) {
        int start = (lastPos[0]-4 < 0) ? 0 : lastPos[0]-4;
        int end = (lastPos[0]+4 > 14) ? 14 : lastPos[0]+4;
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (grid[lastPos[1]][i] == lastPlayer) {
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

    private boolean checkVertical(int lastPlayer) {
        int start = (lastPos[1]-4 < 0) ? 0 : lastPos[1]-4;
        int end = (lastPos[1]+4 > 14) ? 14 : lastPos[1]+4;
        int count = 0;

        for (int i = start; i <= end; i++) {
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

    public boolean checkWin() {
        // int[][] searchRadius = getSearchRadius();
        int lastPlayer = grid[lastPos[1]][lastPos[0]];

        return checkHorizontal(lastPlayer) || checkVertical(lastPlayer) || checkDiagonal1(lastPlayer) || checkDiagonal2(lastPlayer);
    }
}
