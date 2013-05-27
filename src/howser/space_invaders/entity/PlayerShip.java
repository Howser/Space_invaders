package howser.space_invaders.entity;

import howser.space_invaders.InputHandler;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerShip extends Ship {

	private InputHandler input;
	private float speed;
	private ArrayList<ShotEntity> playerShots;
	public int lives = 3;
	public boolean isHit = false;
	private final int HIT_TIMER_LIMIT = 120;
	private int hitTimer = 0;
	private boolean dead = false;
	private Weapon weapon;

	public PlayerShip(Sprite sprite, float x, float y, float speed,
			InputHandler input, SpriteAnimation explosion, Weapon weapon) {
		super(sprite, x, y, explosion);
		this.input = input;
		this.speed = speed;
		input.addKeyListen(KeyEvent.VK_LEFT);
		input.addKeyListen(KeyEvent.VK_RIGHT);
		input.addKeyListen(KeyEvent.VK_SPACE);
		sprite.setTint(Colour.RED);
		this.weapon = weapon;
	}

	public void setLists(ArrayList<ShotEntity> playerShots) {
		this.playerShots = playerShots;
	}

	public void tick() {
		if (!dead) {
			if (!isHit) {
				if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
					Move(-speed, 0);
				}
				if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
					Move(speed, 0);
				}
				if (input.isKeyPressed(KeyEvent.VK_SPACE)) {
					shoot();
				}
			} else {
				hitTimer++;
				if (hitTimer > HIT_TIMER_LIMIT) {
					isHit = false;
				}
			}
			weapon.tick();
		} else {
			explosion.tick();
			this.Move(0, 1);
		}
	}

	public void render(Frame frame) {
		if (!dead) {
			if ((!isHit) || (isHit && hitTimer % 10 > 5)) {
				frame.renderToFrame(sprite.getPixels(), (int) x, (int) y,
						width, height);
			}
		} else if (explosion.isPlaying()) {
			explosion.render(frame, (int) x, (int) y, 1);
		}
	}

	public void shoot() {
		ShotEntity[] shots = weapon.fire(x + 7, y);
		if (shots != null) {
			for (ShotEntity e : shots) {
				playerShots.add(e);
			}
		}
	}

	public void hit() {
		if (!isHit) {
			lives--;
			isHit = true;
			hitTimer = 0;
		}
		if (lives == 0) {
			die();
		}
	}

	public void die() {
		dead = true;
		this.explosion.play();
	}
}
