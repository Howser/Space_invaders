package howser.space_invaders;

import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.state.ExitState;
import howser.space_invaders.state.GameState;
import howser.space_invaders.state.HighScoreState;
import howser.space_invaders.state.MainMenuState;
import howser.space_invaders.state.StateManager;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 256;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Space invaders";
	private boolean running = false;

	private JFrame jFrame;

	private BufferedImage image;
	private int[] pixels;
	private Frame frame;

	private StateManager stateManager;
	private InputHandler input;

	public Game() {

		frame = new Frame(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		jFrame = new JFrame();

		jFrame.setTitle(NAME);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new BorderLayout());
		
		jFrame.add(this, BorderLayout.CENTER);
		jFrame.pack();

		// jFrame.setIconImage(ICON_IMAGE_SOMETHING);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		this.requestFocus();
	}

	public void run() {
		init();

		int frames = 0;
		int ticks = 0;
		double targetTickTime = 1000000000 / 60;
		double lastUpdateTime = System.nanoTime();
		double lastFPSPrintTime = System.nanoTime();

		while (running) {
			double nowTime = System.nanoTime();
			boolean shouldRender = false;

			while (nowTime - lastUpdateTime >= targetTickTime) {
				ticks++;
				tick();
				lastUpdateTime = System.nanoTime();
				shouldRender = true;
			}

			if (shouldRender) {
				render();
				frames++;
			}

			if (nowTime - lastFPSPrintTime >= 1000000000) {
				System.out.println("Updates: " + ticks + ", Frames: " + frames);
				ticks = 0;
				frames = 0;
				lastFPSPrintTime = System.nanoTime();
			}
		}
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
		System.exit(0);
	}

	public void init() {
		input = new InputHandler();
		this.addKeyListener(input);
		stateManager = new StateManager(this);
		stateManager.addState(new MainMenuState("main_menu_state", stateManager,
				input));
		stateManager.addState(new GameState("game_state", stateManager, input, WIDTH, HEIGHT));
		stateManager.addState(new ExitState("exit_state", stateManager, this));
		stateManager.addState(new HighScoreState("highscore_state", stateManager, input));
		stateManager.changeState("main_menu_state");
	}

	public void tick() {
		stateManager.tick();
	}

	public void render() {
		if (this.jFrame != null) {
			BufferStrategy bs = this.getBufferStrategy();
			if (bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			frame.clear(Colour.BLACK);
			stateManager.render(frame);
			frame.getPixels(pixels);

			Graphics g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			g.dispose();

			bs.show();
		}
	}

	public void addInputHandler(InputHandler input) {
		this.addKeyListener(input);
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
}
