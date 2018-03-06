package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CPU extends Paddle {
	
	private double yi, lag = 10, maxSpeed = 8;
	
	public CPU(Pane pane, double x, double y, double radius) {
		super(pane, x, y, radius);
		yi = y;
	}
	
	public Rectangle draw(Ball ball, Pause pObj) {
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (!pObj.paused()) {
					double currentY = getPaddle().getLayoutY();
					double goalY;
					double div;
					if (ball.getX() >= 400) {
						goalY = ball.getY() - yi - getHeight()/2;
						div = lag;
					} else {
						goalY = 0;
						div = lag*2;
					}
					double diff = (goalY-currentY)/div;
					if ((currentY - getHeight()/2 >= -getMaxHeight()/2 && diff < 0) ||
							(currentY + getHeight()/2 <= getMaxHeight()/2 && diff > 0)) {
						double add;
						if (Math.abs(diff) < maxSpeed) {
							add = diff;
						} else {
							add = (diff/Math.abs(diff))*maxSpeed;
						}
						getPaddle().setLayoutY(currentY + add);
					}
	 			}
			}
 		};
 		
 		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), eh));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		return getPaddle();
	}
}
