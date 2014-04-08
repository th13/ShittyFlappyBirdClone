package physics;

import java.awt.geom.Ellipse2D;

public class CircleGuy {
	private static final double RADIUS = 50.0;
	private static final int MOVE_SPEED = 5;
	private static final int JUMP_SPEED = -15;

	public Ellipse2D circle;

	private int x, y;
	private int speedX, speedY;
	
	public CircleGuy(int x, int y) {
		this.x = x;
		this.y = y;
		speedX = 0;
		speedY = 0;
		circle = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
	}
	
	public void update() {
		x += speedX;
		y += speedY;
		circle = new Ellipse2D.Double(x, y, 50, 50);
		speedY++;
	}

	public void jump() {
		speedY = JUMP_SPEED;
	}

	public void moveRight() {
		speedX = MOVE_SPEED;
		x += speedX;
	}

	public void stopMovingX() {
		speedX = 0;
	}

	public void moveLeft() {
		speedX = -MOVE_SPEED;
		x += speedX;
	}

	public Ellipse2D getCircle() {
		return circle;
	}

	public void setCircle(Ellipse2D circle) {
		this.circle = circle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
}
