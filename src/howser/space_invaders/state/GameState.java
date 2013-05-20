package howser.space_invaders.state;

import howser.space_invaders.InputHandler;
import howser.space_invaders.entity.EntityManager;
import howser.space_invaders.entity.PlayerShip;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.SpriteSheet;

public class GameState extends BaseState{

	private EntityManager entityManager;
	private SpriteSheet sprites;
	private InputHandler input;
	
	public GameState(String name, StateManager stateManager, InputHandler input) {
		super(name, stateManager);
		entityManager = new EntityManager();
		sprites = new SpriteSheet("/sprite_sheet.png");
		this.input = input;
	}

	public void tick() {
		entityManager.tick();
	}

	public void render(Frame frame) {
		frame.clear(Colour.BLACK);
		entityManager.render(frame);
	}

	public void onEnter() {
		entityManager.addEntity(new PlayerShip(sprites, 0,0, 16,16, 256/2, 150, 3, input));
	}

	public void onExit() {
		input.clearKeyListens();
	}

	public void reset() {
		
	}
}
