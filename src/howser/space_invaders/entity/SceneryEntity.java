package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;

public class SceneryEntity extends BaseEntity {

	public float renderDepth;
	private float speed;
	private float scale;
	public SceneryEntity(Sprite sprite, int x, float renderDepth, float scale){
		this.x = x;
		this.sprite = sprite;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.renderDepth = renderDepth;
		y = -16*scale;
		this.speed = renderDepth*2;
		this.scale = scale;
	}
	
	public void tick() {
		y += speed;
	}

	public void render(Frame frame) {
		frame.renderToFrame(sprite, (int)x, (int)y, scale);
	}
}
