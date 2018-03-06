package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CPU extends Paddle {
	
	private double yi, lag = 11;
	
	public CPU(Pane pane, double x, double y, double radius) {
		super(pane, x, y, radius);
		yi = y;
	}
	
	public Rectangle draw(Ball ball) {
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				double currentY = getPaddle().getLayoutY();
				double goalY = ball.getY() - yi - getHeight()/2;
				double diff = (goalY-currentY)/lag;
				if ((currentY - getHeight()/2 >= -getMaxHeight()/2 && diff < 0) ||
						(currentY + getHeight()/2 <= getMaxHeight()/2 && diff > 0)) {
					getPaddle().setLayoutY(currentY + diff);
				}
 			}
 		};
 		
 		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), eh));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		return getPaddle();
	}
}
