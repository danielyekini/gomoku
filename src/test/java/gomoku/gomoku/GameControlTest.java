package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import gomoku.gomoku.Controller.GameControl;
import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.Players.Player;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.configure.*;

import java.util.*;

import gomoku.gomoku.util.enums.PlayState;

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
        private Queue<PlayerResponse> moves;
        private String name;

        public MockPlayer(int number, List<PlayerResponse> moves, String name) {
            this.number = number;
            this.moves = new LinkedList<>(moves);
            this.name = name;
        }

        @Override
        public PlayerResponse play(Board board) {
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
        List<PlayerResponse> p1Moves = Arrays.asList(
            new PlayerResponse(PlayState.TRYNEXTTURN, "A1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "B1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "C1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "D1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "E1")
        ); // Winning move sequence
        List<PlayerResponse> p2Moves = Arrays.asList(
            new PlayerResponse(PlayState.TRYNEXTTURN, "A2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "B2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "C2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "D2")
        );

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
        List<PlayerResponse> userMoves = Arrays.asList(
            new PlayerResponse(PlayState.TRYNEXTTURN, "A1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "B1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "C1"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "D1")
        );
        List<PlayerResponse> cpuMoves = Arrays.asList(
            new PlayerResponse(PlayState.TRYNEXTTURN, "A2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "B2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "C2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "D2"), 
            new PlayerResponse(PlayState.TRYNEXTTURN, "E2")
        ); // CPU's winning move sequence

        MockPlayer cpu = new MockPlayer(1, cpuMoves, "CPU");
        MockPlayer user = new MockPlayer(2, userMoves, "User");

        PlayConfig playConfig = new PlayConfig(cpu, user);
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
}

