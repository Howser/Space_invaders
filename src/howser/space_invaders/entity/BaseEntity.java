package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;

public abstract class BaseEntity {
	public int x, y;
	public int width, height;
	
	public abstract void tick();
	public abstract void render(Frame frame);
	
}