package gomoku.gomoku.Model;

import gomoku.gomoku.util.Input;

public class User {

    Input in;

    public User() {
        in = new Input();
    }
    
    public String play() {
        String userInput = in.String("Place your piece: ");
        while (!isValid(userInput)) {
            System.out.println("\nInvalid Input!");
            userInput = in.String("Input a letter from A - O followed by a number from 1 - 15 (e.g. F8): ");
        }

        
        return userInput;
    }

    public boolean isValid(String input) {
        if (input.length() == 2 || input.length() == 3) {
            if (Character.isLetter(input.charAt(0))) {
                if(Character.isDigit(input.charAt(1))) {
                    if (input.length() == 3 && !Character.isDigit(input.charAt(2))) {
                        return false;
                    }
                    return true;
                }
            }
        }

        return false;
    }
}
