package physics;

import java.awt.Rectangle;

public class Ground {
	private int x, y;
	private int width, height;
	private Rectangle rect;

	public Ground(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x, y, width, height);
	}

	public boolean checkForCollision(CircleGuy circleGuy) {
		if (circleGuy.circle.intersects(rect)) {
			circleGuy.setSpeedY((int) -(circleGuy.getSpeedY() / 1.5));
			circleGuy.setY(y - 47);
			
			return true;
		}
		
		return false;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
}
