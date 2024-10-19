package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Services.ProximityService;

@SpringBootTest
public class BoardTests {

    private Board board;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInvalidConsecutiveTurnSamePlayer() {
        // Player 1 places a piece
        board.placePosition(1, "A10");

        // Player 1 attempts to place another piece consecutively
        boolean result = board.placePosition(1, "B10");

        // Assert that it should be invalid (false)
        assertFalse(result, "Player should not be able to take consecutive turns.");
    }

    @Test
    public void testOutOfBoundsPositionX() {
        // Try placing a piece at an invalid x-axis position (out of range: A-O is valid)
        boolean result = board.placePosition(1, "P10");

        // Assert that it should be invalid (false)
        assertFalse(result, "Position should be invalid due to out-of-bounds x-axis.");
    }

    @Test
    public void testOutOfBoundsPositionY() {
        // Try placing a piece at an invalid y-axis position (out of range: 1-15 is valid)
        boolean result = board.placePosition(1, "A16");

        // Assert that it should be invalid (false)
        assertFalse(result, "Position should be invalid due to out-of-bounds y-axis.");
    }

    @Test
    public void testOutOfBoundsMoveNegative() {
        // Player 2 attempts to place a piece in a negative position (nonexistent position)
        assertFalse(board.placePosition(2, "-1"), "Position '-1' should be invalid");
    }

    @Test
    public void testInvalidPositionFormat() {
        // Try placing a piece with an invalid format (e.g., missing a number)
        assertFalse(board.placePosition(1, "A"));  // Invalid format: No y-coordinate
    }

    @Test
    public void testValidPlacementAfterInvalid() {
        // Player 1 tries to place a piece out of bounds
        assertFalse(board.placePosition(1, "P10"));
        
        // Valid move afterward should still work
        assertTrue(board.placePosition(1, "A10"));
    }

    @Test
    public void testInvalidPositionOccupied() {
        // Player 1 places a piece at a valid position
        board.placePosition(1, "A10");

        // Player 2 attempts to place another piece in the same position
        boolean result = board.placePosition(2, "A10");

        // Assert that it should be invalid (false)
        assertFalse(result, "Position should be invalid as it is already occupied.");
    }

    @Test
    public void testPlayerAlternationValid() {
        // Player 1 places a piece
        boolean player1Move = board.placePosition(1, "A10");

        // Player 2 places a piece
        boolean player2Move = board.placePosition(2, "B10");

        // Assert that both moves are valid
        assertFalse(!player1Move && !player2Move, "Both players should alternate successfully.");
    }

    @Test
    public void testValidAndInvalidPlayerAlternation() {
        // Alternating valid and invalid positions
        assertTrue(board.placePosition(1, "A10"));
        assertFalse(board.placePosition(1, "B10")); // Invalid consecutive turn by the same player
        assertTrue(board.placePosition(2, "B10"));
        assertFalse(board.placePosition(2, "C20")); // Out of bounds Y-axis
        assertTrue(board.placePosition(1, "C10"));
    }

    @Test
    void testInitialAvailableMoves() {
        // All positions should be available initially (15x15 board = 225 positions)
        List<String> availableMoves = board.getAvailableMoves();
        assertEquals(225, availableMoves.size(), "Initial available moves should be 225.");

        // Check that certain positions exist in the list
        assertTrue(availableMoves.contains("A15"), "A15 should be available at the start.");
        assertTrue(availableMoves.contains("O1"), "O1 should be available at the start.");
        assertTrue(availableMoves.contains("H8"), "H8 should be available at the start.");
    }

    @Test
    void testAvailableMovesAfterValidMove() {
        // Place a valid move at A15
        board.placePosition(1, "A15");

        // A15 should no longer be available
        List<String> availableMoves = board.getAvailableMoves();
        assertEquals(224, availableMoves.size(), "Available moves should be 224 after one move.");
        assertFalse(availableMoves.contains("A15"), "A15 should no longer be available after the move.");
    }

    @Test
    void testAvailableMovesAfterMultipleMoves() {
        // Place valid moves at A15, B14, and C13
        board.placePosition(1, "A15");
        board.placePosition(2, "B14");
        board.placePosition(1, "C13");

        // A15, B14, and C13 should no longer be available
        List<String> availableMoves = board.getAvailableMoves();
        assertEquals(222, availableMoves.size(), "Available moves should be 222 after three moves.");
        assertFalse(availableMoves.contains("A15"), "A15 should no longer be available after the move.");
        assertFalse(availableMoves.contains("B14"), "B14 should no longer be available after the move.");
        assertFalse(availableMoves.contains("C13"), "C13 should no longer be available after the move.");
    }

    @Test
    void testAvailableMovesAfterInvalidMove() {
        // Place a valid move at A15
        board.placePosition(1, "A15");

        // Try to place a move again at A15, which is invalid
        boolean moveResult = board.placePosition(2, "A15");

        // A15 should still be unavailable, and the size of available moves should not change
        assertFalse(moveResult, "Placing a move on an occupied spot should return false.");
        List<String> availableMoves = board.getAvailableMoves();
        assertEquals(224, availableMoves.size(), "Available moves should still be 224 after an invalid move.");
        assertFalse(availableMoves.contains("A15"), "A15 should remain unavailable after the invalid move.");
    }
}
