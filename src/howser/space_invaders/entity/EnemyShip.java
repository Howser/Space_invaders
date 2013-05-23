package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;

import java.util.Random;

public class EnemyShip extends Ship{
	
	private Random rand;
	private boolean shootingEnabled;
	private int score;
	private float xSpeed, ySpeed;
	private int screenWidth, screenHeight;
	
	public EnemyShip(Sprite sprite, int x, int y, float xSpeed, float ySpeed, int colour, boolean shootingEnabled, int score, int screenWidth, int screenHeight) {
		super(sprite, x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
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
		if (y > screenHeight){
			this.setForRemoval();
		}
		if (x < 0 || x+ width > screenWidth){
			xSpeed *= -1;
		}
	}

	public void render(Frame frame) {
		frame.renderToFrame(sprite, (int)x, (int)y, 1);
	}
	
	public void die(){
		setForRemoval();
	}
}
