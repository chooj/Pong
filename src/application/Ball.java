package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball {
	
	private double xi, yi, xs, ys, radius;
	private Circle ball;
	private int userScore = 0, cpuScore = 0;
	
	public Ball(double x, double y, double xs, double ys, double radius) {
		this.xi = x;
		this.yi = y;
		this.xs = xs;
		this.ys = ys;
		this.radius = radius;
		ball = new Circle(x, y, radius, Color.WHITE);
	}
	
	public void setSpeed(double xs, double ys) {
		this.xs = xs;
		this.ys = ys;
	}
	
	public double getX() {
		return ball.getLayoutX() + xi;
	}
	
	public double getY() {
		return ball.getLayoutY() + yi;
	}
	
	public void setLoc(double x, double y) {
		ball.setLayoutX(x - xi);
		ball.setLayoutY(y - yi);
	}
	
	public double locOnPaddle(Paddle paddle) {
		return getY() - (paddle.getY() + paddle.getHeight()/2);
	}
	
	public boolean inXRange(Bounds bounds, String player) {
		if (player.equals("user")) {
			return getX() - radius + xs < bounds.getMinX() + radius && getX() + radius - xs > bounds.getMinX();
		} else {
			return getX() + radius + xs > bounds.getMaxX() - radius && getX() - radius + xs < bounds.getMaxX();
		}
	}
	
	public boolean inYRange(Paddle paddle) {
		return Math.abs(locOnPaddle(paddle)) < paddle.getHeight()/2 + radius;
	}
	
	public int getUserScore() {
		return userScore;
	}
	
	public int getCpuScore() {
		return cpuScore;
	}
	
	public Circle draw(Game game, Pane pane, Paddle user, Paddle cpu, Pause pObj) {
		
		Bounds bounds = pane.getBoundsInParent();
 		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
 			@Override
	        public void handle(ActionEvent t) {
		        //move the ball
 				if (!pObj.paused()) {
 					ball.setLayoutX(ball.getLayoutX() + xs);
 					ball.setLayoutY(ball.getLayoutY() + ys);
 				}
	            // bounciness
	            if ((inXRange(bounds, "cpu") && inYRange(cpu)) ||
	            		(inXRange(bounds, "user") && inYRange(user))) {
	            		xs = -xs;
	            		// modify spin
	            		Paddle paddle = (ball.getLayoutX() < 0) ? user : cpu;
	            		ys = 8*locOnPaddle(paddle)/(paddle.getHeight()/2);
	            }
	            if (getY() + radius >= bounds.getMaxY() || getY() - radius < bounds.getMinY()) {
	            		ys = -ys;
	            }
	            if (getX() + radius - xs <= bounds.getMinX()) {
		            // cpu wins
		            	game.addCpuScore();
		            	game.reset();
	            } else if (getX() - radius + xs >= bounds.getMaxX()) {
		            	// user wins
		            	game.addUserScore();
		            	game.reset();
	            }
	        }
 		};
 		
 		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), eh));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		
		return ball;
	}
	
}
