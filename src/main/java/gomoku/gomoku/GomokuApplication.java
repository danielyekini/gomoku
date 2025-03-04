package gomoku.gomoku;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gomoku.gomoku.Controller.GameControl;
import gomoku.gomoku.util.Input;
import gomoku.gomoku.util.Menu;

@SpringBootApplication
public class GomokuApplication {

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

	public static void main(String[] args) {
		// SpringApplication.run(GomokuApplication.class, args);

		GameControl control = new GameControl(new Menu(new Input()));
		printGomoku();
		control.start();
		printExitMessage();
	}

}  