package howser.space_invaders.entity;

import howser.space_invaders.gfx.Frame;

import java.util.ArrayList;

public class EntityManager {
	ArrayList<BaseEntity> entities;
	
	public EntityManager(){
		entities = new ArrayList<BaseEntity>();
	}
	
	public void tick(){
		for (BaseEntity e : entities){
			if (e.isToBeRemoved()){
				removeEntity(e);
				continue;
			}
			e.tick();
		}
	}
	
	public void render(Frame frame){
		for (BaseEntity e : entities){
			e.render(frame);
		}
	}
	
	public void addEntity(BaseEntity e){
		entities.add(e);
	}
	
	public void removeEntity(BaseEntity e){
		entities.remove(e);
	}
}
