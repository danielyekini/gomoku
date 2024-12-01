package gomoku.gomoku;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.util.Input;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.configure.*;

import java.util.*;

public class MenuTest {

    // MockInput class to simulate user inputs
    public class MockInput extends Input {
        private Queue<Integer> inputs;

        public MockInput(List<Integer> inputs) {
            this.inputs = new LinkedList<>(inputs);
        }

        @Override
        public int Integer(String prompt) {
            if (inputs.isEmpty()) {
                throw new NoSuchElementException("No more inputs");
            }
            return inputs.poll();
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_PlayUserVsUser() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,1  // Play menu: 1. USER_VS_USER
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNotNull(config, "Config should not be null");
        assertTrue(config instanceof PlayConfig, "Config should be instance of PlayConfig");
        PlayConfig playConfig = (PlayConfig) config;
        assertTrue(playConfig.getPlayer1() instanceof User, "Player1 should be instance of User");
        assertTrue(playConfig.getPlayer2() instanceof User, "Player2 should be instance of User");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_PlayUserVsCPU_SelectCPUPlayer() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,2  // Play menu: 2. USER_VS_CPU
            ,1  // CPU menu: 1. RANDOM
            ,1  // Player menu: 1. PLAYER_1
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNotNull(config, "Config should not be null");
        assertTrue(config instanceof PlayConfig, "Config should be instance of PlayConfig");
        PlayConfig playConfig = (PlayConfig) config;
        assertTrue(playConfig.getPlayer1() instanceof User, "Player1 should be instance of User");
        assertTrue(playConfig.getPlayer2() instanceof CPURandom, "Player2 should be instance of CPURandom");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_Simulate() {
        List<Integer> inputs = Arrays.asList(
            2  // Main menu: 2. SIMULATE
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNotNull(config, "Config should not be null");
        assertTrue(config instanceof SimulateConfig, "Config should be instance of SimulateConfig");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_InvalidOption_MainMenu() {
        List<Integer> inputs = Arrays.asList(
            5  // Invalid option
            ,1  // Main menu: 1. PLAY
            ,1  // Play menu: 1. USER_VS_USER
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNotNull(config, "Config should not be null after invalid input");
        assertTrue(config instanceof PlayConfig,"Config should be instance of PlayConfig");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_BackFromPlayMenu() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,3  // Play menu: 3. BACK
            ,4  // Main menu: 4. EXIT
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNull(config, "Config should be null after exiting");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_InvalidInputsThenValid() {
        List<Integer> inputs = Arrays.asList(
            99  // Invalid main menu option
            ,-1  // Invalid main menu option
            ,1   // Main menu: 1. PLAY
            ,2   // Play menu: 2. USER_VS_CPU
            ,4   // Invalid CPU option
            ,1   // CPU menu: 1. RANDOM
            ,5   // Invalid player option
            ,1   // Player menu: 1. PLAYER_1
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNotNull(config, "Config should not be null after invalid inputs");
        assertTrue(config instanceof PlayConfig, "Config should be instance of PlayConfig");
        PlayConfig playConfig = (PlayConfig) config;
        assertTrue(playConfig.getPlayer1() instanceof User, "Player1 should be instance of User");
        assertTrue(playConfig.getPlayer2() instanceof CPURandom, "Player2 should be instance of CPURandom");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_PlayUserVsCPU_BackFromCPUMenu() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,2  // Play menu: 2. USER_VS_CPU
            ,3  // CPU menu: 3. BACK
            ,3  // Play menu: 3. BACK
            ,4  // Main menu: 4. EXIT
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNull(config, "Config should be null after exiting");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_PlayerTurnSelection() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,2  // Play menu: 2. USER_VS_CPU
            ,1  // CPU menu: 1. RANDOM
            ,2  // Player menu: 2. PLAYER_2
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        PlayConfig config = (PlayConfig) menu.getConfig();

        assertNotNull(config, "Config should not be null");
        assertTrue(config.getPlayer1() instanceof CPURandom, "Player1 should be instance of CPURandom");
        assertTrue(config.getPlayer2() instanceof User, "Player2 should be instance of User");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_InvalidPlayerTurnThenValid() {
        List<Integer> inputs = Arrays.asList(
            1  // Main menu: 1. PLAY
            ,2  // Play menu: 2. USER_VS_CPU
            ,1  // CPU menu: 1. RANDOM
            ,4  // Invalid player option
            ,2  // Player menu: 2. PLAYER_2
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        PlayConfig config = (PlayConfig) menu.getConfig();

        assertNotNull(config, "Config should not be null after invalid player turn");
        assertTrue(config.getPlayer1() instanceof CPURandom, "Player1 should be instance of CPURandom");
        assertTrue(config.getPlayer2() instanceof User, "Player2 should be instance of User");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_TrainOptionNotImplemented() {
        List<Integer> inputs = Arrays.asList(
            3  // Main menu: 3. TRAIN
            ,4  // Main menu: 4. EXIT
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNull(config, "Config should be null after exiting");
    }

    @org.junit.jupiter.api.Test
    public void testGetConfig_ExitImmediately() {
        List<Integer> inputs = Arrays.asList(
            4  // Main menu: 4. EXIT
        );
        MockInput mockInput = new MockInput(inputs);
        Menu menu = new Menu(mockInput);
        GameConfig config = menu.getConfig();

        assertNull(config, "Config should be null when exiting immediately");
    }
}


