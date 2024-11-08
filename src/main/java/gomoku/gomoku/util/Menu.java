package gomoku.gomoku.util;

import java.util.ArrayList;

import gomoku.gomoku.Model.*;
import gomoku.gomoku.Model.CPUPlayers.*;
import gomoku.gomoku.Services.ProximityService;

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

    private int menuOptions(String menuType) {
        int numOptions = 0;
        System.out.println("\n");
        switch (menuType) {
            case "main":
                System.out.println("1. Play");
                System.out.println("2. Simulate");
                System.out.println("3. Train");
                System.out.println("4. Exit");
                numOptions = 4;
                break;
            case "play":
                System.out.println("1. User vs User");
                System.out.println("2. User vs CPU");
                System.out.println("3. Back");
                numOptions = 3;
                break;
            case "cpu":
                System.out.println("Select CPU difficulty");
                System.out.println("1. Random");
                System.out.println("2. Proximity");
                System.out.println("3. Back");
                numOptions = 3;
                break;
            case "player":
                System.out.println("Do you want to be player 1 or player 2?\n");
                System.out.println("1. Player 1");
                System.out.println("2. Player 2");
                System.out.println("3. Back");
                numOptions = 3;
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
        int state = 0;
        boolean exit = false;

        while (!exit) {
            switch (state) {
                case 0:
                    state = menuOptions("main");
                    break;
                case 1:
                    gameRules = new ArrayList<>();

                    if (play(gameRules)) {
                        return gameRules;
                    }

                    state = 0;
                    break;
                case 2:
                    gameRules = new ArrayList<>();

                    if (simulate(gameRules)) {
                        return gameRules;
                    }

                    state = 0;
                    break;
                case 3:
                    System.out.println("\nTO BE IMPLEMENTED");
                    state = 0;
                    break;
                case 4:
                    exit = true;
                default:
                    System.out.println("\nInvalid input! Try again.\n");
            }
        }

        return gameRules;
    }

    private boolean play(ArrayList<Type<?>> gameRules) {
        int option = menuOptions("play");

        if (option < 3) {
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
        int option = menuOptions("cpu");

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
        int option = menuOptions("player");

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
