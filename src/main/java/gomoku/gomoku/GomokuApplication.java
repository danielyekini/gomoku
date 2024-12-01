package gomoku.gomoku;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gomoku.gomoku.Controller.GameControl;
import gomoku.gomoku.util.Input;
import gomoku.gomoku.util.Menu;

@SpringBootApplication
public class GomokuApplication {

	public static void main(String[] args) {
		// SpringApplication.run(GomokuApplication.class, args);

		GameControl control = new GameControl(new Menu(new Input()));
		control.start();
	}

}
