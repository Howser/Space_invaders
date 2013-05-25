package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;

import java.io.Serializable;

public class Weapon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int projectileAmount;
	private float projectileSpeed;
	private float projectileSpread;
	private int cooldown;
	private int cooldownTimer;
	private int damage;
	private Sprite shotSprite;
	private boolean canFire;
	private int dir;
	
	public Weapon(int amount, float speed, float spread, int cooldown, int damage, Sprite shotSprite, int dir){
		this.projectileAmount = amount;
		this.projectileSpeed = speed;
		this.projectileSpread = spread;
		this.cooldown = cooldown;
		this.damage = damage;
		this.shotSprite = shotSprite;
		cooldownTimer = 0;
		this.dir = dir;
	}
	
	public void tick(){
		if (!canFire){
			cooldownTimer++;
			if (cooldownTimer > cooldown){
				canFire = true;
				cooldownTimer = 0;
			}
		}
	}
	
	public ShotEntity[] fire(float x, float y){
		if (canFire){
			ShotEntity[] shots = new ShotEntity[projectileAmount];
			for(int i = 0; i < shots.length; i++){
				//CALCULATINS AND STUFF HERE
				double angle = (projectileSpread/shots.length*(i+0.5)) + dir*180 - (projectileSpread/2);
				float xSpeed = projectileSpeed * (float)Math.sin(Math.toRadians(angle));
				float ySpeed = projectileSpeed * (float)Math.cos(Math.toRadians(angle));
				shots[i] = new ShotEntity(x, y, shotSprite, xSpeed, ySpeed, damage);
			}
			canFire = false;
			cooldownTimer = 0;
			return shots;
		}
		return null;
	}
}
