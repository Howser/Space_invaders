package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.Sprite;

public abstract class BaseEntity {
	public float x, y;
	public int width, height;
	
	protected Sprite sprite;
	private boolean remove;
	
	public BaseEntity(float x, float y, int width, int height, Sprite sprite){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
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