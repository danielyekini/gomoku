package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gomoku.gomoku.Model.Board;

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
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            board.placePosition(1, "A");  // Invalid format: No y-coordinate
        });
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
}
