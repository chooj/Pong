package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class User extends Paddle {

	public User(Pane pane, double x, double y, double radius) {
		super(pane, x, y, radius);
	}
	
	public void moveUp() {
		if (getY() > 0) {
			getPaddle().setLayoutY(getPaddle().getLayoutY() - 24);
		}
	}
	
	public void moveDown(Pane pane) {
		if (getY() + getHeight() < 600) {
			getPaddle().setLayoutY(getPaddle().getLayoutY() + 24);
		}
	}
	
	public Rectangle draw(Scene scene, Pane pane) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				switch(t.getCode()) {
					case UP: moveUp();
						break;
					case DOWN: moveDown(pane);
						break;
					default: break;
				}
			}
		});
		return getPaddle();
	}
}
