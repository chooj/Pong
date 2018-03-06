package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Game game = new Game();
	
	@Override
	public void start(Stage primaryStage) {
		game.run(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
