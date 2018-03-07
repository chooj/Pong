package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class User extends Paddle {

	private boolean up, down;
	
	public User(Pane pane, double x, double y, double radius) {
		super(pane, x, y, radius);
		up = false;
		down = false;
	}
	
	public void setStill() {
		up = false;
		down = false;
	}
	
	public void movement() {
		if (up && !down && getY() > 0) {
			getPaddle().setLayoutY(getPaddle().getLayoutY() - 5);
		} else if (down && !up && getY() + getHeight() < 600) {
			getPaddle().setLayoutY(getPaddle().getLayoutY() + 5);
		}
	}
	
	public Rectangle draw(Scene scene, Pause pObj) {
		
		// key held down
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (!pObj.paused()) {
					switch(t.getCode()) {
					case UP: up = true;
						break;
					case DOWN: down = true;
						break;
					default: break;
				}
				}
			}
		});
		
		// key lifted up
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (!pObj.paused()) {
					switch(t.getCode()) {
					case UP: up = false;
						break;
					case DOWN: down = false;
						break;
					default: break;
				}
				}
			}
		});
		
		// movement
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (!pObj.paused()) {
					movement();
	 			}
			}
 		};
 		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), eh));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		
		return getPaddle();
	}
}
