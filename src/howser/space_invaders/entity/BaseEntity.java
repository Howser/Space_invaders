package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;

public abstract class BaseEntity {
	public int x, y;
	public int width, height;
	
	protected Sprite sprite;
	private boolean remove;
	
	public abstract void tick();
	public abstract void render(Frame frame);
	
	public BaseEntity(){
		remove = false;
	}
	
	public void setForRemoval(){
		remove = true;
	}
	
	public boolean isToBeRemoved(){
		return remove;
	}
}