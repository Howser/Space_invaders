package howser.space_invaders.entity;

import howser.space_invaders.gfx.Sprite;
import howser.space_invaders.gfx.SpriteSheet;

public abstract class Ship extends BaseEntity {

	protected Ship(SpriteSheet sheet, int sheetX, int sheetY, int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.sprite = Sprite.getSpriteFromSheet(sheet, sheetX, sheetY, width, height);
	}

	public void Move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public boolean collides(int ox, int oy, int oWidth, int oHeight) {
		return (((ox > x || ox + oWidth > x) && (ox < x + width || ox + oWidth < x
				+ width)) && ((oy > y || oy + oHeight > y) && (oy < y + height || oy
				+ oHeight < y + height)));
	}
}
