package gomoku.gomoku.Model.Players;

import gomoku.gomoku.Model.Board;
import gomoku.gomoku.util.Input;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.enums.PlayState;

public class User extends Player {

    Input input;

    public User() {
        this.input = new Input();
    }

    @Override
    public PlayerResponse play(Board state) {
        System.out.println("Type 'main' to return to menu, or 'new game' to start a new game.");
        String userInput = input.String("Place your piece: ");
        while (!isValid(userInput, state)) {
            System.out.println("\nInvalid Input!");
            userInput = input.String("Input a letter from A - O followed by a number from 1 - 15 (e.g. F8): ");
        }
        
        if (userInput.equals("main")) {
            return new PlayerResponse(PlayState.MENU);
        }  else if (userInput.equals("new")) {
            return new PlayerResponse(PlayState.NEWGAME);
        }

        return new PlayerResponse(PlayState.TRYNEXTTURN, userInput);
    }

    public boolean isValid(String userInput, Board state) {
        if (userInput.equals("main")) {
            return true;
        } else if (userInput.equals("new")) {
            return true;
        }

        if (userInput.length() == 2 || userInput.length() == 3) {
            if (Character.isLetter(userInput.charAt(0))) {
                if(Character.isDigit(userInput.charAt(1))) {
                    if (userInput.length() == 3 && !Character.isDigit(userInput.charAt(2))) {
                        return false;
                    } else if (state.posAvailable(userInput)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
