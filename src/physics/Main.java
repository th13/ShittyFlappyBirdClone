package physics;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	enum GameState {
		Running, Over, Dead
	}

	private static final String GAME_TITLE = "Physics!";
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1920;
	
	private static final int PIPE_BEGIN_X = 1500;
	private static final int PIPE_OFFSET_EASY = 500; 

	public CircleGuy circleGuy;
	public Ground ground;
	public Pipe pipe, pipe2, pipe3;

	private Image image;
	private Graphics g, second;
	private GameState state = GameState.Running;

	@Override
	public void init() {
		super.init();
		setSize(HEIGHT, WIDTH);
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		addKeyListener(this);

		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(GAME_TITLE);

		circleGuy = new CircleGuy(200, 400);
		ground = new Ground(0, 900, 1000, 50);
		pipe = new Pipe(PIPE_BEGIN_X);
		pipe2 = new Pipe(PIPE_BEGIN_X + PIPE_OFFSET_EASY);
		pipe3 = new Pipe(PIPE_BEGIN_X + 2 * PIPE_OFFSET_EASY);
	}

	@Override
	public void start() {
		super.start();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				circleGuy.update();
				pipe.update();
				pipe2.update();
				pipe3.update();

				if (ground.checkForCollision(circleGuy)) {
					state = GameState.Dead;
				}

				repaint();

				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);

		// Draw CircleGuy
		g2d.fill(circleGuy.circle);

		// Draw ground
		g2d.fill(ground.getRect());
		
		// Draw pipe
		g2d.fill(pipe.getTopRect());
		g2d.fill(pipe.getBotRect());
		
		// Draw second pipe
		g2d.fill(pipe2.getTopRect());
		g2d.fill(pipe2.getBotRect());
		
		// Draw third pipe
		g2d.fill(pipe3.getTopRect());
		g2d.fill(pipe3.getBotRect());

		if (state == GameState.Dead) {
			Font font = new Font(null, Font.BOLD, 30);
			g.setFont(font);
			g.drawString("Dead", 400, 400);
			g.drawString("Press any key to restart", 400, 435);
		}

		// } else if (state == GameState.Dead) {
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.setColor(Color.WHITE);
		// g.drawString("Dead", 360, 240);
		// }
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (state == GameState.Running) {
			switch (ke.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				circleGuy.jump();
				break;
			case KeyEvent.VK_RIGHT:
				circleGuy.moveRight();
				break;
			case KeyEvent.VK_LEFT:
				circleGuy.moveLeft();
				break;
			}
		}
		else {
			reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_LEFT:
			circleGuy.stopMovingX();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}
	
	private void reset() {
		state = GameState.Running;
		circleGuy = new CircleGuy(200, 400);
		pipe = new Pipe(PIPE_BEGIN_X);
		pipe2 = new Pipe(PIPE_BEGIN_X + PIPE_OFFSET_EASY);
		pipe3 = new Pipe(PIPE_BEGIN_X + 2 * PIPE_OFFSET_EASY);
	}
}
