package howser.space_invaders.entity;

import howser.space_invaders.InputHandler;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteSheet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerShip extends Ship {

	private InputHandler input;
	private int speed;
	private Sprite shotSprite;
	private ArrayList<ShotEntity> playerShots;
	private final int SHOT_DELAY = 10;
	private int currentShotDelayTime = 0;

	public PlayerShip(SpriteSheet sheet, int sheetX, int sheetY, int width,
			int height, int x, int y, int speed, InputHandler input,
			Sprite shotSprite) {
		super(sheet, sheetX, sheetY, width, height, x, y);
		this.input = input;
		this.speed = speed;
		input.addKeyListen(KeyEvent.VK_LEFT);
		input.addKeyListen(KeyEvent.VK_RIGHT);
		input.addKeyListen(KeyEvent.VK_SPACE);
		sprite.setTint(Colour.RED);
		this.shotSprite = shotSprite;
	}

	public void setLists(ArrayList<ShotEntity> playerShots) {
		this.playerShots = playerShots;
	}

	public void tick() {
		currentShotDelayTime++;
		if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
			Move(-speed, 0);
		}
		if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
			Move(speed, 0);
		}
		if (input.isKeyPressed(KeyEvent.VK_SPACE)) {
			if (currentShotDelayTime > SHOT_DELAY) {
				shoot();
				currentShotDelayTime = 0;
			}
		}
	}

	public void render(Frame frame) {
		frame.renderToFrame(sprite.getPixels(), (int) x, (int) y, width, height);
	}

	public void shoot() {
		playerShots.add(new ShotEntity(x + 6, y, shotSprite, 0, -5f));
		System.out.println("SHOOTING");
	}
}
