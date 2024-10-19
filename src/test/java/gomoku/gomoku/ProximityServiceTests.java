package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Services.ProximityService;

@SpringBootTest
public class ProximityServiceTests {

    private Board board;
    private ProximityService proximityService;

    @BeforeEach
    void setUp() {
        board = new Board();
        proximityService = new ProximityService();
    }

    @Test
    void testGetAvailableMovesWithinEmptyArea() {
        String[] areaPoints = {"A15", "C13"};
        
        List<String> availableMoves = proximityService.getAvailableMoves(board, areaPoints);
        List<String> expectedMoves = List.of("A15", "B15", "C15", "A14", "B14", "C14", "A13", "B13", "C13");
        
        assertEquals(expectedMoves, availableMoves);
    }

    @Test
    void testGetAvailableMovesWithinPartiallyOccupiedArea() {
        board.placePosition(1, "B14"); // Mark B14 as occupied
        String[] areaPoints = {"A15", "C13"};
        
        List<String> availableMoves = proximityService.getAvailableMoves(board, areaPoints);
        List<String> expectedMoves = List.of("A15", "B15", "C15", "A14", "C14", "A13", "B13", "C13");
        
        assertEquals(expectedMoves, availableMoves);
    }

    @Test
    void testGetProximityArea() {
        board.placePosition(1, "J10");  // Set last move to J10
        
        String[] proximityArea = proximityService.getProximityArea(board, "B14");
        String[] expectedArea = {"B14", "J10"};
        
        assertArrayEquals(expectedArea, proximityArea);
    }
}

