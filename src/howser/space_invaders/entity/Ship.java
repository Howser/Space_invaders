package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteSheet;

public abstract class Ship extends BaseEntity {

	protected Ship(Sprite sprite, float x, float y) {
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void Move(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public boolean collides(int ox, int oy, int oWidth, int oHeight) {
		return (((ox > x || ox + oWidth > x) && (ox < x + width || ox + oWidth < x
				+ width)) && ((oy > y || oy + oHeight > y) && (oy < y + height || oy
				+ oHeight < y + height)));
	}
}
