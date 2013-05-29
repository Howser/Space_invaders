package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

import java.util.ArrayList;

public class ShootingEnemyShip extends EnemyShip {

	private Weapon weapon;
	private ArrayList<ShotEntity> enemyShots;

	public ShootingEnemyShip(Sprite sprite, float x, float y, float xSpeed,
			float ySpeed, int colour, int score, int screenWidth,
			int screenHeight, SpriteAnimation explosion, Weapon weapon,
			ArrayList<ShotEntity> enemyShots) {
		super(sprite, x, y, xSpeed, ySpeed, colour, score, screenWidth,
				screenHeight, explosion);
		this.weapon = weapon;
		this.enemyShots = enemyShots;
	}

	public void tick() {
		if (!dead) {
			weapon.tick();
			if (weapon.canFire()) {
				for (ShotEntity e : weapon.fire(x + 6, y + 16)) {
					enemyShots.add(e);
				}
			}
		}
		super.tick();
	}
}
