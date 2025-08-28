package gomoku.gomoku.util;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.Services.ProximityService;
import gomoku.gomoku.util.configure.*;
import gomoku.gomoku.util.enums.*;

public class Menu {
    Input in;
    
    public Menu() {
        this.in = new Input();
    }

    private int menuOptions(MenuType menuType) {
        int numOptions = 0;
        System.out.println("\n");

        // Present user with menu options
        switch (menuType) {
            case MAIN:

                for (MainOption option : MainOption.values()) {
                    if (option.value() != 0) {
                        System.out.println(option.value() + ". " + option.name());
                    }
                }
                numOptions = MainOption.values().length-1;
                break;

            case PLAY:

                for (PlayOption option : PlayOption.values()) {
                    System.out.println(option.value() + ". " + option.name());
                }
                numOptions = MainOption.values().length;
                break;
                
            case CPU:

                System.out.println("Select CPU difficulty");
                for (CpuOption option : CpuOption.values()) {
                    System.out.println(option.value() + ". " + option.name());
                }
                numOptions = MainOption.values().length;
                break;

            case PLAYER:

                System.out.println("Do you want to be player 1 or player 2?\n");
                for (PlayerOption option : PlayerOption.values()) {
                    System.out.println(option.value() + ". " + option.name());
                }
                numOptions = MainOption.values().length;
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
                default:
                    System.out.println("\nInvalid input! Try again.\n");
            }
        }

        return gameConfig;
    }

    private GameConfig configurePlay() {
        PlayOption option = PlayOption.fromInt(menuOptions(MenuType.PLAY));
        Player player1 = new User();
        Player player2 = null;

        switch (option) {
            case USER_VS_USER:
                player2 = new User();
                break;
            case USER_VS_CPU: 
                player2 = selectCPUPlayer();
                
                switch (playerTurn()) {
                    case 1:
                        player1.number = 1;
                        player2.number = 2;
                        break;
                    case 2:
                        Player p = player2;
                        player2 = player1;
                        player1 = p;
                        player1.number = 1;
                        player2.number = 2;
                        break;
                
                    default:
                        return null;
                }
                break;
            case BACK:
                return null;
        }

        return new PlayConfig(player1, player2);
    }

    private GameConfig configureSimulate() {
        return new SimulateConfig();
        // System.out.println("Do you want to be player 1 or player 2?\n");
    }

    private Player selectCPUPlayer() {
        CpuOption option = CpuOption.fromInt(menuOptions(MenuType.CPU));

        switch (option) {
            case RANDOM:
                return new CPURandom();
            case PROXIMITY:
                return new CPUProximity(new ProximityService());
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
