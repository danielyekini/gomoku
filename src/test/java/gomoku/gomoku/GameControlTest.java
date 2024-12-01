package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import gomoku.gomoku.Controller.GameControl;
import gomoku.gomoku.Model.*;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.configure.*;

import java.util.*;

public class GameControlTest {

    // MockMenu class to simulate menu returning predefined configurations
    public class MockMenu extends Menu {
        private Queue<GameConfig> configs;

        public MockMenu(List<GameConfig> configs) {
            super(null); // We pass null since we won't use 'in' in the mock
            this.configs = new LinkedList<>(configs);
        }

        @Override
        public GameConfig getConfig() {
            if (configs.isEmpty()) {
                return null;
            }
            return configs.poll();
        }
    }

    // MockPlayer class to simulate player moves
    public class MockPlayer extends Player {
        private Queue<String> moves;
        private String name;

        public MockPlayer(int number, List<String> moves, String name) {
            this.number = number;
            this.moves = new LinkedList<>(moves);
            this.name = name;
        }

        @Override
        public String play(Board board) {
            if (moves.isEmpty()) {
                return null; // Simulate resignation or end of moves
            }
            return moves.poll();
        }

        @Override
        public String toString() {
            return "MockPlayer " + name;
        }
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_PlayGame_UserVsUser() {
        // Simulate a game between two users where Player 1 wins with a horizontal line
        List<String> p1Moves = Arrays.asList("A1", "B1", "C1", "D1", "E1"); // Winning move sequence
        List<String> p2Moves = Arrays.asList("A2", "B2", "C2", "D2");

        MockPlayer p1 = new MockPlayer(1, p1Moves, "User1");
        MockPlayer p2 = new MockPlayer(2, p2Moves, "User2");

        PlayConfig playConfig = new PlayConfig(p1, p2);
        MockMenu mockMenu = new MockMenu(Arrays.asList(playConfig));

        GameControl gameControl = new GameControl(mockMenu);
        gameControl.start();

        // The test passes if the game runs without throwing exceptions
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_PlayGame_UserVsCPU() {
        // Simulate a game between a user and a CPU where the CPU wins
        List<String> userMoves = Arrays.asList("A1", "B1", "C1", "D2");
        List<String> cpuMoves = Arrays.asList("A2", "B2", "C2", "D2", "E2"); // CPU's winning move sequence

        MockPlayer user = new MockPlayer(1, userMoves, "User");
        MockPlayer cpu = new MockPlayer(2, cpuMoves, "CPU");

        PlayConfig playConfig = new PlayConfig(user, cpu);
        MockMenu mockMenu = new MockMenu(Arrays.asList(playConfig));

        GameControl gameControl = new GameControl(mockMenu);
        gameControl.start();

        // The test passes if no exceptions are thrown during game execution
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_ExitGameImmediately() {
        // Simulate the user choosing to exit the game immediately
        MockMenu mockMenu = new MockMenu(Arrays.asList((GameConfig) null));

        GameControl gameControl = new GameControl(mockMenu);
        gameControl.start();

        // The test passes if the game exits gracefully without errors
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_ExecuteSimulate_UnimplementedMethod() {
        // Simulate selecting the "Simulate" option
        SimulateConfig simulateConfig = new SimulateConfig();
        MockMenu mockMenu = new MockMenu(Arrays.asList(simulateConfig));

        GameControl gameControl = new GameControl(mockMenu);

        try {
            gameControl.start();
            fail("Expected UnsupportedOperationException for executeSimulate");
        } catch (UnsupportedOperationException e) {
            assertEquals("Unimplemented method 'executeSimulate'", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_ExecuteTrain_UnimplementedMethod() {
        // Simulate selecting the "Train" option
        TrainConfig trainConfig = new TrainConfig();
        MockMenu mockMenu = new MockMenu(Arrays.asList(trainConfig));

        GameControl gameControl = new GameControl(mockMenu);

        try {
            gameControl.start();
            fail("Expected UnsupportedOperationException for executeTrain");
        } catch (UnsupportedOperationException e) {
            assertEquals("Unimplemented method 'executeTrain'", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    public void testGameControl_GameEndsWhenBoardIsFull() {
        // Simulate a game that ends in a draw when the board is full without any player winning
        List<String> p1Moves = new ArrayList<>();
        List<String> p2Moves = new ArrayList<>();

        // Assuming a 5x5 board for simplicity
        String[] positions = {
            "A1", "B1", "C1", "D1", "E1",
            "A2", "B2", "C2", "D2", "E2",
            "A3", "B3", "C3", "D3", "E3",
            "A4", "B4", "C4", "D4", "E4",
            "A5", "B5", "C5", "D5", "E5"
        };

        // Alternate moves between players
        for (int i = 0; i < positions.length; i++) {
            if (i % 2 == 0) {
                p1Moves.add(positions[i]);
            } else {
                p2Moves.add(positions[i]);
            }
        }

        MockPlayer p1 = new MockPlayer(1, p1Moves, "User1");
        MockPlayer p2 = new MockPlayer(2, p2Moves, "User2");

        PlayConfig playConfig = new PlayConfig(p1, p2);
        MockMenu mockMenu = new MockMenu(Arrays.asList(playConfig));

        GameControl gameControl = new GameControl(mockMenu);
        gameControl.start();

        // The test passes if the game ends without errors when the board is full
    }
}

