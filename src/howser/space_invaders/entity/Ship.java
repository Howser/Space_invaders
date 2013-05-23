package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteAnimation;

public abstract class Ship extends BaseEntity {
	
	protected SpriteAnimation explosion;
	protected boolean dead;

	protected Ship(Sprite sprite, float x, float y, SpriteAnimation explosion) {
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.explosion = explosion;
	}

	public void Move(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public boolean collides(float ox, float oy, int oWidth, int oHeight) {
		return (((ox > x || ox + oWidth > x) && (ox < x + width || ox + oWidth < x
				+ width)) && ((oy > y || oy + oHeight > y) && (oy < y + height || oy
				+ oHeight < y + height)));
	}
}
