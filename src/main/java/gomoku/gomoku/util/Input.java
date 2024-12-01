package gomoku.gomoku.util;

import java.util.Scanner;

public class Input {

    private Scanner scan;

    public Input() {
        this.scan = new Scanner(System.in);
    }

    public String String(String input_string){
        System.out.print(input_string);
        return scan.nextLine();
    }

    public char Character(String input_string){
        System.out.print(input_string);
        return scan.next().charAt(0);
    }

    public int Integer(String input_string){
        while (true) {
            System.out.print(input_string);
            String input = scan.nextLine();
    
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Try again");
            }
        }
    }

    public double Double(String input_string){
        System.out.print(input_string);

        Double input = scan.nextDouble();
        while (!(input instanceof Double)) {
            System.out.println("\nInvalid input! Try again");
            System.out.print("\n"+input_string);
            input = scan.nextDouble();
        }

        return input;
    }
}
