package gomoku.gomoku.util;

import gomoku.gomoku.util.configure.GameConfig;
import gomoku.gomoku.util.configure.PlayConfig;
import gomoku.gomoku.util.configure.SimulateConfig;
import gomoku.gomoku.util.enums.CpuOption;
import gomoku.gomoku.util.enums.MainOption;
import gomoku.gomoku.util.enums.MenuType;
import gomoku.gomoku.util.enums.PlayAgainOption;
import gomoku.gomoku.util.enums.PlayOption;
import gomoku.gomoku.util.enums.PlayerOption;
import gomoku.gomoku.util.enums.PlayerType;

public class Menu {
    Input in;
    
    public Menu(Input in) {
        this.in = in;
    }

    public GameConfig getConfig() {
        GameConfig gameConfig = null;
        MainOption state = MainOption.MAIN;
        boolean exit = false;

        while (!exit) {
            switch (state) {
                case MAIN:
                    int option = menuOptions(MenuType.MAIN);
                    state = MainOption.fromInt(option);
                    break;
                case PLAY:
                    gameConfig = configurePlay();

                    if (gameConfig != null) {
                        return gameConfig;
                    }

                    state = MainOption.MAIN;
                    break;
                case SIMULATE:
                    // System.out.println("\nTO BE IMPLEMENTED");
                    gameConfig = configureSimulate();

                    if (gameConfig != null) {
                        return gameConfig;
                    }

                    state = MainOption.MAIN;
                    break;
                case TRAIN:
                    System.out.println("\nTO BE IMPLEMENTED");
                    state = MainOption.MAIN;
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid input! Try again.\n");
            }
        }

        return gameConfig;
    }
    
    public GameConfig playAgain() {
        GameConfig gameConfig = null;
        PlayAgainOption state = PlayAgainOption.MENU;
        boolean exit = false;

        while (!exit) {
            switch (state) {
                case YES:

                    gameConfig = getConfig(MainOption.PLAY);

                    if (gameConfig != null) {
                        return gameConfig;
                    }

                    state = PlayAgainOption.MENU;
                    break;

                case NO:

                    exit = true;
                    break;

                case MENU:
                    
                    state = PlayAgainOption.fromInt(menuOptions(MenuType.PLAYAGAIN));
            }
        }

        return gameConfig;
    }

    private int menuOptions(MenuType menuType) {
        int numOptions = 0;
        System.out.println("\n");

        // Present user with menu options
        switch (menuType) {
            case MAIN:

                System.out.println("> MAIN\n");
                for (MainOption option : MainOption.values()) {
                    if (option.value() != 0) {
                        System.out.println("     > [" + option.value() + "] " + option.name());
                    }
                }
                numOptions = MainOption.values().length-1;
                break;

            case PLAY:

                System.out.println("> SELECT PLAYERS\n");
                for (PlayOption option : PlayOption.values()) {
                    System.out.println("     > [" + option.value() + "] " + option.name());
                }
                numOptions = PlayOption.values().length;
                break;
                
            case CPU:

                System.out.println("> SELECT CPU DIFFICULTY\n");
                for (CpuOption option : CpuOption.values()) {
                    System.out.println("     > [" + option.value() + "] " + option.name());
                }
                numOptions = CpuOption.values().length;
                break;

            case PLAYER:

                System.out.println("> DO YOU WANT TO BE PLAYER 1 OR PLAYER 2?\n");
                for (PlayerOption option : PlayerOption.values()) {
                    System.out.println("     > [" + option.value() + "] " + option.name());
                }
                numOptions = PlayerOption.values().length;
                break;

            case PLAYAGAIN:
                System.out.println("> PLAY AGAIN?:\n");
                for (PlayAgainOption option : PlayAgainOption.values()) {
                    if (option.value() != 0) {
                        System.out.println("     > [" + option.value() + "] " + option.name());
                    }
                }
                numOptions = PlayAgainOption.values().length-1;
                break;

            default:
        }

        // Take user input
        int option = in.Integer("\nSelect an option: ");

        // Validate user input
        while (option < 1 || option > numOptions) {
            System.out.println("\nOption invalid! Try again.");
            option = in.Integer("\nSelect an option: ");
        }

        // Return chosen option
        return option;
    }

    private GameConfig getConfig(MainOption menu) {
        GameConfig gameConfig = null;
        MainOption state = menu;
        switch (state) {
            case PLAY:
                gameConfig = configurePlay();
                break;
            case SIMULATE:
                // System.out.println("\nTO BE IMPLEMENTED");
                gameConfig = configureSimulate();
                break;
            case TRAIN:
                System.out.println("\nTO BE IMPLEMENTED");
                break;
            default:
                System.out.println("\nInvalid input! Try again.\n");
        }

        return gameConfig;
    }

    private GameConfig configurePlay() {
        PlayOption option = PlayOption.fromInt(menuOptions(MenuType.PLAY));
        PlayerType player1 = PlayerType.USER;
        PlayerType player2 = null;

        switch (option) {
            case USER_VS_USER:
                player2 = PlayerType.USER;
                break;
            case USER_VS_CPU: 
                player2 = selectCPUPlayer();

                if (player2 == null) {
                    return null;
                }

                int playerTurn = playerTurn();
                
                if (playerTurn == 2) {
                    PlayerType temp = player1;
                    player1 = player2;
                    player2 = temp;
                    break;
                } else if (playerTurn == -1) return null;
                break;
            case HOTKEY:
                player2 = player1;
                player1 = PlayerType.PROXIMITY;
                return new PlayConfig(player1, player2);
            case BACK:
                return null;
        }

        return new PlayConfig(player1, player2);
    }

    private GameConfig configureSimulate() {
        return new SimulateConfig();
        // System.out.println("Do you want to be player 1 or player 2?\n");
    }

    private PlayerType selectCPUPlayer() {
        CpuOption option = CpuOption.fromInt(menuOptions(MenuType.CPU));

        switch (option) {
            case RANDOM:
                return PlayerType.RANDOM;
            case PROXIMITY:
                return PlayerType.PROXIMITY;
            case BACK:
                return null;
        }

        return null;
    }

    private int playerTurn() {
        PlayerOption option = PlayerOption.fromInt(menuOptions(MenuType.PLAYER));

        switch (option) {
            case PLAYER_1:
                return 1;
            case PLAYER_2:
                return 2;
            default:
                return -1;
        }
    }

}
