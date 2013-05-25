package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

public class Planet extends Ship {

	private float speed;
	private float scale;
	public boolean planetShard;
	public boolean exploding;
	public boolean childrenCreated = false;
	private int hp;
	private boolean reverseX; // this is retarded i know
	public int score;
	
	public Planet(Sprite sprite, float x, float y, SpriteAnimation explosion,
			float scale, float speed, boolean planetShard) {
		super(sprite, x, y, explosion);
		this.speed = speed;
		this.scale = scale;
		this.width *= scale;
		this.height *= scale;
		exploding = false;
		this.planetShard = planetShard;
		if (planetShard) {
			hp = 5;
			score = 20;
			if ((int) speed % 2 == 0) {
				reverseX = true;
			}
		} else {
			hp = 20;
			score = 100;
		}
	}

	public void tick() {
		if (exploding) {
			explosion.tick();
			if (!explosion.isPlaying()) {
				setForRemoval();
			}
		} else {
			if (!planetShard) {
				Move(0, speed);
			} else {
				if (reverseX) {
					Move(-speed / 2, speed);
				} else {
					Move(speed / 2, speed);
				}
			}
			if (hp <= 0 && !explosion.isPlaying()) {
				explode();
			}
		}
	}

	public void render(Frame frame) {
		if (!exploding) {
			frame.renderToFrame(sprite, (int) x, (int) y, scale);
		} else {
			explosion.render(frame, (int) x, (int) y, scale);
		}
	}

	public void hit(int damage) {
		hp -= damage;
	}

	private void explode() {
		exploding = true;
		explosion.play();
	}
	
	public float getScale()	{
		return scale;
	}
}
