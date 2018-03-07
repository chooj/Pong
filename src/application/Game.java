package application;
	
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
	
	private static int width, height, rad, userScore, cpuScore;
	private static Ball ball;
	private static User user;
	private static CPU cpu;
    private static Pause pObj;
    private static Rectangle shade;
	
	public Game() {
		width = 800;
		height = 600;
		rad = 8;
		userScore = 0;
		cpuScore = 0;
		ball = new Ball(width/2, height/2, 8, (Math.random()-0.5)*8, rad);
		user = new User(null, 0, height/2 - rad*8, rad);
		cpu = new CPU(null, width - rad, height/2 - rad*8, rad);
		pObj = new Pause();
		shade = new Rectangle(0, 0, width, height);
		shade.setFill(Color.WHITE);
		shade.setOpacity(0);
	}
	
	public void run(Stage primaryStage) {
		// set the stage
		Pane pane = new Pane();
		Scene scene = new Scene(pane, width, height, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

		// physical components
        user.setPane(pane);
        cpu.setPane(pane);
		pane.getChildren().addAll(ball.draw(this, pane, user, cpu, pObj), cpu.draw(ball, pObj), user.draw(scene, pObj));
		
		// stuff
		pane.getChildren().addAll(centerLine(), shade);
		pauseGame(scene, pane);
		
		// scores
		ScoreText userText = new ScoreText(width/4 - 95, 405, String.valueOf(userScore));
		ScoreText cpuText = new ScoreText(3*width/4 - 95, 405, String.valueOf(cpuScore));
		pane.getChildren().addAll(userText.draw(this, "user"), cpuText.draw(this, "cpu"));
		
	}
	
	public void reset() {
		user.setStill();
		user.setY(height/2 - rad*8);
		cpu.setY(height/2 - rad*8);
		ball.setLoc(width/2, height/2);
		ball.setSpeed(8, (Math.random()-0.5)*8);
		pObj.setStop();
	}
	
	public int getUserScore() {
		return userScore;
	}
	
	public int getCpuScore() {
		return cpuScore;
	}
	
	public void addUserScore() {
		userScore++;
	}
	
	public void addCpuScore() {
		cpuScore++;
	}
	
	public Line centerLine() {
		Line centerLine = new Line(width/2, 0, width/2, height);
		centerLine.setStroke(Color.WHITE);
		return centerLine;
	}
	
	public void pauseGame(Scene scene, Pane pane) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (!pObj.paused()) {
					pause(pane);
				} else if (pObj.getPauseGame()) {
					unpause(pane);
				}
			}
		});
	}
	
	public void pause(Pane pane) {
		pObj.setPauseGame(true);
		shade.setOpacity(0.5);
	}
	
	public void unpause(Pane pane) {
		pObj.setPauseGame(false);
		shade.setOpacity(0);
	}
	
}
