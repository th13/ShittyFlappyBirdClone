package physics;

import java.awt.Rectangle;
import java.util.Random;

public class Pipe {
	private static final int WIDTH = 75;
	private static final int START_X = 1200;
	private int speedX;
	private int startX, x;
	private Rectangle topRect, botRect;

	public Pipe(int x) {
		this.x = startX = x;
		speedX = 3;
		Random r = new Random();
		int next = r.nextInt(350) + 200;
		
		topRect = new Rectangle(x, 0, 75, next);
		botRect = new Rectangle(x, 900 - next, 75, 600 - next);
	}

	public void update() {
		if (x > -WIDTH) {
			x -= speedX;
		} else {
			x = (startX > START_X) ? START_X : startX;
		}
		
		topRect.x = x;
		botRect.x = x;
	}

	public Rectangle getTopRect() {
		return topRect;
	}

	public void setTopRect(Rectangle topRect) {
		this.topRect = topRect;
	}

	public Rectangle getBotRect() {
		return botRect;
	}

	public void setBotRect(Rectangle botRect) {
		this.botRect = botRect;
	}

}
