package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

public class ShootingEnemyShip extends EnemyShip {
	
	private Weapon weapon;

	public ShootingEnemyShip(Sprite sprite, float x, float y, float xSpeed,
			float ySpeed, int colour, int score, int screenWidth,
			int screenHeight, SpriteAnimation explosion, Weapon weapon) {
		super(sprite, x, y, xSpeed, ySpeed, colour, score, screenWidth, screenHeight,
				explosion);
		this.weapon = weapon;
	}
	
	public void tick(){
		weapon.tick();
		weapon.fire(x+6, y);
		super.tick();
	}
}
