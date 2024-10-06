package gomoku.gomoku;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gomoku.gomoku.Controller.GameControl;

@SpringBootApplication
public class GomokuApplication {

	public static void main(String[] args) {
		// SpringApplication.run(GomokuApplication.class, args);

		GameControl control = new GameControl();
		control.start();
	}

}
