package gomoku.gomoku.util;

import java.util.Scanner;

public class Input {

    private Scanner scan;

    public Input() {
        this.scan = new Scanner(System.in);
    }

    public String String(String inputString){
        System.out.print(inputString);
        return scan.nextLine();
    }

    public char Character(String inputString){
        System.out.print(inputString);
        return scan.next().charAt(0);
    }

    public int Integer(String inputString){
        System.out.print(inputString);
        return scan.nextInt();
    }

    public double Double(String inputString){
        System.out.print(inputString);
        return scan.nextDouble();
    }
}
