package application;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class ScoreText {
	
	private Text text;
	
	public ScoreText(double x, double y, String str) {
		text = new Text(x, y, str);
		text.setFill(Color.WHITE);
		text.setFont(new Font("Verdana", 300));
		text.setOpacity(0.75);
	}
	
	public Text draw(Game game, String player) {
		
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
 			@Override
	        public void handle(ActionEvent t) {
 				if (player.equals("user")) {
 	 				text.setText(String.valueOf(game.getUserScore()));
 				} else if (player.equals("cpu")) {
 	 				text.setText(String.valueOf(game.getCpuScore()));
 				}
	        }
 		};
 		
 		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), eh));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		return text;
	}
	
}
