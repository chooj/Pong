package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle {

	private Pane pane;
	private Rectangle paddle;
	private double yi, height;
	
	public Paddle(Pane pane, double x, double y, double radius) {
		this.pane = pane;
		height = radius*16;
		paddle = new Rectangle(x, y, radius, getHeight());
		paddle.setFill(Color.WHITE);
		yi = y;
	}
	
	public Rectangle getPaddle() {
		return paddle;
	}
	
	public void setY(double newY) {
		getPaddle().setLayoutY(newY - yi);
	}
	
	public double getY() {
		return getPaddle().getLayoutY() + yi;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getMaxHeight() {
		return pane.getBoundsInLocal().getMaxY();
	}
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
}
