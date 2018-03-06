// FIND ALTERNATIVE TO Thread.sleep()

package application;
	
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Game {
	
	private static int width = 800, height = 600;
	private static int rad = 8;
	private static int userScore = 0, cpuScore = 0;
	private static Ball ball = new Ball(width/2, height/2, 8, (Math.random()-0.5)*8, rad);
	private static User user = new User(null, 0, height/2 - rad*8, rad);
	private static CPU cpu = new CPU(null, width - rad, height/2 - rad*8, rad);
	
	public Game() {
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
		pane.getChildren().addAll(ball.draw(this, pane, user, cpu), cpu.draw(ball), user.draw(scene, pane));
		
		// stuff
		pane.getChildren().addAll(centerLine(), firstTo11());
		
		// scores
		ScoreText userText = new ScoreText(width/4 - 95, 405, String.valueOf(userScore));
		ScoreText cpuText = new ScoreText(3*width/4 - 95, 405, String.valueOf(cpuScore));
		pane.getChildren().addAll(userText.draw(this, "user"), cpuText.draw(this, "cpu"));
		
	}
	
	public void reset() {
		//pause(750);
		user.setY(height/2 - rad*8);
		cpu.setY(height/2 - rad*8);
		ball.setLoc(width/2, height/2);
		ball.setSpeed(8, (Math.random()-0.5)*8);
		// fix what happens after
	}
	
	public void pause(int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	
	public Text firstTo11() {
		Text text = new Text(width/2 - 60, 70, "First to 11");
		text.setFill(Color.WHITE);
		text.setFont(new Font(30));
		return text;
	}
}
