package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;

public class ShotEntity extends BaseEntity{

	private float xSpeed;
	private float ySpeed;
	
	public ShotEntity(float x, float y, Sprite sprite, float xSpeed, float ySpeed){
		super(x, y, sprite.getWidth(), sprite.getHeight(), sprite);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void tick() {
		x += xSpeed;
		y += ySpeed;
	}

	public void render(Frame frame) {
		frame.renderToFrame(sprite, (int)x, (int)y, 1);
	}
}
