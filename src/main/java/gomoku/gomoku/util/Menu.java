package gomoku.gomoku.util;

import java.util.ArrayList;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.Services.ProximityService;
import gomoku.gomoku.util.enums.*;

public class Menu {
    Input in;
    
    public Menu() {
        this.in = new Input();
    }

    private class Type<T> {
        private T obj;

        public Type(T obj) {
            this.obj = obj;
        }

        public T get() {
            return obj;
        }
    }

    private int menuOptions(MenuType menuType) {
        int numOptions = 0;
        System.out.println("\n");

        switch (menuType) {
            case MAIN:
                for (MainOption option : MainOption.values()) {
                    System.out.println(option.value() + ". " + option.name());
                }
                numOptions = MainOption.values().length;
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

        int option = in.Integer("\nSelect an option: ");

        while (option < 1 || option > numOptions) {
            System.out.println("\nOption invalid! Try again.");
            option = in.Integer("\nSelect an option: ");
        }

        return option;
    }

    public ArrayList<Type<?>> open() {
        ArrayList<Type<?>> gameRules = null;
        MainOption state = MainOption.MAIN;
        boolean exit = false;

        while (!exit) {
            switch (state) {
                case MAIN:
                    int option = menuOptions(MenuType.MAIN);
                    state = MainOption.fromInt(option);
                    break;
                case PLAY:
                    gameRules = new ArrayList<>();

                    if (play(gameRules)) {
                        return gameRules;
                    }

                    state = MainOption.MAIN;
                    break;
                case SIMULATE:
                    gameRules = new ArrayList<>();

                    if (simulate(gameRules)) {
                        return gameRules;
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

        return gameRules;
    }

    private boolean play(ArrayList<Type<?>> gameRules) {
        int option = menuOptions(MenuType.PLAY);

        if (option == 1 || option == 2) {
                gameRules.add(new Type<Player>(new User()));
            if (option == 1) {
                gameRules.add(new Type<Player>(new User()));
            } else {
                if (cpuDifficulty(gameRules) && playerTurn(gameRules)) {
                    return true;
                }

                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean simulate(ArrayList<Type<?>> gameRules) {
        return false;
        // System.out.println("Do you want to be player 1 or player 2?\n");
    }

    private boolean cpuDifficulty(ArrayList<Type<?>> gameRules) {
        int option = menuOptions(MenuType.CPU);

        switch (option) {
            case 1:
                gameRules.add(new Type<Player>(new CPURandom()));
                return true;
            case 2:
                gameRules.add(new Type<Player>(new CPUProximity(new ProximityService())));
                return true;
        }

        return false;
    }

    private boolean playerTurn(ArrayList<Type<?>> gameRules) {
        int option = menuOptions(MenuType.PLAYER);

        switch (option) {
            case 1:
                gameRules.add(1, new Type<Integer>(1));
                return true;
            case 2:
                gameRules.add(1, new Type<Integer>(2));
                return true;
        }

        return false;

    }

}
