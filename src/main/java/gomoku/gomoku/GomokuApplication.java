package gomoku.gomoku;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gomoku.gomoku.Controller.GameControl;
import gomoku.gomoku.util.Input;
import gomoku.gomoku.util.Menu;

@SpringBootApplication
public class GomokuApplication {

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("--cli")) {
            runCli();
        } else {
            SpringApplication.run(GomokuApplication.class, args);
        }
    }

    private static void runCli() {
        printGomoku();
        Menu menu = new Menu(new Input());
        GameControl game = new GameControl(menu);
        game.start();
        printExitMessage();
    }

	private static void printGomoku() {
		String gomokuAscii = """
			   ██████╗  ██████╗ ███╗   ███╗ ██████╗ ██╗  ██╗██╗   ██╗
		   ██╔════╝ ██╔═══██╗████╗ ████║██╔═══██╗██║ ██║ ██║   ██║
		   ██║  ███╗██║   ██║██╔████╔██║██║   ██║████║   ██║   ██║
		   ██║   ██║██║   ██║██║╚██╔╝██║██║   ██║██╔═██║ ██║   ██║
		   ╚██████╔╝╚██████╔╝██║ ╚═╝ ██║╚██████╔╝██║  ██║╚██████╔╝
			   ╚═════╝  ╚═════╝ ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝
	 	""";

		System.out.println("\n" + gomokuAscii);
	}

	private static void printExitMessage() {
	String exitMessage = """
  _____ _              _        ___          ___ _           _           _ 
 |_   _| |_  __ _ _ _ | |_____ | _____ _ _  | _ | |__ _ _  _(_)_ _  __ _| |
   | | | ' \\/ _` | ' \\| / (_-< | _/ _ | '_| |  _| / _` | || | | ' \\/ _` |_|
   |_| |_||_\\__,_|_||_|_\\_/__/ |_|\\___|_|   |_| |_\\__,_|\\_, |_|_||_\\__, (_)
                                                        |__/       |___/   
""";

		System.out.println("\n" + exitMessage);
	}
}