package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gomoku.gomoku.Model.Board;

@SpringBootTest
public class WinningConditionsTests {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    public void placeMoves(Board board, int player, String[] moves) {
        for (String move : moves) {
            board.placePosition(player, move);
        }
    }

    @Test
    public void testHorizontalWin() {
        // Player 1 places 5 pieces horizontally
        String[] movesPlayer1 = {"A15", "B15", "C15", "D15", "E15"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testVerticalWin() {
        // Player 2 places 5 pieces vertically
        String[] movesPlayer1 = {"A15", "A14", "A13", "A12", "A11"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testDiagonalWin1() {
        // Player 1 places 5 pieces diagonally (top-left to bottom-right)
        String[] movesPlayer1 = {"A15", "B14", "C13", "D12", "E11"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testDiagonalWin2() {
        // Player 2 places 5 pieces diagonally (bottom-left to top-right)
        String[] movesPlayer1 = {"E11", "D12", "C13", "B14", "A15"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testNoWin() {
        // Player 1 places some pieces but doesn't form a line
        String[] movesPlayer1 = {"A15", "B14", "C13", "D15", "E11"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that no win is detected
        assertFalse(board.checkWin() == 1);
    }

    // --- Edge Case Tests ---

    @Test
    public void testEdgeHorizontalWin() {
        // Player 1 places 5 pieces horizontally at the top edge
        String[] movesPlayer1 = {"K15", "L15", "M15", "N15", "O15"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testEdgeVerticalWin() {
        // Player 2 places 5 pieces vertically at the left edge
        String[] movesPlayer1 = {"A15", "A14", "A13", "A12", "A11"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
    
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testTopLeftCornerDiagonalWin() {
        // Player 1 places 5 pieces diagonally from the top-left corner
        String[] movesPlayer1 = {"A15", "B14", "C13", "D12", "E11"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testBottomRightCornerDiagonalWin() {
        // Player 2 places 5 pieces diagonally from the bottom-right corner
        String[] movesPlayer1 = {"O1", "N2", "M3", "L4", "K5"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testHorizontalNearRightEdge() {
        // Player 1 places 5 pieces horizontally near the right edge, but not overflowing
        String[] movesPlayer1 = {"K1", "L1", "M1", "N1", "O1"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testVerticalNearBottomEdge() {
        // Player 2 places 5 pieces vertically near the bottom edge, but not overflowing
        String[] movesPlayer1 = {"O1", "O2", "O3", "O4", "O5"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
        // Assert that the board detects a win
        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testNoWinNearEdge() {
        // Player 1 places some pieces near the edges but doesn't form a line
        String[] movesPlayer1 = {"O1", "N2", "M3", "L1", "K1"};
        String[] movesPlayer2 = {"A1", "B1", "C1", "D1"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);
        // Assert that no win is detected
        assertFalse(board.checkWin() == 1);
    }

    // --- Simulating a Real Game ---
    
    @Test
    public void testSimulatedGameHorizontalWin() {
        String[] movesPlayer1 = {"C11", "D11", "E11", "F11", "G11", "H11"};
        String[] movesPlayer2 = {"G8", "F6", "F7", "F8", "F9"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);

        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testSimulatedGameVerticalWin() {
        String[] movesPlayer1 = {"H8", "G7", "H7", "H6", "H9", "H10"};
        String[] movesPlayer2 = {"G8", "F6", "F7", "F8", "F9"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        board.placePosition(1, movesPlayer1[movesPlayer1.length - 1]);

        assertTrue(board.checkWin() == 1);
    }

    @Test
    public void testSimulatedGameNoWin() {
        String[] movesPlayer1 = {"A15", "B15", "C15"};
        String[] movesPlayer2 = {"A14", "B14", "C14"};

        for (int i = 0; i < movesPlayer2.length; i++) {
            board.placePosition(1, movesPlayer1[i]);
            board.placePosition(2, movesPlayer2[i]);
        }

        assertFalse(board.checkWin() == 1);
    }
}

