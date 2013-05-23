package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

import java.util.Random;

public class EnemyShip extends Ship {

	private Random rand;
	private boolean shootingEnabled;
	public int score;
	public float xSpeed, ySpeed;
	private int screenWidth, screenHeight;
	public boolean dead = false;

	public EnemyShip(Sprite sprite, float x, float y, float xSpeed,
			float ySpeed, int colour, boolean shootingEnabled, int score,
			int screenWidth, int screenHeight, SpriteAnimation explosion) {
		super(sprite, x, y, explosion);
		setSpeed(xSpeed, ySpeed);
		sprite.setTint(colour);
		sprite.mirrorY = true;
		this.shootingEnabled = shootingEnabled;
		this.score = score;
		rand = new Random();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public void tick() {
		Move(xSpeed, ySpeed);
		if (y > screenHeight) {
			this.setForRemoval();
		}
		if (x < 0 || x + width > screenWidth) {
			xSpeed *= -1;
		}
		if (dead){
			explosion.tick();
			if (!explosion.isPlaying()){
				setForRemoval();
			}
		}
	}

	public void render(Frame frame) {
		if (!dead) {
			frame.renderToFrame(sprite, (int) x, (int) y, 1);
		} else {
			explosion.render(frame, (int)x, (int)y, 1);
		}
	}

	public void die() {
		dead = true;
		explosion.play();
	}

	public void setSpeed(float xSpeed, float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
}
